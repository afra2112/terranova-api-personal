package com.terranova.api.v1.product.domain.model;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Land extends Product{
    private Double landSizeInM2;
    private String currentUse;
    private String topography;
    private String access;
    private String currentServices;
}
