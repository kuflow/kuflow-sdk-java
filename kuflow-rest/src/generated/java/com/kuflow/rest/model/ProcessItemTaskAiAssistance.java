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
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

/**
 * Status of the latest AI assistance run for a process item task.
 */
@Fluent
public final class ProcessItemTaskAiAssistance implements JsonSerializable<ProcessItemTaskAiAssistance> {

    /*
     * Client-supplied UUID identifying the logical AI assistance attempt this run represents.
     */
    @Generated
    private UUID requestId;

    /*
     * State of an AI assistance run.
     */
    @Generated
    private ProcessItemTaskAiAssistanceState state;

    /*
     * Number of document embeddings still queued. Only present when state is PENDING.
     */
    @Generated
    private Integer embeddingPending;

    /*
     * Number of document embeddings actively processing. Only present when state is PENDING.
     */
    @Generated
    private Integer embeddingProcessing;

    /*
     * Suggested number of seconds to wait before polling again. Only present when state is PENDING.
     */
    @Generated
    private Integer retryAfterSeconds;

    /*
     * The AI model used for the assistance. Only present when state is COMPLETED.
     */
    @Generated
    private String model;

    /*
     * The reason the AI model stopped generating. Only present when state is COMPLETED.
     */
    @Generated
    private String finishReason;

    /*
     * Error code. Only present when state is FAILED.
     */
    @Generated
    private String errorCode;

    /*
     * Human-readable error description. Only present when state is FAILED.
     */
    @Generated
    private String errorMessage;

    /*
     * When this run was started.
     */
    @Generated
    private OffsetDateTime startedAt;

    /*
     * When this run finished (COMPLETED or FAILED). Absent while PENDING.
     */
    @Generated
    private OffsetDateTime finishedAt;

    /**
     * Creates an instance of ProcessItemTaskAiAssistance class.
     */
    @Generated
    public ProcessItemTaskAiAssistance() {}

    /**
     * Get the requestId property: Client-supplied UUID identifying the logical AI assistance attempt this run
     * represents.
     *
     * @return the requestId value.
     */
    @Generated
    public UUID getRequestId() {
        return this.requestId;
    }

    /**
     * Set the requestId property: Client-supplied UUID identifying the logical AI assistance attempt this run
     * represents.
     *
     * @param requestId the requestId value to set.
     * @return the ProcessItemTaskAiAssistance object itself.
     */
    @Generated
    public ProcessItemTaskAiAssistance setRequestId(UUID requestId) {
        this.requestId = requestId;
        return this;
    }

    /**
     * Get the state property: State of an AI assistance run.
     *
     * @return the state value.
     */
    @Generated
    public ProcessItemTaskAiAssistanceState getState() {
        return this.state;
    }

    /**
     * Set the state property: State of an AI assistance run.
     *
     * @param state the state value to set.
     * @return the ProcessItemTaskAiAssistance object itself.
     */
    @Generated
    public ProcessItemTaskAiAssistance setState(ProcessItemTaskAiAssistanceState state) {
        this.state = state;
        return this;
    }

    /**
     * Get the embeddingPending property: Number of document embeddings still queued. Only present when state is
     * PENDING.
     *
     * @return the embeddingPending value.
     */
    @Generated
    public Integer getEmbeddingPending() {
        return this.embeddingPending;
    }

    /**
     * Set the embeddingPending property: Number of document embeddings still queued. Only present when state is
     * PENDING.
     *
     * @param embeddingPending the embeddingPending value to set.
     * @return the ProcessItemTaskAiAssistance object itself.
     */
    @Generated
    public ProcessItemTaskAiAssistance setEmbeddingPending(Integer embeddingPending) {
        this.embeddingPending = embeddingPending;
        return this;
    }

    /**
     * Get the embeddingProcessing property: Number of document embeddings actively processing. Only present when state
     * is PENDING.
     *
     * @return the embeddingProcessing value.
     */
    @Generated
    public Integer getEmbeddingProcessing() {
        return this.embeddingProcessing;
    }

    /**
     * Set the embeddingProcessing property: Number of document embeddings actively processing. Only present when state
     * is PENDING.
     *
     * @param embeddingProcessing the embeddingProcessing value to set.
     * @return the ProcessItemTaskAiAssistance object itself.
     */
    @Generated
    public ProcessItemTaskAiAssistance setEmbeddingProcessing(Integer embeddingProcessing) {
        this.embeddingProcessing = embeddingProcessing;
        return this;
    }

    /**
     * Get the retryAfterSeconds property: Suggested number of seconds to wait before polling again. Only present when
     * state is PENDING.
     *
     * @return the retryAfterSeconds value.
     */
    @Generated
    public Integer getRetryAfterSeconds() {
        return this.retryAfterSeconds;
    }

    /**
     * Set the retryAfterSeconds property: Suggested number of seconds to wait before polling again. Only present when
     * state is PENDING.
     *
     * @param retryAfterSeconds the retryAfterSeconds value to set.
     * @return the ProcessItemTaskAiAssistance object itself.
     */
    @Generated
    public ProcessItemTaskAiAssistance setRetryAfterSeconds(Integer retryAfterSeconds) {
        this.retryAfterSeconds = retryAfterSeconds;
        return this;
    }

    /**
     * Get the model property: The AI model used for the assistance. Only present when state is COMPLETED.
     *
     * @return the model value.
     */
    @Generated
    public String getModel() {
        return this.model;
    }

    /**
     * Set the model property: The AI model used for the assistance. Only present when state is COMPLETED.
     *
     * @param model the model value to set.
     * @return the ProcessItemTaskAiAssistance object itself.
     */
    @Generated
    public ProcessItemTaskAiAssistance setModel(String model) {
        this.model = model;
        return this;
    }

    /**
     * Get the finishReason property: The reason the AI model stopped generating. Only present when state is COMPLETED.
     *
     * @return the finishReason value.
     */
    @Generated
    public String getFinishReason() {
        return this.finishReason;
    }

    /**
     * Set the finishReason property: The reason the AI model stopped generating. Only present when state is COMPLETED.
     *
     * @param finishReason the finishReason value to set.
     * @return the ProcessItemTaskAiAssistance object itself.
     */
    @Generated
    public ProcessItemTaskAiAssistance setFinishReason(String finishReason) {
        this.finishReason = finishReason;
        return this;
    }

    /**
     * Get the errorCode property: Error code. Only present when state is FAILED.
     *
     * @return the errorCode value.
     */
    @Generated
    public String getErrorCode() {
        return this.errorCode;
    }

    /**
     * Set the errorCode property: Error code. Only present when state is FAILED.
     *
     * @param errorCode the errorCode value to set.
     * @return the ProcessItemTaskAiAssistance object itself.
     */
    @Generated
    public ProcessItemTaskAiAssistance setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    /**
     * Get the errorMessage property: Human-readable error description. Only present when state is FAILED.
     *
     * @return the errorMessage value.
     */
    @Generated
    public String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * Set the errorMessage property: Human-readable error description. Only present when state is FAILED.
     *
     * @param errorMessage the errorMessage value to set.
     * @return the ProcessItemTaskAiAssistance object itself.
     */
    @Generated
    public ProcessItemTaskAiAssistance setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    /**
     * Get the startedAt property: When this run was started.
     *
     * @return the startedAt value.
     */
    @Generated
    public OffsetDateTime getStartedAt() {
        return this.startedAt;
    }

    /**
     * Set the startedAt property: When this run was started.
     *
     * @param startedAt the startedAt value to set.
     * @return the ProcessItemTaskAiAssistance object itself.
     */
    @Generated
    public ProcessItemTaskAiAssistance setStartedAt(OffsetDateTime startedAt) {
        this.startedAt = startedAt;
        return this;
    }

    /**
     * Get the finishedAt property: When this run finished (COMPLETED or FAILED). Absent while PENDING.
     *
     * @return the finishedAt value.
     */
    @Generated
    public OffsetDateTime getFinishedAt() {
        return this.finishedAt;
    }

    /**
     * Set the finishedAt property: When this run finished (COMPLETED or FAILED). Absent while PENDING.
     *
     * @param finishedAt the finishedAt value to set.
     * @return the ProcessItemTaskAiAssistance object itself.
     */
    @Generated
    public ProcessItemTaskAiAssistance setFinishedAt(OffsetDateTime finishedAt) {
        this.finishedAt = finishedAt;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("requestId", Objects.toString(this.requestId, null));
        jsonWriter.writeStringField("state", this.state == null ? null : this.state.toString());
        jsonWriter.writeStringField(
            "startedAt",
            this.startedAt == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.startedAt)
        );
        jsonWriter.writeNumberField("embeddingPending", this.embeddingPending);
        jsonWriter.writeNumberField("embeddingProcessing", this.embeddingProcessing);
        jsonWriter.writeNumberField("retryAfterSeconds", this.retryAfterSeconds);
        jsonWriter.writeStringField("model", this.model);
        jsonWriter.writeStringField("finishReason", this.finishReason);
        jsonWriter.writeStringField("errorCode", this.errorCode);
        jsonWriter.writeStringField("errorMessage", this.errorMessage);
        jsonWriter.writeStringField(
            "finishedAt",
            this.finishedAt == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.finishedAt)
        );
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessItemTaskAiAssistance from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessItemTaskAiAssistance if the JsonReader was pointing to an instance of it, or null
     * if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessItemTaskAiAssistance.
     */
    @Generated
    public static ProcessItemTaskAiAssistance fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessItemTaskAiAssistance deserializedProcessItemTaskAiAssistance = new ProcessItemTaskAiAssistance();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("requestId".equals(fieldName)) {
                    deserializedProcessItemTaskAiAssistance.requestId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("state".equals(fieldName)) {
                    deserializedProcessItemTaskAiAssistance.state = ProcessItemTaskAiAssistanceState.fromString(reader.getString());
                } else if ("startedAt".equals(fieldName)) {
                    deserializedProcessItemTaskAiAssistance.startedAt = reader.getNullable(nonNullReader ->
                        CoreUtils.parseBestOffsetDateTime(nonNullReader.getString())
                    );
                } else if ("embeddingPending".equals(fieldName)) {
                    deserializedProcessItemTaskAiAssistance.embeddingPending = reader.getNullable(JsonReader::getInt);
                } else if ("embeddingProcessing".equals(fieldName)) {
                    deserializedProcessItemTaskAiAssistance.embeddingProcessing = reader.getNullable(JsonReader::getInt);
                } else if ("retryAfterSeconds".equals(fieldName)) {
                    deserializedProcessItemTaskAiAssistance.retryAfterSeconds = reader.getNullable(JsonReader::getInt);
                } else if ("model".equals(fieldName)) {
                    deserializedProcessItemTaskAiAssistance.model = reader.getString();
                } else if ("finishReason".equals(fieldName)) {
                    deserializedProcessItemTaskAiAssistance.finishReason = reader.getString();
                } else if ("errorCode".equals(fieldName)) {
                    deserializedProcessItemTaskAiAssistance.errorCode = reader.getString();
                } else if ("errorMessage".equals(fieldName)) {
                    deserializedProcessItemTaskAiAssistance.errorMessage = reader.getString();
                } else if ("finishedAt".equals(fieldName)) {
                    deserializedProcessItemTaskAiAssistance.finishedAt = reader.getNullable(nonNullReader ->
                        CoreUtils.parseBestOffsetDateTime(nonNullReader.getString())
                    );
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessItemTaskAiAssistance;
        });
    }
}
