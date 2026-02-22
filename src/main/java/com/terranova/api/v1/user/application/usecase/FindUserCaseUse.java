package com.terranova.api.v1.user.application.usecase;

import com.terranova.api.v1.user.domain.model.User;
import com.terranova.api.v1.user.domain.ports.out.UserRepositoryPort;

public class FindUserCaseUse {

    private final UserRepositoryPort userRepositoryPort;

    public FindUserCaseUse(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public User findUserByIdentification(String identification){
        return userRepositoryPort.findByIdentification(identification);
    }

    public boolean existsEmailOrIdentification(String email, String identification){
        return userRepositoryPort.existsByEmailOrIdentification(email, identification);
    }
}
