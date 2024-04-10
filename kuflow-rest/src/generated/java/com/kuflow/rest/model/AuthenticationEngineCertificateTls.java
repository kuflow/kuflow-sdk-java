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
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The AuthenticationEngineCertificateTls model.
 */
@Fluent
public final class AuthenticationEngineCertificateTls implements JsonSerializable<AuthenticationEngineCertificateTls> {

    /*
     * The serverRootCaCertificate property.
     */
    private String serverRootCaCertificate;

    /*
     * The clientCertificate property.
     */
    private String clientCertificate;

    /*
     * The clientPrivateKey property.
     */
    private String clientPrivateKey;

    /**
     * Creates an instance of AuthenticationEngineCertificateTls class.
     */
    public AuthenticationEngineCertificateTls() {}

    /**
     * Get the serverRootCaCertificate property: The serverRootCaCertificate property.
     *
     * @return the serverRootCaCertificate value.
     */
    public String getServerRootCaCertificate() {
        return this.serverRootCaCertificate;
    }

    /**
     * Set the serverRootCaCertificate property: The serverRootCaCertificate property.
     *
     * @param serverRootCaCertificate the serverRootCaCertificate value to set.
     * @return the AuthenticationEngineCertificateTls object itself.
     */
    public AuthenticationEngineCertificateTls setServerRootCaCertificate(String serverRootCaCertificate) {
        this.serverRootCaCertificate = serverRootCaCertificate;
        return this;
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
     * Set the clientCertificate property: The clientCertificate property.
     *
     * @param clientCertificate the clientCertificate value to set.
     * @return the AuthenticationEngineCertificateTls object itself.
     */
    public AuthenticationEngineCertificateTls setClientCertificate(String clientCertificate) {
        this.clientCertificate = clientCertificate;
        return this;
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
     * Set the clientPrivateKey property: The clientPrivateKey property.
     *
     * @param clientPrivateKey the clientPrivateKey value to set.
     * @return the AuthenticationEngineCertificateTls object itself.
     */
    public AuthenticationEngineCertificateTls setClientPrivateKey(String clientPrivateKey) {
        this.clientPrivateKey = clientPrivateKey;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("serverRootCaCertificate", this.serverRootCaCertificate);
        jsonWriter.writeStringField("clientCertificate", this.clientCertificate);
        jsonWriter.writeStringField("clientPrivateKey", this.clientPrivateKey);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of AuthenticationEngineCertificateTls from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of AuthenticationEngineCertificateTls if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the AuthenticationEngineCertificateTls.
     */
    public static AuthenticationEngineCertificateTls fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            AuthenticationEngineCertificateTls deserializedAuthenticationEngineCertificateTls = new AuthenticationEngineCertificateTls();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("serverRootCaCertificate".equals(fieldName)) {
                    deserializedAuthenticationEngineCertificateTls.serverRootCaCertificate = reader.getString();
                } else if ("clientCertificate".equals(fieldName)) {
                    deserializedAuthenticationEngineCertificateTls.clientCertificate = reader.getString();
                } else if ("clientPrivateKey".equals(fieldName)) {
                    deserializedAuthenticationEngineCertificateTls.clientPrivateKey = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedAuthenticationEngineCertificateTls;
        });
    }
}
