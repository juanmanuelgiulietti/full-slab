package com.fullslab.brands.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BrandDto {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String name;

    @Size(max = 500, message = "La URL del logo es demasiado larga")
    private String logoUrl;

    @Size(max = 1000, message = "La descripción es demasiado larga")
    private String description;
}