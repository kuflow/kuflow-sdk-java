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

/**
 * Enumerates the action definition types that can be attached to a business artifact and trigger
 * a workflow when invoked from the KuFlow UI.
 */
public class WorkflowBusinessArtifactActionDefinitionType extends ExpandableStringEnum<WorkflowBusinessArtifactActionDefinitionType> {

    public static final WorkflowBusinessArtifactActionDefinitionType DOWNLOADABLE = fromString("DOWNLOADABLE");

    public static final WorkflowBusinessArtifactActionDefinitionType START_PROCESS = fromString("START_PROCESS");

    public static final WorkflowBusinessArtifactActionDefinitionType START_WORKFLOW = fromString("START_WORKFLOW");

    /**
     * Creates a new instance of WorkflowBusinessArtifactActionDefinitionType value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public WorkflowBusinessArtifactActionDefinitionType() {}

    /**
     * Creates or finds a WorkflowBusinessArtifactActionDefinitionType from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding WorkflowBusinessArtifactActionDefinitionType.
     */
    public static WorkflowBusinessArtifactActionDefinitionType fromString(String name) {
        return fromString(name, WorkflowBusinessArtifactActionDefinitionType.class);
    }

    /**
     * Gets known WorkflowBusinessArtifactActionDefinitionType values.
     *
     * @return known WorkflowBusinessArtifactActionDefinitionType values.
     */
    public static Collection<WorkflowBusinessArtifactActionDefinitionType> values() {
        return values(WorkflowBusinessArtifactActionDefinitionType.class);
    }
}
