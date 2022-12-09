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
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import java.util.UUID;

/** Process Events. */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("TASK.STATE_CHANGED")
@Fluent
public final class WebhookEventTaskStateChanged extends WebhookEvent {
    /*
     * The data property.
     */
    @JsonProperty(value = "data", required = true)
    private WebhookEventTaskStateChangedData data;

    /** Creates an instance of WebhookEventTaskStateChanged class. */
    public WebhookEventTaskStateChanged() {}

    /**
     * Get the data property: The data property.
     *
     * @return the data value.
     */
    public WebhookEventTaskStateChangedData getData() {
        return this.data;
    }

    /**
     * Set the data property: The data property.
     *
     * @param data the data value to set.
     * @return the WebhookEventTaskStateChanged object itself.
     */
    public WebhookEventTaskStateChanged setData(WebhookEventTaskStateChangedData data) {
        this.data = data;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public WebhookEventTaskStateChanged setId(UUID id) {
        super.setId(id);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public WebhookEventTaskStateChanged setTimestamp(OffsetDateTime timestamp) {
        super.setTimestamp(timestamp);
        return this;
    }
}
