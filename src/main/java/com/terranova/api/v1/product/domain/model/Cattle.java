package com.terranova.api.v1.product.domain.model;

import com.terranova.api.v1.product.domain.model.enums.CattleGenderEnum;
import com.terranova.api.v1.product.domain.model.enums.CattleTypeEnum;
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
    private CattleGenderEnum gender;
    private CattleTypeEnum cattleType;
    private int quantity;
}
