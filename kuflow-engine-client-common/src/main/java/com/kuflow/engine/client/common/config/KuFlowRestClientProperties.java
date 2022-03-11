/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.config;

import com.kuflow.engine.client.common.validation.NotFillMeAttributesValues;
import com.kuflow.rest.client.KuFlowRestClientProperties.Level;
import java.time.Duration;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "kuflow.api")
@Validated
@NotFillMeAttributesValues
public class KuFlowRestClientProperties {

    @NotNull
    @URL
    private String endpoint;

    @NotNull
    private String applicationId;

    @NotNull
    private String token;

    private Level loggerLevel = Level.NONE;

    private long connectTimeoutMillis = Duration.ofSeconds(10).toMillis();

    private long readTimeoutMillis = Duration.ofSeconds(60).toMillis();

    public String getEndpoint() {
        return this.endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Level getLoggerLevel() {
        return this.loggerLevel;
    }

    public void setLoggerLevel(Level loggerLevel) {
        this.loggerLevel = loggerLevel;
    }

    public long getConnectTimeoutMillis() {
        return this.connectTimeoutMillis;
    }

    public void setConnectTimeoutMillis(long connectTimeoutMillis) {
        this.connectTimeoutMillis = connectTimeoutMillis;
    }

    public long getReadTimeoutMillis() {
        return this.readTimeoutMillis;
    }

    public void setReadTimeoutMillis(long readTimeoutMillis) {
        this.readTimeoutMillis = readTimeoutMillis;
    }
}
