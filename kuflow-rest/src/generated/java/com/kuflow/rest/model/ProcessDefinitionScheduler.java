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
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * A schedule that starts processes of the Process Definition.
 */
@Fluent
public final class ProcessDefinitionScheduler implements JsonSerializable<ProcessDefinitionScheduler> {

    /*
     * The id property.
     */
    @Generated
    private UUID id;

    /*
     * The active property.
     */
    @Generated
    private boolean active;

    /*
     * The frequency property.
     */
    @Generated
    private ProcessDefinitionSchedulerFrequency frequency;

    /*
     * The policy property.
     */
    @Generated
    private ProcessDefinitionSchedulerPolicy policy;

    /*
     * The metadata of the processes that it starts.
     */
    @Generated
    private Map<String, Object> metadata;

    /**
     * Creates an instance of ProcessDefinitionScheduler class.
     */
    @Generated
    public ProcessDefinitionScheduler() {}

    /**
     * Get the id property: The id property.
     *
     * @return the id value.
     */
    @Generated
    public UUID getId() {
        return this.id;
    }

    /**
     * Set the id property: The id property.
     *
     * @param id the id value to set.
     * @return the ProcessDefinitionScheduler object itself.
     */
    @Generated
    public ProcessDefinitionScheduler setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the active property: The active property.
     *
     * @return the active value.
     */
    @Generated
    public boolean isActive() {
        return this.active;
    }

    /**
     * Set the active property: The active property.
     *
     * @param active the active value to set.
     * @return the ProcessDefinitionScheduler object itself.
     */
    @Generated
    public ProcessDefinitionScheduler setActive(boolean active) {
        this.active = active;
        return this;
    }

    /**
     * Get the frequency property: The frequency property.
     *
     * @return the frequency value.
     */
    @Generated
    public ProcessDefinitionSchedulerFrequency getFrequency() {
        return this.frequency;
    }

    /**
     * Set the frequency property: The frequency property.
     *
     * @param frequency the frequency value to set.
     * @return the ProcessDefinitionScheduler object itself.
     */
    @Generated
    public ProcessDefinitionScheduler setFrequency(ProcessDefinitionSchedulerFrequency frequency) {
        this.frequency = frequency;
        return this;
    }

    /**
     * Get the policy property: The policy property.
     *
     * @return the policy value.
     */
    @Generated
    public ProcessDefinitionSchedulerPolicy getPolicy() {
        return this.policy;
    }

    /**
     * Set the policy property: The policy property.
     *
     * @param policy the policy value to set.
     * @return the ProcessDefinitionScheduler object itself.
     */
    @Generated
    public ProcessDefinitionScheduler setPolicy(ProcessDefinitionSchedulerPolicy policy) {
        this.policy = policy;
        return this;
    }

    /**
     * Get the metadata property: The metadata of the processes that it starts.
     *
     * @return the metadata value.
     */
    @Generated
    public Map<String, Object> getMetadata() {
        return this.metadata;
    }

    /**
     * Set the metadata property: The metadata of the processes that it starts.
     *
     * @param metadata the metadata value to set.
     * @return the ProcessDefinitionScheduler object itself.
     */
    @Generated
    public ProcessDefinitionScheduler setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("id", Objects.toString(this.id, null));
        jsonWriter.writeBooleanField("active", this.active);
        jsonWriter.writeJsonField("frequency", this.frequency);
        jsonWriter.writeJsonField("policy", this.policy);
        jsonWriter.writeMapField("metadata", this.metadata, (writer, element) -> writer.writeUntyped(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessDefinitionScheduler from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessDefinitionScheduler if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessDefinitionScheduler.
     */
    @Generated
    public static ProcessDefinitionScheduler fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessDefinitionScheduler deserializedProcessDefinitionScheduler = new ProcessDefinitionScheduler();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedProcessDefinitionScheduler.id = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("active".equals(fieldName)) {
                    deserializedProcessDefinitionScheduler.active = reader.getBoolean();
                } else if ("frequency".equals(fieldName)) {
                    deserializedProcessDefinitionScheduler.frequency = ProcessDefinitionSchedulerFrequency.fromJson(reader);
                } else if ("policy".equals(fieldName)) {
                    deserializedProcessDefinitionScheduler.policy = ProcessDefinitionSchedulerPolicy.fromJson(reader);
                } else if ("metadata".equals(fieldName)) {
                    Map<String, Object> metadata = reader.readMap(reader1 -> reader1.readUntyped());
                    deserializedProcessDefinitionScheduler.metadata = metadata;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessDefinitionScheduler;
        });
    }
}
