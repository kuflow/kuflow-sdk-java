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
 * State of a version of a Process Definition: DRAFT while it is being edited, CURRENT for the one that new
 * processes use, and SUPERSEDED once a later one was published.
 */
public final class ProcessDefinitionVersionState extends ExpandableStringEnum<ProcessDefinitionVersionState> {

    /**
     * Static value DRAFT for ProcessDefinitionVersionState.
     */
    @Generated
    public static final ProcessDefinitionVersionState DRAFT = fromString("DRAFT");

    /**
     * Static value CURRENT for ProcessDefinitionVersionState.
     */
    @Generated
    public static final ProcessDefinitionVersionState CURRENT = fromString("CURRENT");

    /**
     * Static value SUPERSEDED for ProcessDefinitionVersionState.
     */
    @Generated
    public static final ProcessDefinitionVersionState SUPERSEDED = fromString("SUPERSEDED");

    /**
     * Creates a new instance of ProcessDefinitionVersionState value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public ProcessDefinitionVersionState() {}

    /**
     * Creates or finds a ProcessDefinitionVersionState from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding ProcessDefinitionVersionState.
     */
    @Generated
    public static ProcessDefinitionVersionState fromString(String name) {
        return fromString(name, ProcessDefinitionVersionState.class);
    }

    /**
     * Gets known ProcessDefinitionVersionState values.
     *
     * @return known ProcessDefinitionVersionState values.
     */
    @Generated
    public static Collection<ProcessDefinitionVersionState> values() {
        return values(ProcessDefinitionVersionState.class);
    }
}
