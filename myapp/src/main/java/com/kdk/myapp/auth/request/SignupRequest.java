package com.kdk.myapp.auth.request;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupRequest {
    private String username;
    private String password;
    private String nickname;
    private String email;
}
