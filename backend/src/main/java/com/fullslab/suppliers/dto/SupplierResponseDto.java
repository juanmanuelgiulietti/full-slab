package com.fullslab.suppliers.dto;

import com.fullslab.suppliers.entity.Supplier;
import com.fullslab.suppliers.enums.IvaCategory;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SupplierResponseDto {
    private Long supplierId;
    private String name;
    private String cuit;
    private IvaCategory ivaCategory;
    private String email;
    private String phoneNumber;
    private String website;

    public SupplierResponseDto(Supplier supplier) {
        this.supplierId = supplier.getSupplierId();
        this.name = supplier.getName();
        this.cuit = supplier.getCuit();
        this.ivaCategory = supplier.getIvaCategory();
        this.email = supplier.getEmail();
        this.phoneNumber = supplier.getPhoneNumber();
        this.website = supplier.getWebsite();
    }
}
