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

import static com.kuflow.rest.util.TaskElementValueAccessorTaskPageItem.of;

import com.kuflow.rest.KuFlowRestClientException;
import com.kuflow.rest.model.JsonFormsFile;
import com.kuflow.rest.model.JsonFormsPrincipalUser;
import com.kuflow.rest.model.JsonFormsValue;
import com.kuflow.rest.model.TaskElementValueDocumentItem;
import com.kuflow.rest.model.TaskElementValuePrincipalItem;
import com.kuflow.rest.model.TaskPageItem;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Utility class for {@link TaskPageItem}
 */
public final class TaskPageItemUtils {

    private TaskPageItemUtils() {
        throw new RuntimeException("Utility class");
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @return TRUE if all related valid values are TRUE else FALSE.
     */
    public static Boolean getElementValueValid(TaskPageItem taskPageItem, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueValid(of(taskPageItem, elementDefinitionCode));
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param index Element value index
     * @return The requested valid value
     */
    public static Boolean getElementValueValidAt(TaskPageItem taskPageItem, String elementDefinitionCode, int index) {
        return TaskElementValueUtils.getElementValueValidAt(of(taskPageItem, elementDefinitionCode), index);
    }

    /**
     * Set valid to all values
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param valid Valid value
     */
    public static void setElementValueValid(TaskPageItem taskPageItem, String elementDefinitionCode, Boolean valid) {
        TaskElementValueUtils.setElementValueValid(of(taskPageItem, elementDefinitionCode), valid);
    }

    /**
     * Set valid to the selected value
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param valid Valid value
     * @param index Element value index
     */
    public static void setElementValueValidAt(TaskPageItem taskPageItem, String elementDefinitionCode, Boolean valid, int index) {
        TaskElementValueUtils.setElementValueValidAt(of(taskPageItem, elementDefinitionCode), valid, index);
    }

    /**
     * Set an element value
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     */
    public static void setElementValueAsString(TaskPageItem taskPageItem, String elementDefinitionCode, String elementValue) {
        TaskElementValueUtils.setElementValue(of(taskPageItem, elementDefinitionCode), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsStringList(TaskPageItem taskPageItem, String elementDefinitionCode, List<String> elementValues) {
        TaskElementValueUtils.setElementValues(of(taskPageItem, elementDefinitionCode), elementValues);
    }

    /**
     * Add a new element value
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsString(TaskPageItem taskPageItem, String elementDefinitionCode, String elementValue) {
        TaskElementValueUtils.addElementValue(of(taskPageItem, elementDefinitionCode), elementValue);
    }

    /**
     * Add all element values passed
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     */
    public static void addElementValueAsStringList(TaskPageItem taskPageItem, String elementDefinitionCode, List<String> elementValues) {
        TaskElementValueUtils.addElementValues(of(taskPageItem, elementDefinitionCode), elementValues);
    }

    /**
     * Get an element as String
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static String getElementValueAsString(TaskPageItem taskPageItem, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueAsString(of(taskPageItem, elementDefinitionCode));
    }

    /**
     * Get all elements as String
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public static List<String> getElementValueAsStringList(TaskPageItem taskPageItem, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueAsStringList(of(taskPageItem, elementDefinitionCode));
    }

    /**
     * Try to get an element as String
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public static Optional<String> findElementValueAsString(TaskPageItem taskPageItem, String elementDefinitionCode) {
        return TaskElementValueUtils.findElementValueAsString(of(taskPageItem, elementDefinitionCode));
    }

    /**
     * Set an element value
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     */
    public static void setElementValueAsDouble(TaskPageItem taskPageItem, String elementDefinitionCode, Double elementValue) {
        TaskElementValueUtils.setElementValue(of(taskPageItem, elementDefinitionCode), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsDoubleList(TaskPageItem taskPageItem, String elementDefinitionCode, List<Double> elementValues) {
        TaskElementValueUtils.setElementValues(of(taskPageItem, elementDefinitionCode), elementValues);
    }

    /**
     * Add a new element value
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsDouble(TaskPageItem taskPageItem, String elementDefinitionCode, Double elementValue) {
        TaskElementValueUtils.addElementValue(of(taskPageItem, elementDefinitionCode), elementValue);
    }

    /**
     * Add all element values passed
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     */
    public static void addElementValueAsDoubleList(TaskPageItem taskPageItem, String elementDefinitionCode, List<Double> elementValues) {
        TaskElementValueUtils.addElementValues(of(taskPageItem, elementDefinitionCode), elementValues);
    }

    /**
     * Get an element as Double
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static Double getElementValueAsDouble(TaskPageItem taskPageItem, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueAsDouble(of(taskPageItem, elementDefinitionCode));
    }

    /**
     * Get all elements as Double
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public static List<Double> getElementValueAsDoubleList(TaskPageItem taskPageItem, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueAsDoubleList(of(taskPageItem, elementDefinitionCode));
    }

    /**
     * Try to get an element as Double
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public static Optional<Double> findElementValueAsDouble(TaskPageItem taskPageItem, String elementDefinitionCode) {
        return TaskElementValueUtils.findElementValueAsDouble(of(taskPageItem, elementDefinitionCode));
    }

    /**
     * Set an element value
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     */
    public static void setElementValueAsLocalDate(TaskPageItem taskPageItem, String elementDefinitionCode, LocalDate elementValue) {
        TaskElementValueUtils.setElementValue(of(taskPageItem, elementDefinitionCode), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsLocalDateList(
        TaskPageItem taskPageItem,
        String elementDefinitionCode,
        List<LocalDate> elementValues
    ) {
        TaskElementValueUtils.setElementValues(of(taskPageItem, elementDefinitionCode), elementValues);
    }

    /**
     * Add a new element value
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsLocalDate(TaskPageItem taskPageItem, String elementDefinitionCode, LocalDate elementValue) {
        TaskElementValueUtils.addElementValue(of(taskPageItem, elementDefinitionCode), elementValue);
    }

    /**
     * Add all element values passed
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     */
    public static void addElementValueAsLocalDateList(
        TaskPageItem taskPageItem,
        String elementDefinitionCode,
        List<LocalDate> elementValues
    ) {
        TaskElementValueUtils.addElementValues(of(taskPageItem, elementDefinitionCode), elementValues);
    }

    /**
     * Get an element as LocalDate
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static LocalDate getElementValueAsLocalDate(TaskPageItem taskPageItem, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueAsLocalDate(of(taskPageItem, elementDefinitionCode));
    }

    /**
     * Get all elements as LocalDate
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public static List<LocalDate> getElementValueAsLocalDateList(TaskPageItem taskPageItem, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueAsLocalDateList(of(taskPageItem, elementDefinitionCode));
    }

    /**
     * Try to get an element as LocalDate
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public static Optional<LocalDate> findElementValueAsLocalDate(TaskPageItem taskPageItem, String elementDefinitionCode) {
        return TaskElementValueUtils.findElementValueAsLocalDate(of(taskPageItem, elementDefinitionCode));
    }

    /**
     * Set an element value
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     */
    public static void setElementValueAsMap(TaskPageItem taskPageItem, String elementDefinitionCode, Map<String, Object> elementValue) {
        TaskElementValueUtils.setElementValue(of(taskPageItem, elementDefinitionCode), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsMapList(
        TaskPageItem taskPageItem,
        String elementDefinitionCode,
        List<Map<String, Object>> elementValues
    ) {
        TaskElementValueUtils.setElementValues(of(taskPageItem, elementDefinitionCode), elementValues);
    }

    /**
     * Add a new element value
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsMap(TaskPageItem taskPageItem, String elementDefinitionCode, Map<String, Object> elementValue) {
        TaskElementValueUtils.addElementValue(of(taskPageItem, elementDefinitionCode), elementValue);
    }

    /**
     * Add all element values passed
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     */
    public static void addElementValueAsMapList(
        TaskPageItem taskPageItem,
        String elementDefinitionCode,
        List<Map<String, Object>> elementValues
    ) {
        TaskElementValueUtils.addElementValues(of(taskPageItem, elementDefinitionCode), elementValues);
    }

    /**
     * Get an element as Map
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static Map<String, Object> getElementValueAsMap(TaskPageItem taskPageItem, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueAsMap(of(taskPageItem, elementDefinitionCode));
    }

    /**
     * Get all elements as Map
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public static List<Map<String, Object>> getElementValueAsMapList(TaskPageItem taskPageItem, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueAsMapList(of(taskPageItem, elementDefinitionCode));
    }

    /**
     * Try to get an element as Map
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public static Optional<Map<String, Object>> findElementValueAsMap(TaskPageItem taskPageItem, String elementDefinitionCode) {
        return TaskElementValueUtils.findElementValueAsMap(of(taskPageItem, elementDefinitionCode));
    }

    /**
     * Set an element value
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     */
    public static void setElementValueAsDocument(
        TaskPageItem taskPageItem,
        String elementDefinitionCode,
        TaskElementValueDocumentItem elementValue
    ) {
        TaskElementValueUtils.setElementValue(of(taskPageItem, elementDefinitionCode), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsDocumentList(
        TaskPageItem taskPageItem,
        String elementDefinitionCode,
        List<TaskElementValueDocumentItem> elementValues
    ) {
        TaskElementValueUtils.setElementValues(of(taskPageItem, elementDefinitionCode), elementValues);
    }

    /**
     * Add a new element value
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsDocument(
        TaskPageItem taskPageItem,
        String elementDefinitionCode,
        TaskElementValueDocumentItem elementValue
    ) {
        TaskElementValueUtils.addElementValue(of(taskPageItem, elementDefinitionCode), elementValue);
    }

    /**
     * Add all element values passed
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     */
    public static void addElementValueAsDocumentList(
        TaskPageItem taskPageItem,
        String elementDefinitionCode,
        List<TaskElementValueDocumentItem> elementValues
    ) {
        TaskElementValueUtils.addElementValues(of(taskPageItem, elementDefinitionCode), elementValues);
    }

    /**
     * Get an element as Document
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static TaskElementValueDocumentItem getElementValueAsDocument(TaskPageItem taskPageItem, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueAsDocument(of(taskPageItem, elementDefinitionCode));
    }

    /**
     * Get all elements as Document
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public static List<TaskElementValueDocumentItem> getElementValueAsDocumentList(
        TaskPageItem taskPageItem,
        String elementDefinitionCode
    ) {
        return TaskElementValueUtils.getElementValueAsDocumentList(of(taskPageItem, elementDefinitionCode));
    }

    /**
     * Try to get an element as Document
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public static Optional<TaskElementValueDocumentItem> findElementValueAsDocument(
        TaskPageItem taskPageItem,
        String elementDefinitionCode
    ) {
        return TaskElementValueUtils.findElementValueAsDocument(of(taskPageItem, elementDefinitionCode));
    }

    /**
     * Set an element value
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     */
    public static void setElementValueAsPrincipal(
        TaskPageItem taskPageItem,
        String elementDefinitionCode,
        TaskElementValuePrincipalItem elementValue
    ) {
        TaskElementValueUtils.setElementValue(of(taskPageItem, elementDefinitionCode), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsPrincipalList(
        TaskPageItem taskPageItem,
        String elementDefinitionCode,
        List<TaskElementValuePrincipalItem> elementValues
    ) {
        TaskElementValueUtils.setElementValues(of(taskPageItem, elementDefinitionCode), elementValues);
    }

    /**
     * Add a new element value
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsPrincipal(
        TaskPageItem taskPageItem,
        String elementDefinitionCode,
        TaskElementValuePrincipalItem elementValue
    ) {
        TaskElementValueUtils.addElementValue(of(taskPageItem, elementDefinitionCode), elementValue);
    }

    /**
     * Add all element values passed
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     */
    public static void addElementValueAsPrincipalList(
        TaskPageItem taskPageItem,
        String elementDefinitionCode,
        List<TaskElementValuePrincipalItem> elementValues
    ) {
        TaskElementValueUtils.addElementValues(of(taskPageItem, elementDefinitionCode), elementValues);
    }

    /**
     * Get an element as Principal
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static TaskElementValuePrincipalItem getElementValueAsPrincipal(TaskPageItem taskPageItem, String elementDefinitionCode) {
        return TaskElementValueUtils.getElementValueAsPrincipal(of(taskPageItem, elementDefinitionCode));
    }

    /**
     * Get all elements as Principal
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public static List<TaskElementValuePrincipalItem> getElementValueAsPrincipalList(
        TaskPageItem taskPageItem,
        String elementDefinitionCode
    ) {
        return TaskElementValueUtils.getElementValueAsPrincipalList(of(taskPageItem, elementDefinitionCode));
    }

    /**
     * Try to get an element as Principal
     *
     * @param taskPageItem TaskPageItem
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public static Optional<TaskElementValuePrincipalItem> findElementValueAsPrincipal(
        TaskPageItem taskPageItem,
        String elementDefinitionCode
    ) {
        return TaskElementValueUtils.findElementValueAsPrincipal(of(taskPageItem, elementDefinitionCode));
    }

    /**
     * Get a json property as String following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static String getJsonFormsPropertyAsString(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsString(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Try to find a json property as String following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<String> findJsonFormsPropertyAsString(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsString(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Get a json property as Integer following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Integer getJsonFormsPropertyAsInteger(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsInteger(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Try to find a json property as Integer following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Integer> findJsonFormsPropertyAsInteger(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsInteger(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Get a json property as Double following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Double getJsonFormsPropertyAsDouble(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsDouble(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Try to find a json property as Double following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Double> findJsonFormsPropertyAsDouble(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsDouble(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Get a json property as Boolean following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Boolean getJsonFormsPropertyAsBoolean(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsBoolean(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Try to find a json property as Double following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Boolean> findJsonFormsPropertyAsBoolean(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsBoolean(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Get a json property as Instant following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Instant getJsonFormsPropertyAsInstant(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsInstant(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Try to find a json property as Instant following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Instant> findJsonFormsPropertyAsInstant(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsInstant(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Get a json property as OffsetDateTime following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static OffsetDateTime getJsonFormsPropertyAsOffsetDateTime(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsOffsetDateTime(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Try to find a json property as OffsetDateTime following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<OffsetDateTime> findJsonFormsPropertyAsOffsetDateTime(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsOffsetDateTime(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Get a json property as LocalDate following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static LocalDate getJsonFormsPropertyAsLocalDate(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsLocalDate(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Try to find a json property as LocalDate following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<LocalDate> findJsonFormsPropertyAsLocalDate(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsLocalDate(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Get a json property as JsonFormsFile following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static JsonFormsFile getJsonFormsPropertyAsJsonFormsFile(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsJsonFormsFile(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Try to find a json property as JsonFormsFile following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<JsonFormsFile> findJsonFormsPropertyAsJsonFormsFile(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsJsonFormsFile(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Get a json property as JsonFormsPrincipalUser following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static JsonFormsPrincipalUser getJsonFormsPropertyAsJsonFormsPrincipalUser(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsJsonFormsPrincipalUser(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Try to find a json property as JsonFormsPrincipalUser following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<JsonFormsPrincipalUser> findJsonFormsPropertyAsJsonFormsPrincipalUser(
        TaskPageItem taskPageItem,
        String propertyPath
    ) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsJsonFormsPrincipalUser(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Get a json property as List following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist or is not a List
     */
    public static List<Object> getJsonFormsPropertyAsList(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsList(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Try to find a json property as List following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<List<Object>> findJsonFormsPropertyAsList(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsList(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Get a json property as Map following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist or is not a List
     */
    public static Map<String, Object> getJsonFormsPropertyAsMap(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsMap(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Try to find a json property as Map following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Map<String, Object>> findJsonFormsPropertyAsMap(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsMap(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Get a json property following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Object getJsonFormsProperty(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsProperty(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Try to find a json property following the 'propertyPath' passed.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Object> findJsonFormsProperty(TaskPageItem taskPageItem, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsProperty(taskPageItem.getJsonFormsValue(), propertyPath);
    }

    /**
     * Update a json forms data property in the task passed following the 'propertyPath'.
     *
     * @param taskPageItem TaskPageItem
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @param value Value to update
     * @throws KuFlowRestClientException If property parent path doesn't exist
     */
    public static void updateJsonFormsProperty(TaskPageItem taskPageItem, String propertyPath, Object value) {
        if (taskPageItem.getJsonFormsValue() == null) {
            taskPageItem.setJsonFormsValue(new JsonFormsValue());
        }

        JsonFormsValueUtils.updateJsonFormsProperty(taskPageItem.getJsonFormsValue(), propertyPath, value);
    }
}
