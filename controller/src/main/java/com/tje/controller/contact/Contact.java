package com.tje.controller.contact;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor // 전체 필드 초기 생성자
@NoArgsConstructor  // 빈 생성자
public class Contact {
    // key
    private String email;
    private String name;
    private String phone;
}
