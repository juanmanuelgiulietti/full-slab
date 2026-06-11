package com.fullslab.suppliers.dto;

import com.fullslab.suppliers.enums.IvaCategory;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateSupplierDto {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El CUIT es obligatorio")
    @Size(min = 11, max = 11, message = "El CUIT debe tener exactamente 11 dígitos")
    private String cuit;

    @NotNull(message = "La categoría de IVA es obligatoria")
    private IvaCategory ivaCategory;

    @Email(message = "El formato de email no es válido")
    private String email;

    private String phoneNumber;

    private String website;
}