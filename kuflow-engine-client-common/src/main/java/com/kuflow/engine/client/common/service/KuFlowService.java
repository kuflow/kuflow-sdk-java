/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.service;

import com.kuflow.rest.client.controller.ProcessApi;
import com.kuflow.rest.client.controller.TaskApi;
import com.kuflow.rest.client.resource.AssignTaskCommandResource;
import com.kuflow.rest.client.resource.ChangeProcessInitiatorCommandResource;
import com.kuflow.rest.client.resource.DeleteElementCommandResource;
import com.kuflow.rest.client.resource.DeleteElementValueDocumentCommandResource;
import com.kuflow.rest.client.resource.LogResource;
import com.kuflow.rest.client.resource.ProcessResource;
import com.kuflow.rest.client.resource.SaveElementValueDocumentCommandResource;
import com.kuflow.rest.client.resource.SaveProcessUserActionValueDocumentCommandResource;
import com.kuflow.rest.client.resource.TaskResource;
import com.kuflow.rest.client.resource.TaskSaveElementCommandResource;
import java.io.File;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.springframework.stereotype.Service;

@Service
public class KuFlowService {

    private final ProcessApi processApi;

    private final TaskApi taskApi;

    public KuFlowService(TaskApi taskApi, ProcessApi processApi) {
        this.taskApi = taskApi;
        this.processApi = processApi;
    }

    public ProcessResource retrieveProcess(@Nonnull UUID processId) {
        return this.processApi.retrieveProcess(processId);
    }

    public ProcessResource completeProcess(@Nonnull UUID processId) {
        return this.processApi.actionsCompleteProcess(processId);
    }

    public ProcessResource changeProcessInitiator(@Nonnull UUID processId, @Nullable String email, @Nullable UUID principalId) {
        ChangeProcessInitiatorCommandResource command = new ChangeProcessInitiatorCommandResource();
        command.setEmail(email);
        command.setPrincipalId(principalId);

        return this.processApi.actionsChangeProcessInitiator(processId, command);
    }

    public ProcessResource saveProcessUserActionValueDocument(
        @Nonnull UUID processId,
        @Nonnull SaveProcessUserActionValueDocumentCommandResource json,
        @Nonnull File file
    ) {
        return this.processApi.actionsSaveProcessUserActionValueDocument(processId, json, file);
    }

    public TaskResource retrieveTask(@Nonnull UUID taskId) {
        return this.taskApi.retrieveTask(taskId);
    }

    public TaskResource createTask(@Nonnull TaskResource taskResource) {
        return this.taskApi.createTask(taskResource);
    }

    public TaskResource claimTask(@Nonnull UUID taskId) {
        return this.taskApi.actionsClaimTask(taskId);
    }

    public TaskResource assignTask(@Nonnull UUID taskId, @Nullable String email, @Nullable UUID principalId) {
        AssignTaskCommandResource command = new AssignTaskCommandResource();
        command.setEmail(email);
        command.setPrincipalId(principalId);

        return this.taskApi.actionsAssignTask(taskId, command);
    }

    public TaskResource saveTaskElement(@Nonnull UUID taskId, TaskSaveElementCommandResource command) {
        return this.taskApi.actionsSaveTaskElement(taskId, command);
    }

    public TaskResource saveTaskElementValueDocument(@Nonnull UUID taskId, @Nonnull String code, @Nonnull File file) {
        return this.saveTaskElementValueDocument(taskId, code, Boolean.TRUE, file);
    }

    public TaskResource saveTaskElementValueDocument(
        @Nonnull UUID taskId,
        @Nonnull String code,
        @Nullable Boolean valid,
        @Nonnull File file
    ) {
        SaveElementValueDocumentCommandResource command = new SaveElementValueDocumentCommandResource();
        command.setCode(code);
        command.setValid(valid);

        return this.taskApi.actionsSaveTaskElementValueDocument(taskId, command, file);
    }

    public TaskResource saveTaskElementValueDocument(@Nonnull UUID taskId, SaveElementValueDocumentCommandResource json, File file) {
        return this.taskApi.actionsSaveTaskElementValueDocument(taskId, json, file);
    }

    public TaskResource deleteTaskElement(@Nonnull UUID taskId, @Nonnull String code) {
        DeleteElementCommandResource command = new DeleteElementCommandResource();
        command.setCode(code);

        return this.taskApi.actionsDeleteTaskElement(taskId, command);
    }

    public TaskResource deleteTaskElementValueDocument(@Nonnull UUID taskId, @Nonnull UUID documentId) {
        DeleteElementValueDocumentCommandResource command = new DeleteElementValueDocumentCommandResource();
        command.setDocumentId(documentId);

        return this.taskApi.actionsDeleteTaskElementValueDocument(taskId, command);
    }

    public TaskResource completeTask(@Nonnull UUID taskId) {
        return this.taskApi.actionsCompleteTask(taskId);
    }

    public TaskResource appendTaskLog(UUID taskId, LogResource logResource) {
        return this.taskApi.actionsAppendTaskLog(taskId, logResource);
    }
}
