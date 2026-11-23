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
 * What runs the processes of a Process Definition.
 */
public final class ProcessDefinitionWorkflowEngine extends ExpandableStringEnum<ProcessDefinitionWorkflowEngine> {

    /**
     * Static value TEMPORAL for ProcessDefinitionWorkflowEngine.
     */
    @Generated
    public static final ProcessDefinitionWorkflowEngine TEMPORAL = fromString("TEMPORAL");

    /**
     * Static value WEBHOOK for ProcessDefinitionWorkflowEngine.
     */
    @Generated
    public static final ProcessDefinitionWorkflowEngine WEBHOOK = fromString("WEBHOOK");

    /**
     * Static value SERVERLESS_WORKFLOW for ProcessDefinitionWorkflowEngine.
     */
    @Generated
    public static final ProcessDefinitionWorkflowEngine SERVERLESS_WORKFLOW = fromString("SERVERLESS_WORKFLOW");

    /**
     * Static value SERVERLESS_WORKFLOW_V2 for ProcessDefinitionWorkflowEngine.
     */
    @Generated
    public static final ProcessDefinitionWorkflowEngine SERVERLESS_WORKFLOW_V2 = fromString("SERVERLESS_WORKFLOW_V2");

    /**
     * Static value NONE for ProcessDefinitionWorkflowEngine.
     */
    @Generated
    public static final ProcessDefinitionWorkflowEngine NONE = fromString("NONE");

    /**
     * Creates a new instance of ProcessDefinitionWorkflowEngine value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public ProcessDefinitionWorkflowEngine() {}

    /**
     * Creates or finds a ProcessDefinitionWorkflowEngine from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding ProcessDefinitionWorkflowEngine.
     */
    @Generated
    public static ProcessDefinitionWorkflowEngine fromString(String name) {
        return fromString(name, ProcessDefinitionWorkflowEngine.class);
    }

    /**
     * Gets known ProcessDefinitionWorkflowEngine values.
     *
     * @return known ProcessDefinitionWorkflowEngine values.
     */
    @Generated
    public static Collection<ProcessDefinitionWorkflowEngine> values() {
        return values(ProcessDefinitionWorkflowEngine.class);
    }
}
