package com.terranova.api.v1.auth.domain.ports.out;

import com.terranova.api.v1.auth.infrastructure.adapter.in.web.dto.response.facebook.FacebookUserInfo;

public interface FacebookAuthPort {

    FacebookUserInfo verifyToken(String accessToken);
}
