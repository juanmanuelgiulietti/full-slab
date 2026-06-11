package com.fullslab.brands.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BrandResponseDto {

    private Long id;
    private String name;
    private String logoUrl;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}