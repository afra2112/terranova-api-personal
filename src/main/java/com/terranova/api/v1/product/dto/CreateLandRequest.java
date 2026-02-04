package com.terranova.api.v1.product.dto;

import com.terranova.api.v1.product.enums.LandAccessEnum;
import com.terranova.api.v1.product.enums.LandTopographyEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateLandRequest(
        @NotNull
        @Positive
        Double landSizeInM2,

        @NotBlank
        String currentUse,

        @NotNull
        LandTopographyEnum topography,

        @NotNull
        LandAccessEnum access,

        @NotBlank
        String currentServices
) {
}
