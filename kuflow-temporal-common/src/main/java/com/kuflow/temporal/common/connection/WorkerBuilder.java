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
package com.kuflow.temporal.common.connection;

import io.temporal.worker.WorkerOptions;
import io.temporal.worker.WorkflowImplementationOptions;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class WorkerBuilder {

    public static WorkerBuilder instance() {
        return new WorkerBuilder();
    }

    private String taskQueue;

    private WorkerOptions workerOptions;

    private final List<ActivityImplementationRegister> activityImplementations = new LinkedList<>();

    private final List<WorkflowImplementationRegister> workflowImplementationClasses = new LinkedList<>();

    private WorkerBuilder() {}

    public String getTaskQueue() {
        return this.taskQueue;
    }

    public WorkerBuilder withTaskQueue(String taskQueue) {
        this.taskQueue = taskQueue;

        return this;
    }

    public WorkerOptions getWorkerOptions() {
        return this.workerOptions;
    }

    public WorkerBuilder withWorkerOptions(WorkerOptions workerOptions) {
        this.workerOptions = workerOptions;

        return this;
    }

    public List<ActivityImplementationRegister> getActivityImplementations() {
        return this.activityImplementations;
    }

    public WorkerBuilder withActivitiesImplementations(Object... activityImplementations) {
        this.activityImplementations.add(ActivityImplementationRegister.of(activityImplementations));

        return this;
    }

    public List<WorkflowImplementationRegister> getWorkflowImplementationClasses() {
        return this.workflowImplementationClasses;
    }

    public WorkerBuilder withWorkflowImplementationTypes(Class<?>... workflowImplementationClasses) {
        this.workflowImplementationClasses.add(WorkflowImplementationRegister.of(workflowImplementationClasses));

        return this;
    }

    public WorkerBuilder withWorkflowImplementationTypes(WorkflowImplementationOptions options, Class<?>... workflowImplementationClasses) {
        this.workflowImplementationClasses.add(WorkflowImplementationRegister.of(options, workflowImplementationClasses));

        return this;
    }

    static final class WorkflowImplementationRegister {

        public static WorkflowImplementationRegister of(WorkflowImplementationOptions options, Class<?>... workflowImplementationClasses) {
            return new WorkflowImplementationRegister(options, workflowImplementationClasses);
        }

        public static WorkflowImplementationRegister of(Class<?>... workflowImplementationClasses) {
            return new WorkflowImplementationRegister(null, workflowImplementationClasses);
        }

        private final WorkflowImplementationOptions options;
        private final Class<?>[] workflowImplementationClasses;

        private WorkflowImplementationRegister(WorkflowImplementationOptions options, Class<?>[] workflowImplementationClasses) {
            this.options = options;
            this.workflowImplementationClasses = workflowImplementationClasses;
        }

        public WorkflowImplementationOptions getOptions() {
            return this.options;
        }

        public Class<?>[] getWorkflowImplementationClasses() {
            return this.workflowImplementationClasses;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != this.getClass()) {
                return false;
            }
            var that = (WorkflowImplementationRegister) obj;
            return (
                Objects.equals(this.options, that.options) &&
                Arrays.equals(this.workflowImplementationClasses, that.workflowImplementationClasses)
            );
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.options, Arrays.hashCode(this.workflowImplementationClasses));
        }

        @Override
        public String toString() {
            return (
                "WorkflowImplementationRegister[" +
                "options=" +
                this.options +
                ", " +
                "workflowImplementationClasses=" +
                Arrays.toString(this.workflowImplementationClasses) +
                ']'
            );
        }
    }

    static final class ActivityImplementationRegister {

        public static ActivityImplementationRegister of(Object... activityImplementations) {
            return new ActivityImplementationRegister(activityImplementations);
        }

        private final Object[] activityImplementations;

        private ActivityImplementationRegister(Object[] activityImplementations) {
            this.activityImplementations = activityImplementations;
        }

        public Object[] getActivityImplementations() {
            return this.activityImplementations;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != this.getClass()) {
                return false;
            }
            var that = (ActivityImplementationRegister) obj;
            return Arrays.equals(this.activityImplementations, that.activityImplementations);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.activityImplementations);
        }

        @Override
        public String toString() {
            return "ActivityImplementationRegister[" + "activityImplementations=" + Arrays.toString(this.activityImplementations) + ']';
        }
    }
}
