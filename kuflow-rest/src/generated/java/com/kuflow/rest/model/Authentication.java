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
import java.util.UUID;

/**
 * The Authentication model.
 */
@Fluent
public final class Authentication extends AbstractAudited {

    /*
     * The id property.
     */
    @JsonProperty(value = "id")
    private UUID id;

    /*
     * The type property.
     */
    @JsonProperty(value = "type")
    private AuthenticationType type;

    /*
     * Engine authentication token.
     *
     * @deprecated use engineToken.token
     *
     */
    @JsonProperty(value = "token")
    private String token;

    /*
     * Engine authentication token expiration.
     *
     * @deprecated use engineToken.expiredAt
     *
     */
    @JsonProperty(value = "expiredAt")
    private OffsetDateTime expiredAt;

    /*
     * The engineToken property.
     */
    @JsonProperty(value = "engineToken")
    private AuthenticationEngineToken engineToken;

    /*
     * The engineCertificate property.
     */
    @JsonProperty(value = "engineCertificate")
    private AuthenticationEngineCertificate engineCertificate;

    /**
     * Creates an instance of Authentication class.
     */
    public Authentication() {}

    /**
     * Get the id property: The id property.
     *
     * @return the id value.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Set the id property: The id property.
     *
     * @param id the id value to set.
     * @return the Authentication object itself.
     */
    public Authentication setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the type property: The type property.
     *
     * @return the type value.
     */
    public AuthenticationType getType() {
        return this.type;
    }

    /**
     * Set the type property: The type property.
     *
     * @param type the type value to set.
     * @return the Authentication object itself.
     */
    public Authentication setType(AuthenticationType type) {
        this.type = type;
        return this;
    }

    /**
     * Get the token property: Engine authentication token.
     *
     * @deprecated use engineToken.token.
     *
     * @return the token value.
     */
    public String getToken() {
        return this.token;
    }

    /**
     * Set the token property: Engine authentication token.
     *
     * @deprecated use engineToken.token.
     *
     * @param token the token value to set.
     * @return the Authentication object itself.
     */
    public Authentication setToken(String token) {
        this.token = token;
        return this;
    }

    /**
     * Get the expiredAt property: Engine authentication token expiration.
     *
     * @deprecated use engineToken.expiredAt.
     *
     * @return the expiredAt value.
     */
    public OffsetDateTime getExpiredAt() {
        return this.expiredAt;
    }

    /**
     * Set the expiredAt property: Engine authentication token expiration.
     *
     * @deprecated use engineToken.expiredAt.
     *
     * @param expiredAt the expiredAt value to set.
     * @return the Authentication object itself.
     */
    public Authentication setExpiredAt(OffsetDateTime expiredAt) {
        this.expiredAt = expiredAt;
        return this;
    }

    /**
     * Get the engineToken property: The engineToken property.
     *
     * @return the engineToken value.
     */
    public AuthenticationEngineToken getEngineToken() {
        return this.engineToken;
    }

    /**
     * Set the engineToken property: The engineToken property.
     *
     * @param engineToken the engineToken value to set.
     * @return the Authentication object itself.
     */
    public Authentication setEngineToken(AuthenticationEngineToken engineToken) {
        this.engineToken = engineToken;
        return this;
    }

    /**
     * Get the engineCertificate property: The engineCertificate property.
     *
     * @return the engineCertificate value.
     */
    public AuthenticationEngineCertificate getEngineCertificate() {
        return this.engineCertificate;
    }

    /**
     * Set the engineCertificate property: The engineCertificate property.
     *
     * @param engineCertificate the engineCertificate value to set.
     * @return the Authentication object itself.
     */
    public Authentication setEngineCertificate(AuthenticationEngineCertificate engineCertificate) {
        this.engineCertificate = engineCertificate;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Authentication setObjectType(AuditedObjectType objectType) {
        super.setObjectType(objectType);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Authentication setCreatedBy(UUID createdBy) {
        super.setCreatedBy(createdBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Authentication setCreatedAt(OffsetDateTime createdAt) {
        super.setCreatedAt(createdAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Authentication setLastModifiedBy(UUID lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Authentication setLastModifiedAt(OffsetDateTime lastModifiedAt) {
        super.setLastModifiedAt(lastModifiedAt);
        return this;
    }
}
