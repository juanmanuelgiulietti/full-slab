package com.fullslab.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "brands")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_brand;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String logo_url;

    @Column(columnDefinition = "TEXT")
    private String description;
}