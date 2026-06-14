package com.terranova.api.v1.user.infrastructure.adapter.in.web.dto;

import java.util.UUID;

public record SellerSummaryResponse(
        UUID userId,
        String name,
        String email,
        String phoneNumber,
        String profilePicture,
        int userScore
) {
}
