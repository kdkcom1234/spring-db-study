package com.kdk.myapp.post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class PostModifyRequest {
    private String title;
    private String content;
}
