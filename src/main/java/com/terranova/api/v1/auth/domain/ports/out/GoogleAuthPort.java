package com.terranova.api.v1.auth.domain.ports.out;

import com.terranova.api.v1.auth.domain.model.GoogleUserInfo;

public interface GoogleAuthPort {

    GoogleUserInfo verifyToken(String tokenId);
}
