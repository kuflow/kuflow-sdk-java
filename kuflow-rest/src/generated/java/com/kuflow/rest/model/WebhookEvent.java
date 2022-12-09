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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import java.util.UUID;

/** The WebhookEvent model. */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        defaultImpl = WebhookEvent.class)
@JsonTypeName("WebhookEvent")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "PROCESS.STATE_CHANGED", value = WebhookEventProcessStateChanged.class),
    @JsonSubTypes.Type(name = "TASK.STATE_CHANGED", value = WebhookEventTaskStateChanged.class)
})
@Fluent
public class WebhookEvent {
    /*
     * The id property.
     */
    @JsonProperty(value = "id", required = true)
    private UUID id;

    /*
     * The timestamp property.
     */
    @JsonProperty(value = "timestamp", required = true)
    private OffsetDateTime timestamp;

    /** Creates an instance of WebhookEvent class. */
    public WebhookEvent() {}

    /**
     * Get the id property: The id property.
     *
     * @return the id value.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Set the id property: The id property.
     *
     * @param id the id value to set.
     * @return the WebhookEvent object itself.
     */
    public WebhookEvent setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the timestamp property: The timestamp property.
     *
     * @return the timestamp value.
     */
    public OffsetDateTime getTimestamp() {
        return this.timestamp;
    }

    /**
     * Set the timestamp property: The timestamp property.
     *
     * @param timestamp the timestamp value to set.
     * @return the WebhookEvent object itself.
     */
    public WebhookEvent setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
