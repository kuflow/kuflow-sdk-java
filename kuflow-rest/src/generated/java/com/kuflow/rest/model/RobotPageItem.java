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
 * The RobotPageItem model.
 */
@Fluent
public final class RobotPageItem extends AbstractAudited {

    /*
     * Robot ID.
     */
    private UUID id;

    /*
     * Robot Code.
     */
    private String code;

    /*
     * Robot name.
     */
    private String name;

    /*
     * Robot description.
     */
    private String description;

    /*
     * Robot source type
     */
    private RobotSourceType sourceType;

    /*
     * Robot source type
     */
    private RobotSourceFile sourceFile;

    /*
     * Tenant ID.
     */
    private UUID tenantId;

    /**
     * Creates an instance of RobotPageItem class.
     */
    public RobotPageItem() {}

    /**
     * Get the id property: Robot ID.
     *
     * @return the id value.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Set the id property: Robot ID.
     *
     * @param id the id value to set.
     * @return the RobotPageItem object itself.
     */
    public RobotPageItem setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the code property: Robot Code.
     *
     * @return the code value.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Set the code property: Robot Code.
     *
     * @param code the code value to set.
     * @return the RobotPageItem object itself.
     */
    public RobotPageItem setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Get the name property: Robot name.
     *
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: Robot name.
     *
     * @param name the name value to set.
     * @return the RobotPageItem object itself.
     */
    public RobotPageItem setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the description property: Robot description.
     *
     * @return the description value.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description property: Robot description.
     *
     * @param description the description value to set.
     * @return the RobotPageItem object itself.
     */
    public RobotPageItem setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get the sourceType property: Robot source type.
     *
     * @return the sourceType value.
     */
    public RobotSourceType getSourceType() {
        return this.sourceType;
    }

    /**
     * Set the sourceType property: Robot source type.
     *
     * @param sourceType the sourceType value to set.
     * @return the RobotPageItem object itself.
     */
    public RobotPageItem setSourceType(RobotSourceType sourceType) {
        this.sourceType = sourceType;
        return this;
    }

    /**
     * Get the sourceFile property: Robot source type.
     *
     * @return the sourceFile value.
     */
    public RobotSourceFile getSourceFile() {
        return this.sourceFile;
    }

    /**
     * Set the sourceFile property: Robot source type.
     *
     * @param sourceFile the sourceFile value to set.
     * @return the RobotPageItem object itself.
     */
    public RobotPageItem setSourceFile(RobotSourceFile sourceFile) {
        this.sourceFile = sourceFile;
        return this;
    }

    /**
     * Get the tenantId property: Tenant ID.
     *
     * @return the tenantId value.
     */
    public UUID getTenantId() {
        return this.tenantId;
    }

    /**
     * Set the tenantId property: Tenant ID.
     *
     * @param tenantId the tenantId value to set.
     * @return the RobotPageItem object itself.
     */
    public RobotPageItem setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RobotPageItem setCreatedBy(UUID createdBy) {
        super.setCreatedBy(createdBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RobotPageItem setCreatedAt(OffsetDateTime createdAt) {
        super.setCreatedAt(createdAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RobotPageItem setLastModifiedBy(UUID lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RobotPageItem setLastModifiedAt(OffsetDateTime lastModifiedAt) {
        super.setLastModifiedAt(lastModifiedAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
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
        jsonWriter.writeStringField("id", Objects.toString(this.id, null));
        jsonWriter.writeStringField("code", this.code);
        jsonWriter.writeStringField("name", this.name);
        jsonWriter.writeStringField("sourceType", this.sourceType == null ? null : this.sourceType.toString());
        jsonWriter.writeJsonField("sourceFile", this.sourceFile);
        jsonWriter.writeStringField("tenantId", Objects.toString(this.tenantId, null));
        jsonWriter.writeStringField("description", this.description);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of RobotPageItem from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of RobotPageItem if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the RobotPageItem.
     */
    public static RobotPageItem fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            RobotPageItem deserializedRobotPageItem = new RobotPageItem();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("createdBy".equals(fieldName)) {
                    deserializedRobotPageItem.setCreatedBy(reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString())));
                } else if ("createdAt".equals(fieldName)) {
                    deserializedRobotPageItem.setCreatedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("lastModifiedBy".equals(fieldName)) {
                    deserializedRobotPageItem.setLastModifiedBy(
                        reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                } else if ("lastModifiedAt".equals(fieldName)) {
                    deserializedRobotPageItem.setLastModifiedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("id".equals(fieldName)) {
                    deserializedRobotPageItem.id = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("code".equals(fieldName)) {
                    deserializedRobotPageItem.code = reader.getString();
                } else if ("name".equals(fieldName)) {
                    deserializedRobotPageItem.name = reader.getString();
                } else if ("sourceType".equals(fieldName)) {
                    deserializedRobotPageItem.sourceType = RobotSourceType.fromString(reader.getString());
                } else if ("sourceFile".equals(fieldName)) {
                    deserializedRobotPageItem.sourceFile = RobotSourceFile.fromJson(reader);
                } else if ("tenantId".equals(fieldName)) {
                    deserializedRobotPageItem.tenantId = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("description".equals(fieldName)) {
                    deserializedRobotPageItem.description = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedRobotPageItem;
        });
    }
}
