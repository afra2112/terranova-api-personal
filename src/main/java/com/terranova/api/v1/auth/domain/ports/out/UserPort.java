package com.terranova.api.v1.auth.domain.ports.out;

import com.terranova.api.v1.auth.domain.model.NewUserDomain;
import com.terranova.api.v1.user.domain.model.User;
import java.util.List;
import java.util.UUID;

public interface UserPort {

    boolean existByEmailOrIdentification(String email, String identification);

    User createUser(NewUserDomain newUserDomain);

    List<String> getRolesByIdentification(UUID userId);
}
