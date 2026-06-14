package com.terranova.api.v1.user.domain.ports.out;

import com.terranova.api.v1.user.domain.model.SellerSummary;
import com.terranova.api.v1.user.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {

    User save(User user);

    User findById(UUID userId);

    Optional<User> findByGoogleId(String googleId);

    Optional<User> findByEmail(String email);

    List<SellerSummary> findBatchUsers(List<UUID> ids);

    boolean existsByEmailOrIdentification(String email, String identification);
}
