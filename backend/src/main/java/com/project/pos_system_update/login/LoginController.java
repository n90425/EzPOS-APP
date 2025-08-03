package com.project.pos_system_update.login;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.pos_system_update.business.entity.Business;
import com.project.pos_system_update.business.repository.BusinessRepository;
import com.project.pos_system_update.jwt.JwtUtil;

@RestController
@RequestMapping("/api")
public class LoginController {
	
	private final JwtUtil jwtUtil;
	private final BusinessRepository businessRepository;
	private final PasswordEncoder passwordEncoder;
	
	public LoginController(JwtUtil jwtUtil, BusinessRepository businessRepository, PasswordEncoder passwordEncoder) {
		this.jwtUtil = jwtUtil;
		this.businessRepository = businessRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> user) {
		
		System.out.println("user=======" + user);
		
		String businessId = user.get("businessId");
		String businessPwd = user.get("businessPwd");
		
		//사용자 조회
		Business business = businessRepository.findByBusinessId(businessId);
		if (business == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("존재하지 않는 아이디입니다.");
		}
		
		//비밀번호 일치 확인
		if (!passwordEncoder.matches(businessPwd, business.getBusinessPwd())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 일치하지 않습니다.");
        }
		
		//JWT 토큰생성
		String token = jwtUtil.generateToken(businessId);
        return ResponseEntity.ok(Map.of("token", token));
	}
	
	
//	@GetMapping("/me")
//    public ResponseEntity<?> me(@RequestHeader("Authorization") String authHeader) {
//        String token = authHeader.replace("Bearer ", "");
//        if (jwtUtil.validateToken(token)) {
//            String user = jwtUtil.extractUsername(token);
//            return ResponseEntity.ok("Hello, " + user);
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
//    }
	

}
