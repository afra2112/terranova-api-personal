package com.terranova.api.v1.product.domain.model.command.search;

import com.terranova.api.v1.product.domain.model.enums.CattleGenderEnum;
import com.terranova.api.v1.product.domain.model.enums.CattleTypeEnum;
import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import java.math.BigDecimal;
import java.time.LocalDate;

public record SearchCattleCommand(
        String name,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        String city,
        ProductTypeEnum productType,
        LocalDate publishDateFrom,
        LocalDate publishDateTo,
        Double latitude,
        Double longitude,
        Double radiusKm,

        String race,
        Double minWeight,
        Double maxWeight,
        Double minAge,
        Double maxAge,
        CattleGenderEnum gender,
        CattleTypeEnum cattleType,
        Integer minQuantity,
        Integer maxQuantity
) implements SearchProductCommand {
}
