/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.temporal.activity.email.model;

import com.kuflow.common.Experimental;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Response DTO for data source query workflows.
 * This is returned by integrator-implemented workflows.
 */
@Experimental
public class DataSourceQueryResponse {

    private int pageNumber;

    private int pageSize;

    private long totalElements;

    private int totalPages;

    private List<Map<String, Object>> items;

    private String error;

    public int getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public long getTotalElements() {
        return this.totalElements;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Map<String, Object>> getItems() {
        if (this.items == null) {
            return List.of();
        }
        return Collections.unmodifiableList(this.items);
    }

    public void setItems(@Nullable List<Map<String, Object>> items) {
        this.items = null;
        if (items != null) {
            items.forEach(this::addItem);
        }
    }

    public void addItem(Map<String, Object> item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(new LinkedHashMap<>(item));
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
