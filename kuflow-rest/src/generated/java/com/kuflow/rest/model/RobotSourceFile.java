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
 * Robot source type.
 */
@Fluent
public final class RobotSourceFile implements JsonSerializable<RobotSourceFile> {

    /*
     * Robot ID.
     */
    @Generated
    private UUID id;

    /*
     * Source file name.
     */
    @Generated
    private String name;

    /*
     * Source file content type.
     */
    @Generated
    private String contentType;

    /*
     * Source file length.
     */
    @Generated
    private long contentLength;

    /*
     * Source file to check the integrity.
     */
    @Generated
    private String contentHash;

    /**
     * Creates an instance of RobotSourceFile class.
     */
    @Generated
    public RobotSourceFile() {}

    /**
     * Get the id property: Robot ID.
     *
     * @return the id value.
     */
    @Generated
    public UUID getId() {
        return this.id;
    }

    /**
     * Set the id property: Robot ID.
     *
     * @param id the id value to set.
     * @return the RobotSourceFile object itself.
     */
    @Generated
    public RobotSourceFile setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the name property: Source file name.
     *
     * @return the name value.
     */
    @Generated
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: Source file name.
     *
     * @param name the name value to set.
     * @return the RobotSourceFile object itself.
     */
    @Generated
    public RobotSourceFile setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the contentType property: Source file content type.
     *
     * @return the contentType value.
     */
    @Generated
    public String getContentType() {
        return this.contentType;
    }

    /**
     * Set the contentType property: Source file content type.
     *
     * @param contentType the contentType value to set.
     * @return the RobotSourceFile object itself.
     */
    @Generated
    public RobotSourceFile setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    /**
     * Get the contentLength property: Source file length.
     *
     * @return the contentLength value.
     */
    @Generated
    public long getContentLength() {
        return this.contentLength;
    }

    /**
     * Set the contentLength property: Source file length.
     *
     * @param contentLength the contentLength value to set.
     * @return the RobotSourceFile object itself.
     */
    @Generated
    public RobotSourceFile setContentLength(long contentLength) {
        this.contentLength = contentLength;
        return this;
    }

    /**
     * Get the contentHash property: Source file to check the integrity.
     *
     * @return the contentHash value.
     */
    @Generated
    public String getContentHash() {
        return this.contentHash;
    }

    /**
     * Set the contentHash property: Source file to check the integrity.
     *
     * @param contentHash the contentHash value to set.
     * @return the RobotSourceFile object itself.
     */
    @Generated
    public RobotSourceFile setContentHash(String contentHash) {
        this.contentHash = contentHash;
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
        jsonWriter.writeStringField("name", this.name);
        jsonWriter.writeStringField("contentType", this.contentType);
        jsonWriter.writeLongField("contentLength", this.contentLength);
        jsonWriter.writeStringField("contentHash", this.contentHash);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of RobotSourceFile from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of RobotSourceFile if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the RobotSourceFile.
     */
    @Generated
    public static RobotSourceFile fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            RobotSourceFile deserializedRobotSourceFile = new RobotSourceFile();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedRobotSourceFile.id = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("name".equals(fieldName)) {
                    deserializedRobotSourceFile.name = reader.getString();
                } else if ("contentType".equals(fieldName)) {
                    deserializedRobotSourceFile.contentType = reader.getString();
                } else if ("contentLength".equals(fieldName)) {
                    deserializedRobotSourceFile.contentLength = reader.getLong();
                } else if ("contentHash".equals(fieldName)) {
                    deserializedRobotSourceFile.contentHash = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedRobotSourceFile;
        });
    }
}
