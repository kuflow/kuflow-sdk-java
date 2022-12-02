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

package com.kuflow.temporal.activity.kuflow.util;

import com.kuflow.rest.model.DefaultErrorException;
import io.temporal.failure.ApplicationFailure;

public abstract class KuFlowActivitiesFailure {

    public static final String KUFLOW_ACTIVITIES_NON_RETRYABLE_FAILURE = "KuFlowActivities.NonRetryableFailure";
    public static final String KUFLOW_ACTIVITIES_FAILURE = "KuFlowActivities.Failure";

    public static RuntimeException createApplicationFailure(Exception e) {
        if (e instanceof ApplicationFailure) {
            return (ApplicationFailure) e;
        }
        if (e instanceof DefaultErrorException) {
            DefaultErrorException defaultErrorException = (DefaultErrorException) e;
            if (
                (defaultErrorException.getResponse().getStatusCode() >= 400 && defaultErrorException.getResponse().getStatusCode() < 500) &&
                defaultErrorException.getResponse().getStatusCode() != 429
            ) {
                return ApplicationFailure.newNonRetryableFailureWithCause(
                    "Invocation error",
                    KUFLOW_ACTIVITIES_NON_RETRYABLE_FAILURE,
                    e,
                    defaultErrorException.getValue()
                );
            } else {
                return ApplicationFailure.newFailureWithCause(
                    "Invocation error",
                    KUFLOW_ACTIVITIES_FAILURE,
                    e,
                    defaultErrorException.getValue()
                );
            }
        }

        return ApplicationFailure.newFailureWithCause("Invocation error", KUFLOW_ACTIVITIES_FAILURE, e);
    }
}
