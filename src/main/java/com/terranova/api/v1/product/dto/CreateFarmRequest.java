package com.terranova.api.v1.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateFarmRequest(
        @NotNull
        @Positive
        Double totalSpaceInM2,

        @NotNull
        @Positive
        Double buildedSpaceInM2,

        @NotNull
        @Positive
        int stratum,

        @NotNull
        @Positive
        int roomsQuantity,

        @NotNull
        @Positive
        int bathroomsQuantity
) {
}
