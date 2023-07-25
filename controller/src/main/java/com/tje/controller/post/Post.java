package com.tje.controller.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post {
    private long no;
    private String title;
    private String content;
    private String creatorName;
    private long createdTime;
}
