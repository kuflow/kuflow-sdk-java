/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.kuflow;

import com.kuflow.engine.client.activity.kuflow.resource.CompleteProcessRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.CompleteProcessResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.LogRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.LogResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.StartProcessRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.StartProcessResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskClaimRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskClaimResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskCompleteRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskCompleteResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskResponseResource;
import com.kuflow.engine.client.common.service.KuFlowService;
import com.kuflow.engine.client.common.util.TemporalUtils;
import com.kuflow.rest.client.resource.LogResource;
import com.kuflow.rest.client.resource.ProcessResource;
import com.kuflow.rest.client.resource.TaskResource;
import com.kuflow.rest.client.resource.TasksDefinitionSummaryResource;
import io.temporal.activity.Activity;
import io.temporal.activity.ActivityExecutionContext;
import javax.annotation.Nonnull;
import org.springframework.stereotype.Component;

@Component
public class KuFlowActivitiesFacade implements KuFlowActivities {

    private final KuFlowService kuFlowService;

    public KuFlowActivitiesFacade(KuFlowService kuflowService) {
        this.kuFlowService = kuflowService;
    }

    @Nonnull
    @Override
    public StartProcessResponseResource startProcess(@Nonnull StartProcessRequestResource request) {
        ProcessResource processResource = this.kuFlowService.startProcess(request.getProcessId());

        StartProcessResponseResource response = new StartProcessResponseResource();
        response.setProcess(processResource);

        return response;
    }

    @Nonnull
    @Override
    public CompleteProcessResponseResource completeProcess(@Nonnull CompleteProcessRequestResource request) {
        this.kuFlowService.completeProcess(request.getProcessId());

        CompleteProcessResponseResource response = new CompleteProcessResponseResource();
        response.setMessage(String.format("Workflow for the process %s completed", request.getProcessId()));

        return response;
    }

    @Nonnull
    @Override
    public TaskResponseResource createTask(@Nonnull TaskRequestResource request) {
        TasksDefinitionSummaryResource taskDefinition = new TasksDefinitionSummaryResource();
        taskDefinition.setCode(request.getTaskDefinitionCode());

        TaskResource taskResource = new TaskResource();
        taskResource.setProcessId(request.getProcessId());
        taskResource.setTaskDefinition(taskDefinition);
        taskResource.setId(request.getTaskId());
        taskResource.setElementValues(request.getElementValues());

        taskResource = this.kuFlowService.createTask(taskResource);

        TaskResponseResource response = new TaskResponseResource();
        response.setTask(taskResource);

        return response;
    }

    @Nonnull
    @Override
    public TaskResponseResource createTaskAndWaitTermination(@Nonnull TaskRequestResource request) {
        ActivityExecutionContext context = Activity.getExecutionContext();
        String temporalToken = TemporalUtils.getTemporalTokenAsString(context);

        TasksDefinitionSummaryResource taskDefinition = new TasksDefinitionSummaryResource();
        taskDefinition.setCode(request.getTaskDefinitionCode());

        TaskResource taskResource = new TaskResource();
        taskResource.setProcessId(request.getProcessId());
        taskResource.setTaskDefinition(taskDefinition);
        taskResource.setId(request.getTaskId());
        taskResource.setActivityToken(temporalToken);
        taskResource.setActivityResponseVersion("v1");
        taskResource.setElementValues(request.getElementValues());

        this.kuFlowService.createTask(taskResource);

        context.doNotCompleteOnReturn();

        return null;
    }

    @Nonnull
    @Override
    public TaskCompleteResponseResource completeTask(@Nonnull TaskCompleteRequestResource request) {
        this.kuFlowService.completeTask(request.getTaskId());

        TaskCompleteResponseResource response = new TaskCompleteResponseResource();
        response.setTaskId(request.getTaskId());

        return response;
    }

    @Nonnull
    @Override
    public TaskClaimResponseResource claimTask(@Nonnull TaskClaimRequestResource request) {
        this.kuFlowService.claimTask(request.getTaskId());

        TaskClaimResponseResource response = new TaskClaimResponseResource();
        response.setTaskId(request.getTaskId());

        return response;
    }

    @Nonnull
    @Override
    public LogResponseResource appendLog(@Nonnull LogRequestResource request) {
        LogResource logResource = new LogResource();
        logResource.setId(request.getLogId());
        logResource.setLevel(request.getLevel());
        logResource.setMessage(request.getMessage());

        TaskResource taskResource = this.kuFlowService.appendLog(request.getTaskId(), logResource);

        LogResponseResource response = new LogResponseResource();
        response.setTask(taskResource);

        return response;
    }
}
