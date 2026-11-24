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
import java.util.List;

/**
 * What an update of a Process Definition changed, or would change with `dryRun`. When it was applied, also the
 * Process Definition after it, with its new revision and version.
 */
@Fluent
public final class ProcessDefinitionUpdateResult implements JsonSerializable<ProcessDefinitionUpdateResult> {

    /*
     * True when nothing was applied.
     */
    @Generated
    private boolean dryRun;

    /*
     * What changes, compared with the document that the definition had: empty when the document given was equal
     * to it.
     */
    @Generated
    private List<ProcessDefinitionChange> changes;

    /*
     * A whole Process Definition at one of its versions. Its parts refer to each other by code: the forms of the
     * definition and of its tasks, and the task or thread of an action.
     */
    @Generated
    private ProcessDefinition processDefinition;

    /**
     * Creates an instance of ProcessDefinitionUpdateResult class.
     */
    @Generated
    public ProcessDefinitionUpdateResult() {}

    /**
     * Get the dryRun property: True when nothing was applied.
     *
     * @return the dryRun value.
     */
    @Generated
    public boolean isDryRun() {
        return this.dryRun;
    }

    /**
     * Set the dryRun property: True when nothing was applied.
     *
     * @param dryRun the dryRun value to set.
     * @return the ProcessDefinitionUpdateResult object itself.
     */
    @Generated
    public ProcessDefinitionUpdateResult setDryRun(boolean dryRun) {
        this.dryRun = dryRun;
        return this;
    }

    /**
     * Get the changes property: What changes, compared with the document that the definition had: empty when the
     * document given was equal
     * to it.
     *
     * @return the changes value.
     */
    @Generated
    public List<ProcessDefinitionChange> getChanges() {
        return this.changes;
    }

    /**
     * Set the changes property: What changes, compared with the document that the definition had: empty when the
     * document given was equal
     * to it.
     *
     * @param changes the changes value to set.
     * @return the ProcessDefinitionUpdateResult object itself.
     */
    @Generated
    public ProcessDefinitionUpdateResult setChanges(List<ProcessDefinitionChange> changes) {
        this.changes = changes;
        return this;
    }

    /**
     * Get the processDefinition property: A whole Process Definition at one of its versions. Its parts refer to each
     * other by code: the forms of the
     * definition and of its tasks, and the task or thread of an action.
     *
     * @return the processDefinition value.
     */
    @Generated
    public ProcessDefinition getProcessDefinition() {
        return this.processDefinition;
    }

    /**
     * Set the processDefinition property: A whole Process Definition at one of its versions. Its parts refer to each
     * other by code: the forms of the
     * definition and of its tasks, and the task or thread of an action.
     *
     * @param processDefinition the processDefinition value to set.
     * @return the ProcessDefinitionUpdateResult object itself.
     */
    @Generated
    public ProcessDefinitionUpdateResult setProcessDefinition(ProcessDefinition processDefinition) {
        this.processDefinition = processDefinition;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeBooleanField("dryRun", this.dryRun);
        jsonWriter.writeArrayField("changes", this.changes, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeJsonField("processDefinition", this.processDefinition);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessDefinitionUpdateResult from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessDefinitionUpdateResult if the JsonReader was pointing to an instance of it, or null
     * if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessDefinitionUpdateResult.
     */
    @Generated
    public static ProcessDefinitionUpdateResult fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessDefinitionUpdateResult deserializedProcessDefinitionUpdateResult = new ProcessDefinitionUpdateResult();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("dryRun".equals(fieldName)) {
                    deserializedProcessDefinitionUpdateResult.dryRun = reader.getBoolean();
                } else if ("changes".equals(fieldName)) {
                    List<ProcessDefinitionChange> changes = reader.readArray(reader1 -> ProcessDefinitionChange.fromJson(reader1));
                    deserializedProcessDefinitionUpdateResult.changes = changes;
                } else if ("processDefinition".equals(fieldName)) {
                    deserializedProcessDefinitionUpdateResult.processDefinition = ProcessDefinition.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessDefinitionUpdateResult;
        });
    }
}
