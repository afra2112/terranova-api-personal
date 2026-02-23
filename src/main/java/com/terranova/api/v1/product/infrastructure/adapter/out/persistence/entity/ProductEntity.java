package com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity;

import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import com.terranova.api.v1.product.domain.model.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEnum status;

    @Column(nullable = false, updatable = false)
    private LocalDate publishDate;

    private String city;
    private Double latitude;
    private Double longitude;

    @Column(nullable = false)
    private UUID sellerId;

    @Enumerated(EnumType.STRING)
    private ProductTypeEnum productType;
}