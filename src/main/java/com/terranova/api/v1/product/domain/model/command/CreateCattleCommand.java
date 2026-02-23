package com.terranova.api.v1.product.domain.model.command;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record CreateCattleCommand(
        String productType,
        String name,
        BigDecimal price,
        String description,
        String status,
        LocalDate publishDate,
        String city,
        Double latitude,
        Double longitude,
        UUID idSeller,

        String race,
        Double weightInKg,
        Double cattleAgeInYears,
        String gender,
        String cattleType,
        int quantity
) implements CreateProductCommand {}