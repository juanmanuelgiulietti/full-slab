package com.fullslab.requests_item.entity;

import com.fullslab.product_suppliers.entity.ProductSupplier;
import com.fullslab.requests.entity.Request;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "request_items")
@Getter
@Setter
public class RequestItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
            @JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id")
    })
    private ProductSupplier productSupplier;

    private Integer quantity;
}