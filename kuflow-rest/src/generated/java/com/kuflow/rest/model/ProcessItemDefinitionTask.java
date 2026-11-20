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

/**
 * What only a task has.
 */
@Fluent
public final class ProcessItemDefinitionTask implements JsonSerializable<ProcessItemDefinitionTask> {

    /*
     * Where the form of a task comes from: STRUCTURE_DATA, a form of the definition, the one of
     * structureDataDefinitionCode. ELEMENTS is deprecated: the elements of such a task are not part of the definition
     * returned.
     */
    @Generated
    private ProcessItemDefinitionTaskFormOrigin formOrigin;

    /*
     * The code of the form of the task.
     */
    @Generated
    private String structureDataDefinitionCode;

    /*
     * The code of the form of the context of the task.
     */
    @Generated
    private String contextDefinitionCode;

    /*
     * The robot that runs the task.
     */
    @Generated
    private ProcessItemDefinitionTaskRobot robot;

    /*
     * The aiAssistance property.
     */
    @Generated
    private ProcessItemDefinitionTaskAiAssistance aiAssistance;

    /**
     * Creates an instance of ProcessItemDefinitionTask class.
     */
    @Generated
    public ProcessItemDefinitionTask() {}

    /**
     * Get the formOrigin property: Where the form of a task comes from: STRUCTURE_DATA, a form of the definition, the
     * one of
     * structureDataDefinitionCode. ELEMENTS is deprecated: the elements of such a task are not part of the definition
     * returned.
     *
     * @return the formOrigin value.
     */
    @Generated
    public ProcessItemDefinitionTaskFormOrigin getFormOrigin() {
        return this.formOrigin;
    }

    /**
     * Set the formOrigin property: Where the form of a task comes from: STRUCTURE_DATA, a form of the definition, the
     * one of
     * structureDataDefinitionCode. ELEMENTS is deprecated: the elements of such a task are not part of the definition
     * returned.
     *
     * @param formOrigin the formOrigin value to set.
     * @return the ProcessItemDefinitionTask object itself.
     */
    @Generated
    public ProcessItemDefinitionTask setFormOrigin(ProcessItemDefinitionTaskFormOrigin formOrigin) {
        this.formOrigin = formOrigin;
        return this;
    }

    /**
     * Get the structureDataDefinitionCode property: The code of the form of the task.
     *
     * @return the structureDataDefinitionCode value.
     */
    @Generated
    public String getStructureDataDefinitionCode() {
        return this.structureDataDefinitionCode;
    }

    /**
     * Set the structureDataDefinitionCode property: The code of the form of the task.
     *
     * @param structureDataDefinitionCode the structureDataDefinitionCode value to set.
     * @return the ProcessItemDefinitionTask object itself.
     */
    @Generated
    public ProcessItemDefinitionTask setStructureDataDefinitionCode(String structureDataDefinitionCode) {
        this.structureDataDefinitionCode = structureDataDefinitionCode;
        return this;
    }

    /**
     * Get the contextDefinitionCode property: The code of the form of the context of the task.
     *
     * @return the contextDefinitionCode value.
     */
    @Generated
    public String getContextDefinitionCode() {
        return this.contextDefinitionCode;
    }

    /**
     * Set the contextDefinitionCode property: The code of the form of the context of the task.
     *
     * @param contextDefinitionCode the contextDefinitionCode value to set.
     * @return the ProcessItemDefinitionTask object itself.
     */
    @Generated
    public ProcessItemDefinitionTask setContextDefinitionCode(String contextDefinitionCode) {
        this.contextDefinitionCode = contextDefinitionCode;
        return this;
    }

    /**
     * Get the robot property: The robot that runs the task.
     *
     * @return the robot value.
     */
    @Generated
    public ProcessItemDefinitionTaskRobot getRobot() {
        return this.robot;
    }

    /**
     * Set the robot property: The robot that runs the task.
     *
     * @param robot the robot value to set.
     * @return the ProcessItemDefinitionTask object itself.
     */
    @Generated
    public ProcessItemDefinitionTask setRobot(ProcessItemDefinitionTaskRobot robot) {
        this.robot = robot;
        return this;
    }

    /**
     * Get the aiAssistance property: The aiAssistance property.
     *
     * @return the aiAssistance value.
     */
    @Generated
    public ProcessItemDefinitionTaskAiAssistance getAiAssistance() {
        return this.aiAssistance;
    }

    /**
     * Set the aiAssistance property: The aiAssistance property.
     *
     * @param aiAssistance the aiAssistance value to set.
     * @return the ProcessItemDefinitionTask object itself.
     */
    @Generated
    public ProcessItemDefinitionTask setAiAssistance(ProcessItemDefinitionTaskAiAssistance aiAssistance) {
        this.aiAssistance = aiAssistance;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("formOrigin", this.formOrigin == null ? null : this.formOrigin.toString());
        jsonWriter.writeStringField("structureDataDefinitionCode", this.structureDataDefinitionCode);
        jsonWriter.writeStringField("contextDefinitionCode", this.contextDefinitionCode);
        jsonWriter.writeJsonField("robot", this.robot);
        jsonWriter.writeJsonField("aiAssistance", this.aiAssistance);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessItemDefinitionTask from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessItemDefinitionTask if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the ProcessItemDefinitionTask.
     */
    @Generated
    public static ProcessItemDefinitionTask fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessItemDefinitionTask deserializedProcessItemDefinitionTask = new ProcessItemDefinitionTask();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("formOrigin".equals(fieldName)) {
                    deserializedProcessItemDefinitionTask.formOrigin = ProcessItemDefinitionTaskFormOrigin.fromString(reader.getString());
                } else if ("structureDataDefinitionCode".equals(fieldName)) {
                    deserializedProcessItemDefinitionTask.structureDataDefinitionCode = reader.getString();
                } else if ("contextDefinitionCode".equals(fieldName)) {
                    deserializedProcessItemDefinitionTask.contextDefinitionCode = reader.getString();
                } else if ("robot".equals(fieldName)) {
                    deserializedProcessItemDefinitionTask.robot = ProcessItemDefinitionTaskRobot.fromJson(reader);
                } else if ("aiAssistance".equals(fieldName)) {
                    deserializedProcessItemDefinitionTask.aiAssistance = ProcessItemDefinitionTaskAiAssistance.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessItemDefinitionTask;
        });
    }
}
