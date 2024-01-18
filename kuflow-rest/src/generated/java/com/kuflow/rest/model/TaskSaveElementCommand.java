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
import com.kuflow.rest.util.TaskSaveElementCommandUtils;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The TaskSaveElementCommand model.
 */
@Fluent
public final class TaskSaveElementCommand {

    /*
     * The elementDefinitionCode property.
     */
    @JsonProperty(value = "elementDefinitionCode", required = true)
    private String elementDefinitionCode;

    /*
     * The elementValues property.
     */
    @JsonProperty(value = "elementValues")
    private List<TaskElementValue> elementValues;

    /**
     * Creates an instance of TaskSaveElementCommand class.
     */
    public TaskSaveElementCommand() {}

    /**
     * Get the elementDefinitionCode property: The elementDefinitionCode property.
     *
     * @return the elementDefinitionCode value.
     */
    public String getElementDefinitionCode() {
        return this.elementDefinitionCode;
    }

    /**
     * Set the elementDefinitionCode property: The elementDefinitionCode property.
     *
     * @param elementDefinitionCode the elementDefinitionCode value to set.
     * @return the TaskSaveElementCommand object itself.
     */
    public TaskSaveElementCommand setElementDefinitionCode(String elementDefinitionCode) {
        this.elementDefinitionCode = elementDefinitionCode;
        return this;
    }

    /**
     * Get the elementValues property: The elementValues property.
     *
     * @return the elementValues value.
     */
    public List<TaskElementValue> getElementValues() {
        return this.elementValues;
    }

    /**
     * Set the elementValues property: The elementValues property.
     *
     * @param elementValues the elementValues value to set.
     * @return the TaskSaveElementCommand object itself.
     */
    public TaskSaveElementCommand setElementValues(List<TaskElementValue> elementValues) {
        this.elementValues = elementValues;
        return this;
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @return TRUE if all related valid values are TRUE else FALSE.
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public Boolean getElementValueValid() {
        return TaskSaveElementCommandUtils.getElementValueValid(this);
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param index Element value index
     * @return The requested valid value
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public Boolean getElementValueValidAt(int index) {
        return TaskSaveElementCommandUtils.getElementValueValidAt(this, index);
    }

    /**
     * Set valid to all values
     *
     * @param valid Valid value
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand setElementValueValid(Boolean valid) {
        TaskSaveElementCommandUtils.setElementValueValid(this, valid);

        return this;
    }

    /**
     * Set valid to the selected value
     *
     * @param valid Valid value
     * @param index Element value index
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand setElementValueValidAt(Boolean valid, int index) {
        TaskSaveElementCommandUtils.setElementValueValidAt(this, valid, index);

        return this;
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand setElementValueAsString(String elementValue) {
        TaskSaveElementCommandUtils.setElementValueAsString(this, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand setElementValueAsStringList(List<String> elementValues) {
        TaskSaveElementCommandUtils.setElementValueAsStringList(this, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand addElementValueAsString(String elementValue) {
        TaskSaveElementCommandUtils.addElementValueAsString(this, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand addElementValueAsStringList(List<String> elementValues) {
        TaskSaveElementCommandUtils.addElementValueAsStringList(this, elementValues);

        return this;
    }

    /**
     * Get an element as String
     *
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public String getElementValueAsString() {
        return TaskSaveElementCommandUtils.getElementValueAsString(this);
    }

    /**
     * Get all elements as String
     *
     * @return the elements values.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public List<String> getElementValueAsStringList() {
        return TaskSaveElementCommandUtils.getElementValueAsStringList(this);
    }

    /**
     * Try to get an element as String
     *
     * @return the element value if exists.
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public Optional<String> findElementValueAsString() {
        return TaskSaveElementCommandUtils.findElementValueAsString(this);
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand setElementValueAsDouble(Double elementValue) {
        TaskSaveElementCommandUtils.setElementValueAsDouble(this, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand setElementValueAsDoubleList(List<Double> elementValues) {
        TaskSaveElementCommandUtils.setElementValueAsDoubleList(this, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand addElementValueAsDouble(Double elementValue) {
        TaskSaveElementCommandUtils.addElementValueAsDouble(this, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand addElementValueAsDoubleList(List<Double> elementValues) {
        TaskSaveElementCommandUtils.addElementValueAsDoubleList(this, elementValues);

        return this;
    }

    /**
     * Get an element as Double
     *
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public Double getElementValueAsDouble() {
        return TaskSaveElementCommandUtils.getElementValueAsDouble(this);
    }

    /**
     * Get all elements as Double
     *
     * @return the elements values.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public List<Double> getElementValueAsDoubleList() {
        return TaskSaveElementCommandUtils.getElementValueAsDoubleList(this);
    }

    /**
     * Try to get an element as Double
     *
     * @return the element value if exists.
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public Optional<Double> findElementValueAsDouble() {
        return TaskSaveElementCommandUtils.findElementValueAsDouble(this);
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand setElementValueAsLocalDate(LocalDate elementValue) {
        TaskSaveElementCommandUtils.setElementValueAsLocalDate(this, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand setElementValueAsLocalDateList(List<LocalDate> elementValues) {
        TaskSaveElementCommandUtils.setElementValueAsLocalDateList(this, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand addElementValueAsLocalDate(LocalDate elementValue) {
        TaskSaveElementCommandUtils.addElementValueAsLocalDate(this, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand addElementValueAsLocalDateList(List<LocalDate> elementValues) {
        TaskSaveElementCommandUtils.addElementValueAsLocalDateList(this, elementValues);

        return this;
    }

    /**
     * Get an element as LocalDate
     *
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public LocalDate getElementValueAsLocalDate() {
        return TaskSaveElementCommandUtils.getElementValueAsLocalDate(this);
    }

    /**
     * Get all elements as LocalDate
     *
     * @return the elements values.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public List<LocalDate> getElementValueAsLocalDateList() {
        return TaskSaveElementCommandUtils.getElementValueAsLocalDateList(this);
    }

    /**
     * Try to get an element as LocalDate
     *
     * @return the element value if exists.
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public Optional<LocalDate> findElementValueAsLocalDate() {
        return TaskSaveElementCommandUtils.findElementValueAsLocalDate(this);
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand setElementValueAsMap(Map<String, Object> elementValue) {
        TaskSaveElementCommandUtils.setElementValueAsMap(this, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand setElementValueAsMapList(List<Map<String, Object>> elementValues) {
        TaskSaveElementCommandUtils.setElementValueAsMapList(this, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand addElementValueAsMap(Map<String, Object> elementValue) {
        TaskSaveElementCommandUtils.addElementValueAsMap(this, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand addElementValueAsMapList(List<Map<String, Object>> elementValues) {
        TaskSaveElementCommandUtils.addElementValueAsMapList(this, elementValues);

        return this;
    }

    /**
     * Get an element as Map
     *
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public Map<String, Object> getElementValueAsMap() {
        return TaskSaveElementCommandUtils.getElementValueAsMap(this);
    }

    /**
     * Get all elements as Map
     *
     * @return the elements values.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public List<Map<String, Object>> getElementValueAsMapList() {
        return TaskSaveElementCommandUtils.getElementValueAsMapList(this);
    }

    /**
     * Try to get an element as Map
     *
     * @return the element value if exists.
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public Optional<Map<String, Object>> findElementValueAsMap() {
        return TaskSaveElementCommandUtils.findElementValueAsMap(this);
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand setElementValueAsDocument(TaskElementValueDocumentItem elementValue) {
        TaskSaveElementCommandUtils.setElementValueAsDocument(this, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand setElementValueAsDocumentList(List<TaskElementValueDocumentItem> elementValues) {
        TaskSaveElementCommandUtils.setElementValueAsDocumentList(this, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand addElementValueAsDocument(TaskElementValueDocumentItem elementValue) {
        TaskSaveElementCommandUtils.addElementValueAsDocument(this, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand addElementValueAsDocumentList(List<TaskElementValueDocumentItem> elementValues) {
        TaskSaveElementCommandUtils.addElementValueAsDocumentList(this, elementValues);

        return this;
    }

    /**
     * Get an element as Document
     *
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskElementValueDocumentItem getElementValueAsDocument() {
        return TaskSaveElementCommandUtils.getElementValueAsDocument(this);
    }

    /**
     * Get all elements as Document
     *
     * @return the elements values.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public List<TaskElementValueDocumentItem> getElementValueAsDocumentList() {
        return TaskSaveElementCommandUtils.getElementValueAsDocumentList(this);
    }

    /**
     * Try to get an element as Document
     *
     * @return the element value if exists.
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public Optional<TaskElementValueDocumentItem> findElementValueAsDocument() {
        return TaskSaveElementCommandUtils.findElementValueAsDocument(this);
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand setElementValueAsPrincipal(TaskElementValuePrincipalItem elementValue) {
        TaskSaveElementCommandUtils.setElementValueAsPrincipal(this, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand setElementValueAsPrincipalList(List<TaskElementValuePrincipalItem> elementValues) {
        TaskSaveElementCommandUtils.setElementValueAsPrincipalList(this, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand addElementValueAsPrincipal(TaskElementValuePrincipalItem elementValue) {
        TaskSaveElementCommandUtils.addElementValueAsPrincipal(this, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the object itself
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskSaveElementCommand addElementValueAsPrincipalList(List<TaskElementValuePrincipalItem> elementValues) {
        TaskSaveElementCommandUtils.addElementValueAsPrincipalList(this, elementValues);

        return this;
    }

    /**
     * Get an element as Principal
     *
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public TaskElementValuePrincipalItem getElementValueAsPrincipal() {
        return TaskSaveElementCommandUtils.getElementValueAsPrincipal(this);
    }

    /**
     * Get all elements as Principal
     *
     * @return the elements values.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public List<TaskElementValuePrincipalItem> getElementValueAsPrincipalList() {
        return TaskSaveElementCommandUtils.getElementValueAsPrincipalList(this);
    }

    /**
     * Try to get an element as Principal
     *
     * @return the element value if exists.
     * @deprecated in favor of {@link TaskSaveElementCommandUtils}
     */
    @Deprecated
    public Optional<TaskElementValuePrincipalItem> findElementValueAsPrincipal() {
        return TaskSaveElementCommandUtils.findElementValueAsPrincipal(this);
    }
}
