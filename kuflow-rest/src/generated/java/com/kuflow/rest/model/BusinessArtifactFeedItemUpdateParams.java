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
 * Params to update a Business Artifact Feed Item. The populated sub-field (`thread`, `message`) must match the
 * `type` of the item. They replace what it had: a field left out is cleared.
 */
@Fluent
public final class BusinessArtifactFeedItemUpdateParams implements JsonSerializable<BusinessArtifactFeedItemUpdateParams> {

    /*
     * Status of an item. `COMPLETED`, `FAILED` and `CANCELED` are terminal: they close the item, and a closed thread
     * closes its messages.
     */
    @Generated
    private BusinessArtifactFeedItemStatus status;

    /*
     * Progress of an item. Without `total`, the progress is indeterminate.
     */
    @Generated
    private BusinessArtifactFeedItemProgress progress;

    /*
     * The thread property.
     */
    @Generated
    private BusinessArtifactFeedItemThreadParams thread;

    /*
     * The message property.
     */
    @Generated
    private BusinessArtifactFeedItemMessageParams message;

    /**
     * Creates an instance of BusinessArtifactFeedItemUpdateParams class.
     */
    @Generated
    public BusinessArtifactFeedItemUpdateParams() {}

    /**
     * Get the status property: Status of an item. `COMPLETED`, `FAILED` and `CANCELED` are terminal: they close the
     * item, and a closed thread
     * closes its messages.
     *
     * @return the status value.
     */
    @Generated
    public BusinessArtifactFeedItemStatus getStatus() {
        return this.status;
    }

    /**
     * Set the status property: Status of an item. `COMPLETED`, `FAILED` and `CANCELED` are terminal: they close the
     * item, and a closed thread
     * closes its messages.
     *
     * @param status the status value to set.
     * @return the BusinessArtifactFeedItemUpdateParams object itself.
     */
    @Generated
    public BusinessArtifactFeedItemUpdateParams setStatus(BusinessArtifactFeedItemStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Get the progress property: Progress of an item. Without `total`, the progress is indeterminate.
     *
     * @return the progress value.
     */
    @Generated
    public BusinessArtifactFeedItemProgress getProgress() {
        return this.progress;
    }

    /**
     * Set the progress property: Progress of an item. Without `total`, the progress is indeterminate.
     *
     * @param progress the progress value to set.
     * @return the BusinessArtifactFeedItemUpdateParams object itself.
     */
    @Generated
    public BusinessArtifactFeedItemUpdateParams setProgress(BusinessArtifactFeedItemProgress progress) {
        this.progress = progress;
        return this;
    }

    /**
     * Get the thread property: The thread property.
     *
     * @return the thread value.
     */
    @Generated
    public BusinessArtifactFeedItemThreadParams getThread() {
        return this.thread;
    }

    /**
     * Set the thread property: The thread property.
     *
     * @param thread the thread value to set.
     * @return the BusinessArtifactFeedItemUpdateParams object itself.
     */
    @Generated
    public BusinessArtifactFeedItemUpdateParams setThread(BusinessArtifactFeedItemThreadParams thread) {
        this.thread = thread;
        return this;
    }

    /**
     * Get the message property: The message property.
     *
     * @return the message value.
     */
    @Generated
    public BusinessArtifactFeedItemMessageParams getMessage() {
        return this.message;
    }

    /**
     * Set the message property: The message property.
     *
     * @param message the message value to set.
     * @return the BusinessArtifactFeedItemUpdateParams object itself.
     */
    @Generated
    public BusinessArtifactFeedItemUpdateParams setMessage(BusinessArtifactFeedItemMessageParams message) {
        this.message = message;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("status", this.status == null ? null : this.status.toString());
        jsonWriter.writeJsonField("progress", this.progress);
        jsonWriter.writeJsonField("thread", this.thread);
        jsonWriter.writeJsonField("message", this.message);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BusinessArtifactFeedItemUpdateParams from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of BusinessArtifactFeedItemUpdateParams if the JsonReader was pointing to an instance of it,
     * or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the BusinessArtifactFeedItemUpdateParams.
     */
    @Generated
    public static BusinessArtifactFeedItemUpdateParams fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BusinessArtifactFeedItemUpdateParams deserializedBusinessArtifactFeedItemUpdateParams =
                new BusinessArtifactFeedItemUpdateParams();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("status".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItemUpdateParams.status = BusinessArtifactFeedItemStatus.fromString(reader.getString());
                } else if ("progress".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItemUpdateParams.progress = BusinessArtifactFeedItemProgress.fromJson(reader);
                } else if ("thread".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItemUpdateParams.thread = BusinessArtifactFeedItemThreadParams.fromJson(reader);
                } else if ("message".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItemUpdateParams.message = BusinessArtifactFeedItemMessageParams.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBusinessArtifactFeedItemUpdateParams;
        });
    }
}
