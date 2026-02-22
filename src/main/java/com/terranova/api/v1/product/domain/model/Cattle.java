package com.terranova.api.v1.product.domain.model;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Cattle extends Product{
    private String race;
    private Double weight;
    private Double cattleAgeInYears;
    private String gender;
    private String type;
    private int quantity;
}
