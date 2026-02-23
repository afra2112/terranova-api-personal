package com.terranova.api.v1.product.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public abstract class Product {
    private Long productId;
    private String name;
    private BigDecimal price;
    private String description;
    private String status;
    private LocalDate publishDate;
    private String city;
    private Double latitude;
    private Double longitude;
    private UUID sellerId;
    private String productType;
}
