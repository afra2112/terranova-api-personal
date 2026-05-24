package com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity;

import com.terranova.api.v1.product.domain.model.enums.LandAccessEnum;
import com.terranova.api.v1.product.domain.model.enums.LandTopographyEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "lands")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class LandEntity extends ProductEntity{

    private Double landSizeInM2;

    private String currentUse;

    @Enumerated(EnumType.STRING)
    private LandTopographyEnum topography;

    @Enumerated(EnumType.STRING)
    private LandAccessEnum access;

    private String currentServices;

}