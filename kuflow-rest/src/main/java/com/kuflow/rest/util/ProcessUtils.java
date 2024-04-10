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

import static com.kuflow.rest.util.ProcessElementValueAccessorProcess.of;

import com.kuflow.rest.KuFlowRestClientException;
import com.kuflow.rest.model.JsonFormsFile;
import com.kuflow.rest.model.JsonFormsPrincipal;
import com.kuflow.rest.model.JsonFormsValue;
import com.kuflow.rest.model.Process;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Utility class for {@link Process}
 */
public final class ProcessUtils {

    private ProcessUtils() {
        throw new RuntimeException("Utility class");
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param elementDefinitionCode Element Definition Code
     * @return TRUE if all related valid values are TRUE else FALSE.
     */
    public static Boolean getElementValueValid(Process process, String elementDefinitionCode) {
        return ProcessElementValueUtils.getElementValueValid(of(process, elementDefinitionCode));
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @param elementDefinitionCode Element Definition Code
     * @param index Element value index
     * @return The requested valid value
     */
    public static Boolean getElementValueValidAt(Process process, String elementDefinitionCode, int index) {
        return ProcessElementValueUtils.getElementValueValidAt(of(process, elementDefinitionCode), index);
    }

    /**
     * Set valid to all values
     *
     * @param elementDefinitionCode Element Definition Code
     * @param valid Valid value

     */
    public static void setElementValueValid(Process process, String elementDefinitionCode, Boolean valid) {
        ProcessElementValueUtils.setElementValueValid(of(process, elementDefinitionCode), valid);
    }

    /**
     * Set valid to the selected value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param valid Valid value
     * @param index Element value index

     */
    public static void setElementValueValidAt(Process process, String elementDefinitionCode, Boolean valid, int index) {
        ProcessElementValueUtils.setElementValueValidAt(of(process, elementDefinitionCode), valid, index);
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed

     */
    public static void setElementValueAsString(Process process, String elementDefinitionCode, String elementValue) {
        ProcessElementValueUtils.setElementValue(of(process, elementDefinitionCode), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsStringList(Process process, String elementDefinitionCode, List<String> elementValues) {
        ProcessElementValueUtils.setElementValues(of(process, elementDefinitionCode), elementValues);
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsString(Process process, String elementDefinitionCode, String elementValue) {
        ProcessElementValueUtils.addElementValue(of(process, elementDefinitionCode), elementValue);
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     */
    public static void addElementValueAsStringList(Process process, String elementDefinitionCode, List<String> elementValues) {
        ProcessElementValueUtils.addElementValues(of(process, elementDefinitionCode), elementValues);
    }

    /**
     * Get an element as String
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static String getElementValueAsString(Process process, String elementDefinitionCode) {
        return ProcessElementValueUtils.getElementValueAsString(of(process, elementDefinitionCode));
    }

    /**
     * Get all elements as String
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public static List<String> getElementValueAsStringList(Process process, String elementDefinitionCode) {
        return ProcessElementValueUtils.getElementValueAsStringList(of(process, elementDefinitionCode));
    }

    /**
     * Try to get an element as String
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public static Optional<String> findElementValueAsString(Process process, String elementDefinitionCode) {
        return ProcessElementValueUtils.findElementValueAsString(of(process, elementDefinitionCode));
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     */
    public static void setElementValueAsDouble(Process process, String elementDefinitionCode, Double elementValue) {
        ProcessElementValueUtils.setElementValue(of(process, elementDefinitionCode), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsDoubleList(Process process, String elementDefinitionCode, List<Double> elementValues) {
        ProcessElementValueUtils.setElementValues(of(process, elementDefinitionCode), elementValues);
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsDouble(Process process, String elementDefinitionCode, Double elementValue) {
        ProcessElementValueUtils.addElementValue(of(process, elementDefinitionCode), elementValue);
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     */
    public static void addElementValueAsDoubleList(Process process, String elementDefinitionCode, List<Double> elementValues) {
        ProcessElementValueUtils.addElementValues(of(process, elementDefinitionCode), elementValues);
    }

    /**
     * Get an element as Double
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static Double getElementValueAsDouble(Process process, String elementDefinitionCode) {
        return ProcessElementValueUtils.getElementValueAsDouble(of(process, elementDefinitionCode));
    }

    /**
     * Get all elements as Double
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public static List<Double> getElementValueAsDoubleList(Process process, String elementDefinitionCode) {
        return ProcessElementValueUtils.getElementValueAsDoubleList(of(process, elementDefinitionCode));
    }

    /**
     * Try to get an element as Double
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public static Optional<Double> findElementValueAsDouble(Process process, String elementDefinitionCode) {
        return ProcessElementValueUtils.findElementValueAsDouble(of(process, elementDefinitionCode));
    }

    /**
     * Set an element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the value is null all current values are removed
     */
    public static void setElementValueAsLocalDate(Process process, String elementDefinitionCode, LocalDate elementValue) {
        ProcessElementValueUtils.setElementValue(of(process, elementDefinitionCode), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsLocalDateList(Process process, String elementDefinitionCode, List<LocalDate> elementValues) {
        ProcessElementValueUtils.setElementValues(of(process, elementDefinitionCode), elementValues);
    }

    /**
     * Add a new element value
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsLocalDate(Process process, String elementDefinitionCode, LocalDate elementValue) {
        ProcessElementValueUtils.addElementValue(of(process, elementDefinitionCode), elementValue);
    }

    /**
     * Add all element values passed
     *
     * @param elementDefinitionCode Element Definition Code
     * @param elementValues Element values
     */
    public static void addElementValueAsLocalDateList(Process process, String elementDefinitionCode, List<LocalDate> elementValues) {
        ProcessElementValueUtils.addElementValues(of(process, elementDefinitionCode), elementValues);
    }

    /**
     * Get an element as LocalDate
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static LocalDate getElementValueAsLocalDate(Process process, String elementDefinitionCode) {
        return ProcessElementValueUtils.getElementValueAsLocalDate(of(process, elementDefinitionCode));
    }

    /**
     * Get all elements as LocalDate
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the elements values.
     */
    public static List<LocalDate> getElementValueAsLocalDateList(Process process, String elementDefinitionCode) {
        return ProcessElementValueUtils.getElementValueAsLocalDateList(of(process, elementDefinitionCode));
    }

    /**
     * Try to get an element as LocalDate
     *
     * @param elementDefinitionCode Element Definition Code
     * @return the element value if exists.
     */
    public static Optional<LocalDate> findElementValueAsLocalDate(Process process, String elementDefinitionCode) {
        return ProcessElementValueUtils.findElementValueAsLocalDate(of(process, elementDefinitionCode));
    }

    /**
     * Get a json property as String following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static String getEntityPropertyAsString(Process process, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsString(process.getEntity(), propertyPath);
    }

    /**
     * Try to find a json property as String following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<String> findEntityPropertyAsString(Process process, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsString(process.getEntity(), propertyPath);
    }

    /**
     * Get a json property as Integer following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Integer getEntityPropertyAsInteger(Process process, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsInteger(process.getEntity(), propertyPath);
    }

    /**
     * Try to find a json property as Integer following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Integer> findEntityPropertyAsInteger(Process process, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsInteger(process.getEntity(), propertyPath);
    }

    /**
     * Get a json property as Double following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Double getEntityPropertyAsDouble(Process process, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsDouble(process.getEntity(), propertyPath);
    }

    /**
     * Try to find a json property as Double following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Double> findEntityPropertyAsDouble(Process process, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsDouble(process.getEntity(), propertyPath);
    }

    /**
     * Get a json property as Boolean following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Boolean getEntityPropertyAsBoolean(Process process, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsBoolean(process.getEntity(), propertyPath);
    }

    /**
     * Try to find a json property as Double following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Boolean> findEntityPropertyAsBoolean(Process process, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsBoolean(process.getEntity(), propertyPath);
    }

    /**
     * Get a json property as Instant following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Instant getEntityPropertyAsInstant(Process process, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsInstant(process.getEntity(), propertyPath);
    }

    /**
     * Try to find a json property as Instant following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Instant> findEntityPropertyAsInstant(Process process, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsInstant(process.getEntity(), propertyPath);
    }

    /**
     * Get a json property as OffsetDateTime following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static OffsetDateTime getEntityPropertyAsOffsetDateTime(Process process, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsOffsetDateTime(process.getEntity(), propertyPath);
    }

    /**
     * Try to find a json property as OffsetDateTime following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<OffsetDateTime> findEntityPropertyAsOffsetDateTime(Process process, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsOffsetDateTime(process.getEntity(), propertyPath);
    }

    /**
     * Get a json property as LocalDate following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static LocalDate getEntityPropertyAsLocalDate(Process process, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsLocalDate(process.getEntity(), propertyPath);
    }

    /**
     * Try to find a json property as LocalDate following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<LocalDate> findEntityPropertyAsLocalDate(Process process, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsLocalDate(process.getEntity(), propertyPath);
    }

    /**
     * Get a json property as JsonFormsFile following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static JsonFormsFile getEntityPropertyAsJsonFormsFile(Process process, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsJsonFormsFile(process.getEntity(), propertyPath);
    }

    /**
     * Try to find a json property as JsonFormsFile following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<JsonFormsFile> findEntityPropertyAsJsonFormsFile(Process process, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsJsonFormsFile(process.getEntity(), propertyPath);
    }

    /**
     * Get a json property as JsonFormsPrincipal following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static JsonFormsPrincipal getEntityPropertyAsJsonFormsPrincipal(Process process, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsJsonFormsPrincipal(process.getEntity(), propertyPath);
    }

    /**
     * Try to find a json property as JsonFormsPrincipal following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<JsonFormsPrincipal> findEntityPropertyAsJsonFormsPrincipal(Process process, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsJsonFormsPrincipal(process.getEntity(), propertyPath);
    }

    /**
     * Get a json property as List following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist or is not a List
     */
    public static List<Object> getEntityPropertyAsList(Process process, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsList(process.getEntity(), propertyPath);
    }

    /**
     * Try to find a json property as List following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<List<Object>> findEntityPropertyAsList(Process process, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsList(process.getEntity(), propertyPath);
    }

    /**
     * Get a json property as Map following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist or is not a List
     */
    public static Map<String, Object> getEntityPropertyAsMap(Process process, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsMap(process.getEntity(), propertyPath);
    }

    /**
     * Try to find a json property as Map following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Map<String, Object>> findEntityPropertyAsMap(Process process, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsMap(process.getEntity(), propertyPath);
    }

    /**
     * Get a json property following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Object getEntityProperty(Process process, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsProperty(process.getEntity(), propertyPath);
    }

    /**
     * Try to find a json property following the 'propertyPath' passed.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Object> findEntityProperty(Process process, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsProperty(process.getEntity(), propertyPath);
    }

    /**
     * Update a json forms data property in the task passed following the 'propertyPath'.
     *
     * @param process Process
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @param value Value to update
     * @throws KuFlowRestClientException If property parent path doesn't exist
     */
    public static void updateEntityProperty(Process process, String propertyPath, Object value) {
        if (process.getEntity() == null) {
            process.setEntity(new JsonFormsValue());
        }

        JsonFormsValueUtils.updateJsonFormsProperty(process.getEntity(), propertyPath, value);
    }
}
