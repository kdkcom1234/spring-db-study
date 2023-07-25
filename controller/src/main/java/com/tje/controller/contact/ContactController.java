package com.tje.controller.contact;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping(value = "/contacts")
public class ContactController {
    // 동시처리에 대한 지원을 해주는 자료구조
    // 여러명의 유저들이 같은 데이터를 접근할 수 있음.
    // 데이터베이스는 기본적으로 동시성에 대한 구현이 있음.
    Map<Integer, Contact> map = new ConcurrentHashMap<>();

    @GetMapping
    public List<Contact> getContactList() {
        map.put(1, Contact.builder()
                        .id(1).name("홍길동").phone("010-1234-5678")
                        .email("hong@naver.com").build());
        map.put(2, Contact.builder()
                        .id(2).name("김철수").phone("010-9873-8382")
                        .email("kim@gmail.com").build());

        var list = new ArrayList<>(map.values());
        list.sort((a, b)-> b.getId() - a.getId());

        return list;
    }
}
