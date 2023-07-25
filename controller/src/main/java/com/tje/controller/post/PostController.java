package com.tje.controller.post;


import com.tje.controller.contact.Contact;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

// GET /posts
// 게시글 목록이 JSON으로 나오게
@RestController
@RequestMapping("/posts")
public class PostController {
    Map<Integer, Post> map = new ConcurrentHashMap<>();

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
        map.put(1, Post.builder()
                        .no(1)
                        .creatorName("홍길동")
                        .title("1Lorem, ipsum dolor.")
                        .content("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repudiandae maiores sunt ab beatae provident? Eius non accusantium vitae dolor ipsa!")
                        .createdTime(new Date().getTime())
                        .build());
        map.put(2, Post.builder()
                        .no(2)
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

}
