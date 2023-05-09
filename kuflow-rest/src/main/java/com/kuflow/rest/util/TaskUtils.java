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

import static com.kuflow.rest.util.TaskElementValueAccessorTask.of;

import com.kuflow.rest.KuFlowRestClientException;
import com.kuflow.rest.model.JsonFormsFile;
import com.kuflow.rest.model.JsonFormsPrincipalUser;
import com.kuflow.rest.model.JsonFormsValue;
import com.kuflow.rest.model.Task;
import com.kuflow.rest.model.TaskElementValueDocumentItem;
import com.kuflow.rest.model.TaskElementValuePrincipalItem;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Utility class for {@link Task}
 */
public final class TaskUtils {

    private TaskUtils() {
        throw new RuntimeException("Utility class");
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @return TRUE if all related valid values are TRUE else FALSE.
     */
    public static Boolean getElementValueValid(Task task, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueValid(of(task, elementDefinitionCode));
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param index Element value index
     * @return The requested valid value
     */
    public static Boolean getElementValueValidAt(Task task, String elementDefinitionCode, int index) {
        return TaskElementValueUtils.getElementValueValidAt(of(task, elementDefinitionCode), index);
    }

    /**
     * Set valid to all values
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param valid Valid value
     */
    public static void setElementValueValid(Task task, String elementDefinitionCode, Boolean valid) {
        TaskElementValueUtils.setElementValueValid(of(task, elementDefinitionCode), valid);
    }

    /**
     * Set valid to the selected value
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param valid Valid value
     * @param index Element value index
     */
    public static void setElementValueValidAt(Task task, String elementDefinitionCode, Boolean valid, int index) {
        TaskElementValueUtils.setElementValueValidAt(of(task, elementDefinitionCode), valid, index);
    }

    /**
     * Set an element value
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     */
    public static void setElementValueAsString(Task task, String elementDefinitionCode, String elementValue) {
        TaskElementValueUtils.setElementValue(of(task, elementDefinitionCode), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsStringList(Task task, String elementDefinitionCode, List<String> elementValues) {
        TaskElementValueUtils.setElementValues(of(task, elementDefinitionCode), elementValues);
    }

    /**
     * Add a new element value
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsString(Task task, String elementDefinitionCode, String elementValue) {
        TaskElementValueUtils.addElementValue(of(task, elementDefinitionCode), elementValue);
    }

    /**
     * Add all element values passed
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     */
    public static void addElementValueAsStringList(Task task, String elementDefinitionCode, List<String> elementValues) {
        TaskElementValueUtils.addElementValues(of(task, elementDefinitionCode), elementValues);
    }

    /**
     * Get an element as String
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static String getElementValueAsString(Task task, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueAsString(of(task, elementDefinitionCode));
    }

    /**
     * Get all elements as String
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public static List<String> getElementValueAsStringList(Task task, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueAsStringList(of(task, elementDefinitionCode));
    }

    /**
     * Try to get an element as String
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public static Optional<String> findElementValueAsString(Task task, String elementDefinitionCode) {
        return TaskElementValueUtils.findElementValueAsString(of(task, elementDefinitionCode));
    }

    /**
     * Set an element value
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     */
    public static void setElementValueAsDouble(Task task, String elementDefinitionCode, Double elementValue) {
        TaskElementValueUtils.setElementValue(of(task, elementDefinitionCode), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsDoubleList(Task task, String elementDefinitionCode, List<Double> elementValues) {
        TaskElementValueUtils.setElementValues(of(task, elementDefinitionCode), elementValues);
    }

    /**
     * Add a new element value
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsDouble(Task task, String elementDefinitionCode, Double elementValue) {
        TaskElementValueUtils.addElementValue(of(task, elementDefinitionCode), elementValue);
    }

    /**
     * Add all element values passed
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     */
    public static void addElementValueAsDoubleList(Task task, String elementDefinitionCode, List<Double> elementValues) {
        TaskElementValueUtils.addElementValues(of(task, elementDefinitionCode), elementValues);
    }

    /**
     * Get an element as Double
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static Double getElementValueAsDouble(Task task, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueAsDouble(of(task, elementDefinitionCode));
    }

    /**
     * Get all elements as Double
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public static List<Double> getElementValueAsDoubleList(Task task, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueAsDoubleList(of(task, elementDefinitionCode));
    }

    /**
     * Try to get an element as Double
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public static Optional<Double> findElementValueAsDouble(Task task, String elementDefinitionCode) {
        return TaskElementValueUtils.findElementValueAsDouble(of(task, elementDefinitionCode));
    }

    /**
     * Set an element value
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     */
    public static void setElementValueAsLocalDate(Task task, String elementDefinitionCode, LocalDate elementValue) {
        TaskElementValueUtils.setElementValue(of(task, elementDefinitionCode), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsLocalDateList(Task task, String elementDefinitionCode, List<LocalDate> elementValues) {
        TaskElementValueUtils.setElementValues(of(task, elementDefinitionCode), elementValues);
    }

    /**
     * Add a new element value
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsLocalDate(Task task, String elementDefinitionCode, LocalDate elementValue) {
        TaskElementValueUtils.addElementValue(of(task, elementDefinitionCode), elementValue);
    }

    /**
     * Add all element values passed
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     */
    public static void addElementValueAsLocalDateList(Task task, String elementDefinitionCode, List<LocalDate> elementValues) {
        TaskElementValueUtils.addElementValues(of(task, elementDefinitionCode), elementValues);
    }

    /**
     * Get an element as LocalDate
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static LocalDate getElementValueAsLocalDate(Task task, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueAsLocalDate(of(task, elementDefinitionCode));
    }

    /**
     * Get all elements as LocalDate
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public static List<LocalDate> getElementValueAsLocalDateList(Task task, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueAsLocalDateList(of(task, elementDefinitionCode));
    }

    /**
     * Try to get an element as LocalDate
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public static Optional<LocalDate> findElementValueAsLocalDate(Task task, String elementDefinitionCode) {
        return TaskElementValueUtils.findElementValueAsLocalDate(of(task, elementDefinitionCode));
    }

    /**
     * Set an element value
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     */
    public static void setElementValueAsMap(Task task, String elementDefinitionCode, Map<String, Object> elementValue) {
        TaskElementValueUtils.setElementValue(of(task, elementDefinitionCode), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsMapList(Task task, String elementDefinitionCode, List<Map<String, Object>> elementValues) {
        TaskElementValueUtils.setElementValues(of(task, elementDefinitionCode), elementValues);
    }

    /**
     * Add a new element value
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsMap(Task task, String elementDefinitionCode, Map<String, Object> elementValue) {
        TaskElementValueUtils.addElementValue(of(task, elementDefinitionCode), elementValue);
    }

    /**
     * Add all element values passed
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     */
    public static void addElementValueAsMapList(Task task, String elementDefinitionCode, List<Map<String, Object>> elementValues) {
        TaskElementValueUtils.addElementValues(of(task, elementDefinitionCode), elementValues);
    }

    /**
     * Get an element as Map
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static Map<String, Object> getElementValueAsMap(Task task, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueAsMap(of(task, elementDefinitionCode));
    }

    /**
     * Get all elements as Map
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public static List<Map<String, Object>> getElementValueAsMapList(Task task, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueAsMapList(of(task, elementDefinitionCode));
    }

    /**
     * Try to get an element as Map
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public static Optional<Map<String, Object>> findElementValueAsMap(Task task, String elementDefinitionCode) {
        return TaskElementValueUtils.findElementValueAsMap(of(task, elementDefinitionCode));
    }

    /**
     * Set an element value
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     */
    public static void setElementValueAsDocument(Task task, String elementDefinitionCode, TaskElementValueDocumentItem elementValue) {
        TaskElementValueUtils.setElementValue(of(task, elementDefinitionCode), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsDocumentList(
        Task task,
        String elementDefinitionCode,
        List<TaskElementValueDocumentItem> elementValues
    ) {
        TaskElementValueUtils.setElementValues(of(task, elementDefinitionCode), elementValues);
    }

    /**
     * Add a new element value
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsDocument(Task task, String elementDefinitionCode, TaskElementValueDocumentItem elementValue) {
        TaskElementValueUtils.addElementValue(of(task, elementDefinitionCode), elementValue);
    }

    /**
     * Add all element values passed
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     */
    public static void addElementValueAsDocumentList(
        Task task,
        String elementDefinitionCode,
        List<TaskElementValueDocumentItem> elementValues
    ) {
        TaskElementValueUtils.addElementValues(of(task, elementDefinitionCode), elementValues);
    }

    /**
     * Get an element as Document
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static TaskElementValueDocumentItem getElementValueAsDocument(Task task, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueAsDocument(of(task, elementDefinitionCode));
    }

    /**
     * Get all elements as Document
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public static List<TaskElementValueDocumentItem> getElementValueAsDocumentList(Task task, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueAsDocumentList(of(task, elementDefinitionCode));
    }

    /**
     * Try to get an element as Document
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public static Optional<TaskElementValueDocumentItem> findElementValueAsDocument(Task task, String elementDefinitionCode) {
        return TaskElementValueUtils.findElementValueAsDocument(of(task, elementDefinitionCode));
    }

    /**
     * Set an element value
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     */
    public static void setElementValueAsPrincipal(Task task, String elementDefinitionCode, TaskElementValuePrincipalItem elementValue) {
        TaskElementValueUtils.setElementValue(of(task, elementDefinitionCode), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsPrincipalList(
        Task task,
        String elementDefinitionCode,
        List<TaskElementValuePrincipalItem> elementValues
    ) {
        TaskElementValueUtils.setElementValues(of(task, elementDefinitionCode), elementValues);
    }

    /**
     * Add a new element value
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsPrincipal(Task task, String elementDefinitionCode, TaskElementValuePrincipalItem elementValue) {
        TaskElementValueUtils.addElementValue(of(task, elementDefinitionCode), elementValue);
    }

    /**
     * Add all element values passed
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     */
    public static void addElementValueAsPrincipalList(
        Task task,
        String elementDefinitionCode,
        List<TaskElementValuePrincipalItem> elementValues
    ) {
        TaskElementValueUtils.addElementValues(of(task, elementDefinitionCode), elementValues);
    }

    /**
     * Get an element as Principal
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static TaskElementValuePrincipalItem getElementValueAsPrincipal(Task task, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueAsPrincipal(of(task, elementDefinitionCode));
    }

    /**
     * Get all elements as Principal
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public static List<TaskElementValuePrincipalItem> getElementValueAsPrincipalList(Task task, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueAsPrincipalList(of(task, elementDefinitionCode));
    }

    /**
     * Try to get an element as Principal
     *
     * @param task Task
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public static Optional<TaskElementValuePrincipalItem> findElementValueAsPrincipal(Task task, String elementDefinitionCode) {
        return TaskElementValueUtils.findElementValueAsPrincipal(of(task, elementDefinitionCode));
    }

    /**
     * Get a json property as String following the 'propertyPath' passed.
     *
     * @param task Task
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static String getJsonFormsPropertyAsString(Task task, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsString(task.getJsonFormsValue(), propertyPath);
    }

    /**
     * Try to find a json property as String following the 'propertyPath' passed.
     *
     * @param task Task
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<String> findJsonFormsPropertyAsString(Task task, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsString(task.getJsonFormsValue(), propertyPath);
    }

    /**
     * Get a json property as Integer following the 'propertyPath' passed.
     *
     * @param task Task
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Integer getJsonFormsPropertyAsInteger(Task task, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsInteger(task.getJsonFormsValue(), propertyPath);
    }

    /**
     * Try to find a json property as Integer following the 'propertyPath' passed.
     *
     * @param task Task
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Integer> findJsonFormsPropertyAsInteger(Task task, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsInteger(task.getJsonFormsValue(), propertyPath);
    }

    /**
     * Get a json property as Double following the 'propertyPath' passed.
     *
     * @param task Task
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Double getJsonFormsPropertyAsDouble(Task task, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsDouble(task.getJsonFormsValue(), propertyPath);
    }

    /**
     * Try to find a json property as Double following the 'propertyPath' passed.
     *
     * @param task Task
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Double> findJsonFormsPropertyAsDouble(Task task, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsDouble(task.getJsonFormsValue(), propertyPath);
    }

    /**
     * Get a json property as Boolean following the 'propertyPath' passed.
     *
     * @param task Task
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Boolean getJsonFormsPropertyAsBoolean(Task task, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsBoolean(task.getJsonFormsValue(), propertyPath);
    }

    /**
     * Try to find a json property as Double following the 'propertyPath' passed.
     *
     * @param task Task
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Boolean> findJsonFormsPropertyAsBoolean(Task task, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsBoolean(task.getJsonFormsValue(), propertyPath);
    }

    /**
     * Get a json property as LocalDate following the 'propertyPath' passed.
     *
     * @param task Task
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static LocalDate getJsonFormsPropertyAsLocalDate(Task task, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsLocalDate(task.getJsonFormsValue(), propertyPath);
    }

    /**
     * Try to find a json property as LocalDate following the 'propertyPath' passed.
     *
     * @param task Task
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<LocalDate> findJsonFormsPropertyAsLocalDate(Task task, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsLocalDate(task.getJsonFormsValue(), propertyPath);
    }

    /**
     * Get a json property as JsonFormsFile following the 'propertyPath' passed.
     *
     * @param task Task
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static JsonFormsFile getJsonFormsPropertyAsJsonFormsFile(Task task, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsJsonFormsFile(task.getJsonFormsValue(), propertyPath);
    }

    /**
     * Try to find a json property as JsonFormsFile following the 'propertyPath' passed.
     *
     * @param task Task
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<JsonFormsFile> findJsonFormsPropertyAsJsonFormsFile(Task task, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsJsonFormsFile(task.getJsonFormsValue(), propertyPath);
    }

    /**
     * Get a json property as JsonFormsPrincipalUser following the 'propertyPath' passed.
     *
     * @param task Task
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static JsonFormsPrincipalUser getJsonFormsPropertyAsJsonFormsPrincipalUser(Task task, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsJsonFormsPrincipalUser(task.getJsonFormsValue(), propertyPath);
    }

    /**
     * Try to find a json property as JsonFormsPrincipalUser following the 'propertyPath' passed.
     *
     * @param task Task
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<JsonFormsPrincipalUser> findJsonFormsPropertyAsJsonFormsPrincipalUser(Task task, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsJsonFormsPrincipalUser(task.getJsonFormsValue(), propertyPath);
    }

    /**
     * Get a json property as List following the 'propertyPath' passed.
     *
     * @param task Task
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist or is not a List
     */
    public static List<Object> getJsonFormsPropertyAsList(Task task, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsList(task.getJsonFormsValue(), propertyPath);
    }

    /**
     * Try to find a json property as List following the 'propertyPath' passed.
     *
     * @param task Task
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<List<Object>> findJsonFormsPropertyAsList(Task task, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsList(task.getJsonFormsValue(), propertyPath);
    }

    /**
     * Get a json property as Map following the 'propertyPath' passed.
     *
     * @param task Task
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist or is not a List
     */
    public static Map<String, Object> getJsonFormsPropertyAsMap(Task task, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsMap(task.getJsonFormsValue(), propertyPath);
    }

    /**
     * Try to find a json property as Map following the 'propertyPath' passed.
     *
     * @param task Task
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Map<String, Object>> findJsonFormsPropertyAsMap(Task task, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsMap(task.getJsonFormsValue(), propertyPath);
    }

    /**
     * Get a json property following the 'propertyPath' passed.
     *
     * @param task Task
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Object getJsonFormsProperty(Task task, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsProperty(task.getJsonFormsValue(), propertyPath);
    }

    /**
     * Try to find a json property following the 'propertyPath' passed.
     *
     * @param task Task
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Object> findJsonFormsProperty(Task task, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsProperty(task.getJsonFormsValue(), propertyPath);
    }

    /**
     * Update a json forms data property in the task passed following the 'propertyPath'.
     *
     * @param task Task
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @param value Value to update
     * @throws KuFlowRestClientException If property parent path doesn't exist
     */
    public static void updateJsonFormsProperty(Task task, String propertyPath, Object value) {
        if (task.getJsonFormsValue() == null) {
            task.setJsonFormsValue(new JsonFormsValue());
        }

        JsonFormsValueUtils.updateJsonFormsProperty(task.getJsonFormsValue(), propertyPath, value);
    }
}
