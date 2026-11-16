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
 * The BusinessArtifactFeedItemMessageParams model.
 */
@Fluent
public final class BusinessArtifactFeedItemMessageParams implements JsonSerializable<BusinessArtifactFeedItemMessageParams> {

    /*
     * Message text in Markdown format according to the specification https://spec.commonmark.org/
     */
    @Generated
    private String text;

    /*
     * Level of a message, `INFO` when not given.
     */
    @Generated
    private BusinessArtifactFeedItemMessageLevel level;

    /**
     * Creates an instance of BusinessArtifactFeedItemMessageParams class.
     */
    @Generated
    public BusinessArtifactFeedItemMessageParams() {}

    /**
     * Get the text property: Message text in Markdown format according to the specification
     * https://spec.commonmark.org/.
     *
     * @return the text value.
     */
    @Generated
    public String getText() {
        return this.text;
    }

    /**
     * Set the text property: Message text in Markdown format according to the specification
     * https://spec.commonmark.org/.
     *
     * @param text the text value to set.
     * @return the BusinessArtifactFeedItemMessageParams object itself.
     */
    @Generated
    public BusinessArtifactFeedItemMessageParams setText(String text) {
        this.text = text;
        return this;
    }

    /**
     * Get the level property: Level of a message, `INFO` when not given.
     *
     * @return the level value.
     */
    @Generated
    public BusinessArtifactFeedItemMessageLevel getLevel() {
        return this.level;
    }

    /**
     * Set the level property: Level of a message, `INFO` when not given.
     *
     * @param level the level value to set.
     * @return the BusinessArtifactFeedItemMessageParams object itself.
     */
    @Generated
    public BusinessArtifactFeedItemMessageParams setLevel(BusinessArtifactFeedItemMessageLevel level) {
        this.level = level;
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
        jsonWriter.writeStringField("level", this.level == null ? null : this.level.toString());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BusinessArtifactFeedItemMessageParams from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of BusinessArtifactFeedItemMessageParams if the JsonReader was pointing to an instance of it,
     * or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the BusinessArtifactFeedItemMessageParams.
     */
    @Generated
    public static BusinessArtifactFeedItemMessageParams fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BusinessArtifactFeedItemMessageParams deserializedBusinessArtifactFeedItemMessageParams =
                new BusinessArtifactFeedItemMessageParams();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("text".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItemMessageParams.text = reader.getString();
                } else if ("level".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItemMessageParams.level = BusinessArtifactFeedItemMessageLevel.fromString(
                        reader.getString()
                    );
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBusinessArtifactFeedItemMessageParams;
        });
    }
}
