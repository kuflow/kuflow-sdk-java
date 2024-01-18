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
package com.kuflow.rest.model;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

/**
 * The AuthenticationEngineToken model.
 */
@Fluent
public final class AuthenticationEngineToken {

    /*
     * Engine authentication token
     */
    @JsonProperty(value = "token", required = true)
    private String token;

    /*
     * The expiredAt property.
     */
    @JsonProperty(value = "expiredAt", required = true)
    private OffsetDateTime expiredAt;

    /**
     * Creates an instance of AuthenticationEngineToken class.
     */
    public AuthenticationEngineToken() {}

    /**
     * Get the token property: Engine authentication token.
     *
     * @return the token value.
     */
    public String getToken() {
        return this.token;
    }

    /**
     * Set the token property: Engine authentication token.
     *
     * @param token the token value to set.
     * @return the AuthenticationEngineToken object itself.
     */
    public AuthenticationEngineToken setToken(String token) {
        this.token = token;
        return this;
    }

    /**
     * Get the expiredAt property: The expiredAt property.
     *
     * @return the expiredAt value.
     */
    public OffsetDateTime getExpiredAt() {
        return this.expiredAt;
    }

    /**
     * Set the expiredAt property: The expiredAt property.
     *
     * @param expiredAt the expiredAt value to set.
     * @return the AuthenticationEngineToken object itself.
     */
    public AuthenticationEngineToken setExpiredAt(OffsetDateTime expiredAt) {
        this.expiredAt = expiredAt;
        return this;
    }
}
