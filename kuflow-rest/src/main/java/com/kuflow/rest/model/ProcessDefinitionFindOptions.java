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

public class ProcessDefinitionFindOptions {

    private Integer size;

    private Integer page;

    private final List<String> sorts = new LinkedList<>();

    private final List<UUID> tenantIds = new LinkedList<>();

    private final List<String> processDefinitionCodes = new LinkedList<>();

    private final List<ProcessDefinitionState> states = new LinkedList<>();

    private String name;

    public Integer getSize() {
        return this.size;
    }

    public ProcessDefinitionFindOptions setSize(Integer size) {
        this.size = size;

        return this;
    }

    public Integer getPage() {
        return this.page;
    }

    public ProcessDefinitionFindOptions setPage(Integer page) {
        this.page = page;

        return this;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Only the process definitions whose name has every word of this text, in any order, without regard to case or
     * accents.
     */
    public ProcessDefinitionFindOptions setName(String name) {
        this.name = name;

        return this;
    }

    public List<String> getSorts() {
        return unmodifiableList(this.sorts);
    }

    public ProcessDefinitionFindOptions setSorts(List<String> sorts) {
        this.sorts.clear();
        if (sorts != null) {
            this.sorts.addAll(sorts);
        }

        return this;
    }

    public ProcessDefinitionFindOptions setSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");

        return this.setSorts(List.of(sort));
    }

    public ProcessDefinitionFindOptions addSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");
        if (!this.sorts.contains(sort)) {
            this.sorts.add(sort);
        }

        return this;
    }

    public ProcessDefinitionFindOptions removeSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");
        this.sorts.remove(sort);

        return this;
    }

    public List<UUID> getTenantIds() {
        return unmodifiableList(this.tenantIds);
    }

    public ProcessDefinitionFindOptions setTenantIds(List<UUID> tenantIds) {
        this.tenantIds.clear();
        if (tenantIds != null) {
            this.tenantIds.addAll(tenantIds);
        }

        return this;
    }

    public ProcessDefinitionFindOptions setTenantId(UUID tenantId) {
        Objects.requireNonNull(tenantId, "'tenantId' is required");

        return this.setTenantIds(List.of(tenantId));
    }

    public ProcessDefinitionFindOptions addTenantId(UUID tenantId) {
        Objects.requireNonNull(tenantId, "'tenantId' is required");
        if (!this.tenantIds.contains(tenantId)) {
            this.tenantIds.add(tenantId);
        }

        return this;
    }

    public ProcessDefinitionFindOptions removeTenantId(UUID tenantId) {
        Objects.requireNonNull(tenantId, "'tenantId' is required");
        this.tenantIds.remove(tenantId);

        return this;
    }

    public List<String> getProcessDefinitionCodes() {
        return unmodifiableList(this.processDefinitionCodes);
    }

    public ProcessDefinitionFindOptions setProcessDefinitionCodes(List<String> processDefinitionCodes) {
        this.processDefinitionCodes.clear();
        if (processDefinitionCodes != null) {
            this.processDefinitionCodes.addAll(processDefinitionCodes);
        }

        return this;
    }

    public ProcessDefinitionFindOptions setProcessDefinitionCode(String processDefinitionCode) {
        Objects.requireNonNull(processDefinitionCode, "'processDefinitionCode' is required");

        return this.setProcessDefinitionCodes(List.of(processDefinitionCode));
    }

    public ProcessDefinitionFindOptions addProcessDefinitionCode(String processDefinitionCode) {
        Objects.requireNonNull(processDefinitionCode, "'processDefinitionCode' is required");
        if (!this.processDefinitionCodes.contains(processDefinitionCode)) {
            this.processDefinitionCodes.add(processDefinitionCode);
        }

        return this;
    }

    public ProcessDefinitionFindOptions removeProcessDefinitionCode(String processDefinitionCode) {
        Objects.requireNonNull(processDefinitionCode, "'processDefinitionCode' is required");
        this.processDefinitionCodes.remove(processDefinitionCode);

        return this;
    }

    public List<ProcessDefinitionState> getStates() {
        return unmodifiableList(this.states);
    }

    public ProcessDefinitionFindOptions setStates(List<ProcessDefinitionState> states) {
        this.states.clear();
        if (states != null) {
            this.states.addAll(states);
        }

        return this;
    }

    public ProcessDefinitionFindOptions setState(ProcessDefinitionState state) {
        Objects.requireNonNull(state, "'state' is required");

        return this.setStates(List.of(state));
    }

    public ProcessDefinitionFindOptions addState(ProcessDefinitionState state) {
        Objects.requireNonNull(state, "'state' is required");
        if (!this.states.contains(state)) {
            this.states.add(state);
        }

        return this;
    }

    public ProcessDefinitionFindOptions removeState(ProcessDefinitionState state) {
        Objects.requireNonNull(state, "'state' is required");
        this.states.remove(state);

        return this;
    }
}
