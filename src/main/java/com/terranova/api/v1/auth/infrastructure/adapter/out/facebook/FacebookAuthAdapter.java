package com.terranova.api.v1.auth.infrastructure.adapter.out.facebook;

import com.terranova.api.v1.auth.domain.ports.out.FacebookAuthPort;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.response.facebook.FacebookResponse;
import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.response.facebook.FacebookUserInfo;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class FacebookAuthAdapter implements FacebookAuthPort {

    private final RestTemplate restTemplate;

    @Override
    public FacebookUserInfo verifyToken(String accessToken) {

        String url =
                "https://graph.facebook.com/me" +
                        "?fields=id,name,email,picture" +
                        "&access_token=" + accessToken;

        FacebookResponse response =
                restTemplate.getForObject(
                        url,
                        FacebookResponse.class
                );

        System.out.println(response);
        System.out.println("EMAIL: " + response.email());

        if(response == null){
            throw new BusinessException(
                    ErrorCodeEnum.INVALID_FACEBOOK_TOKEN
            );
        }

        return new FacebookUserInfo(
                response.id(),
                response.email(),
                response.name(),
                response.picture()
                        .data()
                        .url()
        );
    }
}
