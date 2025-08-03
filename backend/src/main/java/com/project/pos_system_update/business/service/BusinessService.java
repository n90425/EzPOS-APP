package com.project.pos_system_update.business.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.pos_system_update.business.entity.Business;
import com.project.pos_system_update.business.repository.BusinessRepository;
import com.project.pos_system_update.jwt.JwtUtil;

import jakarta.transaction.Transactional;
@Service
public class BusinessService {
	
	private final BusinessRepository businessRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	
	public BusinessService (BusinessRepository businessRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
		this.businessRepository = businessRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}
	
	@Transactional
	public void signup(Business business) {
		System.out.println(business);
		if (businessRepository.existsByBusinessId(business.getBusinessId())) {
			throw new IllegalArgumentException("이미 등록된 아이디입니다.");
		}
		
//		if (businessRepository.existsByBusinessNum(business.getBusinessNum())) {
//			throw new IllegalArgumentException("이미 등록된 사업자번호입니다.");
//		}
		
		//비밀번호 암호화
		business.setBusinessPwd(passwordEncoder.encode(business.getBusinessPwd()));
		businessRepository.save(business);
		
	}
	
	public Business getuserInfo(String token) {
        if (!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }

        String businessId = jwtUtil.extractUsername(token);
        Business business = businessRepository.findByBusinessId(businessId);

        if (business == null) {
            throw new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.");
        }

        return business;
	}

}
