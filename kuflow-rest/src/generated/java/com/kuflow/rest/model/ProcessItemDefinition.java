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
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * A task or a thread of a Process Definition.
 */
@Fluent
public final class ProcessItemDefinition implements JsonSerializable<ProcessItemDefinition> {

    /*
     * The id property.
     */
    @Generated
    private UUID id;

    /*
     * The code property.
     */
    @Generated
    private String code;

    /*
     * The name property.
     */
    @Generated
    private String name;

    /*
     * The type property.
     */
    @Generated
    private ProcessItemDefinitionType type;

    /*
     * The description property.
     */
    @Generated
    private String description;

    /*
     * Which notifications of a task are sent.
     */
    @Generated
    private ProcessItemDefinitionNotifications notifications;

    /*
     * The rbacEntries property.
     */
    @Generated
    private List<ProcessDefinitionRbacEntry> rbacEntries;

    /*
     * What only a task has.
     */
    @Generated
    private ProcessItemDefinitionTask task;

    /**
     * Creates an instance of ProcessItemDefinition class.
     */
    @Generated
    public ProcessItemDefinition() {}

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
     * @return the ProcessItemDefinition object itself.
     */
    @Generated
    public ProcessItemDefinition setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the code property: The code property.
     *
     * @return the code value.
     */
    @Generated
    public String getCode() {
        return this.code;
    }

    /**
     * Set the code property: The code property.
     *
     * @param code the code value to set.
     * @return the ProcessItemDefinition object itself.
     */
    @Generated
    public ProcessItemDefinition setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Get the name property: The name property.
     *
     * @return the name value.
     */
    @Generated
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: The name property.
     *
     * @param name the name value to set.
     * @return the ProcessItemDefinition object itself.
     */
    @Generated
    public ProcessItemDefinition setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the type property: The type property.
     *
     * @return the type value.
     */
    @Generated
    public ProcessItemDefinitionType getType() {
        return this.type;
    }

    /**
     * Set the type property: The type property.
     *
     * @param type the type value to set.
     * @return the ProcessItemDefinition object itself.
     */
    @Generated
    public ProcessItemDefinition setType(ProcessItemDefinitionType type) {
        this.type = type;
        return this;
    }

    /**
     * Get the description property: The description property.
     *
     * @return the description value.
     */
    @Generated
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description property: The description property.
     *
     * @param description the description value to set.
     * @return the ProcessItemDefinition object itself.
     */
    @Generated
    public ProcessItemDefinition setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get the notifications property: Which notifications of a task are sent.
     *
     * @return the notifications value.
     */
    @Generated
    public ProcessItemDefinitionNotifications getNotifications() {
        return this.notifications;
    }

    /**
     * Set the notifications property: Which notifications of a task are sent.
     *
     * @param notifications the notifications value to set.
     * @return the ProcessItemDefinition object itself.
     */
    @Generated
    public ProcessItemDefinition setNotifications(ProcessItemDefinitionNotifications notifications) {
        this.notifications = notifications;
        return this;
    }

    /**
     * Get the rbacEntries property: The rbacEntries property.
     *
     * @return the rbacEntries value.
     */
    @Generated
    public List<ProcessDefinitionRbacEntry> getRbacEntries() {
        return this.rbacEntries;
    }

    /**
     * Set the rbacEntries property: The rbacEntries property.
     *
     * @param rbacEntries the rbacEntries value to set.
     * @return the ProcessItemDefinition object itself.
     */
    @Generated
    public ProcessItemDefinition setRbacEntries(List<ProcessDefinitionRbacEntry> rbacEntries) {
        this.rbacEntries = rbacEntries;
        return this;
    }

    /**
     * Get the task property: What only a task has.
     *
     * @return the task value.
     */
    @Generated
    public ProcessItemDefinitionTask getTask() {
        return this.task;
    }

    /**
     * Set the task property: What only a task has.
     *
     * @param task the task value to set.
     * @return the ProcessItemDefinition object itself.
     */
    @Generated
    public ProcessItemDefinition setTask(ProcessItemDefinitionTask task) {
        this.task = task;
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
        jsonWriter.writeStringField("code", this.code);
        jsonWriter.writeStringField("name", this.name);
        jsonWriter.writeStringField("type", this.type == null ? null : this.type.toString());
        jsonWriter.writeArrayField("rbacEntries", this.rbacEntries, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeStringField("description", this.description);
        jsonWriter.writeJsonField("notifications", this.notifications);
        jsonWriter.writeJsonField("task", this.task);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessItemDefinition from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessItemDefinition if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessItemDefinition.
     */
    @Generated
    public static ProcessItemDefinition fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessItemDefinition deserializedProcessItemDefinition = new ProcessItemDefinition();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedProcessItemDefinition.id = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("code".equals(fieldName)) {
                    deserializedProcessItemDefinition.code = reader.getString();
                } else if ("name".equals(fieldName)) {
                    deserializedProcessItemDefinition.name = reader.getString();
                } else if ("type".equals(fieldName)) {
                    deserializedProcessItemDefinition.type = ProcessItemDefinitionType.fromString(reader.getString());
                } else if ("rbacEntries".equals(fieldName)) {
                    List<ProcessDefinitionRbacEntry> rbacEntries = reader.readArray(reader1 ->
                        ProcessDefinitionRbacEntry.fromJson(reader1)
                    );
                    deserializedProcessItemDefinition.rbacEntries = rbacEntries;
                } else if ("description".equals(fieldName)) {
                    deserializedProcessItemDefinition.description = reader.getString();
                } else if ("notifications".equals(fieldName)) {
                    deserializedProcessItemDefinition.notifications = ProcessItemDefinitionNotifications.fromJson(reader);
                } else if ("task".equals(fieldName)) {
                    deserializedProcessItemDefinition.task = ProcessItemDefinitionTask.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessItemDefinition;
        });
    }
}
