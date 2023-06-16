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

import io.temporal.worker.Worker;
import java.util.Set;

public class WorkerInfo {

    private final String taskQueue;

    private final Set<String> workflowTypes;

    private final Set<String> activityTypes;

    private Worker worker;

    public WorkerInfo(String taskQueue, Set<String> workflowTypes, Set<String> activityTypes) {
        this.taskQueue = taskQueue;
        this.workflowTypes = workflowTypes;
        this.activityTypes = activityTypes;
    }

    public String getTaskQueue() {
        return this.taskQueue;
    }

    public Set<String> getWorkflowTypes() {
        return this.workflowTypes;
    }

    public Set<String> getActivityTypes() {
        return this.activityTypes;
    }

    public Worker getWorker() {
        return this.worker;
    }

    void registerWorker(Worker worker) {
        this.worker = worker;
    }
}
