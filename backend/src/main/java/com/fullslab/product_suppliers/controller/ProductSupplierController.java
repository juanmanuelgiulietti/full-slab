package com.fullslab.product_suppliers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullslab.product_suppliers.dto.CreateProductSupplier;
import com.fullslab.product_suppliers.dto.ProductSupplierResponseDto;
import com.fullslab.product_suppliers.dto.UpdateProductSupplierDto;
import com.fullslab.product_suppliers.service.ProductSuppliersService;
import com.fullslab.utils.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products_supplier")
@CrossOrigin("*")
public class ProductSupplierController {
    private final ProductSuppliersService productSuppliersService;

    @PostMapping("/{productId}/{supplierId}")
    public ResponseEntity<ApiResponse<ProductSupplierResponseDto>> createProductSupplier(
            @RequestBody CreateProductSupplier dto, 
            @PathVariable Long productId, 
            @PathVariable Long supplierId) {
        
        return ResponseEntity.ok(new ApiResponse<>("Producto asociado correctamente", true, 
                productSuppliersService.createProductSupplier(dto, productId, supplierId)));
    }

    @GetMapping("/{productId}/{supplierId}")
    public ResponseEntity<ApiResponse<ProductSupplierResponseDto>> getProductSupplier(
        @PathVariable Long productId,
            @PathVariable Long supplierId) {
        return ResponseEntity.ok(new ApiResponse<>("Detalle encontrado", true,
                productSuppliersService.getProductSupplier(productId, supplierId)));
    }

    @PutMapping("/{productId}/{supplierId}")
    public ResponseEntity<ApiResponse<ProductSupplierResponseDto>> updateProductSupplier(
            @RequestBody UpdateProductSupplierDto dto,
            @PathVariable Long productId,
            @PathVariable Long supplierId) {
        return ResponseEntity.ok(new ApiResponse<>("Relación actualizada", true,
                productSuppliersService.updateProductSupplier(dto, productId, supplierId)));
    }

    @DeleteMapping("/{productId}/{supplierId}")
    public ResponseEntity<ApiResponse<Void>> deleteProductSupplier(
            @PathVariable Long productId,
            @PathVariable Long supplierId) {
        productSuppliersService.deleteProductSupplier(productId, supplierId);
        return ResponseEntity.ok(new ApiResponse<>("Relación eliminada", true, null));
    }
}
