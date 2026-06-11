package com.fullslab.products.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullslab.brands.repository.BrandRepository;
import com.fullslab.category.entity.Category;
import com.fullslab.category.repository.CategoryRepository;
import com.fullslab.products.dto.BulkResponseDto;
import com.fullslab.products.dto.CreateProductDto;
import com.fullslab.products.dto.ProductResponseDto;
import com.fullslab.products.dto.UpdateProductDto;
import com.fullslab.products.entity.Product;
import com.fullslab.products.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream().map(product -> {
            ProductResponseDto dto = new ProductResponseDto();
            if (product.getBrand() != null) {
                dto.setBrandName(product.getBrand().getName());
            }
            if (product.getCategory() != null) {
                dto.setCategoryName(product.getCategory().getName());
            }
            dto.setName(product.getName());
            dto.setPrice(product.getPrice());
            dto.setDescription(product.getDescription());
            dto.setStock(product.getStock());

            return dto;
        }).collect(Collectors.toList());
    }

    public ProductResponseDto saveProduct(CreateProductDto productDto) {
        Product product = new Product();

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setStock(productDto.getStock());
        product.setMinStock(productDto.getMinStock());

        if (productDto.getBrandId() != null) {
            brandRepository.findById(productDto.getBrandId()).ifPresent(product::setBrand);
        }

        if (productDto.getCategoryId() != null) {
            categoryRepository.findById(productDto.getCategoryId()).ifPresent(product::setCategory);
        }

        Product savedProduct = productRepository.save(product);
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setName(savedProduct.getName());
        responseDto.setPrice(savedProduct.getPrice());
        responseDto.setDescription(savedProduct.getDescription());
        responseDto.setStock(savedProduct.getStock());
        responseDto.setMinStock(savedProduct.getMinStock());

        if (savedProduct.getBrand() != null) {
            responseDto.setBrandName(savedProduct.getBrand().getName());
        }

        if (savedProduct.getCategory() != null) {
            responseDto.setCategoryName(savedProduct.getCategory().getName());
        }

        return responseDto;
    }

    public ProductResponseDto updateProduct(Long id, UpdateProductDto dto) {
        Product product = productRepository.findById(id).orElseThrow();

        if (dto.getName() != null)
            product.setName(dto.getName());
        if (dto.getPrice() != null)
            product.setPrice(dto.getPrice());

        if (dto.getCategoryId() != null) {
            Category newCat = categoryRepository.findById(dto.getCategoryId()).orElseThrow();
            product.setCategory(newCat);
        }

        return new ProductResponseDto(productRepository.save(product));
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        product.setDeletedAt(LocalDateTime.now());

        productRepository.save(product);
    }

    public BulkResponseDto saveAll(List<CreateProductDto> productsDto) {
        BulkResponseDto response = new BulkResponseDto();
        List<ProductResponseDto> successful = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        for (CreateProductDto dto : productsDto) {
            try {
                successful.add(saveProduct(dto));
            } catch (Exception e) {
                errors.add("Error en producto " + dto.getName() + ": " + e.getMessage());
            }
        }

        response.setSuccessfulProducts(successful);
        response.setErrorMessages(errors);
        return response;
    }
}