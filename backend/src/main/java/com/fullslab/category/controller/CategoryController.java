package com.fullslab.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullslab.category.dto.CategoryResponseDto;
import com.fullslab.category.dto.UpdateCategoryDto;
import com.fullslab.category.service.CategoryService;
import com.fullslab.utils.ApiResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponseDto>>> getAll() {
        return ResponseEntity.ok(new ApiResponse<>("Lista de categorías", true, categoryService.getAllCategories()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> updateCategory(
            @PathVariable Long id,
            @RequestBody UpdateCategoryDto dto) {

        CategoryResponseDto updated = categoryService.updateCategory(id, dto);
        return ResponseEntity.ok(new ApiResponse<>("Categoría actualizada con éxito", true, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new ApiResponse<>("Categoría eliminada con éxito", true, null));
    }

}
