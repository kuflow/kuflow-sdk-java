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

import com.azure.core.annotation.Generated;
import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * The operation to perform.
 */
public final class JsonPatchOperationType extends ExpandableStringEnum<JsonPatchOperationType> {

    /**
     * Static value add for JsonPatchOperationType.
     */
    @Generated
    public static final JsonPatchOperationType ADD = fromString("add");

    /**
     * Static value remove for JsonPatchOperationType.
     */
    @Generated
    public static final JsonPatchOperationType REMOVE = fromString("remove");

    /**
     * Static value replace for JsonPatchOperationType.
     */
    @Generated
    public static final JsonPatchOperationType REPLACE = fromString("replace");

    /**
     * Static value move for JsonPatchOperationType.
     */
    @Generated
    public static final JsonPatchOperationType MOVE = fromString("move");

    /**
     * Static value copy for JsonPatchOperationType.
     */
    @Generated
    public static final JsonPatchOperationType COPY = fromString("copy");

    /**
     * Static value test for JsonPatchOperationType.
     */
    @Generated
    public static final JsonPatchOperationType TEST = fromString("test");

    /**
     * Creates a new instance of JsonPatchOperationType value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public JsonPatchOperationType() {}

    /**
     * Creates or finds a JsonPatchOperationType from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding JsonPatchOperationType.
     */
    @Generated
    public static JsonPatchOperationType fromString(String name) {
        return fromString(name, JsonPatchOperationType.class);
    }

    /**
     * Gets known JsonPatchOperationType values.
     *
     * @return known JsonPatchOperationType values.
     */
    @Generated
    public static Collection<JsonPatchOperationType> values() {
        return values(JsonPatchOperationType.class);
    }
}
