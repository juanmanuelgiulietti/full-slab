package com.fullslab.brands.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullslab.brands.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    
}
