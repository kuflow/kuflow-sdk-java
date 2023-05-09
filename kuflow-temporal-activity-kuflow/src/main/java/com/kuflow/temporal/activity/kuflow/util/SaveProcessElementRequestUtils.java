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

import static com.kuflow.temporal.activity.kuflow.util.ProcessElementValueAccessorSaveProcessElementRequest.of;

import com.kuflow.rest.KuFlowRestClientException;
import com.kuflow.rest.util.ProcessElementValueUtils;
import com.kuflow.temporal.activity.kuflow.model.SaveProcessElementRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class SaveProcessElementRequestUtils {

    private SaveProcessElementRequestUtils() {
        throw new RuntimeException("Utility class");
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param request The request object
     * @return TRUE if all related valid values are TRUE else FALSE.
     */
    public static Boolean getElementValueValid(SaveProcessElementRequest request) {
        return ProcessElementValueUtils.getElementValueValid(of(request));
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param request The request object
     * @param index Element value index
     * @return The requested valid value
     */
    public static Boolean getElementValueValidAt(SaveProcessElementRequest request, int index) {
        return ProcessElementValueUtils.getElementValueValidAt(of(request), index);
    }

    /**
     * Set valid to all values
     *
     * @param request The request object
     * @param valid Valid value
     * @return the Task object itself.
     */
    public static SaveProcessElementRequest setElementValueValid(SaveProcessElementRequest request, Boolean valid) {
        ProcessElementValueUtils.setElementValueValid(of(request), valid);

        return request;
    }

    /**
     * Set valid to the selected value
     *
     * @param request The request object
     * @param valid Valid value
     * @param index Element value index
     * @return the Task object itself.
     */
    public static SaveProcessElementRequest setElementValueValidAt(SaveProcessElementRequest request, Boolean valid, int index) {
        ProcessElementValueUtils.setElementValueValidAt(of(request), valid, index);

        return request;
    }

    /**
     * Set an element value
     *
     * @param request The request object
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public static SaveProcessElementRequest setElementValueAsString(SaveProcessElementRequest request, String elementValue) {
        ProcessElementValueUtils.setElementValue(of(request), elementValue);

        return request;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param request The request object
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public static SaveProcessElementRequest setElementValueAsStringList(SaveProcessElementRequest request, List<String> elementValues) {
        ProcessElementValueUtils.setElementValues(of(request), elementValues);

        return request;
    }

    /**
     * Add a new element value
     *
     * @param request The request object
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public static SaveProcessElementRequest addElementValueAsString(SaveProcessElementRequest request, String elementValue) {
        ProcessElementValueUtils.addElementValue(of(request), elementValue);

        return request;
    }

    /**
     * Add all element values passed
     *
     * @param request The request object
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public static SaveProcessElementRequest addElementValueAsStringList(SaveProcessElementRequest request, List<String> elementValues) {
        ProcessElementValueUtils.addElementValues(of(request), elementValues);

        return request;
    }

    /**
     * Get an element as String
     *
     * @param request The request object
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     */
    public static String getElementValueAsString(SaveProcessElementRequest request) {
        return ProcessElementValueUtils.getElementValueAsString(of(request));
    }

    /**
     * Get all elements as String
     *
     * @param request The request object
     * @return the elements values.
     */
    public static List<String> getElementValueAsStringList(SaveProcessElementRequest request) {
        return ProcessElementValueUtils.getElementValueAsStringList(of(request));
    }

    /**
     * Try to get an element as String
     *
     * @param request The request object
     * @return the element value if exists.
     */
    public static Optional<String> findElementValueAsString(SaveProcessElementRequest request) {
        return ProcessElementValueUtils.findElementValueAsString(of(request));
    }

    /**
     * Set an element value
     *
     * @param request The request object
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public static SaveProcessElementRequest setElementValueAsDouble(SaveProcessElementRequest request, Double elementValue) {
        ProcessElementValueUtils.setElementValue(of(request), elementValue);

        return request;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param request The request object
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public static SaveProcessElementRequest setElementValueAsDoubleList(SaveProcessElementRequest request, List<Double> elementValues) {
        ProcessElementValueUtils.setElementValues(of(request), elementValues);

        return request;
    }

    /**
     * Add a new element value
     *
     * @param request The request object
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public static SaveProcessElementRequest addElementValueAsDouble(SaveProcessElementRequest request, Double elementValue) {
        ProcessElementValueUtils.addElementValue(of(request), elementValue);

        return request;
    }

    /**
     * Add all element values passed
     *
     * @param request The request object
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public static SaveProcessElementRequest addElementValueAsDoubleList(SaveProcessElementRequest request, List<Double> elementValues) {
        ProcessElementValueUtils.addElementValues(of(request), elementValues);

        return request;
    }

    /**
     * Get an element as Double
     *
     * @param request The request object
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     */
    public static Double getElementValueAsDouble(SaveProcessElementRequest request) {
        return ProcessElementValueUtils.getElementValueAsDouble(of(request));
    }

    /**
     * Get all elements as Double
     *
     * @param request The request object
     * @return the elements values.
     */
    public static List<Double> getElementValueAsDoubleList(SaveProcessElementRequest request) {
        return ProcessElementValueUtils.getElementValueAsDoubleList(of(request));
    }

    /**
     * Try to get an element as Double
     *
     * @param request The request object
     * @return the element value if exists.
     */
    public static Optional<Double> findElementValueAsDouble(SaveProcessElementRequest request) {
        return ProcessElementValueUtils.findElementValueAsDouble(of(request));
    }

    /**
     * Set an element value
     *
     * @param request The request object
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public static SaveProcessElementRequest setElementValueAsLocalDate(SaveProcessElementRequest request, LocalDate elementValue) {
        ProcessElementValueUtils.setElementValue(of(request), elementValue);

        return request;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param request The request object
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public static SaveProcessElementRequest setElementValueAsLocalDateList(
        SaveProcessElementRequest request,
        List<LocalDate> elementValues
    ) {
        ProcessElementValueUtils.setElementValues(of(request), elementValues);

        return request;
    }

    /**
     * Add a new element value
     *
     * @param request The request object
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public static SaveProcessElementRequest addElementValueAsLocalDate(SaveProcessElementRequest request, LocalDate elementValue) {
        ProcessElementValueUtils.addElementValue(of(request), elementValue);

        return request;
    }

    /**
     * Add all element values passed
     *
     * @param request The request object
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public static SaveProcessElementRequest addElementValueAsLocalDateList(
        SaveProcessElementRequest request,
        List<LocalDate> elementValues
    ) {
        ProcessElementValueUtils.addElementValues(of(request), elementValues);

        return request;
    }

    /**
     * Get an element as LocalDate
     *
     * @param request The request object
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     */
    public static LocalDate getElementValueAsLocalDate(SaveProcessElementRequest request) {
        return ProcessElementValueUtils.getElementValueAsLocalDate(of(request));
    }

    /**
     * Get all elements as LocalDate
     *
     * @param request The request object
     * @return the elements values.
     */
    public static List<LocalDate> getElementValueAsLocalDateList(SaveProcessElementRequest request) {
        return ProcessElementValueUtils.getElementValueAsLocalDateList(of(request));
    }

    /**
     * Try to get an element as LocalDate
     *
     * @param request The request object
     * @return the element value if exists.
     */
    public static Optional<LocalDate> findElementValueAsLocalDate(SaveProcessElementRequest request) {
        return ProcessElementValueUtils.findElementValueAsLocalDate(of(request));
    }

    /**
     * Set an element value
     *
     * @param request The request object
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public static SaveProcessElementRequest setElementValueAsMap(SaveProcessElementRequest request, Map<String, Object> elementValue) {
        ProcessElementValueUtils.setElementValue(of(request), elementValue);

        return request;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param request The request object
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public static SaveProcessElementRequest setElementValueAsMapList(
        SaveProcessElementRequest request,
        List<Map<String, Object>> elementValues
    ) {
        ProcessElementValueUtils.setElementValues(of(request), elementValues);

        return request;
    }

    /**
     * Add a new element value
     *
     * @param request The request object
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public static SaveProcessElementRequest addElementValueAsMap(SaveProcessElementRequest request, Map<String, Object> elementValue) {
        ProcessElementValueUtils.addElementValue(of(request), elementValue);

        return request;
    }

    /**
     * Add all element values passed
     *
     * @param request The request object
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public static SaveProcessElementRequest addElementValueAsMapList(
        SaveProcessElementRequest request,
        List<Map<String, Object>> elementValues
    ) {
        ProcessElementValueUtils.addElementValues(of(request), elementValues);

        return request;
    }
}
