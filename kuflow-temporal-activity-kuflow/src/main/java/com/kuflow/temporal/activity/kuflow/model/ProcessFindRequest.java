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

import com.kuflow.rest.model.ProcessState;
import com.kuflow.rest.util.SearchCriteriaUtils;
import com.kuflow.temporal.common.model.AbstractModel;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ProcessFindRequest extends AbstractModel {

    private Integer page;

    private Integer size;

    /**
     * Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     * <p>Default sort order is ascending. Multiple sort criteria are supported.
     */
    private final List<String> sorts = new LinkedList<>();

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
     * Filter by an array of process states.
     */
    private final List<ProcessState> states = new LinkedList<>();

    /**
     * Filter by an array of initiator ids.
     */
    private final List<UUID> initiatorIds = new LinkedList<>();

    /**
     * Filter by an array of initiator emails.
     */
    private final List<String> initiatorEmails = new LinkedList<>();

    /**
     * Filter by indexed metadata field values.
     */
    private final List<String> metadata = new LinkedList<>();

    public Integer getPage() {
        return this.page;
    }

    public ProcessFindRequest setPage(Integer page) {
        this.page = page;

        return this;
    }

    public Integer getSize() {
        return this.size;
    }

    public ProcessFindRequest setSize(Integer size) {
        this.size = size;

        return this;
    }

    public List<String> getSorts() {
        return unmodifiableList(this.sorts);
    }

    public ProcessFindRequest setSorts(List<String> sorts) {
        this.sorts.clear();
        if (sorts != null) {
            this.sorts.addAll(sorts);
        }

        return this;
    }

    public ProcessFindRequest setSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");

        return this.setSorts(List.of(sort));
    }

    public ProcessFindRequest addSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");
        if (!this.sorts.contains(sort)) {
            this.sorts.add(sort);
        }

        return this;
    }

    public ProcessFindRequest removeSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");
        this.sorts.remove(sort);

        return this;
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

    public List<ProcessState> getStates() {
        return unmodifiableList(this.states);
    }

    public ProcessFindRequest setStates(List<ProcessState> states) {
        this.states.clear();
        if (states != null) {
            this.states.addAll(states);
        }

        return this;
    }

    public ProcessFindRequest setState(ProcessState state) {
        Objects.requireNonNull(state, "'state' is required");

        return this.setStates(List.of(state));
    }

    public ProcessFindRequest addState(ProcessState state) {
        Objects.requireNonNull(state, "'state' is required");
        if (!this.states.contains(state)) {
            this.states.add(state);
        }

        return this;
    }

    public ProcessFindRequest removeState(ProcessState state) {
        Objects.requireNonNull(state, "'state' is required");
        this.states.remove(state);

        return this;
    }

    public List<UUID> getInitiatorIds() {
        return unmodifiableList(this.initiatorIds);
    }

    public ProcessFindRequest setInitiatorIds(List<UUID> initiatorIds) {
        this.initiatorIds.clear();
        if (initiatorIds != null) {
            this.initiatorIds.addAll(initiatorIds);
        }

        return this;
    }

    public ProcessFindRequest setInitiatorId(UUID initiatorId) {
        Objects.requireNonNull(initiatorId, "'initiatorId' is required");

        return this.setInitiatorIds(List.of(initiatorId));
    }

    public ProcessFindRequest addInitiatorId(UUID initiatorId) {
        Objects.requireNonNull(initiatorId, "'initiatorId' is required");
        if (!this.initiatorIds.contains(initiatorId)) {
            this.initiatorIds.add(initiatorId);
        }

        return this;
    }

    public ProcessFindRequest removeInitiatorId(UUID initiatorId) {
        Objects.requireNonNull(initiatorId, "'initiatorId' is required");
        this.initiatorIds.remove(initiatorId);

        return this;
    }

    public List<String> getInitiatorEmails() {
        return unmodifiableList(this.initiatorEmails);
    }

    public ProcessFindRequest setInitiatorEmails(List<String> initiatorEmails) {
        this.initiatorEmails.clear();
        if (initiatorEmails != null) {
            this.initiatorEmails.addAll(initiatorEmails);
        }

        return this;
    }

    public ProcessFindRequest setInitiatorEmail(String initiatorEmail) {
        Objects.requireNonNull(initiatorEmail, "'initiatorEmail' is required");

        return this.setInitiatorEmails(List.of(initiatorEmail));
    }

    public ProcessFindRequest addInitiatorEmail(String initiatorEmail) {
        Objects.requireNonNull(initiatorEmail, "'initiatorEmail' is required");
        if (!this.initiatorEmails.contains(initiatorEmail)) {
            this.initiatorEmails.add(initiatorEmail);
        }

        return this;
    }

    public ProcessFindRequest removeInitiatorEmail(String initiatorEmail) {
        Objects.requireNonNull(initiatorEmail, "'initiatorEmail' is required");
        this.initiatorEmails.remove(initiatorEmail);

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

    /**
     * Replaces the filter expressions with a single one.
     * <p>
     * Named {@code setMetadataItem} and not {@code setMetadata} on purpose. "Metadata" is uncountable, so a
     * single-value {@code setMetadata(String)} overload would be mapped by Jackson to the very same property as
     * {@link #setMetadata(List)}. Jackson would then pick the {@code String} overload as the mutator for the
     * {@code metadata} property and fail to deserialize the JSON array when this request travels as a Temporal
     * activity input. Countable filters do not have this problem: {@code setProcessDefinitionCode(String)} maps
     * to the {@code processDefinitionCode} property, which is a different one from
     * {@code processDefinitionCodes}.
     *
     * @param metadata the filter expression
     */
    public ProcessFindRequest setMetadataItem(String metadata) {
        Objects.requireNonNull(metadata, "'metadata' is required");

        return this.setMetadata(List.of(metadata));
    }

    /**
     * Sets a single "code operation value1 value2..." filter expression, built from its parts and safely
     * encoded so that a value containing a space (or any character requiring percent-encoding) still
     * round-trips correctly. See {@link SearchCriteriaUtils#encodeFilterExpression} for details on the
     * encoding.
     * <p>
     * See {@link #setMetadataItem(String)} for why this method is not named {@code setMetadata}.
     *
     * @param code the metadata field code to filter/sort by
     * @param operation the operation code, e.g. "eq", "le", "ge", "between", "contains", "in"
     * @param values one or more values for the operation
     */
    public ProcessFindRequest setMetadataItem(String code, String operation, String... values) {
        String encoded = SearchCriteriaUtils.encodeFilterExpression(code, operation, values);

        return this.setMetadataItem(encoded);
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
