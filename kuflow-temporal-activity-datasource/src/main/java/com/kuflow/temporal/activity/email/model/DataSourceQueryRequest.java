/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.temporal.activity.email.model;

import com.kuflow.common.Experimental;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Request DTO for data source query operations.
 * This is sent to integrator-implemented workflows.
 * <p>
 * The context map will contain:
 * - tenantId: UUID as String
 * - processId: UUID as String (optional)
 * - Any additional context data
 */
@Experimental
public class DataSourceQueryRequest extends DataSourceActivityRequest {

    private String query;

    private Integer pageNumber;

    private Integer pageSize;

    private Map<String, Object> context;

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }

    public Integer getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Object> getContext() {
        if (this.context == null) {
            return null;
        }
        return Collections.unmodifiableMap(this.context);
    }

    public void setContext(Map<String, Object> context) {
        if (context == null) {
            this.context = null;
        } else {
            this.context = new HashMap<>(context);
        }
    }

    public void putContext(String key, Object value) {
        if (this.context == null) {
            this.context = new HashMap<>();
        }
        this.context.put(key, value);
    }
}
