/*
 * The MIT License
 * Copyright © 2021-present KuFlow S.L.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.kuflow.rest.model;

import static java.util.Collections.unmodifiableList;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class FindTasksOptions {

    /**
     * The number of records returned within a single API call.
     */
    private Integer size;

    /**
     * The page number of the current page in the returned records, 0 is the first page.
     */
    private Integer page;

    /**
     * Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     * <p>Default sort order is ascending. Multiple sort criteria are supported.
     * <p>Please refer to the method description for supported properties.
     */
    private List<String> sorts = new LinkedList<>();

    /**
     * Filter by an array of process ids.
     */
    private List<UUID> processIds = new LinkedList<>();

    /**
     * Filter by an array of task states.
     */
    private List<TaskState> states = new LinkedList<>();

    /**
     * Filter by an array of task definition codes.
     */
    private List<String> taskDefinitionCodes = new LinkedList<>();

    public Integer getSize() {
        return this.size;
    }

    public FindTasksOptions setSize(Integer size) {
        this.size = size;

        return this;
    }

    public Integer getPage() {
        return this.page;
    }

    public FindTasksOptions setPage(Integer page) {
        this.page = page;

        return this;
    }

    public List<String> getSorts() {
        if (this.sorts == null) {
            this.sorts = new LinkedList<>();
        }

        return unmodifiableList(this.sorts);
    }

    public FindTasksOptions setSorts(List<String> sorts) {
        this.sorts.clear();
        if (sorts != null) {
            this.sorts.addAll(sorts);
        }

        return this;
    }

    public FindTasksOptions addSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");
        if (!this.sorts.contains(sort)) {
            this.sorts.add(sort);
        }

        return this;
    }

    public FindTasksOptions removeSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");
        this.sorts.remove(sort);

        return this;
    }

    public List<UUID> getProcessIds() {
        if (this.processIds == null) {
            this.processIds = new LinkedList<>();
        }

        return unmodifiableList(this.processIds);
    }

    public FindTasksOptions setProcessIds(List<UUID> processIds) {
        this.processIds.clear();
        if (processIds != null) {
            this.processIds.addAll(processIds);
        }

        return this;
    }

    public FindTasksOptions addProcessId(UUID processId) {
        Objects.requireNonNull(processId, "'processId' is required");
        if (!this.processIds.contains(processId)) {
            this.processIds.add(processId);
        }

        return this;
    }

    public FindTasksOptions removeProcessId(UUID processId) {
        Objects.requireNonNull(processId, "'processId' is required");
        this.processIds.remove(processId);

        return this;
    }

    public List<TaskState> getStates() {
        if (this.states == null) {
            this.states = new LinkedList<>();
        }

        return unmodifiableList(this.states);
    }

    public FindTasksOptions setStates(List<TaskState> states) {
        this.states.clear();
        if (states != null) {
            this.states.addAll(states);
        }

        return this;
    }

    public FindTasksOptions addState(TaskState state) {
        Objects.requireNonNull(state, "'state' is required");
        if (!this.states.contains(state)) {
            this.states.add(state);
        }

        return this;
    }

    public FindTasksOptions removeState(TaskState state) {
        Objects.requireNonNull(state, "'state' is required");
        this.states.remove(state);

        return this;
    }

    public List<String> getTaskDefinitionCodes() {
        if (this.taskDefinitionCodes == null) {
            this.taskDefinitionCodes = new LinkedList<>();
        }

        return unmodifiableList(this.taskDefinitionCodes);
    }

    public FindTasksOptions setTaskDefinitionCodes(List<String> taskDefinitionCodes) {
        this.taskDefinitionCodes.clear();
        if (taskDefinitionCodes != null) {
            this.taskDefinitionCodes.addAll(taskDefinitionCodes);
        }

        return this;
    }

    public FindTasksOptions addTaskDefinitionCode(String taskDefinitionCode) {
        Objects.requireNonNull(taskDefinitionCode, "'taskDefinitionCode' is required");
        if (!this.taskDefinitionCodes.contains(taskDefinitionCode)) {
            this.taskDefinitionCodes.add(taskDefinitionCode);
        }

        return this;
    }

    public FindTasksOptions removeTaskDefinitionCode(String taskDefinitionCode) {
        Objects.requireNonNull(taskDefinitionCode, "'taskDefinitionCode' is required");
        this.taskDefinitionCodes.remove(taskDefinitionCode);

        return this;
    }
}
