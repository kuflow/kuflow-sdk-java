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
import java.util.Map;

/**
 * The TenantUserMetadata model.
 */
@Fluent
public final class TenantUserMetadata {

    /*
     * The valid property.
     */
    @JsonProperty(value = "valid", required = true)
    private boolean valid;

    /*
     * Dictionary of <any>
     */
    @JsonProperty(value = "value", required = true)
    private Map<String, Object> value;

    /**
     * Creates an instance of TenantUserMetadata class.
     */
    public TenantUserMetadata() {}

    /**
     * Get the valid property: The valid property.
     *
     * @return the valid value.
     */
    public boolean isValid() {
        return this.valid;
    }

    /**
     * Set the valid property: The valid property.
     *
     * @param valid the valid value to set.
     * @return the TenantUserMetadata object itself.
     */
    public TenantUserMetadata setValid(boolean valid) {
        this.valid = valid;
        return this;
    }

    /**
     * Get the value property: Dictionary of &lt;any&gt;.
     *
     * @return the value value.
     */
    public Map<String, Object> getValue() {
        return this.value;
    }

    /**
     * Set the value property: Dictionary of &lt;any&gt;.
     *
     * @param value the value value to set.
     * @return the TenantUserMetadata object itself.
     */
    public TenantUserMetadata setValue(Map<String, Object> value) {
        this.value = value;
        return this;
    }
}
