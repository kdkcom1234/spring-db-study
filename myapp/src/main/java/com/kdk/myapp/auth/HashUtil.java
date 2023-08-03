package com.kdk.myapp.auth;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class HashUtil {
    public String createHash(String cipherText) {
        // https://www.baeldung.com/java-password-hashing
        // https://mia-dahae.tistory.com/120

        // https://blog.kakaocdn.net/dn/IdglW/btrEoJ6HDUJ/FmJqCChB9NCXd6fapmJdAk/img.png
        // 'hashToString': Salt와 함께 해시를 생성
        return BCrypt
                .withDefaults()
                .hashToString(12, cipherText.toCharArray());
    }
}
