package com.kdk.myapp.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class JwtUtil {

    // 임의의 서명 값 - 키파일 등을 읽어서 처리가능
    public String secret = "your-secret";
    // ms 단위
    public final long TOKEN_TIMEOUT = 1000 * 60 * 60 * 24 * 7;

    // JWT 토큰 생성
    public String createToken(long id, String username, String nickname) {
        // 토큰 생성시간과 만료시간을 만듦
        Date now = new Date();
        // 만료시간: 2차인증 이런게 잘걸려있으면 큰문제는 안됨. 내컴퓨터를 다른 사람이 쓴다.
        // 길게: 7일~30일
        // 보통: 1시간~3시간
        // 짧게: 5분~15분
        Date exp = new Date(now.getTime() + TOKEN_TIMEOUT);

        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                // sub: 토큰 소유자
                .withSubject(String.valueOf(id))
                .withClaim("username", username)
                .withClaim("nickname", nickname)
                .withIssuedAt(now)
                .withExpiresAt(exp)
                .sign(algorithm);
    }
}
