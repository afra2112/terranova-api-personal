package com.terranova.api.v1.product.domain.model;

import java.util.UUID;

public record SellerSummary(
        UUID userId,
        String name,
        String email,
        String phoneNumber,
        String profilePicture,
        int userScore
) {
}
