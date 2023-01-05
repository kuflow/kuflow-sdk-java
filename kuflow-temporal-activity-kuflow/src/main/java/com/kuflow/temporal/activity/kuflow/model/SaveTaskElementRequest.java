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
import static java.util.Collections.unmodifiableList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kuflow.rest.KuFlowRestClientException;
import com.kuflow.rest.model.TaskElementValue;
import com.kuflow.rest.model.TaskElementValueDocumentItem;
import com.kuflow.rest.model.TaskElementValuePrincipalItem;
import com.kuflow.temporal.activity.kuflow.util.ProcessElementValueAccessorSaveTaskElementRequest;
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
     */
    @JsonIgnore
    public Boolean getElementValueValid() {
        return getElementValueOfValid(ProcessElementValueAccessorSaveTaskElementRequest.of(this));
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param index Element value index
     * @return The requested valid value
     */
    @JsonIgnore
    public Boolean getElementValueValidAt(int index) {
        return getElementValueOfValidAt(ProcessElementValueAccessorSaveTaskElementRequest.of(this), index);
    }

    /**
     * Set valid to all values
     *
     * @param valid Valid value
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest setElementValueValid(Boolean valid) {
        setElementValueOfValid(ProcessElementValueAccessorSaveTaskElementRequest.of(this), valid);

        return this;
    }

    /**
     * Set valid to the selected value
     *
     * @param valid Valid value
     * @param index Element value index
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest setElementValueValidAt(Boolean valid, int index) {
        setElementValueOfValidAt(ProcessElementValueAccessorSaveTaskElementRequest.of(this), valid, index);

        return this;
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest setElementValueAsString(String elementValue) {
        setElementValueOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest setElementValueAsStringList(List<String> elementValues) {
        setElementValuesOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest addElementValueAsString(String elementValue) {
        addElementValueOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest addElementValueAsStringList(List<String> elementValues) {
        addElementValuesOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValues);

        return this;
    }

    /**
     * Get an element as String
     *
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     */
    @JsonIgnore
    public String getElementValueAsString() {
        return getElementValueOfAsString(ProcessElementValueAccessorSaveTaskElementRequest.of(this));
    }

    /**
     * Get all elements as String
     *
     * @return the elements values.
     */
    @JsonIgnore
    public List<String> getElementValueAsStringList() {
        return getElementValueOfAsStringList(ProcessElementValueAccessorSaveTaskElementRequest.of(this));
    }

    /**
     * Try to get an element as String
     *
     * @return the element value if exists.
     */
    @JsonIgnore
    public Optional<String> findElementValueAsString() {
        return findElementValueOfAsString(ProcessElementValueAccessorSaveTaskElementRequest.of(this));
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest setElementValueAsDouble(Double elementValue) {
        setElementValueOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest setElementValueAsDoubleList(List<Double> elementValues) {
        setElementValuesOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest addElementValueAsDouble(Double elementValue) {
        addElementValueOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest addElementValueAsDoubleList(List<Double> elementValues) {
        addElementValuesOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValues);

        return this;
    }

    /**
     * Get an element as Double
     *
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     */
    @JsonIgnore
    public Double getElementValueAsDouble() {
        return getElementValueOfAsDouble(ProcessElementValueAccessorSaveTaskElementRequest.of(this));
    }

    /**
     * Get all elements as Double
     *
     * @return the elements values.
     */
    @JsonIgnore
    public List<Double> getElementValueAsDoubleList() {
        return getElementValueOfAsDoubleList(ProcessElementValueAccessorSaveTaskElementRequest.of(this));
    }

    /**
     * Try to get an element as Double
     *
     * @return the element value if exists.
     */
    @JsonIgnore
    public Optional<Double> findElementValueAsDouble() {
        return findElementValueOfAsDouble(ProcessElementValueAccessorSaveTaskElementRequest.of(this));
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest setElementValueAsLocalDate(LocalDate elementValue) {
        setElementValueOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest setElementValueAsLocalDateList(List<LocalDate> elementValues) {
        setElementValuesOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest addElementValueAsLocalDate(LocalDate elementValue) {
        addElementValueOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest addElementValueAsLocalDateList(List<LocalDate> elementValues) {
        addElementValuesOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValues);

        return this;
    }

    /**
     * Get an element as LocalDate
     *
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     */
    @JsonIgnore
    public LocalDate getElementValueAsLocalDate() {
        return getElementValueOfAsLocalDate(ProcessElementValueAccessorSaveTaskElementRequest.of(this));
    }

    /**
     * Get all elements as LocalDate
     *
     * @return the elements values.
     */
    @JsonIgnore
    public List<LocalDate> getElementValueAsLocalDateList() {
        return getElementValueOfAsLocalDateList(ProcessElementValueAccessorSaveTaskElementRequest.of(this));
    }

    /**
     * Try to get an element as LocalDate
     *
     * @return the element value if exists.
     */
    @JsonIgnore
    public Optional<LocalDate> findElementValueAsLocalDate() {
        return findElementValueOfAsLocalDate(ProcessElementValueAccessorSaveTaskElementRequest.of(this));
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest setElementValueAsMap(Map<String, Object> elementValue) {
        setElementValueOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest setElementValueAsMapList(List<Map<String, Object>> elementValues) {
        setElementValuesOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest addElementValueAsMap(Map<String, Object> elementValue) {
        addElementValueOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest addElementValueAsMapList(List<Map<String, Object>> elementValues) {
        addElementValuesOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValues);

        return this;
    }

    /**
     * Get an element as Map
     *
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     */
    @JsonIgnore
    public Map<String, Object> getElementValueAsMap() {
        return getElementValueOfAsMap(ProcessElementValueAccessorSaveTaskElementRequest.of(this));
    }

    /**
     * Get all elements as Map
     *
     * @return the elements values.
     */
    @JsonIgnore
    public List<Map<String, Object>> getElementValueAsMapList() {
        return getElementValueOfAsMapList(ProcessElementValueAccessorSaveTaskElementRequest.of(this));
    }

    /**
     * Try to get an element as Map
     *
     * @return the element value if exists.
     */
    @JsonIgnore
    public Optional<Map<String, Object>> findElementValueAsMap() {
        return findElementValueOfAsMap(ProcessElementValueAccessorSaveTaskElementRequest.of(this));
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest setElementValueAsDocument(TaskElementValueDocumentItem elementValue) {
        setElementValueOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest setElementValueAsDocumentList(List<TaskElementValueDocumentItem> elementValues) {
        setElementValuesOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest addElementValueAsDocument(TaskElementValueDocumentItem elementValue) {
        addElementValueOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest addElementValueAsDocumentList(List<TaskElementValueDocumentItem> elementValues) {
        addElementValuesOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValues);

        return this;
    }

    /**
     * Get an element as Document
     *
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     */
    @JsonIgnore
    public TaskElementValueDocumentItem getElementValueAsDocument() {
        return getElementValueOfAsDocument(ProcessElementValueAccessorSaveTaskElementRequest.of(this));
    }

    /**
     * Get all elements as Document
     *
     * @return the elements values.
     */
    @JsonIgnore
    public List<TaskElementValueDocumentItem> getElementValueAsDocumentList() {
        return getElementValueOfAsDocumentList(ProcessElementValueAccessorSaveTaskElementRequest.of(this));
    }

    /**
     * Try to get an element as Document
     *
     * @return the element value if exists.
     */
    @JsonIgnore
    public Optional<TaskElementValueDocumentItem> findElementValueAsDocument() {
        return findElementValueOfAsDocument(ProcessElementValueAccessorSaveTaskElementRequest.of(this));
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest setElementValueAsPrincipal(TaskElementValuePrincipalItem elementValue) {
        setElementValueOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest setElementValueAsPrincipalList(List<TaskElementValuePrincipalItem> elementValues) {
        setElementValuesOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest addElementValueAsPrincipal(TaskElementValuePrincipalItem elementValue) {
        addElementValueOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     */
    @JsonIgnore
    public SaveTaskElementRequest addElementValueAsPrincipalList(List<TaskElementValuePrincipalItem> elementValues) {
        addElementValuesOf(ProcessElementValueAccessorSaveTaskElementRequest.of(this), elementValues);

        return this;
    }

    /**
     * Get an element as Principal
     *
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     */
    @JsonIgnore
    public TaskElementValuePrincipalItem getElementValueAsPrincipal() {
        return getElementValueOfAsPrincipal(ProcessElementValueAccessorSaveTaskElementRequest.of(this));
    }

    /**
     * Get all elements as Principal
     *
     * @return the elements values.
     */
    @JsonIgnore
    public List<TaskElementValuePrincipalItem> getElementValueAsPrincipalList() {
        return getElementValueOfAsPrincipalList(ProcessElementValueAccessorSaveTaskElementRequest.of(this));
    }

    /**
     * Try to get an element as Principal
     *
     * @return the element value if exists.
     */
    @JsonIgnore
    public Optional<TaskElementValuePrincipalItem> findElementValueAsPrincipal() {
        return findElementValueOfAsPrincipal(ProcessElementValueAccessorSaveTaskElementRequest.of(this));
    }
}
