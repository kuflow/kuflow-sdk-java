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
 * The ProcessDefinitionSchedulerPolicy model.
 */
@Fluent
public final class ProcessDefinitionSchedulerPolicy implements JsonSerializable<ProcessDefinitionSchedulerPolicy> {

    /*
     * The overlapPolicy property.
     */
    @Generated
    private ProcessDefinitionSchedulerOverlapPolicy overlapPolicy;

    /**
     * Creates an instance of ProcessDefinitionSchedulerPolicy class.
     */
    @Generated
    public ProcessDefinitionSchedulerPolicy() {}

    /**
     * Get the overlapPolicy property: The overlapPolicy property.
     *
     * @return the overlapPolicy value.
     */
    @Generated
    public ProcessDefinitionSchedulerOverlapPolicy getOverlapPolicy() {
        return this.overlapPolicy;
    }

    /**
     * Set the overlapPolicy property: The overlapPolicy property.
     *
     * @param overlapPolicy the overlapPolicy value to set.
     * @return the ProcessDefinitionSchedulerPolicy object itself.
     */
    @Generated
    public ProcessDefinitionSchedulerPolicy setOverlapPolicy(ProcessDefinitionSchedulerOverlapPolicy overlapPolicy) {
        this.overlapPolicy = overlapPolicy;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("overlapPolicy", this.overlapPolicy == null ? null : this.overlapPolicy.toString());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessDefinitionSchedulerPolicy from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessDefinitionSchedulerPolicy if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the ProcessDefinitionSchedulerPolicy.
     */
    @Generated
    public static ProcessDefinitionSchedulerPolicy fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessDefinitionSchedulerPolicy deserializedProcessDefinitionSchedulerPolicy = new ProcessDefinitionSchedulerPolicy();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("overlapPolicy".equals(fieldName)) {
                    deserializedProcessDefinitionSchedulerPolicy.overlapPolicy = ProcessDefinitionSchedulerOverlapPolicy.fromString(
                        reader.getString()
                    );
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessDefinitionSchedulerPolicy;
        });
    }
}
