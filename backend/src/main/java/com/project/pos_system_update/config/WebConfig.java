package com.project.pos_system_update.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 모든 경로에 대해
                .allowedOrigins("http://localhost:3000")  // React dev 서버
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 모든 HTTP 메서드 허용 (GET, POST 등)
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(true)
                .maxAge(3600); 
    }
}
