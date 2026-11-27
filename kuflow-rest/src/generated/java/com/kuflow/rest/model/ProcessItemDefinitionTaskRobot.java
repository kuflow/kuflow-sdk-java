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
import java.util.Objects;
import java.util.UUID;

/**
 * The robot that runs the task.
 */
@Fluent
public final class ProcessItemDefinitionTaskRobot implements JsonSerializable<ProcessItemDefinitionTaskRobot> {

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
     * The one of the robot with the `id`. An update can leave it out, but not give another one.
     */
    @Generated
    private String name;

    /*
     * The operation property.
     */
    @Generated
    private String operation;

    /*
     * The canStartWhenTaskIsClaimed property.
     */
    @Generated
    private Boolean canStartWhenTaskIsClaimed;

    /*
     * The canCompleteTask property.
     */
    @Generated
    private Boolean canCompleteTask;

    /**
     * Creates an instance of ProcessItemDefinitionTaskRobot class.
     */
    @Generated
    public ProcessItemDefinitionTaskRobot() {}

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
     * @return the ProcessItemDefinitionTaskRobot object itself.
     */
    @Generated
    public ProcessItemDefinitionTaskRobot setId(UUID id) {
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
     * @return the ProcessItemDefinitionTaskRobot object itself.
     */
    @Generated
    public ProcessItemDefinitionTaskRobot setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Get the name property: The one of the robot with the `id`. An update can leave it out, but not give another one.
     *
     * @return the name value.
     */
    @Generated
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: The one of the robot with the `id`. An update can leave it out, but not give another one.
     *
     * @param name the name value to set.
     * @return the ProcessItemDefinitionTaskRobot object itself.
     */
    @Generated
    public ProcessItemDefinitionTaskRobot setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the operation property: The operation property.
     *
     * @return the operation value.
     */
    @Generated
    public String getOperation() {
        return this.operation;
    }

    /**
     * Set the operation property: The operation property.
     *
     * @param operation the operation value to set.
     * @return the ProcessItemDefinitionTaskRobot object itself.
     */
    @Generated
    public ProcessItemDefinitionTaskRobot setOperation(String operation) {
        this.operation = operation;
        return this;
    }

    /**
     * Get the canStartWhenTaskIsClaimed property: The canStartWhenTaskIsClaimed property.
     *
     * @return the canStartWhenTaskIsClaimed value.
     */
    @Generated
    public Boolean isCanStartWhenTaskIsClaimed() {
        return this.canStartWhenTaskIsClaimed;
    }

    /**
     * Set the canStartWhenTaskIsClaimed property: The canStartWhenTaskIsClaimed property.
     *
     * @param canStartWhenTaskIsClaimed the canStartWhenTaskIsClaimed value to set.
     * @return the ProcessItemDefinitionTaskRobot object itself.
     */
    @Generated
    public ProcessItemDefinitionTaskRobot setCanStartWhenTaskIsClaimed(Boolean canStartWhenTaskIsClaimed) {
        this.canStartWhenTaskIsClaimed = canStartWhenTaskIsClaimed;
        return this;
    }

    /**
     * Get the canCompleteTask property: The canCompleteTask property.
     *
     * @return the canCompleteTask value.
     */
    @Generated
    public Boolean isCanCompleteTask() {
        return this.canCompleteTask;
    }

    /**
     * Set the canCompleteTask property: The canCompleteTask property.
     *
     * @param canCompleteTask the canCompleteTask value to set.
     * @return the ProcessItemDefinitionTaskRobot object itself.
     */
    @Generated
    public ProcessItemDefinitionTaskRobot setCanCompleteTask(Boolean canCompleteTask) {
        this.canCompleteTask = canCompleteTask;
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
        jsonWriter.writeStringField("operation", this.operation);
        jsonWriter.writeBooleanField("canStartWhenTaskIsClaimed", this.canStartWhenTaskIsClaimed);
        jsonWriter.writeBooleanField("canCompleteTask", this.canCompleteTask);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessItemDefinitionTaskRobot from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessItemDefinitionTaskRobot if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessItemDefinitionTaskRobot.
     */
    @Generated
    public static ProcessItemDefinitionTaskRobot fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessItemDefinitionTaskRobot deserializedProcessItemDefinitionTaskRobot = new ProcessItemDefinitionTaskRobot();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedProcessItemDefinitionTaskRobot.id = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("code".equals(fieldName)) {
                    deserializedProcessItemDefinitionTaskRobot.code = reader.getString();
                } else if ("name".equals(fieldName)) {
                    deserializedProcessItemDefinitionTaskRobot.name = reader.getString();
                } else if ("operation".equals(fieldName)) {
                    deserializedProcessItemDefinitionTaskRobot.operation = reader.getString();
                } else if ("canStartWhenTaskIsClaimed".equals(fieldName)) {
                    deserializedProcessItemDefinitionTaskRobot.canStartWhenTaskIsClaimed = reader.getNullable(JsonReader::getBoolean);
                } else if ("canCompleteTask".equals(fieldName)) {
                    deserializedProcessItemDefinitionTaskRobot.canCompleteTask = reader.getNullable(JsonReader::getBoolean);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessItemDefinitionTaskRobot;
        });
    }
}
