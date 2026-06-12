package com.fullslab.product_suppliers.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductSupplierResponseDto {
    private ProductSummaryDto product;
    private SupplierSummaryDto supplier;
    private BigDecimal priceOffered;
    private Boolean availability;
}
