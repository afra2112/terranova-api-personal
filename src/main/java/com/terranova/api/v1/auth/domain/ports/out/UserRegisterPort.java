package com.terranova.api.v1.auth.domain.ports.out;

import com.terranova.api.v1.auth.domain.model.AuthenticatedCredentials;
import com.terranova.api.v1.auth.domain.model.NewUserDomain;

public interface UserRegisterPort {

    boolean existByEmailOrIdentification(String email, String identification);

    AuthenticatedCredentials createUser(NewUserDomain newUserDomain);
}
