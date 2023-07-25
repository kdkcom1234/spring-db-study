package com.tje.controller.contact;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Contact {
    private int id;
    private String name;
    private String phone;
    private String email;
}
