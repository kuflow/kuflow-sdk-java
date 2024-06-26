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
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.kuflow.rest.util.ProcessUtils;
import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * The Process model.
 */
@Fluent
public final class Process extends AbstractAudited {

    /*
     * Process ID.
     */
    private UUID id;

    /*
     * Process subject.
     */
    private String subject;

    /*
     * Process state
     */
    private ProcessState state;

    /*
     * The processDefinition property.
     */
    private ProcessDefinitionSummary processDefinition;

    /*
     * Process element values, an ElementValueDocument is not allowed.
     */
    private Map<String, List<ProcessElementValue>> elementValues;

    /*
     * Json form values, used when the render type selected is JSON Forms.
     */
    private JsonFormsValue entity;

    /*
     * The initiator property.
     */
    private Principal initiator;

    /*
     * The relatedProcess property.
     */
    private RelatedProcess relatedProcess;

    /*
     * Tenant ID.
     */
    private UUID tenantId;

    /**
     * Creates an instance of Process class.
     */
    public Process() {}

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
     * @return the Process object itself.
     */
    public Process setId(UUID id) {
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
     * @return the Process object itself.
     */
    public Process setSubject(String subject) {
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
     * @return the Process object itself.
     */
    public Process setState(ProcessState state) {
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
     * @return the Process object itself.
     */
    public Process setProcessDefinition(ProcessDefinitionSummary processDefinition) {
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
     * @return the Process object itself.
     */
    public Process setElementValues(Map<String, List<ProcessElementValue>> elementValues) {
        this.elementValues = elementValues;
        return this;
    }

    /**
     * Get the entity property: Json form values, used when the render type selected is JSON Forms.
     *
     * @return the entity value.
     */
    public JsonFormsValue getEntity() {
        return this.entity;
    }

    /**
     * Set the entity property: Json form values, used when the render type selected is JSON Forms.
     *
     * @param entity the entity value to set.
     * @return the Process object itself.
     */
    public Process setEntity(JsonFormsValue entity) {
        this.entity = entity;
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
     * @return the Process object itself.
     */
    public Process setInitiator(Principal initiator) {
        this.initiator = initiator;
        return this;
    }

    /**
     * Get the relatedProcess property: The relatedProcess property.
     *
     * @return the relatedProcess value.
     */
    public RelatedProcess getRelatedProcess() {
        return this.relatedProcess;
    }

    /**
     * Set the relatedProcess property: The relatedProcess property.
     *
     * @param relatedProcess the relatedProcess value to set.
     * @return the Process object itself.
     */
    public Process setRelatedProcess(RelatedProcess relatedProcess) {
        this.relatedProcess = relatedProcess;
        return this;
    }

    /**
     * Get the tenantId property: Tenant ID.
     *
     * @return the tenantId value.
     */
    public UUID getTenantId() {
        return this.tenantId;
    }

    /**
     * Set the tenantId property: Tenant ID.
     *
     * @param tenantId the tenantId value to set.
     * @return the Process object itself.
     */
    public Process setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Process setObjectType(AuditedObjectType objectType) {
        super.setObjectType(objectType);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Process setCreatedBy(UUID createdBy) {
        super.setCreatedBy(createdBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Process setCreatedAt(OffsetDateTime createdAt) {
        super.setCreatedAt(createdAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Process setLastModifiedBy(UUID lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Process setLastModifiedAt(OffsetDateTime lastModifiedAt) {
        super.setLastModifiedAt(lastModifiedAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("objectType", getObjectType() == null ? null : getObjectType().toString());
        jsonWriter.writeStringField("createdBy", Objects.toString(getCreatedBy(), null));
        jsonWriter.writeStringField(
            "createdAt",
            getCreatedAt() == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(getCreatedAt())
        );
        jsonWriter.writeStringField("lastModifiedBy", Objects.toString(getLastModifiedBy(), null));
        jsonWriter.writeStringField(
            "lastModifiedAt",
            getLastModifiedAt() == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(getLastModifiedAt())
        );
        jsonWriter.writeJsonField("processDefinition", this.processDefinition);
        jsonWriter.writeStringField("id", Objects.toString(this.id, null));
        jsonWriter.writeStringField("subject", this.subject);
        jsonWriter.writeStringField("state", this.state == null ? null : this.state.toString());
        jsonWriter.writeMapField(
            "elementValues",
            this.elementValues,
            (writer, element) -> writer.writeArray(element, (writer1, element1) -> writer1.writeJson(element1))
        );
        jsonWriter.writeJsonField("entity", this.entity);
        jsonWriter.writeJsonField("initiator", this.initiator);
        jsonWriter.writeJsonField("relatedProcess", this.relatedProcess);
        jsonWriter.writeStringField("tenantId", Objects.toString(this.tenantId, null));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Process from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of Process if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Process.
     */
    public static Process fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Process deserializedProcess = new Process();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("objectType".equals(fieldName)) {
                    deserializedProcess.setObjectType(AuditedObjectType.fromString(reader.getString()));
                } else if ("createdBy".equals(fieldName)) {
                    deserializedProcess.setCreatedBy(reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString())));
                } else if ("createdAt".equals(fieldName)) {
                    deserializedProcess.setCreatedAt(reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString())));
                } else if ("lastModifiedBy".equals(fieldName)) {
                    deserializedProcess.setLastModifiedBy(reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString())));
                } else if ("lastModifiedAt".equals(fieldName)) {
                    deserializedProcess.setLastModifiedAt(
                        reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()))
                    );
                } else if ("processDefinition".equals(fieldName)) {
                    deserializedProcess.processDefinition = ProcessDefinitionSummary.fromJson(reader);
                } else if ("id".equals(fieldName)) {
                    deserializedProcess.id = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("subject".equals(fieldName)) {
                    deserializedProcess.subject = reader.getString();
                } else if ("state".equals(fieldName)) {
                    deserializedProcess.state = ProcessState.fromString(reader.getString());
                } else if ("elementValues".equals(fieldName)) {
                    Map<String, List<ProcessElementValue>> elementValues = reader.readMap(
                        reader1 -> reader1.readArray(reader2 -> ProcessElementValue.fromJson(reader2))
                    );
                    deserializedProcess.elementValues = elementValues;
                } else if ("entity".equals(fieldName)) {
                    deserializedProcess.entity = JsonFormsValue.fromJson(reader);
                } else if ("initiator".equals(fieldName)) {
                    deserializedProcess.initiator = Principal.fromJson(reader);
                } else if ("relatedProcess".equals(fieldName)) {
                    deserializedProcess.relatedProcess = RelatedProcess.fromJson(reader);
                } else if ("tenantId".equals(fieldName)) {
                    deserializedProcess.tenantId = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcess;
        });
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param elementDefinitionCode Element Definition Code
     * @return TRUE if all related valid values are TRUE else FALSE.
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public Boolean getElementValueValid(String elementDefinitionCode) {
        return ProcessUtils.getElementValueValid(this, elementDefinitionCode);
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param elementDefinitionCode Element Definition Code
     * @param index Element value index
     * @return The requested valid value
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public Boolean getElementValueValidAt(String elementDefinitionCode, int index) {
        return ProcessUtils.getElementValueValidAt(this, elementDefinitionCode, index);
    }

    /**
     * Set valid to all values
     *
     * @param elementDefinitionCode Element Definition Code
     * @param valid Valid value
     * @return the object itself
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public Process setElementValueValid(String elementDefinitionCode, Boolean valid) {
        ProcessUtils.setElementValueValid(this, elementDefinitionCode, valid);

        return this;
    }

    /**
     * Set valid to the selected value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param valid Valid value
     * @param index Element value index
     * @return the object itself
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public Process setElementValueValidAt(String elementDefinitionCode, Boolean valid, int index) {
        ProcessUtils.setElementValueValidAt(this, elementDefinitionCode, valid, index);

        return this;
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public Process setElementValueAsString(String elementDefinitionCode, String elementValue) {
        ProcessUtils.setElementValueAsString(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public Process setElementValueAsStringList(String elementDefinitionCode, List<String> elementValues) {
        ProcessUtils.setElementValueAsStringList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     * @return the object itself
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public Process addElementValueAsString(String elementDefinitionCode, String elementValue) {
        ProcessUtils.addElementValueAsString(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     * @return the object itself
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public Process addElementValueAsStringList(String elementDefinitionCode, List<String> elementValues) {
        ProcessUtils.addElementValueAsStringList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Get an element as String
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public String getElementValueAsString(String elementDefinitionCode) {
        return ProcessUtils.getElementValueAsString(this, elementDefinitionCode);
    }

    /**
     * Get all elements as String
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public List<String> getElementValueAsStringList(String elementDefinitionCode) {
        return ProcessUtils.getElementValueAsStringList(this, elementDefinitionCode);
    }

    /**
     * Try to get an element as String
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public Optional<String> findElementValueAsString(String elementDefinitionCode) {
        return ProcessUtils.findElementValueAsString(this, elementDefinitionCode);
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public Process setElementValueAsDouble(String elementDefinitionCode, Double elementValue) {
        ProcessUtils.setElementValueAsDouble(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public Process setElementValueAsDoubleList(String elementDefinitionCode, List<Double> elementValues) {
        ProcessUtils.setElementValueAsDoubleList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     * @return the object itself
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public Process addElementValueAsDouble(String elementDefinitionCode, Double elementValue) {
        ProcessUtils.addElementValueAsDouble(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     * @return the object itself
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public Process addElementValueAsDoubleList(String elementDefinitionCode, List<Double> elementValues) {
        ProcessUtils.addElementValueAsDoubleList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Get an element as Double
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public Double getElementValueAsDouble(String elementDefinitionCode) {
        return ProcessUtils.getElementValueAsDouble(this, elementDefinitionCode);
    }

    /**
     * Get all elements as Double
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public List<Double> getElementValueAsDoubleList(String elementDefinitionCode) {
        return ProcessUtils.getElementValueAsDoubleList(this, elementDefinitionCode);
    }

    /**
     * Try to get an element as Double
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public Optional<Double> findElementValueAsDouble(String elementDefinitionCode) {
        return ProcessUtils.findElementValueAsDouble(this, elementDefinitionCode);
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public Process setElementValueAsLocalDate(String elementDefinitionCode, LocalDate elementValue) {
        ProcessUtils.setElementValueAsLocalDate(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public Process setElementValueAsLocalDateList(String elementDefinitionCode, List<LocalDate> elementValues) {
        ProcessUtils.setElementValueAsLocalDateList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     * @return the object itself
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public Process addElementValueAsLocalDate(String elementDefinitionCode, LocalDate elementValue) {
        ProcessUtils.addElementValueAsLocalDate(this, elementDefinitionCode, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     * @return the object itself
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public Process addElementValueAsLocalDateList(String elementDefinitionCode, List<LocalDate> elementValues) {
        ProcessUtils.addElementValueAsLocalDateList(this, elementDefinitionCode, elementValues);

        return this;
    }

    /**
     * Get an element as LocalDate
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public LocalDate getElementValueAsLocalDate(String elementDefinitionCode) {
        return ProcessUtils.getElementValueAsLocalDate(this, elementDefinitionCode);
    }

    /**
     * Get all elements as LocalDate
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public List<LocalDate> getElementValueAsLocalDateList(String elementDefinitionCode) {
        return ProcessUtils.getElementValueAsLocalDateList(this, elementDefinitionCode);
    }

    /**
     * Try to get an element as LocalDate
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     * @deprecated in favor of {@link ProcessUtils}
     */
    @Deprecated
    public Optional<LocalDate> findElementValueAsLocalDate(String elementDefinitionCode) {
        return ProcessUtils.findElementValueAsLocalDate(this, elementDefinitionCode);
    }
}
