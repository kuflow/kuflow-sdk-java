/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.temporal.activity.email.model;

import com.kuflow.common.Experimental;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Request DTO for data source validation operations.
 * This is sent to integrator-implemented workflows for validating data source values.
 */
@Experimental
public class DataSourceValidateValueRequest extends DataSourceActivityRequest {

    private final List<Object> values = new LinkedList<>();

    public List<Object> getValues() {
        return Collections.unmodifiableList(this.values);
    }

    public void setValues(@Nullable List<Object> values) {
        this.values.clear();
        if (values != null) {
            this.values.addAll(values);
        }
    }
}
