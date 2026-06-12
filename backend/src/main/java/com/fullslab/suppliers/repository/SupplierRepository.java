package com.fullslab.suppliers.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullslab.suppliers.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findByCuit(String cuit);
    Optional<Supplier> findByEmail(String email);
}
