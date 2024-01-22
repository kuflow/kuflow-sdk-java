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
import com.kuflow.rest.util.ProcessPageItemUtils;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * The ProcessPageItem model.
 */
@Fluent
public final class ProcessPageItem extends AbstractAudited {

    /*
     * Process ID.
     */
    @JsonProperty(value = "id")
    private UUID id;

    /*
     * Process subject.
     */
    @JsonProperty(value = "subject")
    private String subject;

    /*
     * Process state
     */
    @JsonProperty(value = "state")
    private ProcessState state;

    /*
     * The processDefinition property.
     */
    @JsonProperty(value = "processDefinition", required = true)
    private ProcessDefinitionSummary processDefinition;

    /*
     * Process element values, an ElementValueDocument is not allowed.
     */
    @JsonProperty(value = "elementValues")
    private Map<String, List<ProcessElementValue>> elementValues;

    /*
     * The initiator property.
     */
    @JsonProperty(value = "initiator")
    private Principal initiator;

    /**
     * Creates an instance of ProcessPageItem class.
     */
    public ProcessPageItem() {}

    /**
     * Get the id property: Process ID.
     *
     * @return the id value.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Set the id property: Process ID.
     *
     * @param id the id value to set.
     * @return the ProcessPageItem object itself.
     */
    public ProcessPageItem setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the subject property: Process subject.
     *
     * @return the subject value.
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     * Set the subject property: Process subject.
     *
     * @param subject the subject value to set.
     * @return the ProcessPageItem object itself.
     */
    public ProcessPageItem setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * Get the state property: Process state.
     *
     * @return the state value.
     */
    public ProcessState getState() {
        return this.state;
    }

    /**
     * Set the state property: Process state.
     *
     * @param state the state value to set.
     * @return the ProcessPageItem object itself.
     */
    public ProcessPageItem setState(ProcessState state) {
        this.state = state;
        return this;
    }

    /**
     * Get the processDefinition property: The processDefinition property.
     *
     * @return the processDefinition value.
     */
    public ProcessDefinitionSummary getProcessDefinition() {
        return this.processDefinition;
    }

    /**
     * Set the processDefinition property: The processDefinition property.
     *
     * @param processDefinition the processDefinition value to set.
     * @return the ProcessPageItem object itself.
     */
    public ProcessPageItem setProcessDefinition(ProcessDefinitionSummary processDefinition) {
        this.processDefinition = processDefinition;
        return this;
    }

    /**
     * Get the elementValues property: Process element values, an ElementValueDocument is not allowed.
     *
     * @return the elementValues value.
     */
    public Map<String, List<ProcessElementValue>> getElementValues() {
        return this.elementValues;
    }

    /**
     * Set the elementValues property: Process element values, an ElementValueDocument is not allowed.
     *
     * @param elementValues the elementValues value to set.
     * @return the ProcessPageItem object itself.
     */
    public ProcessPageItem setElementValues(Map<String, List<ProcessElementValue>> elementValues) {
        this.elementValues = elementValues;
        return this;
    }

    /**
     * Get the initiator property: The initiator property.
     *
     * @return the initiator value.
     */
    public Principal getInitiator() {
        return this.initiator;
    }

    /**
     * Set the initiator property: The initiator property.
     *
     * @param initiator the initiator value to set.
     * @return the ProcessPageItem object itself.
     */
    public ProcessPageItem setInitiator(Principal initiator) {
        this.initiator = initiator;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcessPageItem setObjectType(AuditedObjectType objectType) {
        super.setObjectType(objectType);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcessPageItem setCreatedBy(UUID createdBy) {
        super.setCreatedBy(createdBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcessPageItem setCreatedAt(OffsetDateTime createdAt) {
        super.setCreatedAt(createdAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcessPageItem setLastModifiedBy(UUID lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcessPageItem setLastModifiedAt(OffsetDateTime lastModifiedAt) {
        super.setLastModifiedAt(lastModifiedAt);
        return this;
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param elementDefinitionCode Element Definition Code
     * @return TRUE if all related valid values are TRUE else FALSE.
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public Boolean getElementValueValid(String elementDefinitionCode) {
        return ProcessPageItemUtils.getElementValueValid(this, elementDefinitionCode);
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param elementDefinitionCode Element Definition Code
     * @param index Element value index
     * @return The requested valid value
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public Boolean getElementValueValidAt(String elementDefinitionCode, int index) {
        return ProcessPageItemUtils.getElementValueValidAt(this, elementDefinitionCode, index);
    }

    /**
     * Set valid to all values
     *
     * @param elementDefinitionCode Element Definition Code
     * @param valid Valid value
     * @return the object itself
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public ProcessPageItem setElementValueValid(String elementDefinitionCode, Boolean valid) {
        ProcessPageItemUtils.setElementValueValid(this, elementDefinitionCode, valid);

        return this;
    }

    /**
     * Set valid to the selected value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param valid Valid value
     * @param index Element value index
     * @return the object itself
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public ProcessPageItem setElementValueValidAt(String elementDefinitionCode, Boolean valid, int index) {
        ProcessPageItemUtils.setElementValueValidAt(this, elementDefinitionCode, valid, index);

        return this;
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public ProcessPageItem setElementValueAsString(String elementDefinitionCode, String elementValue) {
        ProcessPageItemUtils.setElementValueAsString(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public ProcessPageItem setElementValueAsStringList(String elementDefinitionCode, List<String> elementValues) {
        ProcessPageItemUtils.setElementValueAsStringList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     * @return the object itself
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public ProcessPageItem addElementValueAsString(String elementDefinitionCode, String elementValue) {
        ProcessPageItemUtils.addElementValueAsString(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     * @return the object itself
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public ProcessPageItem addElementValueAsStringList(String elementDefinitionCode, List<String> elementValues) {
        ProcessPageItemUtils.addElementValueAsStringList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Get an element as String
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public String getElementValueAsString(String elementDefinitionCode) {
        return ProcessPageItemUtils.getElementValueAsString(this, elementDefinitionCode);
    }

    /**
     * Get all elements as String
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public List<String> getElementValueAsStringList(String elementDefinitionCode) {
        return ProcessPageItemUtils.getElementValueAsStringList(this, elementDefinitionCode);
    }

    /**
     * Try to get an element as String
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public Optional<String> findElementValueAsString(String elementDefinitionCode) {
        return ProcessPageItemUtils.findElementValueAsString(this, elementDefinitionCode);
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public ProcessPageItem setElementValueAsDouble(String elementDefinitionCode, Double elementValue) {
        ProcessPageItemUtils.setElementValueAsDouble(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public ProcessPageItem setElementValueAsDoubleList(String elementDefinitionCode, List<Double> elementValues) {
        ProcessPageItemUtils.setElementValueAsDoubleList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     * @return the object itself
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public ProcessPageItem addElementValueAsDouble(String elementDefinitionCode, Double elementValue) {
        ProcessPageItemUtils.addElementValueAsDouble(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     * @return the object itself
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public ProcessPageItem addElementValueAsDoubleList(String elementDefinitionCode, List<Double> elementValues) {
        ProcessPageItemUtils.addElementValueAsDoubleList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Get an element as Double
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public Double getElementValueAsDouble(String elementDefinitionCode) {
        return ProcessPageItemUtils.getElementValueAsDouble(this, elementDefinitionCode);
    }

    /**
     * Get all elements as Double
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public List<Double> getElementValueAsDoubleList(String elementDefinitionCode) {
        return ProcessPageItemUtils.getElementValueAsDoubleList(this, elementDefinitionCode);
    }

    /**
     * Try to get an element as Double
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public Optional<Double> findElementValueAsDouble(String elementDefinitionCode) {
        return ProcessPageItemUtils.findElementValueAsDouble(this, elementDefinitionCode);
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public ProcessPageItem setElementValueAsLocalDate(String elementDefinitionCode, LocalDate elementValue) {
        ProcessPageItemUtils.setElementValueAsLocalDate(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public ProcessPageItem setElementValueAsLocalDateList(String elementDefinitionCode, List<LocalDate> elementValues) {
        ProcessPageItemUtils.setElementValueAsLocalDateList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     * @return the object itself
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public ProcessPageItem addElementValueAsLocalDate(String elementDefinitionCode, LocalDate elementValue) {
        ProcessPageItemUtils.addElementValueAsLocalDate(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     * @return the object itself
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public ProcessPageItem addElementValueAsLocalDateList(String elementDefinitionCode, List<LocalDate> elementValues) {
        ProcessPageItemUtils.addElementValueAsLocalDateList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Get an element as LocalDate
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public LocalDate getElementValueAsLocalDate(String elementDefinitionCode) {
        return ProcessPageItemUtils.getElementValueAsLocalDate(this, elementDefinitionCode);
    }

    /**
     * Get all elements as LocalDate
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public List<LocalDate> getElementValueAsLocalDateList(String elementDefinitionCode) {
        return ProcessPageItemUtils.getElementValueAsLocalDateList(this, elementDefinitionCode);
    }

    /**
     * Try to get an element as LocalDate
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     * @deprecated in favor of {@link ProcessPageItemUtils}
     */
    @Deprecated
    public Optional<LocalDate> findElementValueAsLocalDate(String elementDefinitionCode) {
        return ProcessPageItemUtils.findElementValueAsLocalDate(this, elementDefinitionCode);
    }
}
