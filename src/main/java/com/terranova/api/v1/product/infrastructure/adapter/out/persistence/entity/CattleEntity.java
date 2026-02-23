package com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity;

import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.enums.CattleGenderEnum;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.enums.CattleTypeEnum;
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

    @Column(nullable = false, length = 30)
    private String race;

    @Column(nullable = false)
    private Double weightInKg;

    @Column(nullable = false)
    private Double cattleAgeInYears;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CattleGenderEnum gender;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CattleTypeEnum cattleType;

    @Column(nullable = false)
    private int quantity;
}
