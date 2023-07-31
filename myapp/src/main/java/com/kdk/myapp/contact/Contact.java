package com.kdk.myapp.contact;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor // 전체 필드 초기 생성자
@NoArgsConstructor  // 빈 생성자
@Entity
// @Entity는 기본적으로 클래스명(파스칼케이스) -> 테이블명(스네이크케이스) 맵핑
// class: ContactActivity -> table: contact_activity
public class Contact {

    @Id
    // key
    private String email;

    private String name;
    private String phone;

    // 파일을 base64 data-url 문자열로 저장
    private String image;
}
