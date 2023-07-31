package com.kdk.myapp.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    // 동시처리에 대한 지원을 해주는 자료구조
    // 여러명의 유저들이 같은 데이터를 접근할 수 있음.
    // 데이터베이스는 기본적으로 동시성에 대한 구현이 있음.
    Map<String, Contact> map = new ConcurrentHashMap<>();

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

        List<Contact> list =
                repo.findAll(Sort.by("name").ascending());
        return list;
    }

    // HTTP 1.1 POST /contacts
    @PostMapping
    public ResponseEntity<Map<String, Object>> addContact(@RequestBody Contact contact) {
        // 클라이언트에서 넘어온 JSON이 객체로 잘 변환됐는지 확인
        System.out.println(contact.getName());
        System.out.println(contact.getPhone());
        System.out.println(contact.getEmail());

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

        if(contact.getEmail() != null && map.get(contact.getEmail()) != null) {
            // 맵에 해당 이메일이 있음
            // 이미 있는 데이터를 클라이언트(브라우저) 보냈거나
            // 클라이언트에서 중복 데이터를 보냈거나..
            Map<String, Object> res = new HashMap<>();
            res.put("data", null);
            res.put("message", "동일한 정보가 이미 존재합니다.");

            return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
        }

        // 맵에 객체 추가
        map.put(contact.getEmail(), contact);

        // 응답 객체 생성
        // 실제로 생성된 객체를 응답
        Map<String, Object> res = new HashMap<>();
        res.put("data", map.get(contact.getEmail()));
        res.put("message", "created");

        // HTTP Status Code: 201 Created
        // 리소스가 정상적으로 생성되었음.
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

//    DELETE /contacts/{email}
//           : Path(경로)Variable(변수)
//    DELTE /contacts/kdkcom@naver.com
    @DeleteMapping(value = "/{email}")
    // @PathVariable("email") : 경로 문자열{email}과 변수명 String email이 동일하면 안 써도 된다.
    public ResponseEntity removeContact(@PathVariable String email) {
        System.out.println(email);

        // 해당 키(key)의 데이터가 없으면
        if(map.get(email) == null) {
            // 404: NOT FOUND, 해당 경로에 리소스가 없다.
            // DELETE /contacts/kdkcom@naver.com
            // Response Status Code : 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 객체(리소스) 삭제
        map.remove(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}