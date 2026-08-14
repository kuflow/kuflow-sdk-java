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
 * Type of a Process action.
 */
public final class ProcessActionType extends ExpandableStringEnum<ProcessActionType> {

    /**
     * Static value DOWNLOADABLE for ProcessActionType.
     */
    @Generated
    public static final ProcessActionType DOWNLOADABLE = fromString("DOWNLOADABLE");

    /**
     * Static value START_RELATED_PROCESS for ProcessActionType.
     */
    @Generated
    public static final ProcessActionType START_RELATED_PROCESS = fromString("START_RELATED_PROCESS");

    /**
     * Static value START_WORKFLOW for ProcessActionType.
     */
    @Generated
    public static final ProcessActionType START_WORKFLOW = fromString("START_WORKFLOW");

    /**
     * Static value CREATE_TASK for ProcessActionType.
     */
    @Generated
    public static final ProcessActionType CREATE_TASK = fromString("CREATE_TASK");

    /**
     * Static value CREATE_PROCESS_ITEM_MESSAGE for ProcessActionType.
     */
    @Generated
    public static final ProcessActionType CREATE_PROCESS_ITEM_MESSAGE = fromString("CREATE_PROCESS_ITEM_MESSAGE");

    /**
     * Static value CREATE_PROCESS_ITEM_THREAD for ProcessActionType.
     */
    @Generated
    public static final ProcessActionType CREATE_PROCESS_ITEM_THREAD = fromString("CREATE_PROCESS_ITEM_THREAD");

    /**
     * Static value SEND_WORKFLOW_SIGNAL for ProcessActionType.
     */
    @Generated
    public static final ProcessActionType SEND_WORKFLOW_SIGNAL = fromString("SEND_WORKFLOW_SIGNAL");

    /**
     * Creates a new instance of ProcessActionType value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public ProcessActionType() {}

    /**
     * Creates or finds a ProcessActionType from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding ProcessActionType.
     */
    @Generated
    public static ProcessActionType fromString(String name) {
        return fromString(name, ProcessActionType.class);
    }

    /**
     * Gets known ProcessActionType values.
     *
     * @return known ProcessActionType values.
     */
    @Generated
    public static Collection<ProcessActionType> values() {
        return values(ProcessActionType.class);
    }
}
