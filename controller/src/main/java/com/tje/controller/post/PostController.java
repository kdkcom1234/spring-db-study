package com.tje.controller.post;


import com.tje.controller.contact.Contact;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
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

    @GetMapping
    public List<Post> getPostList() {
        map.put(1, Post.builder().no(1).title("게시글1").build());
        map.put(2, Post.builder().no(2).title("게시글1").build());

        var list = new ArrayList<>(map.values());
        // 람다식(lambda expression)
        // 식이 1개인 함수식;
        // 매개변수 영역과 함수 본체를 화살표로 구분함
        // 함수 본체의 수식 값이 반환 값
        list.sort((a,b)-> (int)(b.getNo() - a.getNo()));

        return list;
    }

}
