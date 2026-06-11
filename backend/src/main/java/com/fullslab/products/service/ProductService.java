package com.fullslab.products.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fullslab.brands.repository.BrandRepository;
import com.fullslab.category.entity.Category;
import com.fullslab.category.repository.CategoryRepository;
import com.fullslab.exception.ResourceNotFoundException;
import com.fullslab.products.dto.BulkResponseDto;
import com.fullslab.products.dto.CreateProductDto;
import com.fullslab.products.dto.ProductResponseDto;
import com.fullslab.products.dto.UpdateProductDto;
import com.fullslab.products.entity.Product;
import com.fullslab.products.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private BrandRepository brandRepository;
    private CategoryRepository categoryRepository;

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public ProductResponseDto createProduct(CreateProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setStock(dto.getStock());
        product.setMinStock(dto.getMinStock());
        
        if (dto.getBrandId() != null) {
            var brand = brandRepository.findById(dto.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Marca no encontrada con ID: " + dto.getBrandId()));
            product.setBrand(brand);
        }

        if (dto.getCategoryId() != null) {
            var category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + dto.getCategoryId()));
            product.setCategory(category);
        }

        return mapToResponseDto(productRepository.save(product));
    }

    public ProductResponseDto updateProduct(Long id, UpdateProductDto dto) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        if (dto.getName() != null) product.setName(dto.getName());
        if (dto.getPrice() != null) product.setPrice(dto.getPrice());

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + dto.getCategoryId()));
            product.setCategory(category);
        }

        return mapToResponseDto(productRepository.save(product));
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        product.setDeletedAt(LocalDateTime.now());
        productRepository.save(product);
    }

    public BulkResponseDto createAll(List<CreateProductDto> productsDto) {
        BulkResponseDto response = new BulkResponseDto();
        List<ProductResponseDto> successful = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        for (CreateProductDto dto : productsDto) {
            try {
                successful.add(createProduct(dto));
            } catch (Exception e) {
                errors.add("Error en producto " + dto.getName() + ": " + e.getMessage());
            }
        }

        response.setSuccessfulProducts(successful);
        response.setErrorMessages(errors);
        return response;
    }

    private ProductResponseDto mapToResponseDto(Product p) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        dto.setDescription(p.getDescription());
        dto.setStock(p.getStock());
        
        if (p.getBrand() != null) {
            dto.setBrandName(p.getBrand().getName());
        }
        if (p.getCategory() != null) {
            dto.setCategoryName(p.getCategory().getName());
        }
        
        return dto;
    }
}