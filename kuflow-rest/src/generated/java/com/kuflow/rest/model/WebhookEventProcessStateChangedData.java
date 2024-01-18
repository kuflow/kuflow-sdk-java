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
import java.util.UUID;

/** The WebhookEventProcessStateChangedData model. */
@Fluent
public final class WebhookEventProcessStateChangedData {

    /*
     * The processId property.
     */
    @JsonProperty(value = "processId", required = true)
    private UUID processId;

    /*
     * Process state
     */
    @JsonProperty(value = "processState", required = true)
    private ProcessState processState;

    /** Creates an instance of WebhookEventProcessStateChangedData class. */
    public WebhookEventProcessStateChangedData() {}

    /**
     * Get the processId property: The processId property.
     *
     * @return the processId value.
     */
    public UUID getProcessId() {
        return this.processId;
    }

    /**
     * Set the processId property: The processId property.
     *
     * @param processId the processId value to set.
     * @return the WebhookEventProcessStateChangedData object itself.
     */
    public WebhookEventProcessStateChangedData setProcessId(UUID processId) {
        this.processId = processId;
        return this;
    }

    /**
     * Get the processState property: Process state.
     *
     * @return the processState value.
     */
    public ProcessState getProcessState() {
        return this.processState;
    }

    /**
     * Set the processState property: Process state.
     *
     * @param processState the processState value to set.
     * @return the WebhookEventProcessStateChangedData object itself.
     */
    public WebhookEventProcessStateChangedData setProcessState(ProcessState processState) {
        this.processState = processState;
        return this;
    }
}
