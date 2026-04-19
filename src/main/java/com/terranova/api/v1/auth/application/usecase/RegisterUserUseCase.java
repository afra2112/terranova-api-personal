package com.terranova.api.v1.auth.application.usecase;

import com.terranova.api.v1.auth.domain.model.AuthenticatedCredentials;
import com.terranova.api.v1.auth.domain.model.NewUserDomain;
import com.terranova.api.v1.auth.domain.ports.out.UserPort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;

import java.time.LocalDate;
import java.time.Period;

public class RegisterUserUseCase {

    private final UserPort userPort;

    public RegisterUserUseCase(UserPort userPort) {
        this.userPort = userPort;
    }

    public AuthenticatedCredentials createUser(NewUserDomain newUserDomain){
        if (userPort.existByEmailOrIdentification(newUserDomain.email(), newUserDomain.identification())){
            throw new BusinessException(ErrorCodeEnum.USER_ALREADY_EXISTS, "User with email: " + newUserDomain.email() + ". Or Identification: " + newUserDomain.identification() + ". Already exists, please sign in.");
        }

        validateBirthDate(newUserDomain.birthday());

        return userPort.createUser(newUserDomain);
    }

    private void validateBirthDate(LocalDate date){
        int age = Period.between(date, LocalDate.now()).getYears();

        if (age < 18){
            throw new BusinessException(ErrorCodeEnum.INVALID_BIRTH_DATE);
        }
    }
}
