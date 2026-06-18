package com.terranova.api.v1.auth.domain.ports.out;

public interface EmailPort {

    void sendVerificationCode(String email, String code);
}
