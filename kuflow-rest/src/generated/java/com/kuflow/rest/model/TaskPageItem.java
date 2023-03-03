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

import static com.kuflow.rest.util.TaskElementValueAccessorTaskPageItem.of;
import static com.kuflow.rest.util.TaskHelper.addElementValueOf;
import static com.kuflow.rest.util.TaskHelper.addElementValuesOf;
import static com.kuflow.rest.util.TaskHelper.findElementValueOfAsDocument;
import static com.kuflow.rest.util.TaskHelper.findElementValueOfAsDouble;
import static com.kuflow.rest.util.TaskHelper.findElementValueOfAsLocalDate;
import static com.kuflow.rest.util.TaskHelper.findElementValueOfAsMap;
import static com.kuflow.rest.util.TaskHelper.findElementValueOfAsPrincipal;
import static com.kuflow.rest.util.TaskHelper.findElementValueOfAsString;
import static com.kuflow.rest.util.TaskHelper.getElementValueOfAsDocument;
import static com.kuflow.rest.util.TaskHelper.getElementValueOfAsDocumentList;
import static com.kuflow.rest.util.TaskHelper.getElementValueOfAsDouble;
import static com.kuflow.rest.util.TaskHelper.getElementValueOfAsDoubleList;
import static com.kuflow.rest.util.TaskHelper.getElementValueOfAsLocalDate;
import static com.kuflow.rest.util.TaskHelper.getElementValueOfAsLocalDateList;
import static com.kuflow.rest.util.TaskHelper.getElementValueOfAsMap;
import static com.kuflow.rest.util.TaskHelper.getElementValueOfAsMapList;
import static com.kuflow.rest.util.TaskHelper.getElementValueOfAsPrincipal;
import static com.kuflow.rest.util.TaskHelper.getElementValueOfAsPrincipalList;
import static com.kuflow.rest.util.TaskHelper.getElementValueOfAsString;
import static com.kuflow.rest.util.TaskHelper.getElementValueOfAsStringList;
import static com.kuflow.rest.util.TaskHelper.getElementValueOfValid;
import static com.kuflow.rest.util.TaskHelper.getElementValueOfValidAt;
import static com.kuflow.rest.util.TaskHelper.setElementValueOf;
import static com.kuflow.rest.util.TaskHelper.setElementValueOfValid;
import static com.kuflow.rest.util.TaskHelper.setElementValueOfValidAt;
import static com.kuflow.rest.util.TaskHelper.setElementValuesOf;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/** The TaskPageItem model. */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "objectType")
@JsonTypeName("TASK_PAGE_ITEM")
@Fluent
public final class TaskPageItem extends AbstractAudited {
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
     * Task element values, en ElementValueDocument is not allowed.
     */
    @JsonProperty(value = "elementValues")
    private Map<String, List<TaskElementValue>> elementValues;

    /*
     * The owner property.
     */
    @JsonProperty(value = "owner")
    private Principal owner;

    /** Creates an instance of TaskPageItem class. */
    public TaskPageItem() {}

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
     * @return the TaskPageItem object itself.
     */
    public TaskPageItem setId(UUID id) {
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
     * @return the TaskPageItem object itself.
     */
    public TaskPageItem setState(TaskState state) {
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
     * @return the TaskPageItem object itself.
     */
    public TaskPageItem setTaskDefinition(TaskDefinitionSummary taskDefinition) {
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
     * @return the TaskPageItem object itself.
     */
    public TaskPageItem setProcessId(UUID processId) {
        this.processId = processId;
        return this;
    }

    /**
     * Get the elementValues property: Task element values, en ElementValueDocument is not allowed.
     *
     * @return the elementValues value.
     */
    public Map<String, List<TaskElementValue>> getElementValues() {
        return this.elementValues;
    }

    /**
     * Set the elementValues property: Task element values, en ElementValueDocument is not allowed.
     *
     * @param elementValues the elementValues value to set.
     * @return the TaskPageItem object itself.
     */
    public TaskPageItem setElementValues(Map<String, List<TaskElementValue>> elementValues) {
        this.elementValues = elementValues;
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
     * @return the TaskPageItem object itself.
     */
    public TaskPageItem setOwner(Principal owner) {
        this.owner = owner;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public TaskPageItem setCreatedBy(UUID createdBy) {
        super.setCreatedBy(createdBy);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public TaskPageItem setCreatedAt(OffsetDateTime createdAt) {
        super.setCreatedAt(createdAt);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public TaskPageItem setLastModifiedBy(UUID lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public TaskPageItem setLastModifiedAt(OffsetDateTime lastModifiedAt) {
        super.setLastModifiedAt(lastModifiedAt);
        return this;
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param elementDefinitionCode Element Definition Code
     * @return TRUE if all related valid values are TRUE else FALSE.
     */
    public Boolean getElementValueValid(String elementDefinitionCode) {
        return getElementValueOfValid(of(this, elementDefinitionCode));
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param elementDefinitionCode Element Definition Code
     * @param index Element value index
     * @return The requested valid value
     */
    public Boolean getElementValueValidAt(String elementDefinitionCode, int index) {
        return getElementValueOfValidAt(of(this, elementDefinitionCode), index);
    }

    /**
     * Set valid to all values
     *
     * @param elementDefinitionCode Element Definition Code
     * @param valid Valid value
     * @return the Task object itself.
     */
    public TaskPageItem setElementValueValid(String elementDefinitionCode, Boolean valid) {
        setElementValueOfValid(of(this, elementDefinitionCode), valid);

        return this;
    }

    /**
     * Set valid to the selected value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param valid Valid value
     * @param index Element value index
     * @return the Task object itself.
     */
    public TaskPageItem setElementValueValidAt(String elementDefinitionCode, Boolean valid, int index) {
        setElementValueOfValidAt(of(this, elementDefinitionCode), valid, index);

        return this;
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public TaskPageItem setElementValueAsString(String elementDefinitionCode, String elementValue) {
        setElementValueOf(of(this, elementDefinitionCode), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public TaskPageItem setElementValueAsStringList(String elementDefinitionCode, List<String> elementValues) {
        setElementValuesOf(of(this, elementDefinitionCode), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public TaskPageItem addElementValueAsString(String elementDefinitionCode, String elementValue) {
        addElementValueOf(of(this, elementDefinitionCode), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public TaskPageItem addElementValueAsStringList(String elementDefinitionCode, List<String> elementValues) {
        addElementValuesOf(of(this, elementDefinitionCode), elementValues);

        return this;
    }

    /**
     * Get an element as String
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exists
     */
    public String getElementValueAsString(String elementDefinitionCode) {
        return getElementValueOfAsString(of(this, elementDefinitionCode));
    }

    /**
     * Get all elements as String
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public List<String> getElementValueAsStringList(String elementDefinitionCode) {
        return getElementValueOfAsStringList(of(this, elementDefinitionCode));
    }

    /**
     * Try to get an element as String
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public Optional<String> findElementValueAsString(String elementDefinitionCode) {
        return findElementValueOfAsString(of(this, elementDefinitionCode));
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public TaskPageItem setElementValueAsDouble(String elementDefinitionCode, Double elementValue) {
        setElementValueOf(of(this, elementDefinitionCode), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public TaskPageItem setElementValueAsDoubleList(String elementDefinitionCode, List<Double> elementValues) {
        setElementValuesOf(of(this, elementDefinitionCode), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public TaskPageItem addElementValueAsDouble(String elementDefinitionCode, Double elementValue) {
        addElementValueOf(of(this, elementDefinitionCode), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public TaskPageItem addElementValueAsDoubleList(String elementDefinitionCode, List<Double> elementValues) {
        addElementValuesOf(of(this, elementDefinitionCode), elementValues);

        return this;
    }

    /**
     * Get an element as Double
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exists
     */
    public Double getElementValueAsDouble(String elementDefinitionCode) {
        return getElementValueOfAsDouble(of(this, elementDefinitionCode));
    }

    /**
     * Get all elements as Double
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public List<Double> getElementValueAsDoubleList(String elementDefinitionCode) {
        return getElementValueOfAsDoubleList(of(this, elementDefinitionCode));
    }

    /**
     * Try to get an element as Double
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public Optional<Double> findElementValueAsDouble(String elementDefinitionCode) {
        return findElementValueOfAsDouble(of(this, elementDefinitionCode));
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public TaskPageItem setElementValueAsLocalDate(String elementDefinitionCode, LocalDate elementValue) {
        setElementValueOf(of(this, elementDefinitionCode), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public TaskPageItem setElementValueAsLocalDateList(String elementDefinitionCode, List<LocalDate> elementValues) {
        setElementValuesOf(of(this, elementDefinitionCode), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public TaskPageItem addElementValueAsLocalDate(String elementDefinitionCode, LocalDate elementValue) {
        addElementValueOf(of(this, elementDefinitionCode), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public TaskPageItem addElementValueAsLocalDateList(String elementDefinitionCode, List<LocalDate> elementValues) {
        addElementValuesOf(of(this, elementDefinitionCode), elementValues);

        return this;
    }

    /**
     * Get an element as LocalDate
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exists
     */
    public LocalDate getElementValueAsLocalDate(String elementDefinitionCode) {
        return getElementValueOfAsLocalDate(of(this, elementDefinitionCode));
    }

    /**
     * Get all elements as LocalDate
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public List<LocalDate> getElementValueAsLocalDateList(String elementDefinitionCode) {
        return getElementValueOfAsLocalDateList(of(this, elementDefinitionCode));
    }

    /**
     * Try to get an element as LocalDate
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public Optional<LocalDate> findElementValueAsLocalDate(String elementDefinitionCode) {
        return findElementValueOfAsLocalDate(of(this, elementDefinitionCode));
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public TaskPageItem setElementValueAsMap(String elementDefinitionCode, Map<String, Object> elementValue) {
        setElementValueOf(of(this, elementDefinitionCode), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public TaskPageItem setElementValueAsMapList(
            String elementDefinitionCode, List<Map<String, Object>> elementValues) {
        setElementValuesOf(of(this, elementDefinitionCode), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public TaskPageItem addElementValueAsMap(String elementDefinitionCode, Map<String, Object> elementValue) {
        addElementValueOf(of(this, elementDefinitionCode), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public TaskPageItem addElementValueAsMapList(
            String elementDefinitionCode, List<Map<String, Object>> elementValues) {
        addElementValuesOf(of(this, elementDefinitionCode), elementValues);

        return this;
    }

    /**
     * Get an element as Map
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exists
     */
    public Map<String, Object> getElementValueAsMap(String elementDefinitionCode) {
        return getElementValueOfAsMap(of(this, elementDefinitionCode));
    }

    /**
     * Get all elements as Map
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public List<Map<String, Object>> getElementValueAsMapList(String elementDefinitionCode) {
        return getElementValueOfAsMapList(of(this, elementDefinitionCode));
    }

    /**
     * Try to get an element as Map
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public Optional<Map<String, Object>> findElementValueAsMap(String elementDefinitionCode) {
        return findElementValueOfAsMap(of(this, elementDefinitionCode));
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public TaskPageItem setElementValueAsDocument(
            String elementDefinitionCode, TaskElementValueDocumentItem elementValue) {
        setElementValueOf(of(this, elementDefinitionCode), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public TaskPageItem setElementValueAsDocumentList(
            String elementDefinitionCode, List<TaskElementValueDocumentItem> elementValues) {
        setElementValuesOf(of(this, elementDefinitionCode), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public TaskPageItem addElementValueAsDocument(
            String elementDefinitionCode, TaskElementValueDocumentItem elementValue) {
        addElementValueOf(of(this, elementDefinitionCode), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public TaskPageItem addElementValueAsDocumentList(
            String elementDefinitionCode, List<TaskElementValueDocumentItem> elementValues) {
        addElementValuesOf(of(this, elementDefinitionCode), elementValues);

        return this;
    }

    /**
     * Get an element as Document
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exists
     */
    public TaskElementValueDocumentItem getElementValueAsDocument(String elementDefinitionCode) {
        return getElementValueOfAsDocument(of(this, elementDefinitionCode));
    }

    /**
     * Get all elements as Document
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public List<TaskElementValueDocumentItem> getElementValueAsDocumentList(String elementDefinitionCode) {
        return getElementValueOfAsDocumentList(of(this, elementDefinitionCode));
    }

    /**
     * Try to get an element as Document
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public Optional<TaskElementValueDocumentItem> findElementValueAsDocument(String elementDefinitionCode) {
        return findElementValueOfAsDocument(of(this, elementDefinitionCode));
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public TaskPageItem setElementValueAsPrincipal(
            String elementDefinitionCode, TaskElementValuePrincipalItem elementValue) {
        setElementValueOf(of(this, elementDefinitionCode), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public TaskPageItem setElementValueAsPrincipalList(
            String elementDefinitionCode, List<TaskElementValuePrincipalItem> elementValues) {
        setElementValuesOf(of(this, elementDefinitionCode), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public TaskPageItem addElementValueAsPrincipal(
            String elementDefinitionCode, TaskElementValuePrincipalItem elementValue) {
        addElementValueOf(of(this, elementDefinitionCode), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public TaskPageItem addElementValueAsPrincipalList(
            String elementDefinitionCode, List<TaskElementValuePrincipalItem> elementValues) {
        addElementValuesOf(of(this, elementDefinitionCode), elementValues);

        return this;
    }

    /**
     * Get an element as Principal
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exists
     */
    public TaskElementValuePrincipalItem getElementValueAsPrincipal(String elementDefinitionCode) {
        return getElementValueOfAsPrincipal(of(this, elementDefinitionCode));
    }

    /**
     * Get all elements as Principal
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public List<TaskElementValuePrincipalItem> getElementValueAsPrincipalList(String elementDefinitionCode) {
        return getElementValueOfAsPrincipalList(of(this, elementDefinitionCode));
    }

    /**
     * Try to get an element as Principal
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public Optional<TaskElementValuePrincipalItem> findElementValueAsPrincipal(String elementDefinitionCode) {
        return findElementValueOfAsPrincipal(of(this, elementDefinitionCode));
    }
}