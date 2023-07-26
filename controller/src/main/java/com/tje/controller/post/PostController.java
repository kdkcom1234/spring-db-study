package com.tje.controller.post;


import com.tje.controller.contact.Contact;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

// GET /posts
// 게시글 목록이 JSON으로 나오게
@RestController
@RequestMapping("/posts")
public class PostController {
    // 동시성을 위한 자료 구조
    // HashMap -> ConcurrentHashMap
    // Integer -> AtomicInteger
    Map<Integer, Post> map = new ConcurrentHashMap<>();
    AtomicInteger num = new AtomicInteger(0);

    // post 목록 화면을 제작 post.html, post.js
    // fetch api를 사용하여 /posts 주소를 호출한 후
    // 배열 목록을 div(카드)로 표시한다.

//    -----------------
//    | 게시자         |
//    | ------------- |
//    | 제목(h3)       |
//    | 본문(p)        |
//    |  .....        |
//    |  .....        |
//    | ------------- |
//    | 생성시간       |
//    -----------------

    @GetMapping
    public List<Post> getPostList() {
        // 증가시키고 값 가져오기
        int no = num.incrementAndGet();
        map.put(no, Post.builder()
                        .no(no)
                        .creatorName("홍길동")
                        .title("1Lorem, ipsum dolor.")
                        .content("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repudiandae maiores sunt ab beatae provident? Eius non accusantium vitae dolor ipsa!")
                        .createdTime(new Date().getTime())
                        .build());

        no = num.incrementAndGet();
        map.put(no, Post.builder()
                        .no(no)
                        .creatorName("홍길동")
                        .title("2Lorem, ipsum dolor.")
                        .content("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repudiandae maiores sunt ab beatae provident? Eius non accusantium vitae dolor ipsa!")
                        .createdTime(new Date().getTime())
                        .build());

        var list = new ArrayList<>(map.values());
        // 람다식(lambda expression)
        // 식이 1개인 함수식;
        // 매개변수 영역과 함수 본체를 화살표로 구분함
        // 함수 본체의 수식 값이 반환 값
        list.sort((a,b)-> (int)(b.getNo() - a.getNo()));

        return list;
    }

    // title, content 필수 속성
    // creatorName: 서버에서 가짜데이터로 넣음
//    @PostMapping
//    public ResponseEntity<Map<String, Object>> addPost(...) {
//        // 채번: 번호를 딴다(1 .. 2, 3....)
//        int no = num.incrementAndGet();
//        // 맵에 추가
//    }
}
