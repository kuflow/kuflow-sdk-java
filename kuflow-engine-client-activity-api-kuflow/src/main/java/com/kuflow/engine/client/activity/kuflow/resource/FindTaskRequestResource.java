/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.kuflow.resource;

import com.kuflow.engine.client.common.resource.AbstractResource;
import com.kuflow.rest.client.resource.TaskStateResource;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.springframework.util.Assert;

public class FindTaskRequestResource extends AbstractResource {

    private Integer page;

    private Integer size;

    private List<String> sort;

    private List<UUID> processIds;

    private List<TaskStateResource> states;

    private List<String> taskDefinitionCodes;

    public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<String> getSort() {
        return Collections.unmodifiableList(this.sort);
    }

    public void setSort(List<String> sort) {
        Assert.notNull(sort, "Sort is required");
        this.sort.clear();
        this.sort.addAll(sort);
    }

    public List<UUID> getProcessIds() {
        return Collections.unmodifiableList(this.processIds);
    }

    public void setProcessIds(List<UUID> processIds) {
        Assert.notNull(processIds, "ProcessIds is required");
        this.processIds.clear();
        this.processIds.addAll(processIds);
    }

    public List<TaskStateResource> getStates() {
        return Collections.unmodifiableList(this.states);
    }

    public void setStates(List<TaskStateResource> states) {
        Assert.notNull(states, "States is required");
        this.states.clear();
        this.states.addAll(states);
    }

    public List<String> getTaskDefinitionCodes() {
        return Collections.unmodifiableList(this.taskDefinitionCodes);
    }

    public void setTaskDefinitionCodes(List<String> taskDefinitionCodes) {
        Assert.notNull(taskDefinitionCodes, "TaskDefinitionCodes is required");
        this.taskDefinitionCodes.clear();
        this.taskDefinitionCodes.addAll(taskDefinitionCodes);
    }
}
