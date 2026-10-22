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
import com.azure.core.util.CoreUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

/**
 * The AbstractDeleted model.
 */
@Fluent
public class AbstractDeleted extends AbstractAudited {

    /*
     * Who deleted this model, present only when it is deleted.
     */
    @Generated
    private UUID deletedBy;

    /*
     * When this model was deleted, present only when it is deleted.
     */
    @Generated
    private OffsetDateTime deletedAt;

    /**
     * Creates an instance of AbstractDeleted class.
     */
    @Generated
    public AbstractDeleted() {}

    /**
     * Get the deletedBy property: Who deleted this model, present only when it is deleted.
     *
     * @return the deletedBy value.
     */
    @Generated
    public UUID getDeletedBy() {
        return this.deletedBy;
    }

    /**
     * Set the deletedBy property: Who deleted this model, present only when it is deleted.
     *
     * @param deletedBy the deletedBy value to set.
     * @return the AbstractDeleted object itself.
     */
    @Generated
    public AbstractDeleted setDeletedBy(UUID deletedBy) {
        this.deletedBy = deletedBy;
        return this;
    }

    /**
     * Get the deletedAt property: When this model was deleted, present only when it is deleted.
     *
     * @return the deletedAt value.
     */
    @Generated
    public OffsetDateTime getDeletedAt() {
        return this.deletedAt;
    }

    /**
     * Set the deletedAt property: When this model was deleted, present only when it is deleted.
     *
     * @param deletedAt the deletedAt value to set.
     * @return the AbstractDeleted object itself.
     */
    @Generated
    public AbstractDeleted setDeletedAt(OffsetDateTime deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public AbstractDeleted setCreatedBy(UUID createdBy) {
        super.setCreatedBy(createdBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public AbstractDeleted setCreatedAt(OffsetDateTime createdAt) {
        super.setCreatedAt(createdAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public AbstractDeleted setLastModifiedBy(UUID lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public AbstractDeleted setLastModifiedAt(OffsetDateTime lastModifiedAt) {
        super.setLastModifiedAt(lastModifiedAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("createdBy", Objects.toString(getCreatedBy(), null));
        jsonWriter.writeStringField(
            "createdAt",
            getCreatedAt() == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(getCreatedAt())
        );
        jsonWriter.writeStringField("lastModifiedBy", Objects.toString(getLastModifiedBy(), null));
        jsonWriter.writeStringField(
            "lastModifiedAt",
            getLastModifiedAt() == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(getLastModifiedAt())
        );
        jsonWriter.writeStringField("deletedBy", Objects.toString(this.deletedBy, null));
        jsonWriter.writeStringField(
            "deletedAt",
            this.deletedAt == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.deletedAt)
        );
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of AbstractDeleted from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of AbstractDeleted if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the AbstractDeleted.
     */
    @Generated
    public static AbstractDeleted fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            AbstractDeleted deserializedAbstractDeleted = new AbstractDeleted();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("createdBy".equals(fieldName)) {
                    deserializedAbstractDeleted.setCreatedBy(
                        reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                } else if ("createdAt".equals(fieldName)) {
                    deserializedAbstractDeleted.setCreatedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("lastModifiedBy".equals(fieldName)) {
                    deserializedAbstractDeleted.setLastModifiedBy(
                        reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                } else if ("lastModifiedAt".equals(fieldName)) {
                    deserializedAbstractDeleted.setLastModifiedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("deletedBy".equals(fieldName)) {
                    deserializedAbstractDeleted.deletedBy = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("deletedAt".equals(fieldName)) {
                    deserializedAbstractDeleted.deletedAt = reader.getNullable(nonNullReader ->
                        CoreUtils.parseBestOffsetDateTime(nonNullReader.getString())
                    );
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedAbstractDeleted;
        });
    }
}
