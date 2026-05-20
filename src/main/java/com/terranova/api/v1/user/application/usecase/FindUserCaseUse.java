package com.terranova.api.v1.user.application.usecase;

import com.terranova.api.v1.user.domain.model.SellerSummary;
import com.terranova.api.v1.user.domain.model.User;
import com.terranova.api.v1.user.domain.ports.out.UserRepositoryPort;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FindUserCaseUse {

    private final UserRepositoryPort userRepositoryPort;

    public FindUserCaseUse(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public User findUserByIdentification(String identification){
        return userRepositoryPort.findByIdentification(identification);
    }

    public Map<UUID, SellerSummary> findSellerSummary(List<UUID> ids){
        return userRepositoryPort.findBatchUsers(ids);
    }

    public boolean existsEmailOrIdentification(String email, String identification){
        return userRepositoryPort.existsByEmailOrIdentification(email, identification);
    }
}
