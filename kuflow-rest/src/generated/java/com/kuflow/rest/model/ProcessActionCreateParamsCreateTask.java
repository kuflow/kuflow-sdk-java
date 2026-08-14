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
 * Params for invoking an action of type CREATE_TASK.
 */
@Fluent
public final class ProcessActionCreateParamsCreateTask implements JsonSerializable<ProcessActionCreateParamsCreateTask> {

    /*
     * ID of the thread Process Item the created task is attached to.
     */
    @Generated
    private UUID processItemThreadId;

    /**
     * Creates an instance of ProcessActionCreateParamsCreateTask class.
     */
    @Generated
    public ProcessActionCreateParamsCreateTask() {}

    /**
     * Get the processItemThreadId property: ID of the thread Process Item the created task is attached to.
     *
     * @return the processItemThreadId value.
     */
    @Generated
    public UUID getProcessItemThreadId() {
        return this.processItemThreadId;
    }

    /**
     * Set the processItemThreadId property: ID of the thread Process Item the created task is attached to.
     *
     * @param processItemThreadId the processItemThreadId value to set.
     * @return the ProcessActionCreateParamsCreateTask object itself.
     */
    @Generated
    public ProcessActionCreateParamsCreateTask setProcessItemThreadId(UUID processItemThreadId) {
        this.processItemThreadId = processItemThreadId;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("processItemThreadId", Objects.toString(this.processItemThreadId, null));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessActionCreateParamsCreateTask from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessActionCreateParamsCreateTask if the JsonReader was pointing to an instance of it,
     * or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the ProcessActionCreateParamsCreateTask.
     */
    @Generated
    public static ProcessActionCreateParamsCreateTask fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessActionCreateParamsCreateTask deserializedProcessActionCreateParamsCreateTask = new ProcessActionCreateParamsCreateTask();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("processItemThreadId".equals(fieldName)) {
                    deserializedProcessActionCreateParamsCreateTask.processItemThreadId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessActionCreateParamsCreateTask;
        });
    }
}
