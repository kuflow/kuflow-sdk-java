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

import static com.kuflow.temporal.activity.kuflow.util.ProcessElementValueAccessorSaveTaskElementRequest.of;

import com.kuflow.rest.KuFlowRestClientException;
import com.kuflow.rest.model.TaskElementValueDocumentItem;
import com.kuflow.rest.model.TaskElementValuePrincipalItem;
import com.kuflow.rest.util.TaskElementValueUtils;
import com.kuflow.temporal.activity.kuflow.model.SaveTaskElementRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class SaveTaskElementRequestUtils {

    private SaveTaskElementRequestUtils() {
        throw new RuntimeException("Utility class");
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param request The request object
     * @return TRUE if all related valid values are TRUE else FALSE.
     */
    public static Boolean getElementValueValid(SaveTaskElementRequest request) {
        return TaskElementValueUtils.getElementValueValid(of(request));
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param request The request object
     * @param index Element value index
     * @return The requested valid value
     */
    public static Boolean getElementValueValidAt(SaveTaskElementRequest request, int index) {
        return TaskElementValueUtils.getElementValueValidAt(of(request), index);
    }

    /**
     * Set valid to all values
     *
     * @param request The request object
     * @param valid Valid value
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest setElementValueValid(SaveTaskElementRequest request, Boolean valid) {
        TaskElementValueUtils.setElementValueValid(of(request), valid);

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
    public static SaveTaskElementRequest setElementValueValidAt(SaveTaskElementRequest request, Boolean valid, int index) {
        TaskElementValueUtils.setElementValueValidAt(of(request), valid, index);

        return request;
    }

    /**
     * Set an element value
     *
     * @param request The request object
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest setElementValueAsString(SaveTaskElementRequest request, String elementValue) {
        TaskElementValueUtils.setElementValue(of(request), elementValue);

        return request;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param request The request object
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest setElementValueAsStringList(SaveTaskElementRequest request, List<String> elementValues) {
        TaskElementValueUtils.setElementValues(of(request), elementValues);

        return request;
    }

    /**
     * Add a new element value
     *
     * @param request The request object
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest addElementValueAsString(SaveTaskElementRequest request, String elementValue) {
        TaskElementValueUtils.addElementValue(of(request), elementValue);

        return request;
    }

    /**
     * Add all element values passed
     *
     * @param request The request object
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest addElementValueAsStringList(SaveTaskElementRequest request, List<String> elementValues) {
        TaskElementValueUtils.addElementValues(of(request), elementValues);

        return request;
    }

    /**
     * Get an element as String
     *
     * @param request The request object
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     */
    public static String getElementValueAsString(SaveTaskElementRequest request) {
        return TaskElementValueUtils.getElementValueAsString(of(request));
    }

    /**
     * Get all elements as String
     *
     * @param request The request object
     * @return the elements values.
     */
    public static List<String> getElementValueAsStringList(SaveTaskElementRequest request) {
        return TaskElementValueUtils.getElementValueAsStringList(of(request));
    }

    /**
     * Try to get an element as String
     *
     * @param request The request object
     * @return the element value if exists.
     */
    public static Optional<String> findElementValueAsString(SaveTaskElementRequest request) {
        return TaskElementValueUtils.findElementValueAsString(of(request));
    }

    /**
     * Set an element value
     *
     * @param request The request object
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest setElementValueAsDouble(SaveTaskElementRequest request, Double elementValue) {
        TaskElementValueUtils.setElementValue(of(request), elementValue);

        return request;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param request The request object
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest setElementValueAsDoubleList(SaveTaskElementRequest request, List<Double> elementValues) {
        TaskElementValueUtils.setElementValues(of(request), elementValues);

        return request;
    }

    /**
     * Add a new element value
     *
     * @param request The request object
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest addElementValueAsDouble(SaveTaskElementRequest request, Double elementValue) {
        TaskElementValueUtils.addElementValue(of(request), elementValue);

        return request;
    }

    /**
     * Add all element values passed
     *
     * @param request The request object
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest addElementValueAsDoubleList(SaveTaskElementRequest request, List<Double> elementValues) {
        TaskElementValueUtils.addElementValues(of(request), elementValues);

        return request;
    }

    /**
     * Get an element as Double
     *
     * @param request The request object
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     */
    public static Double getElementValueAsDouble(SaveTaskElementRequest request) {
        return TaskElementValueUtils.getElementValueAsDouble(of(request));
    }

    /**
     * Get all elements as Double
     *
     * @param request The request object
     * @return the elements values.
     */
    public static List<Double> getElementValueAsDoubleList(SaveTaskElementRequest request) {
        return TaskElementValueUtils.getElementValueAsDoubleList(of(request));
    }

    /**
     * Try to get an element as Double
     *
     * @param request The request object
     * @return the element value if exists.
     */
    public static Optional<Double> findElementValueAsDouble(SaveTaskElementRequest request) {
        return TaskElementValueUtils.findElementValueAsDouble(of(request));
    }

    /**
     * Set an element value
     *
     * @param request The request object
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest setElementValueAsLocalDate(SaveTaskElementRequest request, LocalDate elementValue) {
        TaskElementValueUtils.setElementValue(of(request), elementValue);

        return request;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param request The request object
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest setElementValueAsLocalDateList(SaveTaskElementRequest request, List<LocalDate> elementValues) {
        TaskElementValueUtils.setElementValues(of(request), elementValues);

        return request;
    }

    /**
     * Add a new element value
     *
     * @param request The request object
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest addElementValueAsLocalDate(SaveTaskElementRequest request, LocalDate elementValue) {
        TaskElementValueUtils.addElementValue(of(request), elementValue);

        return request;
    }

    /**
     * Add all element values passed
     *
     * @param request The request object
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest addElementValueAsLocalDateList(SaveTaskElementRequest request, List<LocalDate> elementValues) {
        TaskElementValueUtils.addElementValues(of(request), elementValues);

        return request;
    }

    /**
     * Get an element as LocalDate
     *
     * @param request The request object
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     */
    public static LocalDate getElementValueAsLocalDate(SaveTaskElementRequest request) {
        return TaskElementValueUtils.getElementValueAsLocalDate(of(request));
    }

    /**
     * Get all elements as LocalDate
     *
     * @param request The request object
     * @return the elements values.
     */
    public static List<LocalDate> getElementValueAsLocalDateList(SaveTaskElementRequest request) {
        return TaskElementValueUtils.getElementValueAsLocalDateList(of(request));
    }

    /**
     * Try to get an element as LocalDate
     *
     * @param request The request object
     * @return the element value if exists.
     */
    public static Optional<LocalDate> findElementValueAsLocalDate(SaveTaskElementRequest request) {
        return TaskElementValueUtils.findElementValueAsLocalDate(of(request));
    }

    /**
     * Set an element value
     *
     * @param request The request object
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest setElementValueAsMap(SaveTaskElementRequest request, Map<String, Object> elementValue) {
        TaskElementValueUtils.setElementValue(of(request), elementValue);

        return request;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param request The request object
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest setElementValueAsMapList(SaveTaskElementRequest request, List<Map<String, Object>> elementValues) {
        TaskElementValueUtils.setElementValues(of(request), elementValues);

        return request;
    }

    /**
     * Add a new element value
     *
     * @param request The request object
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest addElementValueAsMap(SaveTaskElementRequest request, Map<String, Object> elementValue) {
        TaskElementValueUtils.addElementValue(of(request), elementValue);

        return request;
    }

    /**
     * Add all element values passed
     *
     * @param request The request object
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest addElementValueAsMapList(SaveTaskElementRequest request, List<Map<String, Object>> elementValues) {
        TaskElementValueUtils.addElementValues(of(request), elementValues);

        return request;
    }

    /**
     * Get an element as Map
     *
     * @param request The request object
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     */
    public static Map<String, Object> getElementValueAsMap(SaveTaskElementRequest request) {
        return TaskElementValueUtils.getElementValueAsMap(of(request));
    }

    /**
     * Get all elements as Map
     *
     * @param request The request object
     * @return the elements values.
     */
    public static List<Map<String, Object>> getElementValueAsMapList(SaveTaskElementRequest request) {
        return TaskElementValueUtils.getElementValueAsMapList(of(request));
    }

    /**
     * Try to get an element as Map
     *
     * @param request The request object
     * @return the element value if exists.
     */
    public static Optional<Map<String, Object>> findElementValueAsMap(SaveTaskElementRequest request) {
        return TaskElementValueUtils.findElementValueAsMap(of(request));
    }

    /**
     * Set an element value
     *
     * @param request The request object
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest setElementValueAsDocument(
        SaveTaskElementRequest request,
        TaskElementValueDocumentItem elementValue
    ) {
        TaskElementValueUtils.setElementValue(of(request), elementValue);

        return request;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param request The request object
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest setElementValueAsDocumentList(
        SaveTaskElementRequest request,
        List<TaskElementValueDocumentItem> elementValues
    ) {
        TaskElementValueUtils.setElementValues(of(request), elementValues);

        return request;
    }

    /**
     * Add a new element value
     *
     * @param request The request object
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest addElementValueAsDocument(
        SaveTaskElementRequest request,
        TaskElementValueDocumentItem elementValue
    ) {
        TaskElementValueUtils.addElementValue(of(request), elementValue);

        return request;
    }

    /**
     * Add all element values passed
     *
     * @param request The request object
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest addElementValueAsDocumentList(
        SaveTaskElementRequest request,
        List<TaskElementValueDocumentItem> elementValues
    ) {
        TaskElementValueUtils.addElementValues(of(request), elementValues);

        return request;
    }

    /**
     * Get an element as Document
     *
     * @param request The request object
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     */
    public static TaskElementValueDocumentItem getElementValueAsDocument(SaveTaskElementRequest request) {
        return TaskElementValueUtils.getElementValueAsDocument(of(request));
    }

    /**
     * Get all elements as Document
     *
     * @param request The request object
     * @return the elements values.
     */
    public static List<TaskElementValueDocumentItem> getElementValueAsDocumentList(SaveTaskElementRequest request) {
        return TaskElementValueUtils.getElementValueAsDocumentList(of(request));
    }

    /**
     * Try to get an element as Document
     *
     * @param request The request object
     * @return the element value if exists.
     */
    public static Optional<TaskElementValueDocumentItem> findElementValueAsDocument(SaveTaskElementRequest request) {
        return TaskElementValueUtils.findElementValueAsDocument(of(request));
    }

    /**
     * Set an element value
     *
     * @param request The request object
     * @param elementValue Element value, if the value is null all current values are removed
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest setElementValueAsPrincipal(
        SaveTaskElementRequest request,
        TaskElementValuePrincipalItem elementValue
    ) {
        TaskElementValueUtils.setElementValue(of(request), elementValue);

        return request;
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param request The request object
     * @param elementValues Element values, if the values are null all current values are removed
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest setElementValueAsPrincipalList(
        SaveTaskElementRequest request,
        List<TaskElementValuePrincipalItem> elementValues
    ) {
        TaskElementValueUtils.setElementValues(of(request), elementValues);

        return request;
    }

    /**
     * Add a new element value
     *
     * @param request The request object
     * @param elementValue Element value, if the values is null the value is not added
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest addElementValueAsPrincipal(
        SaveTaskElementRequest request,
        TaskElementValuePrincipalItem elementValue
    ) {
        TaskElementValueUtils.addElementValue(of(request), elementValue);

        return request;
    }

    /**
     * Add all element values passed
     *
     * @param request The request object
     * @param elementValues Element values
     * @return the Task object itself.
     */
    public static SaveTaskElementRequest addElementValueAsPrincipalList(
        SaveTaskElementRequest request,
        List<TaskElementValuePrincipalItem> elementValues
    ) {
        TaskElementValueUtils.addElementValues(of(request), elementValues);

        return request;
    }

    /**
     * Get an element as Principal
     *
     * @param request The request object
     * @return the element value.
     * @throws KuFlowRestClientException If element value doesn't exists
     */
    public static TaskElementValuePrincipalItem getElementValueAsPrincipal(SaveTaskElementRequest request) {
        return TaskElementValueUtils.getElementValueAsPrincipal(of(request));
    }

    /**
     * Get all elements as Principal
     *
     * @param request The request object
     * @return the elements values.
     */
    public static List<TaskElementValuePrincipalItem> getElementValueAsPrincipalList(SaveTaskElementRequest request) {
        return TaskElementValueUtils.getElementValueAsPrincipalList(of(request));
    }

    /**
     * Try to get an element as Principal
     *
     * @param request The request object
     * @return the element value if exists.
     */
    public static Optional<TaskElementValuePrincipalItem> findElementValueAsPrincipal(SaveTaskElementRequest request) {
        return TaskElementValueUtils.findElementValueAsPrincipal(of(request));
    }
}
