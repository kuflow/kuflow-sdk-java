/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.common.authorization;

import com.kuflow.engine.api.controller.AuthenticationApi;
import com.kuflow.engine.api.resource.AuthenticationResource;
import com.kuflow.engine.api.resource.AuthenticationTypeResource;
import io.temporal.authorization.AuthorizationTokenSupplier;
import java.time.Duration;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KuFlowAuthorizationTokenSupplier implements AuthorizationTokenSupplier {

    private static final Logger LOGGER = LoggerFactory.getLogger(KuFlowAuthorizationTokenSupplier.class);

    private static final double EXPIRE_PERCENTAGE = 0.1;

    private static final Duration EXPIRE_MAX_DURATION = Duration.ofMinutes(10);

    private final AuthenticationApi authenticationApi;

    private volatile String token;

    private volatile Instant tokenExpireAt;

    public KuFlowAuthorizationTokenSupplier(AuthenticationApi authenticationApi) {
        this.authenticationApi = authenticationApi;
    }

    @Override
    public String supply() {
        String token = this.requestToken();

        return "Bearer " + token;
    }

    private String requestToken() {
        String token = this.token;
        Instant tokenExpireAt = this.tokenExpireAt;
        if (isTokenNonExpired(token, tokenExpireAt)) {
            return token;
        }

        synchronized (this) {
            token = this.token;
            tokenExpireAt = this.tokenExpireAt;
            if (isTokenNonExpired(token, tokenExpireAt)) {
                return token;
            }

            AuthenticationResource authentication = new AuthenticationResource();
            authentication.setType(AuthenticationTypeResource.ENGINE);
            authentication = this.authenticationApi.createAuthentication(authentication);

            Duration expireDuration = Duration.between(Instant.now(), authentication.getExpiredAt());
            expireDuration = Duration.ofSeconds((long) (expireDuration.getSeconds() * EXPIRE_PERCENTAGE));
            if (expireDuration.compareTo(EXPIRE_MAX_DURATION) > 0) {
                expireDuration = EXPIRE_MAX_DURATION;
            }

            this.token = token = authentication.getToken();
            this.tokenExpireAt = tokenExpireAt = Instant.now().plus(expireDuration);

            LOGGER.debug("Regenerated JWT Temporal authorization token. Expired at: {}", tokenExpireAt);

            return token;
        }
    }

    private static boolean isTokenNonExpired(String token, Instant tokenExpireAt) {
        return token != null && tokenExpireAt != null && Instant.now().isBefore(tokenExpireAt);
    }
}
