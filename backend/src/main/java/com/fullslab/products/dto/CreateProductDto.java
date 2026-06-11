package com.fullslab.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
public class CreateProductDto {
    private String name;
    private BigDecimal price;
    private String description;
    private Integer stock;
    private Integer minStock;
    private Long categoryId;
    private Long brandId;
}