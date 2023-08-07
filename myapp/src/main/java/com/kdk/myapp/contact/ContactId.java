package com.kdk.myapp.contact;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// contact 테이블의 복합키(composite key) 클래스
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactId implements Serializable {
    private long ownerId;
    private String email;

    // 전체 필드 매개변수 생성자
    // equals, hashCode()
}
