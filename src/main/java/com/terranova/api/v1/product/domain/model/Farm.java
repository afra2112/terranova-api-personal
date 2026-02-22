package com.terranova.api.v1.product.domain.model;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Farm extends Product {
    private Double totalSpaceInM2;
    private Double builtSpaceInM2;
    private int stratum;
    private int roomsQuantity;
    private int bathroomsQuantity;
}
