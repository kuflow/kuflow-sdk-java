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
package com.kuflow.temporal.common.util;

import com.kuflow.temporal.common.error.KuFlowTemporalException;
import io.temporal.activity.ActivityExecutionContext;
import io.temporal.common.metadata.POJOActivityInterfaceMetadata;
import io.temporal.common.metadata.POJOActivityMethodMetadata;
import java.util.Base64;

public final class TemporalUtils {

    private TemporalUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getTemporalTokenAsString(ActivityExecutionContext context) {
        byte[] taskToken = context.getTaskToken();

        return (taskToken != null) ? Base64.getEncoder().encodeToString(taskToken) : null;
    }

    public static String getActivityType(Class<?> activityInterface, String activityMethod) {
        POJOActivityInterfaceMetadata pojoActivityInterfaceMetadata = POJOActivityInterfaceMetadata.newInstance(activityInterface);
        POJOActivityMethodMetadata pojoActivityMethodMetadata = pojoActivityInterfaceMetadata
            .getMethodsMetadata()
            .stream()
            .filter(it -> it.getMethod().getName().equals(activityMethod))
            .findFirst()
            .orElseThrow(() -> new KuFlowTemporalException("activityMethod " + activityMethod + " not found"));

        return pojoActivityMethodMetadata.getActivityTypeName();
    }
}
