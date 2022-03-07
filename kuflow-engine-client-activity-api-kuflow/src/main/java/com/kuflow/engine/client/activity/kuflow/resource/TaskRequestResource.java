/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.kuflow.resource;

import com.kuflow.engine.client.common.resource.AbstractResource;
import com.kuflow.rest.client.resource.ElementValueBaseResource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.springframework.util.Assert;

public class TaskRequestResource extends AbstractResource {

    private UUID taskId;

    private UUID processId;

    private String taskDefinitionCode;

    private List<ElementValueBaseResource> elementValues = new LinkedList<>();

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

    public List<ElementValueBaseResource> getElementValues() {
        return Collections.unmodifiableList(this.elementValues);
    }

    public void setElementValues(List<ElementValueBaseResource> elementValues) {
        Assert.notNull(elementValues, "elementValues is required");
        this.elementValues.clear();
        this.elementValues.addAll(elementValues);
    }

    public void addElementValue(ElementValueBaseResource elementValue) {
        Assert.notNull(elementValue, "elementValue is required");
        this.elementValues.add(elementValue);
    }
}
