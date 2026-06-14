package com.terranova.api.v1.product.domain.model.command.image;

public record ReorderImageCommand(
        Long imageId,
        Integer displayOrder
) {
}
