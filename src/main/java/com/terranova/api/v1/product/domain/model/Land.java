package com.terranova.api.v1.product.domain.model;

import com.terranova.api.v1.product.domain.model.enums.LandAccessEnum;
import com.terranova.api.v1.product.domain.model.enums.LandTopographyEnum;
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
    private LandTopographyEnum topography;
    private LandAccessEnum access;
    private String currentServices;
}
