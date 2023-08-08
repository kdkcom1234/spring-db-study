package com.kdk.myapp.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor // 전체 필드 초기 생성자
@NoArgsConstructor  // 빈 생성자
@Entity
public class Post {
    @Id
    // database의 auto_increment를 사용함
    // auto_increment: 레코드가 추가될때 자동으로 증가되는 값을 사용
    // 1, 2, 3 ....
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    // 게시글을 작성 사용자의 nickname
    private String creatorName;
    // 게시글을 작성한 사용자의 Id
    private long creatorId;

    // created_time bigint not null,
    // primitive type: int, char, long, double
    // nullable 불가, long 기본값이 0
    // 데이터베이스에 NOT NULL로 세팅해줌
//    private long createdTime;
    // 데이터베이스에 NULL을 넣고 싶으면 Class 타입을 써야함
    private Long createdTime;
    
    // 댓글 수
    private long commentCnt;
    // 최근 댓글 내용
    private String latestComment;
}

