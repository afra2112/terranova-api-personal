package com.terranova.api.v1.auth.domain.model;

public record GoogleUserInfo(
        String googleId,
        String email,
        String fullName,
        String picture
) {
}