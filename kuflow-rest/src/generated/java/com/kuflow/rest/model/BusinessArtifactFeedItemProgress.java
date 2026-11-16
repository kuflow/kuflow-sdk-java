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
 * Progress of an item. Without `total`, the progress is indeterminate.
 */
@Fluent
public final class BusinessArtifactFeedItemProgress implements JsonSerializable<BusinessArtifactFeedItemProgress> {

    /*
     * The completed property.
     */
    @Generated
    private int completed;

    /*
     * The total property.
     */
    @Generated
    private Integer total;

    /**
     * Creates an instance of BusinessArtifactFeedItemProgress class.
     */
    @Generated
    public BusinessArtifactFeedItemProgress() {}

    /**
     * Get the completed property: The completed property.
     *
     * @return the completed value.
     */
    @Generated
    public int getCompleted() {
        return this.completed;
    }

    /**
     * Set the completed property: The completed property.
     *
     * @param completed the completed value to set.
     * @return the BusinessArtifactFeedItemProgress object itself.
     */
    @Generated
    public BusinessArtifactFeedItemProgress setCompleted(int completed) {
        this.completed = completed;
        return this;
    }

    /**
     * Get the total property: The total property.
     *
     * @return the total value.
     */
    @Generated
    public Integer getTotal() {
        return this.total;
    }

    /**
     * Set the total property: The total property.
     *
     * @param total the total value to set.
     * @return the BusinessArtifactFeedItemProgress object itself.
     */
    @Generated
    public BusinessArtifactFeedItemProgress setTotal(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeIntField("completed", this.completed);
        jsonWriter.writeNumberField("total", this.total);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BusinessArtifactFeedItemProgress from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of BusinessArtifactFeedItemProgress if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the BusinessArtifactFeedItemProgress.
     */
    @Generated
    public static BusinessArtifactFeedItemProgress fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BusinessArtifactFeedItemProgress deserializedBusinessArtifactFeedItemProgress = new BusinessArtifactFeedItemProgress();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("completed".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItemProgress.completed = reader.getInt();
                } else if ("total".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItemProgress.total = reader.getNullable(JsonReader::getInt);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBusinessArtifactFeedItemProgress;
        });
    }
}
