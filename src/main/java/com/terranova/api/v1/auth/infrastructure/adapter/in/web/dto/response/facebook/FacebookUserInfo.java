package com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.response.facebook;

public record FacebookUserInfo(
        String facebookId,
        String email,
        String fullName,
        String picture
) {}
