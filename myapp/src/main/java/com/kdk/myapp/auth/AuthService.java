package com.kdk.myapp.auth;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private LoginRepository repo;
    private ProfileRepository profileRepo;

    @Autowired
    public AuthService(LoginRepository repo, ProfileRepository profileRepo) {
        this.repo = repo;
        this.profileRepo = profileRepo;
    }

    // profile 정보 생성과 login 정보 생성은 1개의 처리로 실행
    // 둘 중에 하나라도 FAIL(오류)가 나면 둘다 생성이 되면 안 됨.

    // insert, update, delete DML(데이터조작)
    // 데이터 조작작업에서 논리적으로 1개 묶을 수 있는 방법
    // 트랜잭션(transaction): 거래
    // Controller에서는 transaction 처리가 안 됨.

    // @Controller: 요청값 검증, 간단한 데이터조작, 적절한 응답값 반환
    // @Service: 트랜잭션처리, 외부시스템연동

    // @Transactional: 메서드레벨
    // JPA 데이터처리에 대해서 메서드 수준으로 트랜잭션을 처리해줌
    // 메서드 { insert, insert, update, delete } : 1개의 tx로 묶어줌.
    // 메서드에서 예외처리가 발생하면 rollback(원상태로 복구)을 함.
    // 메서드가 정상처리되면 commit(반영)이 실행됨. DB파일에 반영

    // 테이블락(insert), 로우락(delete, update)
    // 아이솔레이션 레벨(commit, uncommited)

    @Transactional
    public long createIdentity(SignupRequest req) {
        HashUtil util = new HashUtil();

        // 1. login 정보를 insert
        Login toSaveLogin =
            Login.builder()
                .username(req.getUsername())
                .password(util.createHash(req.getPassword()))
                .build();
        Login savedLogin = repo.save(toSaveLogin);

        // 2. profile 정보를 insert(login_id포함)하고 레코드의 id값을 가져옴;
        Profile toSaveProfile =
                Profile.builder()
                        .nickname(req.getNickname())
                        .email(req.getEmail())
                        .login(savedLogin)
                        .build();
        long profileId = profileRepo.save(toSaveProfile).getId();

        // 3. 로그인 정보에는 profile_id값만 저장
        savedLogin.setProfileId(profileId);
        repo.save(savedLogin);

        // 4. profile_id를 반환
        return profileId;
    }
}
