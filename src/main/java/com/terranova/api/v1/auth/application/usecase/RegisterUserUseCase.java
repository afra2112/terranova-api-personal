package com.terranova.api.v1.auth.application.usecase;

import com.terranova.api.v1.auth.domain.model.AuthenticatedCredentials;
import com.terranova.api.v1.auth.domain.model.NewUserDomain;
import com.terranova.api.v1.auth.domain.ports.out.UserRegisterPort;

import java.time.LocalDate;
import java.time.Period;

public class RegisterUserUseCase {

    private final UserRegisterPort userRegisterPort;

    public RegisterUserUseCase(UserRegisterPort userRegisterPort) {
        this.userRegisterPort = userRegisterPort;
    }

    public AuthenticatedCredentials createUser(NewUserDomain newUserDomain){
        if (userRegisterPort.existByEmailOrIdentification(newUserDomain.email(), newUserDomain.identification())){
//            throw new BusinessException();
        }

        validateBirthDate(newUserDomain.birthday());

        return userRegisterPort.createUser(newUserDomain);
    }

    private void validateBirthDate(LocalDate date){
        int age = Period.between(date, LocalDate.now()).getYears();

        if (age < 18){
//            throw new BusinessException("You must have al least 18 years of age");
        }
    }
}
