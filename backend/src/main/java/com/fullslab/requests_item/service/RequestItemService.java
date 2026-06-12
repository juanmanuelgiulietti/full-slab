package com.fullslab.requests_item.service;

import com.fullslab.product_suppliers.repository.ProductSuppliersRepository;
import com.fullslab.requests.repository.RequestRepository;
import com.fullslab.requests_item.dto.RequestItemDto;
import com.fullslab.requests_item.dto.RequestItemResponseDto;
import com.fullslab.requests_item.entity.RequestItem;
import com.fullslab.requests_item.repository.RequestItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestItemService {

    private final RequestItemRepository itemRepository;
    private final RequestRepository requestRepository;
    private final ProductSuppliersRepository productSupplierRepository;

    @Transactional
    public RequestItemResponseDto create(RequestItemDto dto) {
        var request = requestRepository.findById(dto.getRequestId())
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));
        
        var productSupplier = productSupplierRepository.findByProductIdAndSupplierId(dto.getProductId(), dto.getSupplierId())
                .orElseThrow(() -> new EntityNotFoundException("Producto-Proveedor no encontrado"));

        RequestItem item = new RequestItem();
        item.setRequest(request);
        item.setProductSupplier(productSupplier);
        item.setQuantity(dto.getQuantity());

        return mapToDto(itemRepository.save(item));
    }

    @Transactional(readOnly = true)
    public List<RequestItemResponseDto> findAll() {
        return itemRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RequestItemResponseDto findById(Long id) {
        return itemRepository.findById(id).map(this::mapToDto)
                .orElseThrow(() -> new EntityNotFoundException("Ítem no encontrado"));
    }

    @Transactional
    public RequestItemResponseDto updateQuantity(Long id, Integer quantity) {
        RequestItem item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ítem no encontrado"));
        
        item.setQuantity(quantity);
        return mapToDto(itemRepository.save(item));
    }

    @Transactional
    public void delete(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new EntityNotFoundException("Ítem no encontrado");
        }
        itemRepository.deleteById(id);
    }

    private RequestItemResponseDto mapToDto(RequestItem item) {
        RequestItemResponseDto dto = new RequestItemResponseDto();
        dto.setId(item.getId());
        dto.setRequestId(item.getRequest().getId());
        dto.setProductId(item.getProductSupplier().getId().getProductId());
        dto.setSupplierId(item.getProductSupplier().getId().getSupplierId());
        dto.setQuantity(item.getQuantity());
        return dto;
    }
}