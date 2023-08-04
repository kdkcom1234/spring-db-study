package com.kdk.myapp.auth.configuration;

import com.kdk.myapp.auth.util.HashUtil;
import com.kdk.myapp.auth.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration: 의존성 주입이 가능한 싱글턴 객체 선언 메서드를 작성할 수 있음.
// @SpringBootApplication은 @Configuration이 있는 클래스를 찾아서
// @Bean이 있는 메서드의 리턴타입들은 의존성 주입을 할 수 있도록 설정
@Configuration
public class AuthConfiguration {

    // @Bean: 의존성 주입을 할 수 있는 싱글턴 객체 선언
    // 객체 생성 패턴은 메서드 내부에 있음.
    // 싱글턴 객체명은 메서드명으로 등록됨
    @Bean
    public HashUtil hashUtil() {
        return new HashUtil();
    }

    @Bean
    public JwtUtil jwtUtil() { return new JwtUtil(); }
}
