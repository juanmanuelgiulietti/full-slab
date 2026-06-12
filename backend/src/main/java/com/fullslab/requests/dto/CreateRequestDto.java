package com.fullslab.requests.dto;

import java.util.List;

import com.fullslab.requests_item.dto.RequestItemDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateRequestDto {
    @NotNull(message = "El ID del usuario es obligatorio")
    private Long userId;

    @NotEmpty(message = "El pedido debe tener al menos un ítem")
    private List<RequestItemDto> items;
}