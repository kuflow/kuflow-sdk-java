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
import java.util.List;
import java.util.UUID;

/** The RelatedProcess model. */
@Fluent
public final class RelatedProcess {
    /*
     * Processes whose relationship target is the current process.
     */
    @JsonProperty(value = "incoming")
    private List<UUID> incoming;

    /*
     * Processes to which the current process relates.
     */
    @JsonProperty(value = "outcoming")
    private List<UUID> outcoming;

    /** Creates an instance of RelatedProcess class. */
    public RelatedProcess() {}

    /**
     * Get the incoming property: Processes whose relationship target is the current process.
     *
     * @return the incoming value.
     */
    public List<UUID> getIncoming() {
        return this.incoming;
    }

    /**
     * Set the incoming property: Processes whose relationship target is the current process.
     *
     * @param incoming the incoming value to set.
     * @return the RelatedProcess object itself.
     */
    public RelatedProcess setIncoming(List<UUID> incoming) {
        this.incoming = incoming;
        return this;
    }

    /**
     * Get the outcoming property: Processes to which the current process relates.
     *
     * @return the outcoming value.
     */
    public List<UUID> getOutcoming() {
        return this.outcoming;
    }

    /**
     * Set the outcoming property: Processes to which the current process relates.
     *
     * @param outcoming the outcoming value to set.
     * @return the RelatedProcess object itself.
     */
    public RelatedProcess setOutcoming(List<UUID> outcoming) {
        this.outcoming = outcoming;
        return this;
    }
}
