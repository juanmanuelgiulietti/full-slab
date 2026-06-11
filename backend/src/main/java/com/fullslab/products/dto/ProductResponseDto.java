package com.fullslab.products.dto;

import java.math.BigDecimal;

import com.fullslab.products.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
public class ProductResponseDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Integer stock;
    private Integer minStock;
    private String brandName;
    private String categoryName;

    public ProductResponseDto(Product product) {
        this.id = product.getProductId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.stock = product.getStock();
        this.minStock = product.getMinStock();
        this.brandName = (product.getBrand() != null) ? product.getBrand().getName() : null;
        this.categoryName = (product.getCategory() != null) ? product.getCategory().getName() : null;
    }
}
