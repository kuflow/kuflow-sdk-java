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
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * Command to assign task, only one option is required.
 */
@Fluent
public final class TaskAssignCommand implements JsonSerializable<TaskAssignCommand> {

    /*
     * The principalId property.
     */
    private UUID principalId;

    /*
     * The email property.
     */
    private String email;

    /**
     * Creates an instance of TaskAssignCommand class.
     */
    public TaskAssignCommand() {}

    /**
     * Get the principalId property: The principalId property.
     *
     * @return the principalId value.
     */
    public UUID getPrincipalId() {
        return this.principalId;
    }

    /**
     * Set the principalId property: The principalId property.
     *
     * @param principalId the principalId value to set.
     * @return the TaskAssignCommand object itself.
     */
    public TaskAssignCommand setPrincipalId(UUID principalId) {
        this.principalId = principalId;
        return this;
    }

    /**
     * Get the email property: The email property.
     *
     * @return the email value.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Set the email property: The email property.
     *
     * @param email the email value to set.
     * @return the TaskAssignCommand object itself.
     */
    public TaskAssignCommand setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("principalId", Objects.toString(this.principalId, null));
        jsonWriter.writeStringField("email", this.email);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of TaskAssignCommand from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of TaskAssignCommand if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the TaskAssignCommand.
     */
    public static TaskAssignCommand fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            TaskAssignCommand deserializedTaskAssignCommand = new TaskAssignCommand();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("principalId".equals(fieldName)) {
                    deserializedTaskAssignCommand.principalId = reader.getNullable(
                        nonNullReader -> UUID.fromString(nonNullReader.getString())
                    );
                } else if ("email".equals(fieldName)) {
                    deserializedTaskAssignCommand.email = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedTaskAssignCommand;
        });
    }
}
