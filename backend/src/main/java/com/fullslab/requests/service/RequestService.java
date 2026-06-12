package com.fullslab.requests.service;

import com.fullslab.requests.dto.*;
import com.fullslab.requests.entity.*;
import com.fullslab.requests.enums.RequestStatus;
import com.fullslab.requests.repository.*;
import com.fullslab.requests_item.dto.RequestItemResponseDto;
import com.fullslab.requests_item.entity.RequestItem;
import com.fullslab.users.repository.UserRepository;
import com.fullslab.product_suppliers.repository.ProductSuppliersRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final ProductSuppliersRepository productSupplierRepository;

    @Transactional
    public RequestResponseDto createRequest(CreateRequestDto dto) {
        var user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        Request request = new Request();
        request.setUser(user);
        
        var items = dto.getItems().stream().map(itemDto -> {
            var productSupplier = productSupplierRepository
                    .findByProductIdAndSupplierId(itemDto.getProductId(), itemDto.getSupplierId())
                    .orElseThrow(() -> new EntityNotFoundException("Producto-Proveedor no encontrado"));

            RequestItem item = new RequestItem();
            item.setRequest(request);
            item.setProductSupplier(productSupplier);
            item.setQuantity(itemDto.getQuantity());
            return item;
        }).collect(Collectors.toList());

        request.setItems(items);
        return mapToResponseDto(requestRepository.save(request));
    }

    @Transactional(readOnly = true)
    public RequestResponseDto getRequestById(Long id) {
        Request request = requestRepository.findById(id)
                .filter(r -> r.getDeletedAt() == null)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));
        return mapToResponseDto(request);
    }

    @Transactional(readOnly = true)
    public List<RequestResponseDto> getAllRequests() {
        return requestRepository.findAll().stream()
                .filter(r -> r.getDeletedAt() == null)
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public RequestResponseDto updateStatus(Long id, RequestStatus status) {
        Request request = requestRepository.findById(id)
                .filter(r -> r.getDeletedAt() == null)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));
        
        request.setStatus(status);
        return mapToResponseDto(requestRepository.save(request));
    }

    @Transactional
    public void deleteRequest(Long id) {
        Request request = requestRepository.findById(id)
                .filter(r -> r.getDeletedAt() == null)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));
        
        request.setDeletedAt(LocalDateTime.now());
        requestRepository.save(request);
    }

    private RequestResponseDto mapToResponseDto(Request request) {
        RequestResponseDto dto = new RequestResponseDto();
        dto.setId(request.getId());
        dto.setStatus(request.getStatus().name());
        dto.setCreatedAt(request.getCreatedAt());
        dto.setItems(request.getItems().stream().map(item -> {
            RequestItemResponseDto itemDto = new RequestItemResponseDto();
            itemDto.setProductId(item.getProductSupplier().getId().getProductId());
            itemDto.setSupplierId(item.getProductSupplier().getId().getSupplierId());
            itemDto.setQuantity(item.getQuantity());
            return itemDto;
        }).collect(Collectors.toList()));
        return dto;
    }
}