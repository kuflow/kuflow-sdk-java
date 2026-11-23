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
 * State of a Process Definition: DRAFT if it was never published, PUBLISHED if processes can be created from it,
 * and UNPUBLISHED if no process can be created from it anymore.
 */
public final class ProcessDefinitionState extends ExpandableStringEnum<ProcessDefinitionState> {

    /**
     * Static value DRAFT for ProcessDefinitionState.
     */
    @Generated
    public static final ProcessDefinitionState DRAFT = fromString("DRAFT");

    /**
     * Static value PUBLISHED for ProcessDefinitionState.
     */
    @Generated
    public static final ProcessDefinitionState PUBLISHED = fromString("PUBLISHED");

    /**
     * Static value UNPUBLISHED for ProcessDefinitionState.
     */
    @Generated
    public static final ProcessDefinitionState UNPUBLISHED = fromString("UNPUBLISHED");

    /**
     * Creates a new instance of ProcessDefinitionState value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public ProcessDefinitionState() {}

    /**
     * Creates or finds a ProcessDefinitionState from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding ProcessDefinitionState.
     */
    @Generated
    public static ProcessDefinitionState fromString(String name) {
        return fromString(name, ProcessDefinitionState.class);
    }

    /**
     * Gets known ProcessDefinitionState values.
     *
     * @return known ProcessDefinitionState values.
     */
    @Generated
    public static Collection<ProcessDefinitionState> values() {
        return values(ProcessDefinitionState.class);
    }
}
