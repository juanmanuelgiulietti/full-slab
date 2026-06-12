package com.fullslab.suppliers.service;

import org.springframework.stereotype.Service;

import com.fullslab.exception.ResourceNotFoundException;
import com.fullslab.suppliers.dto.CreateSupplierDto;
import com.fullslab.suppliers.dto.SupplierResponseDto;
import com.fullslab.suppliers.dto.UpdateSupplierDto;
import com.fullslab.suppliers.entity.Supplier;
import com.fullslab.suppliers.repository.SupplierRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierResponseDto createSupplier(CreateSupplierDto dto) {
        Supplier supplier = new Supplier();
        
        supplier.setName(dto.getName());
        supplier.setCuit(dto.getCuit());
        supplier.setIvaCategory(dto.getIvaCategory());
        supplier.setEmail(dto.getEmail());
        supplier.setPhoneNumber(dto.getPhoneNumber());
        supplier.setWebsite(dto.getWebsite());

        Supplier savedSupplier = supplierRepository.save(supplier);

        return new SupplierResponseDto(savedSupplier);
    }

    public SupplierResponseDto updateSupplier(Long id, UpdateSupplierDto dto) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con ID: " + id));

        if (dto.getName() != null) supplier.setName(dto.getName());
        if (dto.getCuit() != null) supplier.setCuit(dto.getCuit());
        if (dto.getIvaCategory() != null) supplier.setIvaCategory(dto.getIvaCategory());
        if (dto.getEmail() != null) supplier.setEmail(dto.getEmail());
        if (dto.getPhoneNumber() != null) supplier.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getWebsite() != null) supplier.setWebsite(dto.getWebsite());

        Supplier updatedSupplier = supplierRepository.save(supplier);
        return new SupplierResponseDto(updatedSupplier);
    }

    public SupplierResponseDto getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con ID: " + id));
        return new SupplierResponseDto(supplier);
    }

    public void deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con ID: " + id));
        supplierRepository.delete(supplier);
    }
}