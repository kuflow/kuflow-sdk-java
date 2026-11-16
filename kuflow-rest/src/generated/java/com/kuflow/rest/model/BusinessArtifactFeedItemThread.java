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
 * The BusinessArtifactFeedItemThread model.
 */
@Fluent
public final class BusinessArtifactFeedItemThread implements JsonSerializable<BusinessArtifactFeedItemThread> {

    /*
     * The title property.
     */
    @Generated
    private String title;

    /*
     * Description in Markdown format according to the specification https://spec.commonmark.org/
     */
    @Generated
    private String description;

    /**
     * Creates an instance of BusinessArtifactFeedItemThread class.
     */
    @Generated
    public BusinessArtifactFeedItemThread() {}

    /**
     * Get the title property: The title property.
     *
     * @return the title value.
     */
    @Generated
    public String getTitle() {
        return this.title;
    }

    /**
     * Set the title property: The title property.
     *
     * @param title the title value to set.
     * @return the BusinessArtifactFeedItemThread object itself.
     */
    @Generated
    public BusinessArtifactFeedItemThread setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Get the description property: Description in Markdown format according to the specification
     * https://spec.commonmark.org/.
     *
     * @return the description value.
     */
    @Generated
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description property: Description in Markdown format according to the specification
     * https://spec.commonmark.org/.
     *
     * @param description the description value to set.
     * @return the BusinessArtifactFeedItemThread object itself.
     */
    @Generated
    public BusinessArtifactFeedItemThread setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("title", this.title);
        jsonWriter.writeStringField("description", this.description);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BusinessArtifactFeedItemThread from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of BusinessArtifactFeedItemThread if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the BusinessArtifactFeedItemThread.
     */
    @Generated
    public static BusinessArtifactFeedItemThread fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BusinessArtifactFeedItemThread deserializedBusinessArtifactFeedItemThread = new BusinessArtifactFeedItemThread();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("title".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItemThread.title = reader.getString();
                } else if ("description".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItemThread.description = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBusinessArtifactFeedItemThread;
        });
    }
}
