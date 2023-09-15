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

/** The AuthenticationEngineCertificate model. */
@Fluent
public final class AuthenticationEngineCertificate {
    /*
     * The namespace property.
     */
    @JsonProperty(value = "namespace", required = true)
    private String namespace;

    /*
     * The tls property.
     */
    @JsonProperty(value = "tls", required = true)
    private AuthenticationEngineCertificateTls tls;

    /** Creates an instance of AuthenticationEngineCertificate class. */
    public AuthenticationEngineCertificate() {}

    /**
     * Get the namespace property: The namespace property.
     *
     * @return the namespace value.
     */
    public String getNamespace() {
        return this.namespace;
    }

    /**
     * Set the namespace property: The namespace property.
     *
     * @param namespace the namespace value to set.
     * @return the AuthenticationEngineCertificate object itself.
     */
    public AuthenticationEngineCertificate setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    /**
     * Get the tls property: The tls property.
     *
     * @return the tls value.
     */
    public AuthenticationEngineCertificateTls getTls() {
        return this.tls;
    }

    /**
     * Set the tls property: The tls property.
     *
     * @param tls the tls value to set.
     * @return the AuthenticationEngineCertificate object itself.
     */
    public AuthenticationEngineCertificate setTls(AuthenticationEngineCertificateTls tls) {
        this.tls = tls;
        return this;
    }
}
