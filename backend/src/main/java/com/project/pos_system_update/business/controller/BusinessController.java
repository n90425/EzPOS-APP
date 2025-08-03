package com.project.pos_system_update.business.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.pos_system_update.business.entity.Business;
import com.project.pos_system_update.business.service.BusinessService;

@RestController
@RequestMapping("/api")
public class BusinessController {
	
	private final BusinessService businessService;
	
	public BusinessController(BusinessService businessService) {
		this.businessService = businessService;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody Business business) {
		System.out.println(business);
		businessService.signup(business);
		return ResponseEntity.ok("회원가입 성공");
	}
	
    @GetMapping("/userinfo")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("토큰이 없습니다.");
        }

        String token = authHeader.substring(7);
        Business business = businessService.getuserInfo(token);
        return ResponseEntity.ok(business); // Business 엔티티 전체를 리턴
    }

}
