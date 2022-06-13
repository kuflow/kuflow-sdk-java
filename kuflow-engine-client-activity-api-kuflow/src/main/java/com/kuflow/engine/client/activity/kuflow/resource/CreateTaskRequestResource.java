/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.kuflow.resource;

import com.kuflow.engine.client.common.resource.AbstractResource;
import com.kuflow.rest.client.resource.PrincipalResource;
import com.kuflow.rest.client.resource.TaskElementValueWrapperResource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CreateTaskRequestResource extends AbstractResource {

    private UUID taskId;

    private UUID processId;

    private String taskDefinitionCode;

    private Map<String, TaskElementValueWrapperResource> elementValues = new HashMap<>();

    private PrincipalResource owner;

    public UUID getTaskId() {
        return this.taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public UUID getProcessId() {
        return this.processId;
    }

    public void setProcessId(UUID processId) {
        this.processId = processId;
    }

    public String getTaskDefinitionCode() {
        return this.taskDefinitionCode;
    }

    public void setTaskDefinitionCode(String code) {
        this.taskDefinitionCode = code;
    }

    public Map<String, TaskElementValueWrapperResource> getElementValues() {
        return this.elementValues;
    }

    public void setElementValues(Map<String, TaskElementValueWrapperResource> elementValues) {
        this.elementValues = elementValues;
    }

    public void putElementValuesItem(String key, TaskElementValueWrapperResource elementValue) {
        if (this.elementValues == null) {
            this.elementValues = new HashMap<>();
        }

        this.elementValues.put(key, elementValue);
    }

    public PrincipalResource getOwner() {
        return this.owner;
    }

    public void setOwner(PrincipalResource owner) {
        this.owner = owner;
    }
}
