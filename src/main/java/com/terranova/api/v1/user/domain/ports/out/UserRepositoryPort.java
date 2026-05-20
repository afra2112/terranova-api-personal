package com.terranova.api.v1.user.domain.ports.out;

import com.terranova.api.v1.user.domain.model.SellerSummary;
import com.terranova.api.v1.user.domain.model.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserRepositoryPort {

    User save(User user);

    User findByIdentification(String identification);

    Map<UUID, SellerSummary> findBatchUsers(List<UUID> ids);

    boolean existsByEmailOrIdentification(String email, String identification);
}
