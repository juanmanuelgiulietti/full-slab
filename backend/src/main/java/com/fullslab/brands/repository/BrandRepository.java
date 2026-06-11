package com.fullslab.brands.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullslab.brands.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    
}
