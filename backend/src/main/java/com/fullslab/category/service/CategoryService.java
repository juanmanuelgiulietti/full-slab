package com.fullslab.category.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullslab.category.dto.CategoryResponseDto;
import com.fullslab.category.dto.CreateCategoryDto;
import com.fullslab.category.dto.UpdateCategoryDto;
import com.fullslab.category.entity.Category;
import com.fullslab.category.repository.CategoryRepository;
import com.fullslab.exception.ResourceNotFoundException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public CategoryResponseDto saveCategory(CreateCategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setIsActive(true);
        
        Category savedCategory = repository.save(category); 
        return new CategoryResponseDto(savedCategory); 
    }

    public CategoryResponseDto updateCategory(Long id, UpdateCategoryDto dto) {
        Category category = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + id));
        
        if (dto.getName() != null) {
            category.setName(dto.getName());
        }
        if (dto.getIsActive() != null) {
            category.setIsActive(dto.getIsActive());
        }

        Category updatedCategory = repository.save(category);
        return new CategoryResponseDto(updatedCategory);
    }

    public void deleteCategory(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede borrar, la categoría con ID " + id + " no existe.");
        }
        repository.deleteById(id);
    }

    public List<CategoryResponseDto> getAllCategories() {
        return repository.findAll().stream()
                .map(CategoryResponseDto::new)
                .collect(Collectors.toList());
    }
}