package com.fullslab.suppliers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullslab.suppliers.dto.CreateSupplierDto;
import com.fullslab.suppliers.dto.SupplierResponseDto;
import com.fullslab.suppliers.dto.UpdateSupplierDto;
import com.fullslab.suppliers.service.SupplierService;
import com.fullslab.utils.ApiResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/suppliers")
@CrossOrigin(origins = "*")
public class SupplierController {
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<ApiResponse<SupplierResponseDto>> createSupplier(@RequestBody CreateSupplierDto supplierDto) {
        SupplierResponseDto createdSupplier = supplierService.createSupplier(supplierDto);

        return ResponseEntity.ok(new ApiResponse<>("Proveedor creado exitosamente", true, createdSupplier));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SupplierResponseDto>> getSupplier(@PathVariable Long id) {
        SupplierResponseDto supplier = supplierService.getSupplierById(id);
        return ResponseEntity.ok(new ApiResponse<>("Proveedor encontrado exitosamente", true, supplier));
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SupplierResponseDto>> updateSupplier(@PathVariable Long id, @RequestBody UpdateSupplierDto supplierDto) { 
        SupplierResponseDto updatedSupplier = supplierService.updateSupplier(id, supplierDto);

        return ResponseEntity.ok(new ApiResponse<>("Proveedor actualizado exitosamente", true, updatedSupplier));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<SupplierResponseDto>> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok(new ApiResponse<>("Proveedor eliminado exitosamente", true, null));
    }
}
