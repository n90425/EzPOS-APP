package com.project.pos_system_update.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.pos_system_update.business.entity.Business;

public interface BusinessRepository extends JpaRepository<Business, String> {
	Business findByBusinessId(String businessId);
	boolean existsByBusinessId(String businessId);
	
	Business findByBusinessNum(String businessNum);
//	boolean existsByBusinessNum(String businessNum);
}
