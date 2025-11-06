/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.temporal.activity.email;


import com.kuflow.common.Experimental;
import com.kuflow.temporal.activity.email.model.DataSourceQueryRequest;
import com.kuflow.temporal.activity.email.model.DataSourceQueryResponse;
import com.kuflow.temporal.activity.email.model.DataSourceValidateValueRequest;
import com.kuflow.temporal.activity.email.model.DataSourceValidateValueResponse;
import io.temporal.activity.ActivityInterface;
import io.temporal.workflow.WorkflowMethod;

@Experimental
@ActivityInterface(namePrefix = "KuFlow_DataSource_")
public interface DataSourceActivities {
    @WorkflowMethod
    DataSourceQueryResponse runQuery(DataSourceQueryRequest request);

    @WorkflowMethod
    DataSourceValidateValueResponse validateValue(DataSourceValidateValueRequest request);
}
