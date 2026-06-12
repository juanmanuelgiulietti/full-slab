package com.fullslab.product_suppliers.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.fullslab.exception.ResourceNotFoundException;
import com.fullslab.product_suppliers.dto.CreateProductSupplier;
import com.fullslab.product_suppliers.dto.ProductSummaryDto;
import com.fullslab.product_suppliers.dto.ProductSupplierResponseDto;
import com.fullslab.product_suppliers.dto.SupplierSummaryDto;
import com.fullslab.product_suppliers.dto.UpdateProductSupplierDto;
import com.fullslab.product_suppliers.entity.ProductSupplier;
import com.fullslab.product_suppliers.entity.ProductSupplierId;
import com.fullslab.product_suppliers.repository.ProductSuppliersRepository;
import com.fullslab.products.entity.Product;
import com.fullslab.products.repository.ProductRepository;
import com.fullslab.suppliers.entity.Supplier;
import com.fullslab.suppliers.repository.SupplierRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductSuppliersService {
    private final ProductSuppliersRepository productSuppliersRepository;
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;

    public ProductSupplierResponseDto createProductSupplier(CreateProductSupplier dto, Long productId,
            Long supplierId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado"));
        ProductSupplier ps = new ProductSupplier();

        ProductSupplierId id = new ProductSupplierId(productId, supplierId);
        ps.setId(id);

        ps.setProduct(product);
        ps.setSupplier(supplier);
        ps.setPriceOffered(dto.getPriceOffered());
        ps.setAvailability(dto.getAvailability());

        ProductSupplier saved = productSuppliersRepository.save(ps);
        return mapToResponseDto(saved);
    }

    public ProductSupplierResponseDto getProductSupplier(Long productId, Long supplierId) {
        ProductSupplier ps = productSuppliersRepository.findByProductIdAndSupplierId(productId, supplierId)
                .orElseThrow(() -> new ResourceNotFoundException("Relación no encontrada."));
        
        return mapToResponseDto(ps);
    }

    public ProductSupplierResponseDto updateProductSupplier(UpdateProductSupplierDto dto, Long productId,
            Long supplierId) {
        ProductSupplier ps = productSuppliersRepository.findByProductIdAndSupplierId(productId, supplierId)
                .orElseThrow(() -> new ResourceNotFoundException("Relación no encontrada."));

        ps.setPriceOffered(dto.getPriceOffered());
        ps.setAvailability(dto.getAvailability());

        ProductSupplier updatedProductSupplier = productSuppliersRepository.save(ps);
        return mapToResponseDto(updatedProductSupplier);
    }

    public void deleteProductSupplier(Long productId, Long supplierId) {
        ProductSupplier ps = productSuppliersRepository.findByProductIdAndSupplierId(productId, supplierId)
                .orElseThrow(() -> new ResourceNotFoundException("Relación no encontrada."));

        ps.setDeletedAt(LocalDateTime.now());
        productSuppliersRepository.save(ps);
    }

    private ProductSupplierResponseDto mapToResponseDto(ProductSupplier ps) {
        ProductSupplierResponseDto dto = new ProductSupplierResponseDto();
        
        dto.setPriceOffered(ps.getPriceOffered());
        dto.setAvailability(ps.getAvailability());

        ProductSummaryDto productSummary = new ProductSummaryDto(ps.getProduct().getProductId(), ps.getProduct().getName());
        SupplierSummaryDto supplierSummary = new SupplierSummaryDto(ps.getSupplier().getSupplierId(), ps.getSupplier().getName());
        
        dto.setProduct(productSummary);
        dto.setSupplier(supplierSummary);

        return dto;
    }
}
