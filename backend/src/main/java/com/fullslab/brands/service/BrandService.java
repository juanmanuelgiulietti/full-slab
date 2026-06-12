package com.fullslab.brands.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fullslab.brands.dto.BrandResponseDto;
import com.fullslab.brands.dto.CreateBrandDto;
import com.fullslab.brands.entity.Brand;
import com.fullslab.brands.repository.BrandRepository;
import com.fullslab.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandService {
    private BrandRepository brandRepository;

    public BrandResponseDto createBrand(CreateBrandDto brandDto) {
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

    public BrandResponseDto updateBrand(Long id, CreateBrandDto brandDto) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca no encontrada con ID: " + id));

        brand.setName(brandDto.getName());
        brand.setLogoUrl(brandDto.getLogoUrl());
        brand.setDescription(brandDto.getDescription());

        return mapToResponse(brandRepository.save(brand));
    }

    public void deleteBrand(Long id) {
        if (!brandRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar, marca no encontrada con ID: " + id);
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