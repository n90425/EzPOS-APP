package com.project.pos_system_update.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
	@Value("${jwt.secret}")
	private String secret;
	
	private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);	//랜덤키 생성
	private final long EXPIRATION = 1000 * 60 * 60; //1시간
	
	//토큰 생성
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(SignatureAlgorithm.HS256, secret.getBytes(StandardCharsets.UTF_8))
	            .compact();
	}
	
	//토큰 유효성 검사
	public boolean validateToken(String token) {
		try {
			Jwts.parser()
		      .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
	            .parseClaimsJws(token);
	        return true;
	    } catch (JwtException e) {
	        return false;
	    }
	}
	
	// 토큰에서 사용자명 추출
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
	
	
//	public String generateToken(String username) {
//		return Jwts.builder()
//				.setSubject(username)
//				.setIssuedAt(new Date())
//				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
//				.signWith(key)
//				.compact();
//	}
//	
//	public boolean validateToken(String token) {
//		try {
//			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//			return true;
//		} catch (JwtException e) {
//			return false;
//		}
//	}
//	
//	public String extractUsername(String token) {
//		return Jwts.parserBuilder()
//				.setSigningKey(key).build()
//				.parseClaimsJws(token)
//				.getBody()
//				.getSubject();
//	}

}
