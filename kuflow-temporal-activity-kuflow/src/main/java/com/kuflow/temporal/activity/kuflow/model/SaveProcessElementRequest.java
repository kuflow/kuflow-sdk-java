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
import com.kuflow.rest.model.ProcessElementValue;
import com.kuflow.temporal.activity.kuflow.util.SaveProcessElementRequestUtils;
import com.kuflow.temporal.common.model.AbstractModel;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SaveProcessElementRequest extends AbstractModel {

    private UUID processId;

    private String elementDefinitionCode;

    private List<ProcessElementValue> elementValues;

    public UUID getProcessId() {
        return this.processId;
    }

    /**
     * Set the processId property
     *
     * @param processId the processId value to set.
     * @return the SaveProcessElementRequest object itself.
     */
    public SaveProcessElementRequest setProcessId(UUID processId) {
        this.processId = processId;

        return this;
    }

    public String getElementDefinitionCode() {
        return this.elementDefinitionCode;
    }

    /**
     * Set the elementDefinitionCode property
     *
     * @param elementDefinitionCode the elementDefinitionCode value to set.
     * @return the SaveProcessElementRequest object itself.
     */
    public SaveProcessElementRequest setElementDefinitionCode(String elementDefinitionCode) {
        this.elementDefinitionCode = elementDefinitionCode;

        return this;
    }

    /**
     * Get the elementValues property: The elementValues property.
     *
     * @return the elementValues value.
     */
    public List<ProcessElementValue> getElementValues() {
        if (this.elementValues == null) {
            return null;
        }

        return unmodifiableList(this.elementValues);
    }

    /**
     * Set the elementValues property: The elementValues property.
     *
     * @param elementValues the elementValues value to set.
     * @return the SaveProcessElementRequest object itself.
     */
    public SaveProcessElementRequest setElementValues(List<ProcessElementValue> elementValues) {
        this.elementValues = elementValues;

        return this;
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @return TRUE if all related valid values are TRUE else FALSE.
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public Boolean getElementValueValid() {
        return SaveProcessElementRequestUtils.getElementValueValid(this);
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param index Element value index
     * @return The requested valid value
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public Boolean getElementValueValidAt(int index) {
        return SaveProcessElementRequestUtils.getElementValueValidAt(this, index);
    }

    /**
     * Set valid to all values
     *
     * @param valid Valid value
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveProcessElementRequest setElementValueValid(Boolean valid) {
        SaveProcessElementRequestUtils.setElementValueValid(this, valid);

        return this;
    }

    /**
     * Set valid to the selected value
     *
     * @param valid Valid value
     * @param index Element value index
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveProcessElementRequest setElementValueValidAt(Boolean valid, int index) {
        SaveProcessElementRequestUtils.setElementValueValidAt(this, valid, index);

        return this;
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveProcessElementRequest setElementValueAsString(String elementValue) {
        SaveProcessElementRequestUtils.setElementValueAsString(this, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveProcessElementRequest setElementValueAsStringList(List<String> elementValues) {
        SaveProcessElementRequestUtils.setElementValueAsStringList(this, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveProcessElementRequest addElementValueAsString(String elementValue) {
        SaveProcessElementRequestUtils.addElementValueAsString(this, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveProcessElementRequest addElementValueAsStringList(List<String> elementValues) {
        SaveProcessElementRequestUtils.addElementValueAsStringList(this, elementValues);

        return this;
    }

    /**
     * Get an element as String
     *
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public String getElementValueAsString() {
        return SaveProcessElementRequestUtils.getElementValueAsString(this);
    }

    /**
     * Get all elements as String
     *
     * @return the elements values.
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public List<String> getElementValueAsStringList() {
        return SaveProcessElementRequestUtils.getElementValueAsStringList(this);
    }

    /**
     * Try to get an element as String
     *
     * @return the element value if exists.
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public Optional<String> findElementValueAsString() {
        return SaveProcessElementRequestUtils.findElementValueAsString(this);
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveProcessElementRequest setElementValueAsDouble(Double elementValue) {
        SaveProcessElementRequestUtils.setElementValueAsDouble(this, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveProcessElementRequest setElementValueAsDoubleList(List<Double> elementValues) {
        SaveProcessElementRequestUtils.setElementValueAsDoubleList(this, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveProcessElementRequest addElementValueAsDouble(Double elementValue) {
        SaveProcessElementRequestUtils.addElementValueAsDouble(this, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveProcessElementRequest addElementValueAsDoubleList(List<Double> elementValues) {
        SaveProcessElementRequestUtils.addElementValueAsDoubleList(this, elementValues);

        return this;
    }

    /**
     * Get an element as Double
     *
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public Double getElementValueAsDouble() {
        return SaveProcessElementRequestUtils.getElementValueAsDouble(this);
    }

    /**
     * Get all elements as Double
     *
     * @return the elements values.
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public List<Double> getElementValueAsDoubleList() {
        return SaveProcessElementRequestUtils.getElementValueAsDoubleList(this);
    }

    /**
     * Try to get an element as Double
     *
     * @return the element value if exists.
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public Optional<Double> findElementValueAsDouble() {
        return SaveProcessElementRequestUtils.findElementValueAsDouble(this);
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveProcessElementRequest setElementValueAsLocalDate(LocalDate elementValue) {
        SaveProcessElementRequestUtils.setElementValueAsLocalDate(this, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveProcessElementRequest setElementValueAsLocalDateList(List<LocalDate> elementValues) {
        SaveProcessElementRequestUtils.setElementValueAsLocalDateList(this, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveProcessElementRequest addElementValueAsLocalDate(LocalDate elementValue) {
        SaveProcessElementRequestUtils.addElementValueAsLocalDate(this, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the Task object itself.
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public SaveProcessElementRequest addElementValueAsLocalDateList(List<LocalDate> elementValues) {
        SaveProcessElementRequestUtils.addElementValueAsLocalDateList(this, elementValues);

        return this;
    }

    /**
     * Get an element as LocalDate
     *
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public LocalDate getElementValueAsLocalDate() {
        return SaveProcessElementRequestUtils.getElementValueAsLocalDate(this);
    }

    /**
     * Get all elements as LocalDate
     *
     * @return the elements values.
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public List<LocalDate> getElementValueAsLocalDateList() {
        return SaveProcessElementRequestUtils.getElementValueAsLocalDateList(this);
    }

    /**
     * Try to get an element as LocalDate
     *
     * @return the element value if exists.
     * @deprecated in favor of {@link SaveProcessElementRequestUtils}
     */
    @JsonIgnore
    @Deprecated(since = "2.1.2", forRemoval = true)
    public Optional<LocalDate> findElementValueAsLocalDate() {
        return SaveProcessElementRequestUtils.findElementValueAsLocalDate(this);
    }
}
