package com.terranova.api.v1.auth.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record NewUserDomain(
        UUID userId,
        String identification,
        String names,
        String lastName,
        String email,
        String password,
        String phoneNumber,
        LocalDate birthday,
        LocalDateTime registerDate,
        String profilePicture,
        List<String> roles,
        int userScore,
        List<UUID> refreshTokenIds
) {
}
