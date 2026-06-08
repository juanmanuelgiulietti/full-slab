package com.fullslab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullslab.dto.BrandDto;
import com.fullslab.service.BrandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/brands")
@CrossOrigin(origins = "*")
public class BrandController {
    @Autowired
    private BrandService brandService;

   @GetMapping
    public List<BrandDto> getAllBrands() {
        return brandService.getAllBrands();
    }

    @PostMapping
    public BrandDto createBrand(@RequestBody BrandDto brandDto) {
        return brandService.saveBrand(brandDto);
    }
    
}
