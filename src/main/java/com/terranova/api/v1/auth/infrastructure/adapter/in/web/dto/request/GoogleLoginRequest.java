package com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.request;

public record GoogleLoginRequest(
        String idToken
) {
}
