package com.tje.controller.account;

import lombok.*;

@Data // getter, setter 자동으로 만들어줌, 컴파일 시점에
@Builder
@AllArgsConstructor
public class Account {
    private String ano;
    private String ownerName;
    private long balance;
    private long createTime;
}
