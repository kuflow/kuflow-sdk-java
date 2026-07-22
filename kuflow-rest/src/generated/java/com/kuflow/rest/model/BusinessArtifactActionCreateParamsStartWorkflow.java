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
import java.util.Map;

/**
 * Params for invoking an action of type START_WORKFLOW.
 */
@Fluent
public final class BusinessArtifactActionCreateParamsStartWorkflow
    implements JsonSerializable<BusinessArtifactActionCreateParamsStartWorkflow>
{

    /*
     * Input passed to the workflow. Validated against the action's input schema.
     */
    @Generated
    private Map<String, Object> input;

    /**
     * Creates an instance of BusinessArtifactActionCreateParamsStartWorkflow class.
     */
    @Generated
    public BusinessArtifactActionCreateParamsStartWorkflow() {}

    /**
     * Get the input property: Input passed to the workflow. Validated against the action's input schema.
     *
     * @return the input value.
     */
    @Generated
    public Map<String, Object> getInput() {
        return this.input;
    }

    /**
     * Set the input property: Input passed to the workflow. Validated against the action's input schema.
     *
     * @param input the input value to set.
     * @return the BusinessArtifactActionCreateParamsStartWorkflow object itself.
     */
    @Generated
    public BusinessArtifactActionCreateParamsStartWorkflow setInput(Map<String, Object> input) {
        this.input = input;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeMapField("input", this.input, (writer, element) -> writer.writeUntyped(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BusinessArtifactActionCreateParamsStartWorkflow from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of BusinessArtifactActionCreateParamsStartWorkflow if the JsonReader was pointing to an
     * instance of it, or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the BusinessArtifactActionCreateParamsStartWorkflow.
     */
    @Generated
    public static BusinessArtifactActionCreateParamsStartWorkflow fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BusinessArtifactActionCreateParamsStartWorkflow deserializedBusinessArtifactActionCreateParamsStartWorkflow =
                new BusinessArtifactActionCreateParamsStartWorkflow();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("input".equals(fieldName)) {
                    Map<String, Object> input = reader.readMap(reader1 -> reader1.readUntyped());
                    deserializedBusinessArtifactActionCreateParamsStartWorkflow.input = input;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBusinessArtifactActionCreateParamsStartWorkflow;
        });
    }
}
