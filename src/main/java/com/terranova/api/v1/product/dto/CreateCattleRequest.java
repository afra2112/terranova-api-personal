package com.terranova.api.v1.product.dto;

import com.terranova.api.v1.product.enums.CattleGenderEnum;
import com.terranova.api.v1.product.enums.CattleTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateCattleRequest(
        @NotBlank
        String race,

        @NotNull
        @Positive
        Double weight,

        @Positive
        @NotNull
        Double cattleAgeInYears,

        @NotNull
        CattleGenderEnum gender,

        @NotNull
        CattleTypeEnum type,

        @NotNull
        @Positive
        int quantity
)  {
}
