package com.terranova.api.v1.product.domain.model.command;

import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record CreateFarmCommand(
        ProductTypeEnum productType,
        String name,
        BigDecimal price,
        String description,
        String status,
        LocalDate publishDate,
        String city,
        Double latitude,
        Double longitude,
        UUID idSeller,

        Double totalSpaceInM2,
        Double builtSpaceInM2,
        int stratum,
        int roomsQuantity,
        int bathroomsQuantity
) implements CreateProductCommand {}