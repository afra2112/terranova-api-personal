package com.terranova.api.v1.product.domain.model.command;

import com.terranova.api.v1.product.domain.model.enums.LandAccessEnum;
import com.terranova.api.v1.product.domain.model.enums.LandTopographyEnum;
import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record CreateLandCommand(
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

        Double landSizeInM2,
        String currentUse,
        LandTopographyEnum topography,
        LandAccessEnum access,
        String currentServices
) implements CreateProductCommand { }