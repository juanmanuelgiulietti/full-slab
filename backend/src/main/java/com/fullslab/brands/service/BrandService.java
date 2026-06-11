package com.fullslab.brands.service;

import com.fullslab.brands.dto.BrandDto;
import com.fullslab.brands.dto.BrandResponseDto;
import com.fullslab.brands.entity.Brand;
import com.fullslab.brands.repository.BrandRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public BrandResponseDto createBrand(BrandDto brandDto) {
        Brand brand = new Brand();
        brand.setName(brandDto.getName());
        brand.setLogoUrl(brandDto.getLogoUrl());
        brand.setDescription(brandDto.getDescription());
        
        Brand savedBrand = brandRepository.save(brand);
        
    return mapToResponse(savedBrand);
}

    public List<BrandResponseDto> getAllBrands() {
        return brandRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public BrandResponseDto updateBrand(Long id, BrandDto brandDto) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con ID: " + id));

        brand.setName(brandDto.getName());
        brand.setLogoUrl(brandDto.getLogoUrl());
        brand.setDescription(brandDto.getDescription());

        return mapToResponse(brandRepository.save(brand));
    }

    public void deleteBrand(Long id) {
        if (!brandRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar, marca no encontrada con ID: " + id);
        }
        brandRepository.deleteById(id);
    }

    private BrandResponseDto mapToResponse(Brand brand) {
        BrandResponseDto response = new BrandResponseDto();
        response.setId(brand.getBrand_id());
        response.setName(brand.getName());
        response.setLogoUrl(brand.getLogoUrl());
        response.setDescription(brand.getDescription());
        response.setCreatedAt(brand.getCreatedAt());
        response.setUpdatedAt(brand.getUpdatedAt());
        return response;
    }
}