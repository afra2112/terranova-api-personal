package com.terranova.api.v1.auth.infrastructure.adapter.out.email;

import com.terranova.api.v1.auth.domain.ports.out.EmailPort;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailAdapter implements EmailPort {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendVerificationCode(String email, String code) {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(email);
        mail.setSubject("Terranova - Verificación de Correo");
        mail.setText("""
                Bienvenido a Terranova.
                Tu codigo de verificacion es:
                
                %s
                
                Este codigo expira en 15 minutos.
                """.formatted(code));
        javaMailSender.send(mail);
    }
}
