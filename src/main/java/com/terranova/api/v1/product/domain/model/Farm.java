package com.terranova.api.v1.product.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Farm extends Product {
    private Double totalSpaceInM2;
    private Double builtSpaceInM2;
    private int stratum;
    private int roomsQuantity;
    private int bathroomsQuantity;
}
