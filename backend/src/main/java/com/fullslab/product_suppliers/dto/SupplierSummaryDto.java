package com.fullslab.product_suppliers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierSummaryDto {
    private Long supplierId;
    private String name;
}
