package com.fullslab.requests.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

import com.fullslab.requests_item.dto.RequestItemResponseDto;

@Data
public class RequestResponseDto {
    private Long id;
    private String status;
    private LocalDateTime createdAt;
    private List<RequestItemResponseDto> items;
}