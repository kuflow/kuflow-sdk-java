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

import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * Process Item Type.
 */
public final class ProcessItemType extends ExpandableStringEnum<ProcessItemType> {

    /**
     * Static value TASK for ProcessItemType.
     */
    public static final ProcessItemType TASK = fromString("TASK");

    /**
     * Static value MESSAGE for ProcessItemType.
     */
    public static final ProcessItemType MESSAGE = fromString("MESSAGE");

    /**
     * Static value THREAD for ProcessItemType.
     */
    public static final ProcessItemType THREAD = fromString("THREAD");

    /**
     * Creates a new instance of ProcessItemType value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public ProcessItemType() {}

    /**
     * Creates or finds a ProcessItemType from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding ProcessItemType.
     */
    public static ProcessItemType fromString(String name) {
        return fromString(name, ProcessItemType.class);
    }

    /**
     * Gets known ProcessItemType values.
     *
     * @return known ProcessItemType values.
     */
    public static Collection<ProcessItemType> values() {
        return values(ProcessItemType.class);
    }
}
