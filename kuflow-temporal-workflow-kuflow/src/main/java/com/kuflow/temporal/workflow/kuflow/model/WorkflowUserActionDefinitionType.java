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
