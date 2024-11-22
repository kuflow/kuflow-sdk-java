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

public class ProcessItemFindOptions {

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
     * Filter principals that exists in one of tenant ids.
     */
    private final List<UUID> tenantIds = new LinkedList<>();

    /**
     * Filter by an array of process ids.
     */
    private List<UUID> processIds = new LinkedList<>();

    /**
     * Filter by an array of types.
     */
    private List<ProcessItemType> types = new LinkedList<>();

    /**
     * Filter by an array of process item task states.
     */
    private List<ProcessItemTaskState> taskStates = new LinkedList<>();

    /**
     * Filter by an array of process item definition codes.
     */
    private List<String> processItemDefinitionCode = new LinkedList<>();

    public Integer getSize() {
        return this.size;
    }

    public ProcessItemFindOptions setSize(Integer size) {
        this.size = size;

        return this;
    }

    public Integer getPage() {
        return this.page;
    }

    public ProcessItemFindOptions setPage(Integer page) {
        this.page = page;

        return this;
    }

    public List<String> getSorts() {
        if (this.sorts == null) {
            this.sorts = new LinkedList<>();
        }

        return unmodifiableList(this.sorts);
    }

    public ProcessItemFindOptions setSorts(List<String> sorts) {
        this.sorts.clear();
        if (sorts != null) {
            this.sorts.addAll(sorts);
        }

        return this;
    }

    public ProcessItemFindOptions setSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");

        return this.setSorts(List.of(sort));
    }

    public ProcessItemFindOptions addSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");
        if (!this.sorts.contains(sort)) {
            this.sorts.add(sort);
        }

        return this;
    }

    public ProcessItemFindOptions removeSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");
        this.sorts.remove(sort);

        return this;
    }

    public List<UUID> getTenantIds() {
        return unmodifiableList(this.tenantIds);
    }

    public ProcessItemFindOptions setTenantIds(List<UUID> tenantIds) {
        this.tenantIds.clear();
        if (tenantIds != null) {
            this.tenantIds.addAll(tenantIds);
        }

        return this;
    }

    public ProcessItemFindOptions setTenantId(UUID tenantId) {
        Objects.requireNonNull(tenantId, "'tenantId' is required");

        return this.setTenantIds(List.of(tenantId));
    }

    public ProcessItemFindOptions addTenantId(UUID tenantId) {
        Objects.requireNonNull(tenantId, "'tenantId' is required");
        if (!this.tenantIds.contains(tenantId)) {
            this.tenantIds.add(tenantId);
        }

        return this;
    }

    public ProcessItemFindOptions removeTenantId(UUID tenantId) {
        Objects.requireNonNull(tenantId, "'tenantId' is required");
        this.tenantIds.remove(tenantId);

        return this;
    }

    public List<UUID> getProcessIds() {
        if (this.processIds == null) {
            this.processIds = new LinkedList<>();
        }

        return unmodifiableList(this.processIds);
    }

    public ProcessItemFindOptions setProcessIds(List<UUID> processIds) {
        this.processIds.clear();
        if (processIds != null) {
            this.processIds.addAll(processIds);
        }

        return this;
    }

    public ProcessItemFindOptions setProcessId(UUID processId) {
        Objects.requireNonNull(processId, "'processId' is required");

        return this.setProcessIds(List.of(processId));
    }

    public ProcessItemFindOptions addProcessId(UUID processId) {
        Objects.requireNonNull(processId, "'processId' is required");
        if (!this.processIds.contains(processId)) {
            this.processIds.add(processId);
        }

        return this;
    }

    public ProcessItemFindOptions removeProcessId(UUID processId) {
        Objects.requireNonNull(processId, "'processId' is required");
        this.processIds.remove(processId);

        return this;
    }

    public List<ProcessItemType> getTypes() {
        if (this.types == null) {
            this.types = new LinkedList<>();
        }

        return unmodifiableList(this.types);
    }

    public ProcessItemFindOptions setTypes(List<ProcessItemType> types) {
        this.types.clear();
        if (types != null) {
            this.types.addAll(types);
        }

        return this;
    }

    public ProcessItemFindOptions setType(ProcessItemType type) {
        Objects.requireNonNull(type, "'type' is required");

        return this.setTypes(List.of(type));
    }

    public ProcessItemFindOptions addType(ProcessItemType type) {
        Objects.requireNonNull(type, "'type' is required");
        if (!this.types.contains(type)) {
            this.types.add(type);
        }

        return this;
    }

    public ProcessItemFindOptions removeType(ProcessItemType type) {
        Objects.requireNonNull(type, "'type' is required");
        this.types.remove(type);

        return this;
    }

    public List<ProcessItemTaskState> getTaskStates() {
        if (this.taskStates == null) {
            this.taskStates = new LinkedList<>();
        }

        return unmodifiableList(this.taskStates);
    }

    public ProcessItemFindOptions setTaskStates(List<ProcessItemTaskState> taskStates) {
        this.taskStates.clear();
        if (taskStates != null) {
            this.taskStates.addAll(taskStates);
        }

        return this;
    }

    public ProcessItemFindOptions setTaskState(ProcessItemTaskState taskState) {
        Objects.requireNonNull(taskState, "'taskState' is required");

        return this.setTaskStates(List.of(taskState));
    }

    public ProcessItemFindOptions addTaskState(ProcessItemTaskState taskState) {
        Objects.requireNonNull(taskState, "'taskState' is required");
        if (!this.taskStates.contains(taskState)) {
            this.taskStates.add(taskState);
        }

        return this;
    }

    public ProcessItemFindOptions removeTaskState(ProcessItemTaskState taskState) {
        Objects.requireNonNull(taskState, "'taskState' is required");
        this.taskStates.remove(taskState);

        return this;
    }

    public List<String> getProcessItemDefinitionCode() {
        if (this.processItemDefinitionCode == null) {
            this.processItemDefinitionCode = new LinkedList<>();
        }

        return unmodifiableList(this.processItemDefinitionCode);
    }

    public ProcessItemFindOptions setProcessItemDefinitionCode(List<String> processItemDefinitionCode) {
        this.processItemDefinitionCode.clear();
        if (processItemDefinitionCode != null) {
            this.processItemDefinitionCode.addAll(processItemDefinitionCode);
        }

        return this;
    }

    public ProcessItemFindOptions setProcessItemDefinitionCode(String processItemDefinitionCode) {
        Objects.requireNonNull(processItemDefinitionCode, "'processItemDefinitionCode' is required");

        return this.setProcessItemDefinitionCode(List.of(processItemDefinitionCode));
    }

    public ProcessItemFindOptions addProcessItemDefinitionCode(String processItemDefinitionCode) {
        Objects.requireNonNull(processItemDefinitionCode, "'processItemDefinitionCode' is required");
        if (!this.processItemDefinitionCode.contains(processItemDefinitionCode)) {
            this.processItemDefinitionCode.add(processItemDefinitionCode);
        }

        return this;
    }

    public ProcessItemFindOptions removeProcessItemDefinitionCode(String processItemDefinitionCode) {
        Objects.requireNonNull(processItemDefinitionCode, "'processItemDefinitionCode' is required");
        this.processItemDefinitionCode.remove(processItemDefinitionCode);

        return this;
    }
}
