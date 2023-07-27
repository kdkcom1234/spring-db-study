package com.tje.controller.post;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor // 전체 필드 초기 생성자
@NoArgsConstructor  // 빈 생성자
public class Post {
    private long no;
    private String title;
    private String content;
    private String creatorName;
    private long createdTime;
}

