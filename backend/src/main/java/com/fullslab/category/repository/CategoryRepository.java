package com.fullslab.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullslab.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
