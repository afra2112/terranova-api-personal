package com.terranova.api.v1.auth.domain.ports.out;

import java.util.List;
import java.util.UUID;

public interface TokenGeneratorPort {

    String generateToken(UUID userId, List<String> roles);
}
