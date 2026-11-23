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
 * The ProcessDefinitionSchedulerFrequency model.
 */
@Fluent
public final class ProcessDefinitionSchedulerFrequency implements JsonSerializable<ProcessDefinitionSchedulerFrequency> {

    /*
     * The cronExpression property.
     */
    @Generated
    private String cronExpression;

    /*
     * The timeZone property.
     */
    @Generated
    private String timeZone;

    /**
     * Creates an instance of ProcessDefinitionSchedulerFrequency class.
     */
    @Generated
    public ProcessDefinitionSchedulerFrequency() {}

    /**
     * Get the cronExpression property: The cronExpression property.
     *
     * @return the cronExpression value.
     */
    @Generated
    public String getCronExpression() {
        return this.cronExpression;
    }

    /**
     * Set the cronExpression property: The cronExpression property.
     *
     * @param cronExpression the cronExpression value to set.
     * @return the ProcessDefinitionSchedulerFrequency object itself.
     */
    @Generated
    public ProcessDefinitionSchedulerFrequency setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
        return this;
    }

    /**
     * Get the timeZone property: The timeZone property.
     *
     * @return the timeZone value.
     */
    @Generated
    public String getTimeZone() {
        return this.timeZone;
    }

    /**
     * Set the timeZone property: The timeZone property.
     *
     * @param timeZone the timeZone value to set.
     * @return the ProcessDefinitionSchedulerFrequency object itself.
     */
    @Generated
    public ProcessDefinitionSchedulerFrequency setTimeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("cronExpression", this.cronExpression);
        jsonWriter.writeStringField("timeZone", this.timeZone);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessDefinitionSchedulerFrequency from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessDefinitionSchedulerFrequency if the JsonReader was pointing to an instance of it,
     * or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the ProcessDefinitionSchedulerFrequency.
     */
    @Generated
    public static ProcessDefinitionSchedulerFrequency fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessDefinitionSchedulerFrequency deserializedProcessDefinitionSchedulerFrequency = new ProcessDefinitionSchedulerFrequency();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("cronExpression".equals(fieldName)) {
                    deserializedProcessDefinitionSchedulerFrequency.cronExpression = reader.getString();
                } else if ("timeZone".equals(fieldName)) {
                    deserializedProcessDefinitionSchedulerFrequency.timeZone = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessDefinitionSchedulerFrequency;
        });
    }
}
