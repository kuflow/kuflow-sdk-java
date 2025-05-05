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
package com.kuflow.temporal.workflow.kuflow.model;

import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

public class WorkflowUserActionDefinitionType extends ExpandableStringEnum<WorkflowUserActionDefinitionType> {

    public static final WorkflowUserActionDefinitionType DOWNLOADABLE = fromString("DOWNLOADABLE");

    public static final WorkflowUserActionDefinitionType START_RELATED_PROCESS = fromString("START_RELATED_PROCESS");

    public static final WorkflowUserActionDefinitionType START_WORKFLOW = fromString("START_WORKFLOW");

    public static final WorkflowUserActionDefinitionType CREATE_TASK = fromString("CREATE_TASK");

    public static final WorkflowUserActionDefinitionType CREATE_PROCESS_ITEM_MESSAGE = fromString("CREATE_PROCESS_ITEM_MESSAGE");

    public static final WorkflowUserActionDefinitionType CREATE_PROCESS_ITEM_THREAD = fromString("CREATE_PROCESS_ITEM_THREAD");

    /**
     * Creates a new instance of ProcessState value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public WorkflowUserActionDefinitionType() {}

    /**
     * Creates or finds a UserActionDefinitionType from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding UserActionDefinitionType.
     */
    public static WorkflowUserActionDefinitionType fromString(String name) {
        return fromString(name, WorkflowUserActionDefinitionType.class);
    }

    /**
     * Gets known UserActionDefinitionType values.
     *
     * @return known UserActionDefinitionType values.
     */
    public static Collection<WorkflowUserActionDefinitionType> values() {
        return values(WorkflowUserActionDefinitionType.class);
    }
}
