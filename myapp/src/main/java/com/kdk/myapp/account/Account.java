package com.kdk.myapp.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data // getter, setter 자동으로 만들어줌, 컴파일 시점에
@Builder
@AllArgsConstructor
public class Account {
    private String ano;
    private String ownerName;
    private long balance;
    private long createTime;
}
