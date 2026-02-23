package com.terranova.api.v1.product.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Cattle extends Product{
    private String race;
    private Double weightInKg;
    private Double cattleAgeInYears;
    private String gender;
    private String cattleType;
    private int quantity;
}
