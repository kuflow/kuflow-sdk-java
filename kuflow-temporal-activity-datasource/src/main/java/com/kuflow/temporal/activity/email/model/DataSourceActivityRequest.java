/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.temporal.activity.email.model;

import com.kuflow.common.Experimental;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.util.UUID;

/**
 * Abstract base class for data source activity requests.
 * Contains common properties shared by all data source operations (query, validation, etc.).
 */
@Experimental
public abstract class DataSourceActivityRequest {

    @NotNull
    private UUID tenantId;

    @NotNull
    private String code;

    private String invocationActivityName;

    private Duration invocationTimeout;

    private String invocationTaskQueue;

    public UUID getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInvocationActivityName() {
        return this.invocationActivityName;
    }

    public void setInvocationActivityName(String invocationActivityName) {
        this.invocationActivityName = invocationActivityName;
    }

    public Duration getInvocationTimeout() {
        return this.invocationTimeout;
    }

    public void setInvocationTimeout(Duration invocationTimeout) {
        this.invocationTimeout = invocationTimeout;
    }

    public String getInvocationTaskQueue() {
        return this.invocationTaskQueue;
    }

    public void setInvocationTaskQueue(String invocationTaskQueue) {
        this.invocationTaskQueue = invocationTaskQueue;
    }
}
