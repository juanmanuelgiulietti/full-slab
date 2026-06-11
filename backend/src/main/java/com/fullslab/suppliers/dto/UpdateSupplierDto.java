package com.fullslab.suppliers.dto;

import com.fullslab.suppliers.enums.IvaCategory;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
public class UpdateSupplierDto {
    private String name;

    private String cuit;

    @NotNull(message = "La categoría de IVA no puede ser nula")
    private IvaCategory ivaCategory;

    @Email(message = "El correo electrónico debe ser válido")
    private String email;

    private String phoneNumber;
    private String website;
}
