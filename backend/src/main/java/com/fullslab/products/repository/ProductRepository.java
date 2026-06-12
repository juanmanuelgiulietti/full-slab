package com.fullslab.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullslab.products.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}