package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.image;

import java.time.LocalDateTime;

public record ImageResponse(
        Long productId,
        Long idImage,
        String fileName,
        String url,
        String contentType,
        long size,
        Integer displayOrder,
        boolean isCoverImage,
        LocalDateTime createdAt
) {
}
