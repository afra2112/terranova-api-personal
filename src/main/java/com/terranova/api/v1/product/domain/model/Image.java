package com.terranova.api.v1.product.domain.model;

import java.time.LocalDateTime;

public record Image(
        Long idImagen,
        String fileName,
        String url,
        String contentType,
        long size,
        Integer displayOrder,
        LocalDateTime createdAt,
        Long productId
) {
}
