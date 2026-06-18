package com.terranova.api.v1.auth.application.usecase;

import com.terranova.api.v1.auth.domain.ports.out.FacebookAuthPort;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.response.facebook.FacebookUserInfo;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import com.terranova.api.v1.shared.security.utils.AuthFacade;
import com.terranova.api.v1.user.domain.model.User;
import com.terranova.api.v1.user.domain.ports.out.UserRepositoryPort;

public class LinkFacebookAccountUseCase {

    private final FacebookAuthPort facebookAuthPort;
    private final UserRepositoryPort userRepositoryPort;
    private final AuthFacade authFacade;

    public LinkFacebookAccountUseCase(FacebookAuthPort facebookAuthPort, UserRepositoryPort userRepositoryPort, AuthFacade authFacade) {
        this.facebookAuthPort = facebookAuthPort;
        this.userRepositoryPort = userRepositoryPort;
        this.authFacade = authFacade;
    }

    public void link(String accessToken){

        FacebookUserInfo facebookUser = facebookAuthPort.verifyToken(accessToken);

        User authenticatedUser =
                userRepositoryPort.findById(
                        authFacade.getAuthenticatedId()
                );

        validateEmailMatch(
                authenticatedUser,
                facebookUser
        );

        validateFacebookNotLinked(
                authenticatedUser
        );

        validateFacebookNotUsed(
                authenticatedUser,
                facebookUser
        );

        User updated =
                authenticatedUser.toBuilder()
                        .facebookId(
                                facebookUser.facebookId()
                        )
                        .build();

        userRepositoryPort.save(updated);
    }

    private void validateEmailMatch(
            User user,
            FacebookUserInfo facebookUser
    ){

        if(
                !user.email().equalsIgnoreCase(
                        facebookUser.email()
                )
        ){
            throw new BusinessException(
                    ErrorCodeEnum.INVALID_FACEBOOK_ACCOUNT, "Current email authenticated: " + user.email() + "\"user's facebook email\": " + facebookUser.email()
            );
        }
    }

    private void validateFacebookNotLinked(
            User user
    ){

        if(user.facebookId() != null){
            throw new BusinessException(
                    ErrorCodeEnum.FACEBOOK_ALREADY_LINKED
            );
        }
    }

    private void validateFacebookNotUsed(User currentUser, FacebookUserInfo facebookUser){
        userRepositoryPort.findByFacebookId(facebookUser.facebookId())
                .ifPresent(existing -> {
                    if(!existing.userId().equals(currentUser.userId())){
                        throw new BusinessException(
                                ErrorCodeEnum.FACEBOOK_ACCOUNT_ALREADY_USED, "Facebook account already linked to another user, facebook email: " + currentUser.email() + ". Current userId: " + currentUser.userId()
                        );
                    }
                });
    }
}