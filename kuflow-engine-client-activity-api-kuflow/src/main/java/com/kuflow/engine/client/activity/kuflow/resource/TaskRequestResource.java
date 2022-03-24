/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.kuflow.resource;

import com.kuflow.engine.client.common.resource.AbstractResource;
import com.kuflow.rest.client.resource.ElementValueWrapperResource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TaskRequestResource extends AbstractResource {

    private UUID taskId;

    private UUID processId;

    private String taskDefinitionCode;

    private Map<String, ElementValueWrapperResource> elementValues = new HashMap<>();

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

    public Map<String, ElementValueWrapperResource> getElementValues() {
        return this.elementValues;
    }

    public void setElementValues(Map<String, ElementValueWrapperResource> elementValues) {
        this.elementValues = elementValues;
    }

    public void putElementValue(String key, ElementValueWrapperResource elementValue) {
        if (this.elementValues == null) {
            this.elementValues = new HashMap<>();
        }

        this.elementValues.put(key, elementValue);
    }
}
