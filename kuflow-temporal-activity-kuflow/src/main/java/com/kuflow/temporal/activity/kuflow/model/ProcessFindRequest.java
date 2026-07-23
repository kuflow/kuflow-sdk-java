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

package com.kuflow.temporal.activity.kuflow.model;

import static java.util.Collections.unmodifiableList;

import com.kuflow.rest.util.SearchCriteriaUtils;
import com.kuflow.temporal.common.model.AbstractModel;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ProcessFindRequest extends AbstractModel {

    private Integer page;

    private Integer size;

    private List<String> sorts;

    /**
     * Filter by tenantId.
     */
    private final List<UUID> tenantIds = new LinkedList<>();

    /**
     * Filter by process definition ids.
     */
    private final List<UUID> processDefinitionIds = new LinkedList<>();

    /**
     * Filter by process definition codes.
     */
    private final List<String> processDefinitionCodes = new LinkedList<>();

    /**
     * Filter by indexed metadata field values.
     */
    private final List<String> metadata = new LinkedList<>();

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

    public List<String> getSorts() {
        if (this.sorts == null) {
            this.sorts = new LinkedList<>();
        }

        return unmodifiableList(this.sorts);
    }

    public void setSorts(List<String> sorts) {
        this.sorts.clear();
        if (sorts != null) {
            this.sorts.addAll(sorts);
        }
    }

    public void addSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");
        if (!this.sorts.contains(sort)) {
            this.sorts.add(sort);
        }
    }

    public void removeSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");
        this.sorts.remove(sort);
    }

    public List<UUID> getTenantIds() {
        return unmodifiableList(this.tenantIds);
    }

    public ProcessFindRequest setTenantIds(List<UUID> tenantIds) {
        this.tenantIds.clear();
        if (tenantIds != null) {
            this.tenantIds.addAll(tenantIds);
        }

        return this;
    }

    public ProcessFindRequest setTenantId(UUID tenantId) {
        Objects.requireNonNull(tenantId, "'tenantId' is required");

        return this.setTenantIds(List.of(tenantId));
    }

    public ProcessFindRequest addTenantId(UUID tenantId) {
        Objects.requireNonNull(tenantId, "'tenantId' is required");
        if (!this.tenantIds.contains(tenantId)) {
            this.tenantIds.add(tenantId);
        }

        return this;
    }

    public ProcessFindRequest removeTenantId(UUID tenantId) {
        Objects.requireNonNull(tenantId, "'tenantId' is required");
        this.tenantIds.remove(tenantId);

        return this;
    }

    public List<UUID> getProcessDefinitionIds() {
        return unmodifiableList(this.processDefinitionIds);
    }

    public ProcessFindRequest setProcessDefinitionIds(List<UUID> processDefinitionIds) {
        this.processDefinitionIds.clear();
        if (processDefinitionIds != null) {
            this.processDefinitionIds.addAll(processDefinitionIds);
        }

        return this;
    }

    public ProcessFindRequest setProcessDefinitionId(UUID processDefinitionId) {
        Objects.requireNonNull(processDefinitionId, "'processDefinitionId' is required");

        return this.setProcessDefinitionIds(List.of(processDefinitionId));
    }

    public ProcessFindRequest addProcessDefinitionId(UUID processDefinitionId) {
        Objects.requireNonNull(processDefinitionId, "'processDefinitionId' is required");
        if (!this.processDefinitionIds.contains(processDefinitionId)) {
            this.processDefinitionIds.add(processDefinitionId);
        }

        return this;
    }

    public ProcessFindRequest removeProcessDefinitionId(UUID processDefinitionId) {
        Objects.requireNonNull(processDefinitionId, "'processDefinitionId' is required");
        this.processDefinitionIds.remove(processDefinitionId);

        return this;
    }

    public List<String> getProcessDefinitionCodes() {
        return unmodifiableList(this.processDefinitionCodes);
    }

    public ProcessFindRequest setProcessDefinitionCodes(List<String> processDefinitionCodes) {
        this.processDefinitionCodes.clear();
        if (processDefinitionCodes != null) {
            this.processDefinitionCodes.addAll(processDefinitionCodes);
        }

        return this;
    }

    public ProcessFindRequest setProcessDefinitionCode(String processDefinitionCode) {
        Objects.requireNonNull(processDefinitionCode, "'processDefinitionCode' is required");

        return this.setProcessDefinitionCodes(List.of(processDefinitionCode));
    }

    public ProcessFindRequest addProcessDefinitionCode(String processDefinitionCode) {
        Objects.requireNonNull(processDefinitionCode, "'processDefinitionCode' is required");
        if (!this.processDefinitionCodes.contains(processDefinitionCode)) {
            this.processDefinitionCodes.add(processDefinitionCode);
        }

        return this;
    }

    public ProcessFindRequest removeProcessDefinitionCode(String processDefinitionCode) {
        Objects.requireNonNull(processDefinitionCode, "'processDefinitionCode' is required");
        this.processDefinitionCodes.remove(processDefinitionCode);

        return this;
    }

    public List<String> getMetadata() {
        return unmodifiableList(this.metadata);
    }

    public ProcessFindRequest setMetadata(List<String> metadata) {
        this.metadata.clear();
        if (metadata != null) {
            this.metadata.addAll(metadata);
        }

        return this;
    }

    public ProcessFindRequest setMetadata(String metadata) {
        Objects.requireNonNull(metadata, "'metadata' is required");

        return this.setMetadata(List.of(metadata));
    }

    /**
     * Sets a single "code operation value1 value2..." filter expression, built from its parts and safely
     * encoded so that a value containing a space (or any character requiring percent-encoding) still
     * round-trips correctly. See {@link SearchCriteriaUtils#encodeFilterExpression} for details on the
     * encoding.
     *
     * @param code the metadata field code to filter/sort by
     * @param operation the operation code, e.g. "eq", "le", "ge", "between", "contains", "in"
     * @param values one or more values for the operation
     */
    public ProcessFindRequest setMetadata(String code, String operation, String... values) {
        String encoded = SearchCriteriaUtils.encodeFilterExpression(code, operation, values);

        return this.setMetadata(encoded);
    }

    public ProcessFindRequest addMetadata(String metadata) {
        Objects.requireNonNull(metadata, "'metadata' is required");
        if (!this.metadata.contains(metadata)) {
            this.metadata.add(metadata);
        }

        return this;
    }

    /**
     * Adds a single "code operation value1 value2..." filter expression, built from its parts and safely
     * encoded so that a value containing a space (or any character requiring percent-encoding) still
     * round-trips correctly. See {@link SearchCriteriaUtils#encodeFilterExpression} for details on the
     * encoding.
     *
     * @param code the metadata field code to filter/sort by
     * @param operation the operation code, e.g. "eq", "le", "ge", "between", "contains", "in"
     * @param values one or more values for the operation
     */
    public ProcessFindRequest addMetadata(String code, String operation, String... values) {
        String encoded = SearchCriteriaUtils.encodeFilterExpression(code, operation, values);

        return this.addMetadata(encoded);
    }

    public ProcessFindRequest removeMetadata(String metadata) {
        Objects.requireNonNull(metadata, "'metadata' is required");
        this.metadata.remove(metadata);

        return this;
    }
}
