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
package com.kuflow.spring.boot.autoconfigure.properties;

import com.azure.core.http.policy.HttpLogDetailLevel;
import com.kuflow.spring.boot.autoconfigure.validation.NotFillMeAttributesValues;
import java.time.Duration;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "kuflow.api")
@Validated
@NotFillMeAttributesValues
public class KuFlowRestClientProperties {

    @URL
    private String endpoint;

    @NotNull
    private String clientId;

    @NotNull
    private String clientSecret;

    private HttpLogDetailLevel loggerLevel = HttpLogDetailLevel.NONE;

    private Duration connectTimeout;

    private Duration readTimeout;

    public String getEndpoint() {
        return this.endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     * @deprecated use {@link #clientId}
     * @param applicationId the client id
     */
    @Deprecated
    public void setApplicationId(String applicationId) {
        this.clientId = applicationId;
    }

    /**
     * @deprecated use {@link #clientSecret}
     * @param token the client secret
     */
    public void setToken(String token) {
        this.clientSecret = token;
    }

    public HttpLogDetailLevel getLoggerLevel() {
        return this.loggerLevel;
    }

    public void setLoggerLevel(HttpLogDetailLevel loggerLevel) {
        this.loggerLevel = loggerLevel;
    }

    public Duration getConnectTimeout() {
        return this.connectTimeout;
    }

    public void setConnectTimeout(Duration connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Duration getReadTimeout() {
        return this.readTimeout;
    }

    public void setReadTimeout(Duration readTimeout) {
        this.readTimeout = readTimeout;
    }
}
