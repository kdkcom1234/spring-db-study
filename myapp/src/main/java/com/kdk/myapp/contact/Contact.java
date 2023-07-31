package com.kdk.myapp.contact;

import jakarta.persistence.Column;
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

// ORM(Object Relaship Mapping)
// : 소프트웨어의 객체를 데이터베이스 테이블로 맵핑하는 방법
// - 사용 이유
// : 데이터베이스의 의존도를 낮추는 방법
// : 객체지향적인 원래의 소프트웨어 개발방법을 사용하자(코드위주로)

// JPA(Java Persistent API)
// : 내부적으로 Hibernate를 이용하여 구현된 interface를 제공
// : 자바에서 ORM을 처리하는 표준방법
public class Contact {

    // @Id: 엔티티의 PK(primary key)를 지정
    // Primary Key: 유일한 속성 값이며, 모든 속성 값이 PK에 종속됨.
    // PK: 유일성+대표성이 만족이 되어야함.

    // PK 컬럼 및 제약조건 설정
    @Id
    // key
    private String email; // 계정 Id, 인터넷세계의 집주소(불변)

    // 제약조건, NOT NULL
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String phone;

    // 컬럼의 데이터 타입을 변경
    @Column(columnDefinition = "LONGTEXT")
    // 파일을 base64 data-url 문자열로 저장
    private String image;
}
