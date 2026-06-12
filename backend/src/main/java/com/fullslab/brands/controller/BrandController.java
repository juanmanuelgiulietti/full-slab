package com.fullslab.brands.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullslab.brands.dto.CreateBrandDto;
import com.fullslab.brands.dto.BrandResponseDto;
import com.fullslab.brands.service.BrandService;
import com.fullslab.utils.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/brands")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BrandController {
    private BrandService brandService;

    
    @PostMapping
    public ResponseEntity<ApiResponse<BrandResponseDto>> create(@Valid @RequestBody CreateBrandDto brandDto) {
        BrandResponseDto createdBrand = brandService.createBrand(brandDto);
    
        return ResponseEntity.ok(new ApiResponse<>("Marca creada exitosamente", true, createdBrand));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<BrandResponseDto>>> getAll() {
        return ResponseEntity.ok(new ApiResponse<>("Lista de marcas", true, brandService.getAllBrands()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BrandResponseDto>> update(@PathVariable Long id, @Valid @RequestBody CreateBrandDto brandDto) {
        return ResponseEntity.ok(new ApiResponse<>("Marca actualizada exitosamente", true, brandService.updateBrand(id, brandDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.ok(new ApiResponse<>("Marca eliminada exitosamente", true, "ID: " + id));
    }
}