package com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;

public record VerifyEmailRequest(@NotBlank String email, @NotBlank String code) {
}
