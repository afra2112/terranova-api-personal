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
    private Double weight;

    @Column(nullable = false)
    private Double cattleAgeInYears;

    @Column(nullable = false)
    private CattleGenderEnum gender;

    @Enumerated(EnumType.STRING)
    private CattleTypeEnum type;

    @Column(nullable = false)
    private int quantity;
}
