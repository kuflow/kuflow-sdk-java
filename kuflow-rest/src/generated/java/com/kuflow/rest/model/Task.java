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

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.kuflow.rest.util.TaskUtils;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/** The Task model. */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "objectType")
@JsonTypeName("TASK")
@Fluent
public final class Task extends AbstractAudited {

    /*
     * The id property.
     */
    @JsonProperty(value = "id")
    private UUID id;

    /*
     * Task state
     */
    @JsonProperty(value = "state")
    private TaskState state;

    /*
     * In creation task, one of 'id, version or code' is mandatory.
     */
    @JsonProperty(value = "taskDefinition", required = true)
    private TaskDefinitionSummary taskDefinition;

    /*
     * The processId property.
     */
    @JsonProperty(value = "processId", required = true)
    private UUID processId;

    /*
     * Task element values, en ElementValueDocument is not allowed, used when the task render type selected is
     * JSON Forms
     *
     */
    @JsonProperty(value = "elementValues")
    private Map<String, List<TaskElementValue>> elementValues;

    /*
     * Json form values, used when the render type selected is JSON Forms.
     *
     */
    @JsonProperty(value = "jsonFormsValue")
    private JsonFormsValue jsonFormsValue;

    /*
     * The logs property.
     */
    @JsonProperty(value = "logs")
    private List<Log> logs;

    /*
     * The owner property.
     */
    @JsonProperty(value = "owner")
    private Principal owner;

    /** Creates an instance of Task class. */
    public Task() {}

    /**
     * Get the id property: The id property.
     *
     * @return the id value.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Set the id property: The id property.
     *
     * @param id the id value to set.
     * @return the Task object itself.
     */
    public Task setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the state property: Task state.
     *
     * @return the state value.
     */
    public TaskState getState() {
        return this.state;
    }

    /**
     * Set the state property: Task state.
     *
     * @param state the state value to set.
     * @return the Task object itself.
     */
    public Task setState(TaskState state) {
        this.state = state;
        return this;
    }

    /**
     * Get the taskDefinition property: In creation task, one of 'id, version or code' is mandatory.
     *
     * @return the taskDefinition value.
     */
    public TaskDefinitionSummary getTaskDefinition() {
        return this.taskDefinition;
    }

    /**
     * Set the taskDefinition property: In creation task, one of 'id, version or code' is mandatory.
     *
     * @param taskDefinition the taskDefinition value to set.
     * @return the Task object itself.
     */
    public Task setTaskDefinition(TaskDefinitionSummary taskDefinition) {
        this.taskDefinition = taskDefinition;
        return this;
    }

    /**
     * Get the processId property: The processId property.
     *
     * @return the processId value.
     */
    public UUID getProcessId() {
        return this.processId;
    }

    /**
     * Set the processId property: The processId property.
     *
     * @param processId the processId value to set.
     * @return the Task object itself.
     */
    public Task setProcessId(UUID processId) {
        this.processId = processId;
        return this;
    }

    /**
     * Get the elementValues property: Task element values, en ElementValueDocument is not allowed, used when the task
     * render type selected is JSON Forms.
     *
     * @return the elementValues value.
     */
    public Map<String, List<TaskElementValue>> getElementValues() {
        return this.elementValues;
    }

    /**
     * Set the elementValues property: Task element values, en ElementValueDocument is not allowed, used when the task
     * render type selected is JSON Forms.
     *
     * @param elementValues the elementValues value to set.
     * @return the Task object itself.
     */
    public Task setElementValues(Map<String, List<TaskElementValue>> elementValues) {
        this.elementValues = elementValues;
        return this;
    }

    /**
     * Get the jsonFormsValue property: Json form values, used when the render type selected is JSON Forms.
     *
     * @return the jsonFormsValue value.
     */
    public JsonFormsValue getJsonFormsValue() {
        return this.jsonFormsValue;
    }

    /**
     * Set the jsonFormsValue property: Json form values, used when the render type selected is JSON Forms.
     *
     * @param jsonFormsValue the jsonFormsValue value to set.
     * @return the Task object itself.
     */
    public Task setJsonFormsValue(JsonFormsValue jsonFormsValue) {
        this.jsonFormsValue = jsonFormsValue;
        return this;
    }

    /**
     * Get the logs property: The logs property.
     *
     * @return the logs value.
     */
    public List<Log> getLogs() {
        return this.logs;
    }

    /**
     * Set the logs property: The logs property.
     *
     * @param logs the logs value to set.
     * @return the Task object itself.
     */
    public Task setLogs(List<Log> logs) {
        this.logs = logs;
        return this;
    }

    /**
     * Get the owner property: The owner property.
     *
     * @return the owner value.
     */
    public Principal getOwner() {
        return this.owner;
    }

    /**
     * Set the owner property: The owner property.
     *
     * @param owner the owner value to set.
     * @return the Task object itself.
     */
    public Task setOwner(Principal owner) {
        this.owner = owner;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Task setCreatedBy(UUID createdBy) {
        super.setCreatedBy(createdBy);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Task setCreatedAt(OffsetDateTime createdAt) {
        super.setCreatedAt(createdAt);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Task setLastModifiedBy(UUID lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Task setLastModifiedAt(OffsetDateTime lastModifiedAt) {
        super.setLastModifiedAt(lastModifiedAt);
        return this;
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param elementDefinitionCode Element Definition Code
     * @return TRUE if all related valid values are TRUE else FALSE.
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Boolean getElementValueValid(String elementDefinitionCode) {
        return TaskUtils.getElementValueValid(this, elementDefinitionCode);
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param elementDefinitionCode Element Definition Code
     * @param index Element value index
     * @return The requested valid value
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Boolean getElementValueValidAt(String elementDefinitionCode, int index) {
        return TaskUtils.getElementValueValidAt(this, elementDefinitionCode, index);
    }

    /**
     * Set valid to all values
     *
     * @param elementDefinitionCode Element Definition Code
     * @param valid Valid value
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task setElementValueValid(String elementDefinitionCode, Boolean valid) {
        TaskUtils.setElementValueValid(this, elementDefinitionCode, valid);

        return this;
    }

    /**
     * Set valid to the selected value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param valid Valid value
     * @param index Element value index
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task setElementValueValidAt(String elementDefinitionCode, Boolean valid, int index) {
        TaskUtils.setElementValueValidAt(this, elementDefinitionCode, valid, index);

        return this;
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task setElementValueAsString(String elementDefinitionCode, String elementValue) {
        TaskUtils.setElementValueAsString(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task setElementValueAsStringList(String elementDefinitionCode, List<String> elementValues) {
        TaskUtils.setElementValueAsStringList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task addElementValueAsString(String elementDefinitionCode, String elementValue) {
        TaskUtils.addElementValueAsString(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task addElementValueAsStringList(String elementDefinitionCode, List<String> elementValues) {
        TaskUtils.addElementValueAsStringList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Get an element as String
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     *     doesn't exist
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public String getElementValueAsString(String elementDefinitionCode) {
        return TaskUtils.getElementValueAsString(this, elementDefinitionCode);
    }

    /**
     * Get all elements as String
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     *     doesn't exist
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public List<String> getElementValueAsStringList(String elementDefinitionCode) {
        return TaskUtils.getElementValueAsStringList(this, elementDefinitionCode);
    }

    /**
     * Try to get an element as String
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Optional<String> findElementValueAsString(String elementDefinitionCode) {
        return TaskUtils.findElementValueAsString(this, elementDefinitionCode);
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task setElementValueAsDouble(String elementDefinitionCode, Double elementValue) {
        TaskUtils.setElementValueAsDouble(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task setElementValueAsDoubleList(String elementDefinitionCode, List<Double> elementValues) {
        TaskUtils.setElementValueAsDoubleList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task addElementValueAsDouble(String elementDefinitionCode, Double elementValue) {
        TaskUtils.addElementValueAsDouble(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task addElementValueAsDoubleList(String elementDefinitionCode, List<Double> elementValues) {
        TaskUtils.addElementValueAsDoubleList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Get an element as Double
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     *     doesn't exist
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Double getElementValueAsDouble(String elementDefinitionCode) {
        return TaskUtils.getElementValueAsDouble(this, elementDefinitionCode);
    }

    /**
     * Get all elements as Double
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     *     doesn't exist
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public List<Double> getElementValueAsDoubleList(String elementDefinitionCode) {
        return TaskUtils.getElementValueAsDoubleList(this, elementDefinitionCode);
    }

    /**
     * Try to get an element as Double
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Optional<Double> findElementValueAsDouble(String elementDefinitionCode) {
        return TaskUtils.findElementValueAsDouble(this, elementDefinitionCode);
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task setElementValueAsLocalDate(String elementDefinitionCode, LocalDate elementValue) {
        TaskUtils.setElementValueAsLocalDate(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task setElementValueAsLocalDateList(String elementDefinitionCode, List<LocalDate> elementValues) {
        TaskUtils.setElementValueAsLocalDateList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task addElementValueAsLocalDate(String elementDefinitionCode, LocalDate elementValue) {
        TaskUtils.addElementValueAsLocalDate(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task addElementValueAsLocalDateList(String elementDefinitionCode, List<LocalDate> elementValues) {
        TaskUtils.addElementValueAsLocalDateList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Get an element as LocalDate
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     *     doesn't exist
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public LocalDate getElementValueAsLocalDate(String elementDefinitionCode) {
        return TaskUtils.getElementValueAsLocalDate(this, elementDefinitionCode);
    }

    /**
     * Get all elements as LocalDate
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     *     doesn't exist
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public List<LocalDate> getElementValueAsLocalDateList(String elementDefinitionCode) {
        return TaskUtils.getElementValueAsLocalDateList(this, elementDefinitionCode);
    }

    /**
     * Try to get an element as LocalDate
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Optional<LocalDate> findElementValueAsLocalDate(String elementDefinitionCode) {
        return TaskUtils.findElementValueAsLocalDate(this, elementDefinitionCode);
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task setElementValueAsMap(String elementDefinitionCode, Map<String, Object> elementValue) {
        TaskUtils.setElementValueAsMap(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task setElementValueAsMapList(String elementDefinitionCode, List<Map<String, Object>> elementValues) {
        TaskUtils.setElementValueAsMapList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task addElementValueAsMap(String elementDefinitionCode, Map<String, Object> elementValue) {
        TaskUtils.addElementValueAsMap(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task addElementValueAsMapList(String elementDefinitionCode, List<Map<String, Object>> elementValues) {
        TaskUtils.addElementValueAsMapList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Get an element as Map
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     *     doesn't exist
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Map<String, Object> getElementValueAsMap(String elementDefinitionCode) {
        return TaskUtils.getElementValueAsMap(this, elementDefinitionCode);
    }

    /**
     * Get all elements as Map
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     *     doesn't exist
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public List<Map<String, Object>> getElementValueAsMapList(String elementDefinitionCode) {
        return TaskUtils.getElementValueAsMapList(this, elementDefinitionCode);
    }

    /**
     * Try to get an element as Map
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Optional<Map<String, Object>> findElementValueAsMap(String elementDefinitionCode) {
        return TaskUtils.findElementValueAsMap(this, elementDefinitionCode);
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task setElementValueAsDocument(String elementDefinitionCode, TaskElementValueDocumentItem elementValue) {
        TaskUtils.setElementValueAsDocument(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task setElementValueAsDocumentList(String elementDefinitionCode, List<TaskElementValueDocumentItem> elementValues) {
        TaskUtils.setElementValueAsDocumentList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task addElementValueAsDocument(String elementDefinitionCode, TaskElementValueDocumentItem elementValue) {
        TaskUtils.addElementValueAsDocument(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task addElementValueAsDocumentList(String elementDefinitionCode, List<TaskElementValueDocumentItem> elementValues) {
        TaskUtils.addElementValueAsDocumentList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Get an element as Document
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     *     doesn't exist
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public TaskElementValueDocumentItem getElementValueAsDocument(String elementDefinitionCode) {
        return TaskUtils.getElementValueAsDocument(this, elementDefinitionCode);
    }

    /**
     * Get all elements as Document
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     *     doesn't exist
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public List<TaskElementValueDocumentItem> getElementValueAsDocumentList(String elementDefinitionCode) {
        return TaskUtils.getElementValueAsDocumentList(this, elementDefinitionCode);
    }

    /**
     * Try to get an element as Document
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Optional<TaskElementValueDocumentItem> findElementValueAsDocument(String elementDefinitionCode) {
        return TaskUtils.findElementValueAsDocument(this, elementDefinitionCode);
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task setElementValueAsPrincipal(String elementDefinitionCode, TaskElementValuePrincipalItem elementValue) {
        TaskUtils.setElementValueAsPrincipal(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task setElementValueAsPrincipalList(String elementDefinitionCode, List<TaskElementValuePrincipalItem> elementValues) {
        TaskUtils.setElementValueAsPrincipalList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task addElementValueAsPrincipal(String elementDefinitionCode, TaskElementValuePrincipalItem elementValue) {
        TaskUtils.addElementValueAsPrincipal(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     * @return the object itself
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Task addElementValueAsPrincipalList(String elementDefinitionCode, List<TaskElementValuePrincipalItem> elementValues) {
        TaskUtils.addElementValueAsPrincipalList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Get an element as Principal
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     *     doesn't exist
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public TaskElementValuePrincipalItem getElementValueAsPrincipal(String elementDefinitionCode) {
        return TaskUtils.getElementValueAsPrincipal(this, elementDefinitionCode);
    }

    /**
     * Get all elements as Principal
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     *     doesn't exist
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public List<TaskElementValuePrincipalItem> getElementValueAsPrincipalList(String elementDefinitionCode) {
        return TaskUtils.getElementValueAsPrincipalList(this, elementDefinitionCode);
    }

    /**
     * Try to get an element as Principal
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     * @deprecated in favor of {@link TaskUtils}
     */
    @Deprecated
    public Optional<TaskElementValuePrincipalItem> findElementValueAsPrincipal(String elementDefinitionCode) {
        return TaskUtils.findElementValueAsPrincipal(this, elementDefinitionCode);
    }
}
