package com.fullslab.products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullslab.products.dto.CreateProductDto;
import com.fullslab.products.dto.ProductResponseDto;
import com.fullslab.products.dto.UpdateProductDto;
import com.fullslab.products.service.ProductService;
import com.fullslab.utils.ApiResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getAll() {
        return ResponseEntity.ok(new ApiResponse<>("Lista", true, productService.getAllProducts()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponseDto>> create(@RequestBody CreateProductDto dto) {
        return ResponseEntity.ok(new ApiResponse<>("Producto creado", true, productService.createProduct(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> update(@PathVariable Long id,
            @RequestBody UpdateProductDto dto) {
        return ResponseEntity
                .ok(new ApiResponse<>("Producto actualizado", true, productService.updateProduct(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(new ApiResponse<>("Producto eliminado", true, null));
    }
}
