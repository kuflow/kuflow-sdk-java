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
 * Defines values for ProcessDefinitionSchedulerOverlapPolicy.
 */
public final class ProcessDefinitionSchedulerOverlapPolicy extends ExpandableStringEnum<ProcessDefinitionSchedulerOverlapPolicy> {

    /**
     * Static value SKIP for ProcessDefinitionSchedulerOverlapPolicy.
     */
    @Generated
    public static final ProcessDefinitionSchedulerOverlapPolicy SKIP = fromString("SKIP");

    /**
     * Static value ALLOW_ALL for ProcessDefinitionSchedulerOverlapPolicy.
     */
    @Generated
    public static final ProcessDefinitionSchedulerOverlapPolicy ALLOW_ALL = fromString("ALLOW_ALL");

    /**
     * Creates a new instance of ProcessDefinitionSchedulerOverlapPolicy value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public ProcessDefinitionSchedulerOverlapPolicy() {}

    /**
     * Creates or finds a ProcessDefinitionSchedulerOverlapPolicy from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding ProcessDefinitionSchedulerOverlapPolicy.
     */
    @Generated
    public static ProcessDefinitionSchedulerOverlapPolicy fromString(String name) {
        return fromString(name, ProcessDefinitionSchedulerOverlapPolicy.class);
    }

    /**
     * Gets known ProcessDefinitionSchedulerOverlapPolicy values.
     *
     * @return known ProcessDefinitionSchedulerOverlapPolicy values.
     */
    @Generated
    public static Collection<ProcessDefinitionSchedulerOverlapPolicy> values() {
        return values(ProcessDefinitionSchedulerOverlapPolicy.class);
    }
}
