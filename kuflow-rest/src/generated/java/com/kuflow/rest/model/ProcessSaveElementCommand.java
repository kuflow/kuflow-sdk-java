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
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.kuflow.rest.util.ProcessSaveElementCommandUtils;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Command to save process element.
 */
@Fluent
public final class ProcessSaveElementCommand implements JsonSerializable<ProcessSaveElementCommand> {

    /*
     * The elementDefinitionCode property.
     */
    private String elementDefinitionCode;

    /*
     * The elementValues property.
     */
    private List<ProcessElementValue> elementValues;

    /**
     * Creates an instance of ProcessSaveElementCommand class.
     */
    public ProcessSaveElementCommand() {}

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
     * @return the ProcessSaveElementCommand object itself.
     */
    public ProcessSaveElementCommand setElementDefinitionCode(String elementDefinitionCode) {
        this.elementDefinitionCode = elementDefinitionCode;
        return this;
    }

    /**
     * Get the elementValues property: The elementValues property.
     *
     * @return the elementValues value.
     */
    public List<ProcessElementValue> getElementValues() {
        return this.elementValues;
    }

    /**
     * Set the elementValues property: The elementValues property.
     *
     * @param elementValues the elementValues value to set.
     * @return the ProcessSaveElementCommand object itself.
     */
    public ProcessSaveElementCommand setElementValues(List<ProcessElementValue> elementValues) {
        this.elementValues = elementValues;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("elementDefinitionCode", this.elementDefinitionCode);
        jsonWriter.writeArrayField("elementValues", this.elementValues, (writer, element) -> writer.writeJson(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessSaveElementCommand from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessSaveElementCommand if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessSaveElementCommand.
     */
    public static ProcessSaveElementCommand fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessSaveElementCommand deserializedProcessSaveElementCommand = new ProcessSaveElementCommand();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("elementDefinitionCode".equals(fieldName)) {
                    deserializedProcessSaveElementCommand.elementDefinitionCode = reader.getString();
                } else if ("elementValues".equals(fieldName)) {
                    List<ProcessElementValue> elementValues = reader.readArray(reader1 -> ProcessElementValue.fromJson(reader1));
                    deserializedProcessSaveElementCommand.elementValues = elementValues;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessSaveElementCommand;
        });
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @return TRUE if all related valid values are TRUE else FALSE.
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public Boolean getElementValueValid() {
        return ProcessSaveElementCommandUtils.getElementValueValid(this);
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param index Element value index
     * @return The requested valid value
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public Boolean getElementValueValidAt(int index) {
        return ProcessSaveElementCommandUtils.getElementValueValidAt(this, index);
    }

    /**
     * Set valid to all values
     *
     * @param valid Valid value
     * @return the object itself
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public ProcessSaveElementCommand setElementValueValid(Boolean valid) {
        ProcessSaveElementCommandUtils.setElementValueValid(this, valid);

        return this;
    }

    /**
     * Set valid to the selected value
     *
     * @param valid Valid value
     * @param index Element value index
     * @return the object itself
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public ProcessSaveElementCommand setElementValueValidAt(Boolean valid, int index) {
        ProcessSaveElementCommandUtils.setElementValueValidAt(this, valid, index);

        return this;
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public ProcessSaveElementCommand setElementValueAsString(String elementValue) {
        ProcessSaveElementCommandUtils.setElementValueAsString(this, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public ProcessSaveElementCommand setElementValueAsStringList(List<String> elementValues) {
        ProcessSaveElementCommandUtils.setElementValueAsStringList(this, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the object itself
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public ProcessSaveElementCommand addElementValueAsString(String elementValue) {
        ProcessSaveElementCommandUtils.addElementValueAsString(this, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the object itself
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public ProcessSaveElementCommand addElementValueAsStringList(List<String> elementValues) {
        ProcessSaveElementCommandUtils.addElementValueAsStringList(this, elementValues);

        return this;
    }

    /**
     * Get an element as String
     *
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public String getElementValueAsString() {
        return ProcessSaveElementCommandUtils.getElementValueAsString(this);
    }

    /**
     * Get all elements as String
     *
     * @return the elements values.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public List<String> getElementValueAsStringList() {
        return ProcessSaveElementCommandUtils.getElementValueAsStringList(this);
    }

    /**
     * Try to get an element as String
     *
     * @return the element value if exists.
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public Optional<String> findElementValueAsString() {
        return ProcessSaveElementCommandUtils.findElementValueAsString(this);
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public ProcessSaveElementCommand setElementValueAsDouble(Double elementValue) {
        ProcessSaveElementCommandUtils.setElementValueAsDouble(this, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public ProcessSaveElementCommand setElementValueAsDoubleList(List<Double> elementValues) {
        ProcessSaveElementCommandUtils.setElementValueAsDoubleList(this, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the object itself
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public ProcessSaveElementCommand addElementValueAsDouble(Double elementValue) {
        ProcessSaveElementCommandUtils.addElementValueAsDouble(this, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the object itself
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public ProcessSaveElementCommand addElementValueAsDoubleList(List<Double> elementValues) {
        ProcessSaveElementCommandUtils.addElementValueAsDoubleList(this, elementValues);

        return this;
    }

    /**
     * Get an element as Double
     *
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public Double getElementValueAsDouble() {
        return ProcessSaveElementCommandUtils.getElementValueAsDouble(this);
    }

    /**
     * Get all elements as Double
     *
     * @return the elements values.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public List<Double> getElementValueAsDoubleList() {
        return ProcessSaveElementCommandUtils.getElementValueAsDoubleList(this);
    }

    /**
     * Try to get an element as Double
     *
     * @return the element value if exists.
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public Optional<Double> findElementValueAsDouble() {
        return ProcessSaveElementCommandUtils.findElementValueAsDouble(this);
    }

    /**
     * Set an element value
     *
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public ProcessSaveElementCommand setElementValueAsLocalDate(LocalDate elementValue) {
        ProcessSaveElementCommandUtils.setElementValueAsLocalDate(this, elementValue);

        return this;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the object itself
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public ProcessSaveElementCommand setElementValueAsLocalDateList(List<LocalDate> elementValues) {
        ProcessSaveElementCommandUtils.setElementValueAsLocalDateList(this, elementValues);

        return this;
    }

    /**
     * Add a new element value
     *
     * @param elementValue Element value, if the values is null the value is not added
     * @return the object itself
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public ProcessSaveElementCommand addElementValueAsLocalDate(LocalDate elementValue) {
        ProcessSaveElementCommandUtils.addElementValueAsLocalDate(this, elementValue);

        return this;
    }

    /**
     * Add all element values passed
     *
     * @param elementValues Element values
     * @return the object itself
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public ProcessSaveElementCommand addElementValueAsLocalDateList(List<LocalDate> elementValues) {
        ProcessSaveElementCommandUtils.addElementValueAsLocalDateList(this, elementValues);

        return this;
    }

    /**
     * Get an element as LocalDate
     *
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public LocalDate getElementValueAsLocalDate() {
        return ProcessSaveElementCommandUtils.getElementValueAsLocalDate(this);
    }

    /**
     * Get all elements as LocalDate
     *
     * @return the elements values.
     * @throws com.kuflow.rest.KuFlowRestClientException com.kuflow.rest.KuFlowRestClientException If element value
     * doesn't exist
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public List<LocalDate> getElementValueAsLocalDateList() {
        return ProcessSaveElementCommandUtils.getElementValueAsLocalDateList(this);
    }

    /**
     * Try to get an element as LocalDate
     *
     * @return the element value if exists.
     * @deprecated in favor of {@link ProcessSaveElementCommandUtils}
     */
    @Deprecated
    public Optional<LocalDate> findElementValueAsLocalDate() {
        return ProcessSaveElementCommandUtils.findElementValueAsLocalDate(this);
    }
}
