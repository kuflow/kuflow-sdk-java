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
package com.kuflow.rest.util;

import static com.kuflow.rest.util.TaskElementValueAccessorTaskSaveElementCommand.of;

import com.kuflow.rest.model.TaskElementValueDocumentItem;
import com.kuflow.rest.model.TaskElementValuePrincipalItem;
import com.kuflow.rest.model.TaskSaveElementCommand;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Utility class for {@link TaskSaveElementCommand}
 */
public final class TaskSaveElementCommandUtils {

    private TaskSaveElementCommandUtils() {
        throw new RuntimeException("Utility class");
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param command TaskSaveElementCommand
     * @return TRUE if all related valid values are TRUE else FALSE.
     */
    public static Boolean getElementValueValid(TaskSaveElementCommand command) {
        return TaskElementValueUtils.getElementValueValid(of(command));
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param command TaskSaveElementCommand

     * @param index Element value index
     * @return The requested valid value
     */
    public static Boolean getElementValueValidAt(TaskSaveElementCommand command, int index) {
        return TaskElementValueUtils.getElementValueValidAt(of(command), index);
    }

    /**
     * Set valid to all values
     *
     * @param command TaskSaveElementCommand

     * @param valid Valid value
     */
    public static void setElementValueValid(TaskSaveElementCommand command, Boolean valid) {
        TaskElementValueUtils.setElementValueValid(of(command), valid);
    }

    /**
     * Set valid to the selected value
     *
     * @param command TaskSaveElementCommand

     * @param valid Valid value
     * @param index Element value index
     */
    public static void setElementValueValidAt(TaskSaveElementCommand command, Boolean valid, int index) {
        TaskElementValueUtils.setElementValueValidAt(of(command), valid, index);
    }

    /**
     * Set an element value
     *
     * @param command TaskSaveElementCommand

     * @param elementValue Element value, if the value is null all current values are removed
     */
    public static void setElementValueAsString(TaskSaveElementCommand command, String elementValue) {
        TaskElementValueUtils.setElementValue(of(command), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param command TaskSaveElementCommand

     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsStringList(TaskSaveElementCommand command, List<String> elementValues) {
        TaskElementValueUtils.setElementValues(of(command), elementValues);
    }

    /**
     * Add a new element value
     *
     * @param command TaskSaveElementCommand

     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsString(TaskSaveElementCommand command, String elementValue) {
        TaskElementValueUtils.addElementValue(of(command), elementValue);
    }

    /**
     * Add all element values passed
     *
     * @param command TaskSaveElementCommand

     * @param elementValues Element values
     */
    public static void addElementValueAsStringList(TaskSaveElementCommand command, List<String> elementValues) {
        TaskElementValueUtils.addElementValues(of(command), elementValues);
    }

    /**
     * Get an element as String
     *
     * @param command TaskSaveElementCommand

     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static String getElementValueAsString(TaskSaveElementCommand command) {
        return TaskElementValueUtils.getElementValueAsString(of(command));
    }

    /**
     * Get all elements as String
     *
     * @param command TaskSaveElementCommand

     * @return the elements values.
     */
    public static List<String> getElementValueAsStringList(TaskSaveElementCommand command) {
        return TaskElementValueUtils.getElementValueAsStringList(of(command));
    }

    /**
     * Try to get an element as String
     *
     * @param command TaskSaveElementCommand

     * @return the element value if exists.
     */
    public static Optional<String> findElementValueAsString(TaskSaveElementCommand command) {
        return TaskElementValueUtils.findElementValueAsString(of(command));
    }

    /**
     * Set an element value
     *
     * @param command TaskSaveElementCommand

     * @param elementValue Element value, if the value is null all current values are removed
     */
    public static void setElementValueAsDouble(TaskSaveElementCommand command, Double elementValue) {
        TaskElementValueUtils.setElementValue(of(command), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param command TaskSaveElementCommand

     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsDoubleList(TaskSaveElementCommand command, List<Double> elementValues) {
        TaskElementValueUtils.setElementValues(of(command), elementValues);
    }

    /**
     * Add a new element value
     *
     * @param command TaskSaveElementCommand

     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsDouble(TaskSaveElementCommand command, Double elementValue) {
        TaskElementValueUtils.addElementValue(of(command), elementValue);
    }

    /**
     * Add all element values passed
     *
     * @param command TaskSaveElementCommand

     * @param elementValues Element values
     */
    public static void addElementValueAsDoubleList(TaskSaveElementCommand command, List<Double> elementValues) {
        TaskElementValueUtils.addElementValues(of(command), elementValues);
    }

    /**
     * Get an element as Double
     *
     * @param command TaskSaveElementCommand

     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static Double getElementValueAsDouble(TaskSaveElementCommand command) {
        return TaskElementValueUtils.getElementValueAsDouble(of(command));
    }

    /**
     * Get all elements as Double
     *
     * @param command TaskSaveElementCommand

     * @return the elements values.
     */
    public static List<Double> getElementValueAsDoubleList(TaskSaveElementCommand command) {
        return TaskElementValueUtils.getElementValueAsDoubleList(of(command));
    }

    /**
     * Try to get an element as Double
     *
     * @param command TaskSaveElementCommand

     * @return the element value if exists.
     */
    public static Optional<Double> findElementValueAsDouble(TaskSaveElementCommand command) {
        return TaskElementValueUtils.findElementValueAsDouble(of(command));
    }

    /**
     * Set an element value
     *
     * @param command TaskSaveElementCommand

     * @param elementValue Element value, if the value is null all current values are removed
     */
    public static void setElementValueAsLocalDate(TaskSaveElementCommand command, LocalDate elementValue) {
        TaskElementValueUtils.setElementValue(of(command), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param command TaskSaveElementCommand

     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsLocalDateList(TaskSaveElementCommand command, List<LocalDate> elementValues) {
        TaskElementValueUtils.setElementValues(of(command), elementValues);
    }

    /**
     * Add a new element value
     *
     * @param command TaskSaveElementCommand

     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsLocalDate(TaskSaveElementCommand command, LocalDate elementValue) {
        TaskElementValueUtils.addElementValue(of(command), elementValue);
    }

    /**
     * Add all element values passed
     *
     * @param command TaskSaveElementCommand

     * @param elementValues Element values
     */
    public static void addElementValueAsLocalDateList(TaskSaveElementCommand command, List<LocalDate> elementValues) {
        TaskElementValueUtils.addElementValues(of(command), elementValues);
    }

    /**
     * Get an element as LocalDate
     *
     * @param command TaskSaveElementCommand

     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static LocalDate getElementValueAsLocalDate(TaskSaveElementCommand command) {
        return TaskElementValueUtils.getElementValueAsLocalDate(of(command));
    }

    /**
     * Get all elements as LocalDate
     *
     * @param command TaskSaveElementCommand

     * @return the elements values.
     */
    public static List<LocalDate> getElementValueAsLocalDateList(TaskSaveElementCommand command) {
        return TaskElementValueUtils.getElementValueAsLocalDateList(of(command));
    }

    /**
     * Try to get an element as LocalDate
     *
     * @param command TaskSaveElementCommand

     * @return the element value if exists.
     */
    public static Optional<LocalDate> findElementValueAsLocalDate(TaskSaveElementCommand command) {
        return TaskElementValueUtils.findElementValueAsLocalDate(of(command));
    }

    /**
     * Set an element value
     *
     * @param command TaskSaveElementCommand

     * @param elementValue Element value, if the value is null all current values are removed
     */
    public static void setElementValueAsMap(TaskSaveElementCommand command, Map<String, Object> elementValue) {
        TaskElementValueUtils.setElementValue(of(command), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param command TaskSaveElementCommand

     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsMapList(TaskSaveElementCommand command, List<Map<String, Object>> elementValues) {
        TaskElementValueUtils.setElementValues(of(command), elementValues);
    }

    /**
     * Add a new element value
     *
     * @param command TaskSaveElementCommand

     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsMap(TaskSaveElementCommand command, Map<String, Object> elementValue) {
        TaskElementValueUtils.addElementValue(of(command), elementValue);
    }

    /**
     * Add all element values passed
     *
     * @param command TaskSaveElementCommand

     * @param elementValues Element values
     */
    public static void addElementValueAsMapList(TaskSaveElementCommand command, List<Map<String, Object>> elementValues) {
        TaskElementValueUtils.addElementValues(of(command), elementValues);
    }

    /**
     * Get an element as Map
     *
     * @param command TaskSaveElementCommand

     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static Map<String, Object> getElementValueAsMap(TaskSaveElementCommand command) {
        return TaskElementValueUtils.getElementValueAsMap(of(command));
    }

    /**
     * Get all elements as Map
     *
     * @param command TaskSaveElementCommand

     * @return the elements values.
     */
    public static List<Map<String, Object>> getElementValueAsMapList(TaskSaveElementCommand command) {
        return TaskElementValueUtils.getElementValueAsMapList(of(command));
    }

    /**
     * Try to get an element as Map
     *
     * @param command TaskSaveElementCommand

     * @return the element value if exists.
     */
    public static Optional<Map<String, Object>> findElementValueAsMap(TaskSaveElementCommand command) {
        return TaskElementValueUtils.findElementValueAsMap(of(command));
    }

    /**
     * Set an element value
     *
     * @param command TaskSaveElementCommand

     * @param elementValue Element value, if the value is null all current values are removed
     */
    public static void setElementValueAsDocument(TaskSaveElementCommand command, TaskElementValueDocumentItem elementValue) {
        TaskElementValueUtils.setElementValue(of(command), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param command TaskSaveElementCommand

     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsDocumentList(TaskSaveElementCommand command, List<TaskElementValueDocumentItem> elementValues) {
        TaskElementValueUtils.setElementValues(of(command), elementValues);
    }

    /**
     * Add a new element value
     *
     * @param command TaskSaveElementCommand

     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsDocument(TaskSaveElementCommand command, TaskElementValueDocumentItem elementValue) {
        TaskElementValueUtils.addElementValue(of(command), elementValue);
    }

    /**
     * Add all element values passed
     *
     * @param command TaskSaveElementCommand

     * @param elementValues Element values
     */
    public static void addElementValueAsDocumentList(TaskSaveElementCommand command, List<TaskElementValueDocumentItem> elementValues) {
        TaskElementValueUtils.addElementValues(of(command), elementValues);
    }

    /**
     * Get an element as Document
     *
     * @param command TaskSaveElementCommand

     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static TaskElementValueDocumentItem getElementValueAsDocument(TaskSaveElementCommand command) {
        return TaskElementValueUtils.getElementValueAsDocument(of(command));
    }

    /**
     * Get all elements as Document
     *
     * @param command TaskSaveElementCommand

     * @return the elements values.
     */
    public static List<TaskElementValueDocumentItem> getElementValueAsDocumentList(TaskSaveElementCommand command) {
        return TaskElementValueUtils.getElementValueAsDocumentList(of(command));
    }

    /**
     * Try to get an element as Document
     *
     * @param command TaskSaveElementCommand

     * @return the element value if exists.
     */
    public static Optional<TaskElementValueDocumentItem> findElementValueAsDocument(TaskSaveElementCommand command) {
        return TaskElementValueUtils.findElementValueAsDocument(of(command));
    }

    /**
     * Set an element value
     *
     * @param command TaskSaveElementCommand

     * @param elementValue Element value, if the value is null all current values are removed
     */
    public static void setElementValueAsPrincipal(TaskSaveElementCommand command, TaskElementValuePrincipalItem elementValue) {
        TaskElementValueUtils.setElementValue(of(command), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param command TaskSaveElementCommand

     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsPrincipalList(TaskSaveElementCommand command, List<TaskElementValuePrincipalItem> elementValues) {
        TaskElementValueUtils.setElementValues(of(command), elementValues);
    }

    /**
     * Add a new element value
     *
     * @param command TaskSaveElementCommand

     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsPrincipal(TaskSaveElementCommand command, TaskElementValuePrincipalItem elementValue) {
        TaskElementValueUtils.addElementValue(of(command), elementValue);
    }

    /**
     * Add all element values passed
     *
     * @param command TaskSaveElementCommand

     * @param elementValues Element values
     */
    public static void addElementValueAsPrincipalList(TaskSaveElementCommand command, List<TaskElementValuePrincipalItem> elementValues) {
        TaskElementValueUtils.addElementValues(of(command), elementValues);
    }

    /**
     * Get an element as Principal
     *
     * @param command TaskSaveElementCommand

     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static TaskElementValuePrincipalItem getElementValueAsPrincipal(TaskSaveElementCommand command) {
        return TaskElementValueUtils.getElementValueAsPrincipal(of(command));
    }

    /**
     * Get all elements as Principal
     *
     * @param command TaskSaveElementCommand

     * @return the elements values.
     */
    public static List<TaskElementValuePrincipalItem> getElementValueAsPrincipalList(TaskSaveElementCommand command) {
        return TaskElementValueUtils.getElementValueAsPrincipalList(of(command));
    }

    /**
     * Try to get an element as Principal
     *
     * @param command TaskSaveElementCommand

     * @return the element value if exists.
     */
    public static Optional<TaskElementValuePrincipalItem> findElementValueAsPrincipal(TaskSaveElementCommand command) {
        return TaskElementValueUtils.findElementValueAsPrincipal(of(command));
    }
}
