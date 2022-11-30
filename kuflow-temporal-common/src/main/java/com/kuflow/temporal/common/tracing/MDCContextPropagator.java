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
package com.kuflow.temporal.common.tracing;

import io.temporal.api.common.v1.Payload;
import io.temporal.common.context.ContextPropagator;
import io.temporal.common.converter.GlobalDataConverter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.MDC;

public class MDCContextPropagator implements ContextPropagator {

    private static final String TRACE_ID = "TRACEID";
    private static final String SPAN_ID = "SPANID";

    public String getName() {
        return this.getClass().getName();
    }

    public Object getCurrentContext() {
        Map<String, String> context = new HashMap<>();
        for (Map.Entry<String, String> entry : MDC.getCopyOfContextMap().entrySet()) {
            if (this.filterMetadata(entry.getKey())) {
                context.put(entry.getKey(), entry.getValue());
            }
        }
        return context;
    }

    @SuppressWarnings("unchecked")
    public void setCurrentContext(Object context) {
        Map<String, String> contextMap = (Map<String, String>) context;
        for (Map.Entry<String, String> entry : contextMap.entrySet()) {
            MDC.put(entry.getKey(), entry.getValue());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Payload> serializeContext(Object context) {
        Map<String, String> contextMap = (Map<String, String>) context;

        return contextMap
            .entrySet()
            .stream()
            .collect(Collectors.toMap(Map.Entry::getKey, e -> GlobalDataConverter.get().toPayload(e.getValue()).orElseThrow()));
    }

    @Override
    public Object deserializeContext(Map<String, Payload> context) {
        return context
            .entrySet()
            .stream()
            .collect(
                Collectors.toMap(Map.Entry::getKey, e -> GlobalDataConverter.get().fromPayload(e.getValue(), String.class, String.class))
            );
    }

    private boolean filterMetadata(String key) {
        if (key == null) {
            return false;
        }

        String candidate = key.toUpperCase();
        return TRACE_ID.equals(candidate) || SPAN_ID.equals(candidate);
    }
}
