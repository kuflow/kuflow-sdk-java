package com.kuflow.temporal.workflow.kuflow.model;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

public class WorkflowProcessItemTaskRobotRequest {
    /**
     * The unique identifier of a process.
     * This identifier is used to track and reference a process in the workflow system.
     */
    private UUID processId;

    /**
     * The identifier of a specific process item within a workflow.
     * This ID is used to uniquely identify and reference a process item.
     */
    private UUID processItemId;

    /**
     * The unique identifier of the robot associated with the workflow process item task.
     */
    private UUID robotId;

    /**
     * The operation to be performed by the robot within the workflow process.
     * This value specifies the nature of the operation the robot is expected to execute.
     */
    private String robotOperation;

    /**
     * The timestamp when the request was made.
     * This field is used to record the exact time the associated robot operation
     * or process item task was requested.
     */
    private Instant requestTime;

    /**
     * The time zone associated with the request.
     * This allows timestamp values to be interpreted in the context of a specific geographical region
     * or offset from UTC.
     */
    private ZoneId requestTimeZone;

    public UUID getProcessId() {
        return this.processId;
    }

    public void setProcessId(UUID processId) {
        this.processId = processId;
    }

    public UUID getProcessItemId() {
        return this.processItemId;
    }

    public void setProcessItemId(UUID processItemId) {
        this.processItemId = processItemId;
    }

    public UUID getRobotId() {
        return this.robotId;
    }

    public void setRobotId(UUID robotId) {
        this.robotId = robotId;
    }

    public String getRobotOperation() {
        return this.robotOperation;
    }

    public void setRobotOperation(String robotOperation) {
        this.robotOperation = robotOperation;
    }

    public void setRequestTime(Instant requestTime) {
        this.requestTime = requestTime;
    }

    public Instant getRequestTime() {
        return this.requestTime;
    }

    public void setRequestTimeZone(ZoneId requestTimeZone) {
        this.requestTimeZone = requestTimeZone;
    }

    public ZoneId getRequestTimeZone() {
        return this.requestTimeZone;
    }
}
