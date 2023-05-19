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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kuflow.rest.KuFlowRestClientException;
import com.kuflow.rest.model.TaskElementValue;
import com.kuflow.rest.model.TaskElementValueDocumentItem;
import com.kuflow.rest.model.TaskElementValuePrincipalItem;
import com.kuflow.temporal.activity.kuflow.util.SaveTaskElementRequestUtils;
import com.kuflow.temporal.common.model.AbstractModel;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class SaveTaskElementRequest extends AbstractModel {

    private UUID taskId;

    private String elementDefinitionCode;

    private List<TaskElementValue> elementValues;

    public UUID getTaskId() {
        return this.taskId;
    }

    public SaveTaskElementRequest setTaskId(UUID taskId) {
        this.taskId = taskId;

        return this;
    }

    public String getElementDefinitionCode() {
        return this.elementDefinitionCode;
    }

    public SaveTaskElementRequest setElementDefinitionCode(String elementDefinitionCode) {
        this.elementDefinitionCode = elementDefinitionCode;

        return this;
    }

    /**
     * Get the elementValues property: The elementValues property.
     *
     * @return the elementValues value.
     */
    public List<TaskElementValue> getElementValues() {
        if (this.elementValues == null) {
            this.elementValues = new LinkedList<>();
        }

        return unmodifiableList(this.elementValues);
    }

    /**
     * Set the elementValues property: The elementValues property.
     *
     * @param elementValues the elementValues value to set.
     * @return the SaveTaskElementRequest object itself.
     */
    public SaveTaskElementRequest setElementValues(List<TaskElementValue> elementValues) {
        if (this.elementValues == null) {
            this.elementValues = new LinkedList<>();
        }

        this.elementValues.clear();
        if (elementValues != null) {
            this.elementValues.addAll(elementValues);
        }

        return this;
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @return TRUE if all related valid values are TRUE else FALSE.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public Boolean getElementValueValid() {
        return SaveTaskElementRequestUtils.getElementValueValid(this);
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param index Element value index
     * @return The requested valid value
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public Boolean getElementValueValidAt(int index) {
        return SaveTaskElementRequestUtils.getElementValueValidAt(this, index);
    }

    /**
     * Set valid to all values
     *
     * @param valid Valid value
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest setElementValueValid(Boolean valid) {
        SaveTaskElementRequestUtils.setElementValueValid(this, valid);

        return this;
    }

    /**
     * Set valid to the selected value
     *
     * @param valid Valid value
     * @param index Element value index
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest setElementValueValidAt(Boolean valid, int index) {
        SaveTaskElementRequestUtils.setElementValueValidAt(this, valid, index);

        return this;
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest setElementValueAsString(String elementValue) {
        SaveTaskElementRequestUtils.setElementValueAsString(this, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest setElementValueAsStringList(List<String> elementValues) {
        SaveTaskElementRequestUtils.setElementValueAsStringList(this, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest addElementValueAsString(String elementValue) {
        SaveTaskElementRequestUtils.addElementValueAsString(this, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest addElementValueAsStringList(List<String> elementValues) {
        SaveTaskElementRequestUtils.addElementValueAsStringList(this, elementValues);

        return this;
    }

    /**
     * Get an element as String
     *
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public String getElementValueAsString() {
        return SaveTaskElementRequestUtils.getElementValueAsString(this);
    }

    /**
     * Get all elements as String
     *
     * @return the elements values.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public List<String> getElementValueAsStringList() {
        return SaveTaskElementRequestUtils.getElementValueAsStringList(this);
    }

    /**
     * Try to get an element as String
     *
     * @return the element value if exists.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public Optional<String> findElementValueAsString() {
        return SaveTaskElementRequestUtils.findElementValueAsString(this);
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest setElementValueAsDouble(Double elementValue) {
        SaveTaskElementRequestUtils.setElementValueAsDouble(this, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest setElementValueAsDoubleList(List<Double> elementValues) {
        SaveTaskElementRequestUtils.setElementValueAsDoubleList(this, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest addElementValueAsDouble(Double elementValue) {
        SaveTaskElementRequestUtils.addElementValueAsDouble(this, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest addElementValueAsDoubleList(List<Double> elementValues) {
        SaveTaskElementRequestUtils.addElementValueAsDoubleList(this, elementValues);

        return this;
    }

    /**
     * Get an element as Double
     *
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public Double getElementValueAsDouble() {
        return SaveTaskElementRequestUtils.getElementValueAsDouble(this);
    }

    /**
     * Get all elements as Double
     *
     * @return the elements values.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public List<Double> getElementValueAsDoubleList() {
        return SaveTaskElementRequestUtils.getElementValueAsDoubleList(this);
    }

    /**
     * Try to get an element as Double
     *
     * @return the element value if exists.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public Optional<Double> findElementValueAsDouble() {
        return SaveTaskElementRequestUtils.findElementValueAsDouble(this);
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest setElementValueAsLocalDate(LocalDate elementValue) {
        SaveTaskElementRequestUtils.setElementValueAsLocalDate(this, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest setElementValueAsLocalDateList(List<LocalDate> elementValues) {
        SaveTaskElementRequestUtils.setElementValueAsLocalDateList(this, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest addElementValueAsLocalDate(LocalDate elementValue) {
        SaveTaskElementRequestUtils.addElementValueAsLocalDate(this, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest addElementValueAsLocalDateList(List<LocalDate> elementValues) {
        SaveTaskElementRequestUtils.addElementValueAsLocalDateList(this, elementValues);

        return this;
    }

    /**
     * Get an element as LocalDate
     *
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public LocalDate getElementValueAsLocalDate() {
        return SaveTaskElementRequestUtils.getElementValueAsLocalDate(this);
    }

    /**
     * Get all elements as LocalDate
     *
     * @return the elements values.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public List<LocalDate> getElementValueAsLocalDateList() {
        return SaveTaskElementRequestUtils.getElementValueAsLocalDateList(this);
    }

    /**
     * Try to get an element as LocalDate
     *
     * @return the element value if exists.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public Optional<LocalDate> findElementValueAsLocalDate() {
        return SaveTaskElementRequestUtils.findElementValueAsLocalDate(this);
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest setElementValueAsMap(Map<String, Object> elementValue) {
        SaveTaskElementRequestUtils.setElementValueAsMap(this, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest setElementValueAsMapList(List<Map<String, Object>> elementValues) {
        SaveTaskElementRequestUtils.setElementValueAsMapList(this, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest addElementValueAsMap(Map<String, Object> elementValue) {
        SaveTaskElementRequestUtils.addElementValueAsMap(this, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest addElementValueAsMapList(List<Map<String, Object>> elementValues) {
        SaveTaskElementRequestUtils.addElementValueAsMapList(this, elementValues);

        return this;
    }

    /**
     * Get an element as Map
     *
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public Map<String, Object> getElementValueAsMap() {
        return SaveTaskElementRequestUtils.getElementValueAsMap(this);
    }

    /**
     * Get all elements as Map
     *
     * @return the elements values.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public List<Map<String, Object>> getElementValueAsMapList() {
        return SaveTaskElementRequestUtils.getElementValueAsMapList(this);
    }

    /**
     * Try to get an element as Map
     *
     * @return the element value if exists.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public Optional<Map<String, Object>> findElementValueAsMap() {
        return SaveTaskElementRequestUtils.findElementValueAsMap(this);
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest setElementValueAsDocument(TaskElementValueDocumentItem elementValue) {
        SaveTaskElementRequestUtils.setElementValueAsDocument(this, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest setElementValueAsDocumentList(List<TaskElementValueDocumentItem> elementValues) {
        SaveTaskElementRequestUtils.setElementValueAsDocumentList(this, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest addElementValueAsDocument(TaskElementValueDocumentItem elementValue) {
        SaveTaskElementRequestUtils.addElementValueAsDocument(this, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest addElementValueAsDocumentList(List<TaskElementValueDocumentItem> elementValues) {
        SaveTaskElementRequestUtils.addElementValueAsDocumentList(this, elementValues);

        return this;
    }

    /**
     * Get an element as Document
     *
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public TaskElementValueDocumentItem getElementValueAsDocument() {
        return SaveTaskElementRequestUtils.getElementValueAsDocument(this);
    }

    /**
     * Get all elements as Document
     *
     * @return the elements values.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public List<TaskElementValueDocumentItem> getElementValueAsDocumentList() {
        return SaveTaskElementRequestUtils.getElementValueAsDocumentList(this);
    }

    /**
     * Try to get an element as Document
     *
     * @return the element value if exists.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public Optional<TaskElementValueDocumentItem> findElementValueAsDocument() {
        return SaveTaskElementRequestUtils.findElementValueAsDocument(this);
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest setElementValueAsPrincipal(TaskElementValuePrincipalItem elementValue) {
        SaveTaskElementRequestUtils.setElementValueAsPrincipal(this, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest setElementValueAsPrincipalList(List<TaskElementValuePrincipalItem> elementValues) {
        SaveTaskElementRequestUtils.setElementValueAsPrincipalList(this, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest addElementValueAsPrincipal(TaskElementValuePrincipalItem elementValue) {
        SaveTaskElementRequestUtils.addElementValueAsPrincipal(this, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveTaskElementRequest addElementValueAsPrincipalList(List<TaskElementValuePrincipalItem> elementValues) {
        SaveTaskElementRequestUtils.addElementValueAsPrincipalList(this, elementValues);

        return this;
    }

    /**
     * Get an element as Principal
     *
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public TaskElementValuePrincipalItem getElementValueAsPrincipal() {
        return SaveTaskElementRequestUtils.getElementValueAsPrincipal(this);
    }

    /**
     * Get all elements as Principal
     *
     * @return the elements values.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public List<TaskElementValuePrincipalItem> getElementValueAsPrincipalList() {
        return SaveTaskElementRequestUtils.getElementValueAsPrincipalList(this);
    }

    /**
     * Try to get an element as Principal
     *
     * @return the element value if exists.
     * @deprecated in favor of {@link SaveTaskElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public Optional<TaskElementValuePrincipalItem> findElementValueAsPrincipal() {
        return SaveTaskElementRequestUtils.findElementValueAsPrincipal(this);
    }
}
