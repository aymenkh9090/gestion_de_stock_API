package com.gestiondestock.controller;

import com.gestiondestock.controller.api.CategoryApi;
import com.gestiondestock.dto.CategoryDto;
import com.gestiondestock.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class CategoryController implements CategoryApi {
     private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ResponseEntity<CategoryDto> save(CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.save(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @Override
    public ResponseEntity<CategoryDto> findById(Integer idCategory) {
        return ResponseEntity.ok(categoryService.findById(idCategory));
    }

    @Override
    public ResponseEntity<CategoryDto> findByCode(String codeCategory) {
        return ResponseEntity.ok(categoryService.findByCode(codeCategory));
    }

    @Override
    public ResponseEntity<List<CategoryDto>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());

    }

    @Override
    public ResponseEntity<Void> deleteById(Integer idCategory) {
        categoryService.deleteById(idCategory);
        return ResponseEntity.noContent().build();
    }
}
