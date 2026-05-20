package com.terranova.api.v1.user.domain.model;

import java.util.UUID;

public record SellerSummary(
        UUID sellerId,
        String sellerName,
        String sellerEmail,
        String sellerPhone,
        String profilePicture,
        int sellerScore
) {
}
