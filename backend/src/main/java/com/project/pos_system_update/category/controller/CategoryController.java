package com.project.pos_system_update.category.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.pos_system_update.category.entity.Category;
import com.project.pos_system_update.category.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 등록
    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.save(category));
    }

    // 특정 businessId 전체 카테고리 조회
    @GetMapping("/{businessId}")
    public ResponseEntity<List<Category>> getAll(@PathVariable String businessId) {
        return ResponseEntity.ok(categoryService.findAllByBusinessId(businessId));
    }

    // 카테고리 상세 조회
    @GetMapping("/{businessId}/{categoryId}")
    public ResponseEntity<Category> getOne(@PathVariable String businessId,
                                           @PathVariable Integer categoryId) {
        return categoryService.findById(businessId, categoryId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 삭제
    @DeleteMapping("/{businessId}/{categoryId}")
    public ResponseEntity<Void> delete(@PathVariable String businessId,
                                       @PathVariable Integer categoryId) {
        categoryService.delete(businessId, categoryId);
        return ResponseEntity.ok().build();
    }
}