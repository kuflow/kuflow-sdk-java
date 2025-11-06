/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.temporal.activity.email.model;

import com.kuflow.common.Experimental;

import java.util.ArrayList;
import java.util.List;

/**
 * Response DTO for data source query workflows.
 * This is returned by integrator-implemented workflows.
 */
@Experimental
public class DataSourceValidateValueResponse {

    private String error;

    private List<DataSourceValidateValueResult> validations = new ArrayList<>();

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<DataSourceValidateValueResult> getValidations() {
        return this.validations;
    }

    public void setValidations(List<DataSourceValidateValueResult> validations) {
        this.validations = validations;
    }

    public void addValidation(DataSourceValidateValueResult validation) {
        this.validations.add(validation);
    }
}
