package com.project.pos_system_update.category.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.project.pos_system_update.category.entity.Category;
import com.project.pos_system_update.category.entity.CategoryId;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, CategoryId> {

	//특정 사업장 카테고리 조회
	List<Category> findAllByIdBusinessId(String bysinessId);
	
	//특정 사정장, 부모카테고리 아이디로 자식카테고리 조회
	List<Category> findByParent_Id(CategoryId parentId);
}
