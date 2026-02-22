package com.terranova.api.v1.product.domain.model.command;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateLandCommand(
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

        Double landSizeInM2,
        String currentUse,
        String topography,
        String access,
        String currentServices
) implements CreateProductCommand { }