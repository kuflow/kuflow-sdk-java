/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.impl.task.service;

import com.kuflow.engine.client.activity.api.task.resource.TaskRequestResource;
import com.kuflow.engine.client.activity.api.task.resource.TaskResponseResource;
import com.kuflow.engine.client.activity.api.task.service.KuFlowDetachedActivities;
import com.kuflow.engine.client.common.api.resource.TaskResource;
import com.kuflow.engine.client.common.api.resource.TasksDefinitionSummaryResource;
import com.kuflow.engine.client.common.service.KuFlowService;
import com.kuflow.engine.client.common.util.TemporalUtils;
import io.temporal.activity.Activity;
import io.temporal.activity.ActivityExecutionContext;
import org.springframework.stereotype.Component;

@Component
public class KuFlowDetachedActivitiesFacadeImpl implements KuFlowDetachedActivities {

    private final KuFlowService kuFlowService;

    public KuFlowDetachedActivitiesFacadeImpl(KuFlowService kuflowService) {
        this.kuFlowService = kuflowService;
    }

    @Override
    public TaskResponseResource createDetachedTask(TaskRequestResource request) {
        ActivityExecutionContext context = Activity.getExecutionContext();
        String temporalToken = TemporalUtils.getTemporalTokenAsString(context);

        TasksDefinitionSummaryResource taskDefinition = new TasksDefinitionSummaryResource();
        taskDefinition.setCode(request.getTaskDefinitionCode());

        TaskResource taskResource = new TaskResource();
        taskResource.setProcessId(request.getProcessId());
        taskResource.setTaskDefinition(taskDefinition);
        taskResource.setId(request.getTaskId());
        taskResource.setActivityToken(temporalToken);
        taskResource.setElementValues(request.getElementValues());

        taskResource = this.kuFlowService.createTask(taskResource);

        context.doNotCompleteOnReturn();

        return null;
    }
}
