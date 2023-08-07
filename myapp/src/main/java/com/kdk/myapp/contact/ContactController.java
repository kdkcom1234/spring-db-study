package com.kdk.myapp.contact;

import com.kdk.myapp.auth.Auth;
import com.kdk.myapp.auth.AuthProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@RestController
@RequestMapping(value = "/contacts")
public class ContactController {
    // 싱글턴: 첫 실행시점 객체가 1번 생성됨. 이후 부터는 생성된 객체를 재사용
    // static: JVM 실행 시점에 객체를 생성

    // 의존성 주입(Dependency Injection)
    // 객체를 사용하는 곳 외부에서 객체를 생성한 후에
    // 객체를 사용하는 곳에 필드, 메서드 매개변수로 넣어줌

    // 스프링프레임워크에서는
    // 1. @Configuration 클래스의 @Bean 클래스를 생성
    //    -> @Bean 클래스: 싱글턴 객체를 생성하는 메서드의 반환 클래스
    // 2. @Autowired 어노테이션 변수에 @Bean 객체를 의존성 주입

    // 스프링 프레임워크의 DATA JPA 라이브러리
    // @Repository 인터페이스에 맞는 동적 클래스를 실행 시점에 생성하여
    // --- Hibernate 프레임워크로 구성된 클래스 (insert, update...) 생성
    // @Autowired 키워드가 있는 선언 변수에 주입한다.

/*
----- 객체 생성을 정의하는 곳
@Configuration
public class ResourceConfig {

    @Bean
    // 메서드의 리턴타입의 클래스 의존성 주입을 할 수 있게됨
    public Repository getRepo() {
        Config config = new Config();
        return new Repository(config);
    }
}
------ 객체를 사용하는 곳(생성구문 사용X)
public class A {
  @Autowired
  Repository repo;
  public void findAll() {
    repo.findAll();
  }
}

public class B {
  @Autowired
  Repository repo;
}
 */

    @Autowired
    ContactRepository repo;

//    @Autowired
//    public ContactController(ContactRepository r) {
//        this.repo = r;
//    }

    // GET /contacts
    @GetMapping
    public List<Contact> getContactList() {
        // repo.findAll(); 전체 테이블 목록 조회
        // SELECT * FROM 테이블
        // repo.findAll(Sort sort); 정렬하여 전체 테이블 목록 조회
        // SELECT * FROM 테이블 ORDER BY 정렬컬럼, 정렬컬럼....

        // JPA Respository Sort 인터페이스를 사용
//        List<Contact> list =
//                repo.findAll(Sort.by("name").ascending());

        // Native-Query를 이용한 방법
//        List<Contact> list = repo.findContactsSortByName();
        
        // repository query creation을 이용한 방법
        List<Contact> list = repo.findAllByOrderByName();

        return list;
    }

    // GET /contacts/paging?page=0&size=10
    // query-string으로 받을 것임
    // ?키=값&키=값....
    // @RequestParam
    // quer-string 값을 매개변수 받는 어노테이션
    @Auth
    @GetMapping(value = "/paging")
    public Page<Contact> getContactsPaging
        (@RequestParam int page, @RequestParam int size,
         @RequestAttribute AuthProfile authProfile) {
        System.out.println(page);
        System.out.println(size);
        System.out.println(authProfile);

        // 기본적으로 key 정렬(default)
        // 정렬 설정 없이 간다.
        // SQL: ORDER BY email DESC
        // 정렬 매개변수 객체
        Sort sort = Sort.by("email").descending();
        // 페이지 매개변수 객체
        // SQL: OFFSET page * size LIMIT size
        // OFFSET: 어떤 기준점에 대한 거리
        // OFFSET 10: 10번째까지 이후
        // LIMT 10: 10건의 레코드
        // LIMIT 10 OFFSET 10: 앞으로 10건을 건너뛰고 다음 10건을 조회
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        // 해당 사용자가 소유자인 연락처 목록만 조회
        return repo.findByOwnerId(authProfile.getId(), pageRequest);
    }

    // GET /contacts/paging/searchByName?page=0&size=10&name=hong
    @GetMapping(value = "/paging/searchByName")
    public Page<Contact> getContactsPagingSearchName
            (@RequestParam int page,
             @RequestParam int size,
             @RequestParam String name) {
        System.out.println(page);
        System.out.println(size);
        System.out.println(name);

        // 기본적으로 key 정렬(default)
        Sort sort = Sort.by("email").descending();

        // 페이지 매개변수 객체
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        return repo.findByNameContaining(name, pageRequest);
    }

    @GetMapping(value = "/paging/search")
    public Page<Contact> getContactsPagingSearch
            (@RequestParam int page,
             @RequestParam int size,
             @RequestParam String query) {
        System.out.println(page);
        System.out.println(size);
        System.out.println(query);

        // 기본적으로 key 정렬(default)
        Sort sort = Sort.by("email").descending();

        // 페이지 매개변수 객체
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        return repo.findByNameContainsOrPhoneContains
                        (query, query, pageRequest);
    }

    // HTTP 1.1 POST /contacts
    @Auth
    @PostMapping
    public ResponseEntity<Map<String, Object>> addContact
                            (@RequestBody Contact contact,
                             @RequestAttribute AuthProfile authProfile) {
        // 클라이언트에서 넘어온 JSON이 객체로 잘 변환됐는지 확인
//        System.out.println(contact.getName());
//        System.out.println(contact.getPhone());
//        System.out.println(contact.getEmail());
        System.out.println(authProfile);

        // 1. ------------ 데이터 검증 단계
        // 이메일 필수값 검증
        // 400: bad request
        if(contact.getEmail() == null || contact.getEmail().isEmpty()) {
            // 응답 객체 생성
            Map<String, Object> res = new HashMap<>();
            res.put("data", null);
            res.put("message", "[email] field is required");

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(res);
        }

        // 이메일 형식 검증
        // 400: bad request

        // 이메일 중복 검증
        // 409: conflict

//         JPA Query creation을 사용
        if(contact.getEmail()!= null && repo.findByEmail(contact.getEmail()).isPresent()) {

//         Native query를 사용
//        if(contact.getEmail()!= null && repo.findContactByEmail(contact.getEmail()).isPresent()) {

//         JPA Repository의 기본 인터페이스 메서드를 사용
//        if(contact.getEmail() != null && repo.findById(contact.getEmail()).isPresent()) {

            // 맵에 해당 이메일이 있음
            // 이미 있는 데이터를 클라이언트(브라우저) 보냈거나
            // 클라이언트에서 중복 데이터를 보냈거나..
            Map<String, Object> res = new HashMap<>();
            res.put("data", null);
            res.put("message", "동일한 정보가 이미 존재합니다.");

            return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
        }

        // 2. --------- 데이터 생성
        // 테이블에 레코드 추가
        // key값이 테이블에 이미 있으면 update
        // 없으면 insert 구문이 실행됨.

        // 생성자의 id를 설정함
        contact.setOwnerId(authProfile.getId());
        // 테이블에 저장하고 생성된 객체를 반환
        Contact savedContact = repo.save(contact);

        // 3. --------- 응답 처리
        // 응답 객체 생성(ResponseEntity)
        // 상태코드, 데이터, 메시지
        // 실제로 생성된 레코드(row)를 응답
        // 생성된 레코드가 존재하는지 여부..
        if(savedContact != null) {
            Map<String, Object> res = new HashMap<>();
            res.put("data", savedContact);
            res.put("message", "created");

            // HTTP Status Code: 201 Created
            // 리소스가 정상적으로 생성되었음.
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }

        return ResponseEntity.ok().build();
    }

//    DELETE /contacts/{email}
//           : Path(경로)Variable(변수)
//    DELETE /contacts/kdkcom@naver.com
    @DeleteMapping(value = "/{email}")
    // @PathVariable("email") : 경로 문자열{email}과 변수명 String email이 동일하면 안 써도 된다.
    public ResponseEntity removeContact(@PathVariable String email) {
        System.out.println(email);

        // 해당 키(key)의 데이터가 없으면
//        if(map.get(email) == null) {


        // PK값으로 레코드로 1건 조회해서 없으면

//         JPA Repository 기본 메서드 사용
//        if(!repo.findById(email).isPresent()){

//         Native Query를 이용하여 사용
//        if(!repo.findContactByEmail(email).isPresent()){

//          Query Creation을 이용하여 사용
        if(!repo.findByEmail(email).isPresent()){
            // 404: NOT FOUND, 해당 경로에 리소스가 없다.
            // DELETE /contacts/kdkcom@naver.com
            // Response Status Code : 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

//        // 객체(리소스-서버의램) 삭제
//        map.remove(email);
        // 레코드(리소스-데이터베이스의파일일부분) 삭제
        repo.deleteById(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // PUT(전체수정), PATCH(일부수정)
    // PUT /hong@gmail.com
    // {"name":"길동", "phone":"010.."}

    /*
    @Data
    class ContactModifyRequest {
       private String name;
       private String phone;
    }
     */
    @PutMapping(value = "/{email}")
    public ResponseEntity modifyContact
            (@PathVariable String email,
             @RequestBody ContactModifyRequest contact) {
        System.out.println(email);
        System.out.println(contact);
        
        // 1. 키값으로 조회해옴
        Optional<Contact> findedContact = repo.findById(email);
        
        // 2. 해당 레코드가 있는지 확인
        if(!findedContact.isPresent()){
            // 404: NOT FOUND, 해당 경로에 리소스가 없다.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        // 존재하는 레코드(키값, @Id값이 존재)
        Contact toModifyContact = findedContact.get();
        // 3. 조회해온 레코드에 필드값을 수정
        // 매개변수에 name값이 있으면 수정
        if(contact.getName() != null && !contact.getName().isEmpty()) {
            toModifyContact.setName(contact.getName());
        }
        // 매개변수에 phone값이 있으면 수정
        if(contact.getPhone() != null && !contact.getPhone().isEmpty()) {
            toModifyContact.setPhone(contact.getPhone());
        }
        
        // (@Id 값이 존재하므로 update를 시도)
        repo.save(toModifyContact);
        
        // 200 OK 처리
        return ResponseEntity.ok().build();
    }
}
