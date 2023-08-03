package com.kdk.myapp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private LoginRepository repo;

    @Autowired
    private AuthService service;

//    @GetMapping(value = "/logins")
//    public List<Login> getLogins() {
//        return repo.findAll();
//    }

    @PostMapping(value = "/signup")
    public ResponseEntity signUp(@RequestBody SignupRequest req) {
        System.out.println(req);

        // 1. Validation
        // 입력값 검증
        // 사용자이름없거나/중복이거나, 패스워드없거나, 닉네임, 이메일 없음...

        // 2. Buisness Logic(데이터 처리)
        // profile, login 생성 트랜잭션 처리
        long profileId = service.createIdentity(req);

        // 3. Response
        // 201: created
        return ResponseEntity.status(HttpStatus.CREATED).body(profileId);
    }
}
