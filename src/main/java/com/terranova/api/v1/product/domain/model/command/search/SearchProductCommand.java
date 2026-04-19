package com.terranova.api.v1.product.domain.model.command.search;

import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import java.math.BigDecimal;
import java.time.LocalDate;

public sealed interface SearchProductCommand permits SearchCattleCommand, SearchFarmCommand, SearchLandCommand {
    String name();
    BigDecimal minPrice();
    BigDecimal maxPrice();
    String city();
    ProductTypeEnum productType();
    LocalDate publishDateFrom();
    LocalDate publishDateTo();
    Double latitude();
    Double longitude();
    Double radiusKm();
}
