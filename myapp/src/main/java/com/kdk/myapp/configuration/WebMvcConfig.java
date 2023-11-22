package com.kdk.myapp.configuration;

import com.kdk.myapp.auth.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private AuthInterceptor authInterceptor;

    // CORS(cross orgin resource sharing)
    // 다른 origin끼리 자원을 공유할 수 있게 하는 것
    // 기본으로 웹(js)에서는 CORS가 안 됨.

    // origin의 구성요소는 프로토콜+주소(도메인,IP)+포트
    // http:// + 127.0.0.1 + :5500
    // http://localhost:8080

    // 서버쪽에서 접근 가능한 orgin 목록, 경로목록, 메서드 목록을 설정해주어야함.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**") // 모든 경로에 대해
                .allowedOrigins(
                        "http://localhost:5500",
                        "http://127.0.0.1:5500",
                        "https://d2biz7e3hafxh5.cloudfront.net",
                        "http://ec2-52-79-101-98.ap-northeast-2.compute.amazonaws.com") // 로컬 호스트 origin 허용
                .allowedMethods("*"); // 모든 메서드 허용(GET, POST.....)
    }

    // 인증처리용 인터셉터를 추가
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor);
    }
}