package com.project.pos_system_update.category.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.pos_system_update.category.entity.Category;
import com.project.pos_system_update.category.entity.CategoryId;
import com.project.pos_system_update.category.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	private final CategoryRepository categoryRepository;
	
	//카테고리 등록
	public Category save(Category category) {
		return categoryRepository.save(category);
	}
	//특정 사업장 전체 카테고리 조회
	public List<Category> findAllByBusinessId(String businessId) {
        return categoryRepository.findAllByIdBusinessId(businessId);
    }

    // 카테고리 상세 조회
    public Optional<Category> findById(String businessId, Integer categoryId) {
        return categoryRepository.findById(new CategoryId(businessId, categoryId));
    }

    // 삭제
    public void delete(String businessId, Integer categoryId) {
        categoryRepository.deleteById(new CategoryId(businessId, categoryId));
    }
}
