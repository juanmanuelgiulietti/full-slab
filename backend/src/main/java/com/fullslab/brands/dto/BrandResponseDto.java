package com.fullslab.brands.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class BrandResponseDto {

    private Long id;
    private String name;
    private String logoUrl;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}