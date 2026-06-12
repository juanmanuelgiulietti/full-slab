package com.fullslab.product_suppliers.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateProductSupplier {
    @DecimalMin("0.0")
    @NotNull
    private BigDecimal priceOffered;

    @NotNull
    private Boolean availability;
}
