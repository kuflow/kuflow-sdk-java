/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.kuflow;

import com.kuflow.engine.client.activity.kuflow.resource.CompleteProcessRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.CompleteProcessResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.CreateTaskRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.CreateTaskResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.LogRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.LogResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.RetrieveProcessRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.RetrieveProcessResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.RetrieveTaskRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.RetrieveTaskResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskAssignRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskAssignResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskClaimRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskClaimResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskCompleteRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskCompleteResponseResource;
import com.kuflow.engine.client.common.service.KuFlowService;
import com.kuflow.engine.client.common.util.TemporalUtils;
import com.kuflow.rest.client.resource.LogResource;
import com.kuflow.rest.client.resource.PrincipalResource;
import com.kuflow.rest.client.resource.ProcessResource;
import com.kuflow.rest.client.resource.TaskResource;
import com.kuflow.rest.client.resource.TasksDefinitionSummaryResource;
import io.temporal.activity.Activity;
import io.temporal.activity.ActivityExecutionContext;
import io.temporal.failure.ApplicationFailure;
import javax.annotation.Nonnull;
import org.springframework.stereotype.Component;

@Component
public class KuFlowActivitiesImpl implements KuFlowActivities {

    private final KuFlowService kuFlowService;

    public KuFlowActivitiesImpl(KuFlowService kuflowService) {
        this.kuFlowService = kuflowService;
    }

    @Nonnull
    @Override
    public RetrieveProcessResponseResource retrieveProcess(@Nonnull RetrieveProcessRequestResource request) {
        this.validateRetrieveProcessRequest(request);

        ProcessResource process = this.kuFlowService.retrieveProcess(request.getProcessId());

        RetrieveProcessResponseResource response = new RetrieveProcessResponseResource();
        response.setProcess(process);

        return response;
    }

    @Nonnull
    @Override
    public CompleteProcessResponseResource completeProcess(@Nonnull CompleteProcessRequestResource request) {
        this.validateCompleteProcessRequest(request);

        this.kuFlowService.completeProcess(request.getProcessId());

        CompleteProcessResponseResource response = new CompleteProcessResponseResource();
        response.setMessage(String.format("Workflow for the process %s completed", request.getProcessId()));

        return response;
    }

    @Nonnull
    @Override
    public RetrieveTaskResponseResource retrieveTask(@Nonnull RetrieveTaskRequestResource request) {
        this.validateRetrieveTaskRequest(request);

        TaskResource task = this.kuFlowService.retrieveTask(request.getTaskId());

        RetrieveTaskResponseResource response = new RetrieveTaskResponseResource();
        response.setTask(task);

        return response;
    }

    @Nonnull
    @Override
    public CreateTaskResponseResource createTask(@Nonnull CreateTaskRequestResource request) {
        this.validateTaskRequest(request);

        TasksDefinitionSummaryResource taskDefinition = new TasksDefinitionSummaryResource();
        taskDefinition.setCode(request.getTaskDefinitionCode());

        TaskResource taskResource = new TaskResource();
        taskResource.setProcessId(request.getProcessId());
        taskResource.setTaskDefinition(taskDefinition);
        taskResource.setId(request.getTaskId());
        taskResource.setElementValues(request.getElementValues());

        taskResource = this.kuFlowService.createTask(taskResource);

        CreateTaskResponseResource response = new CreateTaskResponseResource();
        response.setTask(taskResource);

        return response;
    }

    @Nonnull
    @Override
    public CreateTaskResponseResource createTaskAndWaitTermination(@Nonnull CreateTaskRequestResource request) {
        this.validateTaskRequest(request);

        ActivityExecutionContext context = Activity.getExecutionContext();
        String temporalToken = TemporalUtils.getTemporalTokenAsString(context);

        TasksDefinitionSummaryResource taskDefinition = new TasksDefinitionSummaryResource();
        taskDefinition.setCode(request.getTaskDefinitionCode());

        TaskResource taskResource = new TaskResource();
        taskResource.setProcessId(request.getProcessId());
        taskResource.setTaskDefinition(taskDefinition);
        taskResource.setId(request.getTaskId());
        taskResource.setActivityToken(temporalToken);
        taskResource.setActivityResponseVersion("v1.0");
        taskResource.setElementValues(request.getElementValues());
        if (request.getOwnerId() != null) {
            PrincipalResource ownerResource = new PrincipalResource();
            ownerResource.setId(request.getOwnerId());
            taskResource.setOwner(ownerResource);
        }

        this.kuFlowService.createTask(taskResource);

        context.doNotCompleteOnReturn();

        return null;
    }

    @Nonnull
    @Override
    public TaskCompleteResponseResource completeTask(@Nonnull TaskCompleteRequestResource request) {
        this.validateTaskCompleteRequest(request);

        this.kuFlowService.completeTask(request.getTaskId());

        TaskCompleteResponseResource response = new TaskCompleteResponseResource();
        response.setTaskId(request.getTaskId());

        return response;
    }

    @Nonnull
    @Override
    public TaskClaimResponseResource claimTask(@Nonnull TaskClaimRequestResource request) {
        this.validateTaskClaimRequest(request);

        this.kuFlowService.claimTask(request.getTaskId());

        TaskClaimResponseResource response = new TaskClaimResponseResource();
        response.setTaskId(request.getTaskId());

        return response;
    }

    @Nonnull
    @Override
    public TaskAssignResponseResource assignTask(@Nonnull TaskAssignRequestResource request) {
        this.validateTaskAssignRequest(request);

        this.kuFlowService.assignTask(request.getTaskId(), request.getEmail(), request.getPrincipalId());

        TaskAssignResponseResource response = new TaskAssignResponseResource();
        response.setTaskId(request.getTaskId());

        return response;
    }

    @Nonnull
    @Override
    public LogResponseResource appendLog(@Nonnull LogRequestResource request) {
        this.validateLogRequest(request);

        LogResource logResource = new LogResource();
        logResource.setId(request.getLogId());
        logResource.setLevel(request.getLevel());
        logResource.setMessage(request.getMessage());

        TaskResource taskResource = this.kuFlowService.appendLog(request.getTaskId(), logResource);

        LogResponseResource response = new LogResponseResource();
        response.setTask(taskResource);

        return response;
    }

    private void validateRetrieveProcessRequest(RetrieveProcessRequestResource request) {
        if (request.getProcessId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("processId is required", "KuFlowActivities.validation");
        }
    }

    private void validateCompleteProcessRequest(CompleteProcessRequestResource request) {
        if (request.getProcessId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("processId is required", "KuFlowActivities.validation");
        }
    }

    private void validateRetrieveTaskRequest(RetrieveTaskRequestResource request) {
        if (request.getTaskId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("TaskId is required", "KuFlowActivities.validation");
        }
    }

    private void validateTaskRequest(CreateTaskRequestResource request) {
        if (request.getProcessId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("processId is required", "KuFlowActivities.validation");
        }
        if (request.getTaskDefinitionCode() == null) {
            throw ApplicationFailure.newNonRetryableFailure("taskDefinitionCode is required", "KuFlowActivities.validation");
        }
    }

    private void validateTaskCompleteRequest(TaskCompleteRequestResource request) {
        if (request.getTaskId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("taskId is required", "KuFlowActivities.validation");
        }
    }

    private void validateTaskClaimRequest(TaskClaimRequestResource request) {
        if (request.getTaskId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("taskId is required", "KuFlowActivities.validation");
        }
    }

    private void validateTaskAssignRequest(TaskAssignRequestResource request) {
        if (request.getTaskId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("taskId is required", "KuFlowActivities.validation");
        }
        if (request.getEmail() == null && request.getPrincipalId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("email or principalId is required", "KuFlowActivities.validation");
        }
    }

    private void validateLogRequest(LogRequestResource request) {
        if (request.getTaskId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("processId is required", "KuFlowActivities.validation");
        }
        if (request.getLevel() == null) {
            throw ApplicationFailure.newNonRetryableFailure("level is required", "KuFlowActivities.validation");
        }
        if (request.getMessage() == null) {
            throw ApplicationFailure.newNonRetryableFailure("message is required", "KuFlowActivities.validation");
        }
    }
}
