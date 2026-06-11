package com.fullslab.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
public class BulkResponseDto {
    private List<ProductResponseDto> successfulProducts;
    private List<String> errorMessages;

    public void setSuccessfulProducts(List<ProductResponseDto> products) {
    this.successfulProducts = products;
    }
}