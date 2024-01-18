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

/**
 * The TaskElementValue model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", defaultImpl = TaskElementValue.class)
@JsonTypeName("TaskElementValue")
@JsonSubTypes(
    {
        @JsonSubTypes.Type(name = "STRING", value = TaskElementValueString.class),
        @JsonSubTypes.Type(name = "NUMBER", value = TaskElementValueNumber.class),
        @JsonSubTypes.Type(name = "OBJECT", value = TaskElementValueObject.class),
        @JsonSubTypes.Type(name = "DOCUMENT", value = TaskElementValueDocument.class),
        @JsonSubTypes.Type(name = "PRINCIPAL", value = TaskElementValuePrincipal.class),
    }
)
@Fluent
public class TaskElementValue {

    /*
     * The valid property.
     */
    @JsonProperty(value = "valid")
    private Boolean valid;

    /**
     * Creates an instance of TaskElementValue class.
     */
    public TaskElementValue() {}

    /**
     * Get the valid property: The valid property.
     *
     * @return the valid value.
     */
    public Boolean isValid() {
        return this.valid;
    }

    /**
     * Set the valid property: The valid property.
     *
     * @param valid the valid value to set.
     * @return the TaskElementValue object itself.
     */
    public TaskElementValue setValid(Boolean valid) {
        this.valid = valid;
        return this;
    }
}
