package com.terranova.api.v1.user.domain.model;

import com.terranova.api.v1.user.domain.AuthProviderEnum;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record User(
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
        List<UUID> refreshTokenIds,
        String googleId,
        AuthProviderEnum authProvider
) {
}
