package com.terranova.api.v1.auth.infrastructure.adapter.out.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.terranova.api.v1.auth.domain.model.GoogleUserInfo;
import com.terranova.api.v1.auth.domain.ports.out.GoogleAuthPort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Component
public class GoogleAuthAdapter implements GoogleAuthPort {

    private final String clientId;

    public GoogleAuthAdapter(@Value("${GOOGLE_CLIENT_ID}") String clientId) {
        this.clientId = clientId;
    }

    @Override
    public GoogleUserInfo verifyToken(String tokenId) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(),
                    GsonFactory.getDefaultInstance()
            ).setAudience(List.of(clientId)).build();

            GoogleIdToken token = verifier.verify(tokenId);

            if (token == null){
                throw new BusinessException(ErrorCodeEnum.INVALID_TOKEN);
            }

            GoogleIdToken.Payload payload = token.getPayload();

            return new GoogleUserInfo(
                    payload.getSubject(),
                    payload.getEmail(),
                    (String) payload.get("name"),
                    (String) payload.get("picture")
            );
        } catch (GeneralSecurityException | IOException e) {
            throw new BusinessException(
                    ErrorCodeEnum.INVALID_TOKEN,
                    "Invalid Google token"
            );
        }
    }
}
