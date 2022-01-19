/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.impl.task.service;

import com.kuflow.engine.client.activity.api.task.resource.CompleteProcessRequestResource;
import com.kuflow.engine.client.activity.api.task.resource.CompleteProcessResponseResource;
import com.kuflow.engine.client.activity.api.task.resource.LogRequestResource;
import com.kuflow.engine.client.activity.api.task.resource.LogResponseResource;
import com.kuflow.engine.client.activity.api.task.resource.StartProcessRequestResource;
import com.kuflow.engine.client.activity.api.task.resource.StartProcessResponseResource;
import com.kuflow.engine.client.activity.api.task.resource.TaskClaimRequestResource;
import com.kuflow.engine.client.activity.api.task.resource.TaskClaimResponseResource;
import com.kuflow.engine.client.activity.api.task.resource.TaskCompleteRequestResource;
import com.kuflow.engine.client.activity.api.task.resource.TaskCompleteResponseResource;
import com.kuflow.engine.client.activity.api.task.resource.TaskRequestResource;
import com.kuflow.engine.client.activity.api.task.resource.TaskResponseResource;
import com.kuflow.engine.client.activity.api.task.service.KuFlowActivities;
import com.kuflow.engine.client.common.api.resource.LogResource;
import com.kuflow.engine.client.common.api.resource.ProcessResource;
import com.kuflow.engine.client.common.api.resource.TaskResource;
import com.kuflow.engine.client.common.api.resource.TasksDefinitionSummaryResource;
import com.kuflow.engine.client.common.service.KuFlowService;
import org.springframework.stereotype.Component;

@Component
public class KuFlowActivitiesFacadeImpl implements KuFlowActivities {

    private final KuFlowService kuFlowService;

    public KuFlowActivitiesFacadeImpl(KuFlowService kuflowService) {
        this.kuFlowService = kuflowService;
    }

    @Override
    public StartProcessResponseResource startProcess(StartProcessRequestResource request) {
        ProcessResource processResource = this.kuFlowService.startProcess(request.getProcessId());

        StartProcessResponseResource response = new StartProcessResponseResource();
        response.setProcess(processResource);

        return response;
    }

    @Override
    public CompleteProcessResponseResource completeProcess(CompleteProcessRequestResource request) {
        this.kuFlowService.completeProcess(request.getProcessId());

        CompleteProcessResponseResource response = new CompleteProcessResponseResource();
        response.setMessage(String.format("Workflow for the process %s completed", request.getProcessId()));

        return response;
    }

    @Override
    public TaskResponseResource createTask(TaskRequestResource request) {
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

    @Override
    public TaskCompleteResponseResource completeTask(TaskCompleteRequestResource request) {
        this.kuFlowService.completeTask(request.getTaskId());

        TaskCompleteResponseResource response = new TaskCompleteResponseResource();
        response.setTaskId(request.getTaskId());

        return response;
    }

    @Override
    public TaskClaimResponseResource claimTask(TaskClaimRequestResource request) {
        this.kuFlowService.claimTask(request.getTaskId());

        TaskClaimResponseResource response = new TaskClaimResponseResource();
        response.setTaskId(request.getTaskId());

        return response;
    }

    @Override
    public LogResponseResource appendLog(LogRequestResource request) {
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
