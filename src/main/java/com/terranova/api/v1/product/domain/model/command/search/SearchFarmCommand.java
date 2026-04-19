package com.terranova.api.v1.product.domain.model.command.search;

import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import java.math.BigDecimal;
import java.time.LocalDate;

public record SearchFarmCommand(
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

        Double minTotalSpace,
        Double maxTotalSpace,
        Double minBuiltSpace,
        Double maxBuiltSpace,
        Integer minStratum,
        Integer maxStratum,
        Integer minRooms,
        Integer maxRooms,
        Integer minBathrooms,
        Integer maxBathrooms
) implements SearchProductCommand {
}
