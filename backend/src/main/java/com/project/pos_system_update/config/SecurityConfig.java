package com.project.pos_system_update.config;

import org.springframework.security.config.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    //위에 PasswordEncoder를 만들면서 Spring Security가 몰래 켜져서 CORS를 차단함 
    @Bean 
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http
    		.cors(Customizer.withDefaults()) //WebMvcConfigurer와 연결
    		.csrf(csrf -> csrf.disable())
    		.authorizeHttpRequests(auth -> auth
    				.requestMatchers("/api/login", "/api/signup","/api/**").permitAll()
    				.anyRequest().authenticated()
    		);
    	return http.build();
    }
}