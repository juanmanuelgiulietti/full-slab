package com.fullslab.product_suppliers.repository;

import com.fullslab.product_suppliers.entity.ProductSupplier;
import com.fullslab.product_suppliers.entity.ProductSupplierId;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSuppliersRepository extends JpaRepository<ProductSupplier, ProductSupplierId> {
    Optional<ProductSupplier> findByProductIdAndSupplierId(Long productId, Long supplierId);
}
