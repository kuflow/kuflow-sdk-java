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
package com.kuflow.temporal.activity.kuflow;

import static com.kuflow.temporal.activity.kuflow.util.KuFlowActivitiesFailure.createApplicationFailure;
import static com.kuflow.temporal.activity.kuflow.util.KuFlowActivitiesValidation.validateTaskRequest;

import com.kuflow.rest.KuFlowRestClient;
import com.kuflow.rest.operation.TaskOperations;
import com.kuflow.temporal.activity.kuflow.model.CreateTaskRequest;
import com.kuflow.temporal.common.util.TemporalUtils;
import io.temporal.activity.Activity;
import io.temporal.activity.ActivityExecutionContext;
import javax.annotation.Nonnull;

public class KuFlowAsyncActivitiesImpl implements KuFlowAsyncActivities {

    private final TaskOperations taskOperations;

    public KuFlowAsyncActivitiesImpl(KuFlowRestClient kuFlowRestClient) {
        this.taskOperations = kuFlowRestClient.getTaskOperations();
    }

    @Override
    public void createTaskAndWaitFinished(@Nonnull CreateTaskRequest request) {
        try {
            validateTaskRequest(request);

            ActivityExecutionContext context = Activity.getExecutionContext();
            String activityToken = TemporalUtils.getTemporalTokenAsString(context);

            this.taskOperations.createTask(request.getTask(), activityToken);

            context.doNotCompleteOnReturn();
        } catch (Exception e) {
            throw createApplicationFailure(e);
        }
    }
}
