package com.terranova.api.v1.product.domain.model;

import java.util.UUID;

public record SellerSummary(
        UUID sellerId,
        String sellerName,
        String sellerPhone,
        String sellerEmail,
        int sellerScore
) {
}
