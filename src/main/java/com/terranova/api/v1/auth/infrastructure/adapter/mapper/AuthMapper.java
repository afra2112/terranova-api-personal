package com.terranova.api.v1.auth.infrastructure.adapter.mapper;

import com.terranova.api.v1.auth.domain.model.AuthenticatedCredentials;
import com.terranova.api.v1.auth.domain.model.NewUserDomain;
import com.terranova.api.v1.auth.domain.model.UserCredential;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.request.AuthRequest;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.request.RegisterRequest;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.response.AuthResponse;
import com.terranova.api.v1.user.domain.model.User;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class AuthMapper {

    public UserCredential toUserCredential(AuthRequest request){
        return new UserCredential(
                request.email(),
                request.password()
        );
    }

    public AuthResponse toAuthResponse(AuthenticatedCredentials authenticatedCredentials){
        return new AuthResponse(
                authenticatedCredentials.accessToken(),
                authenticatedCredentials.refreshToken()
        );
    }

    public NewUserDomain fromRequestToNewUserDomain(RegisterRequest request){
        return new NewUserDomain(
                null,
                request.identification(),
                request.names(),
                request.lastName(),
                request.email(),
                request.password(),
                request.phoneNumber(),
                request.birthday(),
                LocalDateTime.now(),
                null,
                new ArrayList<>(),
                0,
                new ArrayList<>()
        );
    }

    public User fromUserAuthDomainToUserDomain(NewUserDomain newUserDomain){
        return new User(
                newUserDomain.userId(),
                newUserDomain.identification(),
                newUserDomain.names(),
                newUserDomain.lastName(),
                newUserDomain.email(),
                newUserDomain.password(),
                newUserDomain.phoneNumber(),
                newUserDomain.birthday(),
                newUserDomain.registerDate(),
                newUserDomain.profilePicture(),
                newUserDomain.roles(),
                newUserDomain.userScore()
        );
    }
}
