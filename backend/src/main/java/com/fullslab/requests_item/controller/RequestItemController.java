package com.fullslab.requests_item.controller;

import com.fullslab.requests_item.dto.RequestItemDto;
import com.fullslab.requests_item.dto.RequestItemResponseDto;
import com.fullslab.requests_item.service.RequestItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request-items")
@RequiredArgsConstructor
public class RequestItemController {

    private final RequestItemService itemService;

    @PostMapping
    public ResponseEntity<RequestItemResponseDto> create(@Valid @RequestBody RequestItemDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<RequestItemResponseDto>> findAll() {
        return ResponseEntity.ok(itemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestItemResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.findById(id));
    }

    @PutMapping("/{id}/quantity")
    public ResponseEntity<RequestItemResponseDto> updateQuantity(
            @PathVariable Long id, 
            @RequestParam Integer quantity) {
        return ResponseEntity.ok(itemService.updateQuantity(id, quantity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}