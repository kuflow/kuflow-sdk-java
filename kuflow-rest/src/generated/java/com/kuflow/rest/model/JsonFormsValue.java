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

/** Json form values, used when the render type selected is JSON Forms. */
@Fluent
public final class JsonFormsValue {

    /*
     * true if the data complain the related json schema.
     */
    @JsonProperty(value = "valid")
    private Boolean valid;

    /*
     * json value filled that complain with the related json schema.
     */
    @JsonProperty(value = "data")
    private Map<String, Object> data;

    /** Creates an instance of JsonFormsValue class. */
    public JsonFormsValue() {}

    /**
     * Get the valid property: true if the data complain the related json schema.
     *
     * @return the valid value.
     */
    public Boolean isValid() {
        return this.valid;
    }

    /**
     * Set the valid property: true if the data complain the related json schema.
     *
     * @param valid the valid value to set.
     * @return the JsonFormsValue object itself.
     */
    public JsonFormsValue setValid(Boolean valid) {
        this.valid = valid;
        return this;
    }

    /**
     * Get the data property: json value filled that complain with the related json schema.
     *
     * @return the data value.
     */
    public Map<String, Object> getData() {
        return this.data;
    }

    /**
     * Set the data property: json value filled that complain with the related json schema.
     *
     * @param data the data value to set.
     * @return the JsonFormsValue object itself.
     */
    public JsonFormsValue setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }
}
