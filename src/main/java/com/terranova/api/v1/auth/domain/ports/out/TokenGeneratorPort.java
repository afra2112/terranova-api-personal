package com.terranova.api.v1.auth.domain.ports.out;

import java.util.List;

public interface TokenGeneratorPort {

    String generateToken(String identification, List<String> roles);
}
