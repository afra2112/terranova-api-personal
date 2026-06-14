package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.image;

public record ReorderImageRequest(
        Long imageId,
        Integer displayOrder
) {
}
