package com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "farms")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SuperBuilder
public class FarmEntity extends ProductEntity{

    private Double totalSpaceInM2;
    private Double builtSpaceInM2;
    private int stratum;
    private int roomsQuantity;
    private int bathroomsQuantity;
}