package com.fullslab.service;

import com.fullslab.dto.BrandDto;
import com.fullslab.entity.Brand;
import com.fullslab.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<BrandDto> getAllBrands() {
        return brandRepository.findAll().stream().map(brand -> {
            BrandDto dto = new BrandDto();
            dto.setName(brand.getName());
            dto.setLogo_url(brand.getLogo_url());
            dto.setDescription(brand.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }

    public BrandDto saveBrand(BrandDto brandDto) {
    Brand brand = new Brand();
    brand.setName(brandDto.getName());
    brand.setLogo_url(brandDto.getLogo_url());
    brand.setDescription(brandDto.getDescription());
    
    Brand savedBrand = brandRepository.save(brand);
    
    BrandDto responseDto = new BrandDto();
    responseDto.setName(savedBrand.getName());
    responseDto.setLogo_url(savedBrand.getLogo_url());
    responseDto.setDescription(savedBrand.getDescription());
    
    return responseDto;
}
}