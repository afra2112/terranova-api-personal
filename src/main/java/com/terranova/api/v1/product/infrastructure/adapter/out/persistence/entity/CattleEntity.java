package com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity;

import com.terranova.api.v1.product.domain.model.enums.CattleGenderEnum;
import com.terranova.api.v1.product.domain.model.enums.CattleTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "cattles")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class CattleEntity extends ProductEntity {

    @Column(length = 30)
    private String race;

    private Double weightInKg;

    private Double cattleAgeInYears;

    @Enumerated(EnumType.STRING)
    private CattleGenderEnum gender;

    @Enumerated(EnumType.STRING)
    private CattleTypeEnum cattleType;

    private int quantity;
}
