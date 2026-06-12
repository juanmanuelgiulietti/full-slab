package com.fullslab.requests_item.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestItemResponseDto {
    private Long id;
    private Long requestId;
    private Long productId;
    private Long supplierId;
    private Integer quantity;
}