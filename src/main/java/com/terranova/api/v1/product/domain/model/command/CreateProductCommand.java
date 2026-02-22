package com.terranova.api.v1.product.domain.model.command;

import java.math.BigDecimal;
import java.util.UUID;

public sealed interface CreateProductCommand permits CreateCattleCommand, CreateFarmCommand, CreateLandCommand {
    String productType();
    String name();
    BigDecimal price();
    String description();
    String city();
    Double latitude();
    Double longitude();
    UUID idSeller();
}