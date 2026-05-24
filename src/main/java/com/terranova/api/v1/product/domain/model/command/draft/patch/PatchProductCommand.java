package com.terranova.api.v1.product.domain.model.command.draft.patch;

import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;

import java.math.BigDecimal;
import java.util.UUID;

public sealed interface PatchProductCommand permits PatchCattleCommand, PatchLandCommand, PatchFarmCommand {
    Long productId();
    ProductTypeEnum productType();
    String name();
    BigDecimal price();
    String description();
    String city();
    Double latitude();
    Double longitude();
    UUID idSeller();
}
