package com.terranova.api.v1.auth.domain.model;

import java.util.List;
import java.util.UUID;

public record AuthenticatedUser(
        UUID userId,
        List<String> roles
){ }
