package com.fullslab.products.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
public class UpdateProductDto {
    private String name;
    private BigDecimal price;
    private Long categoryId;
    private Long brandId;
}
