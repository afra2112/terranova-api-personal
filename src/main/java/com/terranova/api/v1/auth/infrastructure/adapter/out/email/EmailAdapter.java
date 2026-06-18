package com.terranova.api.v1.auth.infrastructure.adapter.out.email;

import brevo.ApiClient;
import brevo.ApiException;
import brevo.Configuration;
import brevo.auth.ApiKeyAuth;
import brevoApi.TransactionalEmailsApi;
import brevoModel.CreateSmtpEmail;
import brevoModel.SendSmtpEmail;
import brevoModel.SendSmtpEmailSender;
import brevoModel.SendSmtpEmailTo;
import com.terranova.api.v1.auth.domain.ports.out.EmailPort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailAdapter implements EmailPort {

    @Value("${BREVO_API_KEY}")
    private String brevoApiKey;

    @Override
    public void sendVerificationCode(String email, String code) {

        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey(brevoApiKey);

        TransactionalEmailsApi apiInstance = new TransactionalEmailsApi();
        SendSmtpEmailSender sender = new SendSmtpEmailSender();
        sender.setName("Terranova");
        sender.setEmail("notif.terranova@gmail.com");

        SendSmtpEmailTo recipient = new SendSmtpEmailTo();
        recipient.setEmail(email);

        List<SendSmtpEmailTo> toList = new ArrayList<>();
        toList.add(recipient);

        SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
        sendSmtpEmail.setSender(sender);
        sendSmtpEmail.setTo(toList);
        sendSmtpEmail.setSubject("Terranova - Verificación de Correo");
        sendSmtpEmail.setTextContent("""
                Bienvenido a Terranova.
                Tu codigo de verificacion es:
                
                %s
                
                Este codigo expira en 15 minutos.
                """.formatted(code));

        try {
            CreateSmtpEmail result = apiInstance.sendTransacEmail(sendSmtpEmail);
            System.out.println("Email sent successfully! Message ID: " + result.getMessageId());
        } catch (ApiException e) {
            throw new BusinessException(ErrorCodeEnum.BREVO_RESPONSE_ERROR, "Reason: " + e.getResponseBody() + ". Status Code: " + e.getCode() + ". StackTrace: " + e.getStackTrace());
        }

    }
}
