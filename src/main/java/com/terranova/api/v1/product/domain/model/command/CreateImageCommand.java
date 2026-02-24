package com.terranova.api.v1.product.domain.model.command;

import java.time.LocalDateTime;

public record CreateImageCommand(
        String originalFilename,
        String contentType,
        long size,
        Integer displayOrder,
        byte[] content
) {
}
