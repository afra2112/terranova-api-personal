package com.terranova.api.v1.shared.security.utils;

import java.util.UUID;

public interface AuthFacade {
    UUID getAuthenticatedId();
    boolean hasRole(String role);
}
