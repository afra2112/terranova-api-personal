package com.terranova.api.v1.product.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Land extends Product{
    private Double landSizeInM2;
    private String currentUse;
    private String topography;
    private String access;
    private String currentServices;
}
