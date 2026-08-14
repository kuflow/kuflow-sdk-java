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
 * Params for invoking an action of type CREATE_PROCESS_ITEM_MESSAGE.
 * Messages are created through the built-in `__KF_MESSAGE__` action
 * definition code.
 */
@Fluent
public final class ProcessActionCreateParamsCreateProcessItemMessage
    implements JsonSerializable<ProcessActionCreateParamsCreateProcessItemMessage>
{

    /*
     * Text of the message to create.
     */
    @Generated
    private String text;

    /*
     * ID of the thread Process Item the created message is attached to.
     */
    @Generated
    private UUID processItemThreadId;

    /**
     * Creates an instance of ProcessActionCreateParamsCreateProcessItemMessage class.
     */
    @Generated
    public ProcessActionCreateParamsCreateProcessItemMessage() {}

    /**
     * Get the text property: Text of the message to create.
     *
     * @return the text value.
     */
    @Generated
    public String getText() {
        return this.text;
    }

    /**
     * Set the text property: Text of the message to create.
     *
     * @param text the text value to set.
     * @return the ProcessActionCreateParamsCreateProcessItemMessage object itself.
     */
    @Generated
    public ProcessActionCreateParamsCreateProcessItemMessage setText(String text) {
        this.text = text;
        return this;
    }

    /**
     * Get the processItemThreadId property: ID of the thread Process Item the created message is attached to.
     *
     * @return the processItemThreadId value.
     */
    @Generated
    public UUID getProcessItemThreadId() {
        return this.processItemThreadId;
    }

    /**
     * Set the processItemThreadId property: ID of the thread Process Item the created message is attached to.
     *
     * @param processItemThreadId the processItemThreadId value to set.
     * @return the ProcessActionCreateParamsCreateProcessItemMessage object itself.
     */
    @Generated
    public ProcessActionCreateParamsCreateProcessItemMessage setProcessItemThreadId(UUID processItemThreadId) {
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
        jsonWriter.writeStringField("text", this.text);
        jsonWriter.writeStringField("processItemThreadId", Objects.toString(this.processItemThreadId, null));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessActionCreateParamsCreateProcessItemMessage from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessActionCreateParamsCreateProcessItemMessage if the JsonReader was pointing to an
     * instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessActionCreateParamsCreateProcessItemMessage.
     */
    @Generated
    public static ProcessActionCreateParamsCreateProcessItemMessage fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessActionCreateParamsCreateProcessItemMessage deserializedProcessActionCreateParamsCreateProcessItemMessage =
                new ProcessActionCreateParamsCreateProcessItemMessage();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("text".equals(fieldName)) {
                    deserializedProcessActionCreateParamsCreateProcessItemMessage.text = reader.getString();
                } else if ("processItemThreadId".equals(fieldName)) {
                    deserializedProcessActionCreateParamsCreateProcessItemMessage.processItemThreadId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessActionCreateParamsCreateProcessItemMessage;
        });
    }
}
