/*
 * The MIT License
 * Copyright © 2021-present KuFlow S.L.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.kuflow.temporal.worker.authorization;

import com.kuflow.rest.KuFlowRestClient;
import com.kuflow.rest.model.Authentication;
import com.kuflow.rest.model.AuthenticationCreateParams;
import com.kuflow.rest.model.AuthenticationEngineToken;
import com.kuflow.rest.model.AuthenticationType;
import com.kuflow.rest.operation.AuthenticationOperations;
import com.kuflow.temporal.worker.connection.WorkerInformation;
import io.temporal.authorization.AuthorizationTokenSupplier;
import java.time.Duration;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KuFlowAuthorizationTokenSupplier implements AuthorizationTokenSupplier {

    private static final Logger LOGGER = LoggerFactory.getLogger(KuFlowAuthorizationTokenSupplier.class);

    private static final double EXPIRE_PERCENTAGE = 0.1;

    private static final Duration EXPIRE_MAX_DURATION = Duration.ofMinutes(10);

    private final AuthenticationOperations authenticationOperations;

    private final WorkerInformation workerInformation;

    private volatile String token;

    private volatile Instant tokenExpireAt;

    public KuFlowAuthorizationTokenSupplier(KuFlowRestClient kuFlowRestClient, WorkerInformation workerInformation) {
        this.authenticationOperations = kuFlowRestClient.getAuthenticationOperations();
        this.workerInformation = workerInformation;
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

            AuthenticationCreateParams params = new AuthenticationCreateParams()
                .setType(AuthenticationType.ENGINE_TOKEN)
                .setTenantId(this.workerInformation.getTenantId());

            Authentication authentication = this.authenticationOperations.createAuthentication(params);
            AuthenticationEngineToken authenticationEngineToken = authentication.getEngineToken();

            Duration expireDuration = Duration.between(Instant.now(), authenticationEngineToken.getExpiredAt());
            expireDuration = Duration.ofSeconds((long) (expireDuration.getSeconds() * EXPIRE_PERCENTAGE));
            if (expireDuration.compareTo(EXPIRE_MAX_DURATION) > 0) {
                expireDuration = EXPIRE_MAX_DURATION;
            }

            this.token = token = authenticationEngineToken.getToken();
            this.tokenExpireAt = tokenExpireAt = Instant.now().plus(expireDuration);

            LOGGER.debug("Regenerated JWT Temporal authorization token. Expired at: {}", tokenExpireAt);

            return token;
        }
    }

    private static boolean isTokenNonExpired(String token, Instant tokenExpireAt) {
        return token != null && tokenExpireAt != null && Instant.now().isBefore(tokenExpireAt);
    }
}
