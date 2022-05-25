/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.service;

import com.kuflow.rest.client.controller.ProcessApi;
import com.kuflow.rest.client.controller.TaskApi;
import com.kuflow.rest.client.resource.AssignTaskCommandResource;
import com.kuflow.rest.client.resource.DeleteElementCommandResource;
import com.kuflow.rest.client.resource.DeleteElementValueDocumentCommandResource;
import com.kuflow.rest.client.resource.LogResource;
import com.kuflow.rest.client.resource.ProcessResource;
import com.kuflow.rest.client.resource.SaveElementValueDocumentCommandResource;
import com.kuflow.rest.client.resource.TaskElementValueOrArrayValueResource;
import com.kuflow.rest.client.resource.TaskResource;
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

    public TaskResource actionsSaveElement(@Nonnull UUID taskId, TaskElementValueOrArrayValueResource elementValueOrArrayValueResource) {
        return this.taskApi.actionsSaveElement(taskId, elementValueOrArrayValueResource);
    }

    public TaskResource actionsSaveElementDocument(@Nonnull UUID taskId, @Nonnull String code, @Nonnull File file) {
        return this.actionsSaveElementDocument(taskId, code, Boolean.TRUE, file);
    }

    public TaskResource actionsSaveElementDocument(
        @Nonnull UUID taskId,
        @Nonnull String code,
        @Nullable Boolean valid,
        @Nonnull File file
    ) {
        SaveElementValueDocumentCommandResource command = new SaveElementValueDocumentCommandResource();
        command.setCode(code);
        command.setValid(valid);

        return this.taskApi.actionsSaveElementValueDocument(taskId, command, file);
    }

    public TaskResource actionsSaveElementDocument(@Nonnull UUID taskId, SaveElementValueDocumentCommandResource json, File file) {
        return this.taskApi.actionsSaveElementValueDocument(taskId, json, file);
    }

    public TaskResource actionsDeleteElement(@Nonnull UUID taskId, @Nonnull String code) {
        DeleteElementCommandResource command = new DeleteElementCommandResource();
        command.setCode(code);

        return this.taskApi.actionsDeleteElement(taskId, command);
    }

    public TaskResource actionsDeleteDocument(@Nonnull UUID taskId, @Nonnull String documentId) {
        DeleteElementValueDocumentCommandResource command = new DeleteElementValueDocumentCommandResource();
        command.setDocumentId(documentId);

        return this.taskApi.actionsDeleteValueDocument(taskId, command);
    }

    public TaskResource completeTask(@Nonnull UUID taskId) {
        return this.taskApi.actionsCompleteTask(taskId);
    }

    public TaskResource appendLog(UUID taskId, LogResource logResource) {
        return this.taskApi.actionsAppendLog(taskId, logResource);
    }
}
