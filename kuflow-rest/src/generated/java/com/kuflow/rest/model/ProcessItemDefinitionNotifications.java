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
 * Which notifications of a task are sent.
 */
@Fluent
public final class ProcessItemDefinitionNotifications implements JsonSerializable<ProcessItemDefinitionNotifications> {

    /*
     * The processItemTaskCreatedCandidates property.
     */
    @Generated
    private Boolean processItemTaskCreatedCandidates;

    /*
     * The processItemTaskAssignedOwner property.
     */
    @Generated
    private Boolean processItemTaskAssignedOwner;

    /*
     * The processItemTaskPendingReminderListed property.
     */
    @Generated
    private Boolean processItemTaskPendingReminderListed;

    /*
     * The processItemTaskPendingReminder property.
     */
    @Generated
    private Boolean processItemTaskPendingReminder;

    /**
     * Creates an instance of ProcessItemDefinitionNotifications class.
     */
    @Generated
    public ProcessItemDefinitionNotifications() {}

    /**
     * Get the processItemTaskCreatedCandidates property: The processItemTaskCreatedCandidates property.
     *
     * @return the processItemTaskCreatedCandidates value.
     */
    @Generated
    public Boolean isProcessItemTaskCreatedCandidates() {
        return this.processItemTaskCreatedCandidates;
    }

    /**
     * Set the processItemTaskCreatedCandidates property: The processItemTaskCreatedCandidates property.
     *
     * @param processItemTaskCreatedCandidates the processItemTaskCreatedCandidates value to set.
     * @return the ProcessItemDefinitionNotifications object itself.
     */
    @Generated
    public ProcessItemDefinitionNotifications setProcessItemTaskCreatedCandidates(Boolean processItemTaskCreatedCandidates) {
        this.processItemTaskCreatedCandidates = processItemTaskCreatedCandidates;
        return this;
    }

    /**
     * Get the processItemTaskAssignedOwner property: The processItemTaskAssignedOwner property.
     *
     * @return the processItemTaskAssignedOwner value.
     */
    @Generated
    public Boolean isProcessItemTaskAssignedOwner() {
        return this.processItemTaskAssignedOwner;
    }

    /**
     * Set the processItemTaskAssignedOwner property: The processItemTaskAssignedOwner property.
     *
     * @param processItemTaskAssignedOwner the processItemTaskAssignedOwner value to set.
     * @return the ProcessItemDefinitionNotifications object itself.
     */
    @Generated
    public ProcessItemDefinitionNotifications setProcessItemTaskAssignedOwner(Boolean processItemTaskAssignedOwner) {
        this.processItemTaskAssignedOwner = processItemTaskAssignedOwner;
        return this;
    }

    /**
     * Get the processItemTaskPendingReminderListed property: The processItemTaskPendingReminderListed property.
     *
     * @return the processItemTaskPendingReminderListed value.
     */
    @Generated
    public Boolean isProcessItemTaskPendingReminderListed() {
        return this.processItemTaskPendingReminderListed;
    }

    /**
     * Set the processItemTaskPendingReminderListed property: The processItemTaskPendingReminderListed property.
     *
     * @param processItemTaskPendingReminderListed the processItemTaskPendingReminderListed value to set.
     * @return the ProcessItemDefinitionNotifications object itself.
     */
    @Generated
    public ProcessItemDefinitionNotifications setProcessItemTaskPendingReminderListed(Boolean processItemTaskPendingReminderListed) {
        this.processItemTaskPendingReminderListed = processItemTaskPendingReminderListed;
        return this;
    }

    /**
     * Get the processItemTaskPendingReminder property: The processItemTaskPendingReminder property.
     *
     * @return the processItemTaskPendingReminder value.
     */
    @Generated
    public Boolean isProcessItemTaskPendingReminder() {
        return this.processItemTaskPendingReminder;
    }

    /**
     * Set the processItemTaskPendingReminder property: The processItemTaskPendingReminder property.
     *
     * @param processItemTaskPendingReminder the processItemTaskPendingReminder value to set.
     * @return the ProcessItemDefinitionNotifications object itself.
     */
    @Generated
    public ProcessItemDefinitionNotifications setProcessItemTaskPendingReminder(Boolean processItemTaskPendingReminder) {
        this.processItemTaskPendingReminder = processItemTaskPendingReminder;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeBooleanField("processItemTaskCreatedCandidates", this.processItemTaskCreatedCandidates);
        jsonWriter.writeBooleanField("processItemTaskAssignedOwner", this.processItemTaskAssignedOwner);
        jsonWriter.writeBooleanField("processItemTaskPendingReminderListed", this.processItemTaskPendingReminderListed);
        jsonWriter.writeBooleanField("processItemTaskPendingReminder", this.processItemTaskPendingReminder);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessItemDefinitionNotifications from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessItemDefinitionNotifications if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the ProcessItemDefinitionNotifications.
     */
    @Generated
    public static ProcessItemDefinitionNotifications fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessItemDefinitionNotifications deserializedProcessItemDefinitionNotifications = new ProcessItemDefinitionNotifications();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("processItemTaskCreatedCandidates".equals(fieldName)) {
                    deserializedProcessItemDefinitionNotifications.processItemTaskCreatedCandidates = reader.getNullable(
                        JsonReader::getBoolean
                    );
                } else if ("processItemTaskAssignedOwner".equals(fieldName)) {
                    deserializedProcessItemDefinitionNotifications.processItemTaskAssignedOwner = reader.getNullable(
                        JsonReader::getBoolean
                    );
                } else if ("processItemTaskPendingReminderListed".equals(fieldName)) {
                    deserializedProcessItemDefinitionNotifications.processItemTaskPendingReminderListed = reader.getNullable(
                        JsonReader::getBoolean
                    );
                } else if ("processItemTaskPendingReminder".equals(fieldName)) {
                    deserializedProcessItemDefinitionNotifications.processItemTaskPendingReminder = reader.getNullable(
                        JsonReader::getBoolean
                    );
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessItemDefinitionNotifications;
        });
    }
}
