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

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

/** The AuthenticationEngineWorkerConfigTls model. */
@Immutable
public final class AuthenticationEngineWorkerConfigTls {
    /*
     * The serverRootCaCertificate property.
     */
    @JsonProperty(value = "serverRootCaCertificate", access = JsonProperty.Access.WRITE_ONLY)
    private String serverRootCaCertificate;

    /*
     * The clientCertificate property.
     */
    @JsonProperty(value = "clientCertificate", access = JsonProperty.Access.WRITE_ONLY)
    private String clientCertificate;

    /*
     * The clientPrivateKey property.
     */
    @JsonProperty(value = "clientPrivateKey", access = JsonProperty.Access.WRITE_ONLY)
    private String clientPrivateKey;

    /*
     * The clientCertificateValidFrom property.
     */
    @JsonProperty(value = "clientCertificateValidFrom", access = JsonProperty.Access.WRITE_ONLY)
    private OffsetDateTime clientCertificateValidFrom;

    /*
     * The clientCertificateValidUntil property.
     */
    @JsonProperty(value = "clientCertificateValidUntil", access = JsonProperty.Access.WRITE_ONLY)
    private OffsetDateTime clientCertificateValidUntil;

    /** Creates an instance of AuthenticationEngineWorkerConfigTls class. */
    public AuthenticationEngineWorkerConfigTls() {}

    /**
     * Get the serverRootCaCertificate property: The serverRootCaCertificate property.
     *
     * @return the serverRootCaCertificate value.
     */
    public String getServerRootCaCertificate() {
        return this.serverRootCaCertificate;
    }

    /**
     * Get the clientCertificate property: The clientCertificate property.
     *
     * @return the clientCertificate value.
     */
    public String getClientCertificate() {
        return this.clientCertificate;
    }

    /**
     * Get the clientPrivateKey property: The clientPrivateKey property.
     *
     * @return the clientPrivateKey value.
     */
    public String getClientPrivateKey() {
        return this.clientPrivateKey;
    }

    /**
     * Get the clientCertificateValidFrom property: The clientCertificateValidFrom property.
     *
     * @return the clientCertificateValidFrom value.
     */
    public OffsetDateTime getClientCertificateValidFrom() {
        return this.clientCertificateValidFrom;
    }

    /**
     * Get the clientCertificateValidUntil property: The clientCertificateValidUntil property.
     *
     * @return the clientCertificateValidUntil value.
     */
    public OffsetDateTime getClientCertificateValidUntil() {
        return this.clientCertificateValidUntil;
    }
}
