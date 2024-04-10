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

import com.kuflow.rest.KuFlowRestClientException;
import com.kuflow.rest.model.JsonFormsFile;
import com.kuflow.rest.model.JsonFormsPrincipal;
import com.kuflow.rest.model.JsonFormsValue;
import com.kuflow.rest.util.JsonFormsValueUtils;
import com.kuflow.temporal.activity.kuflow.model.SaveProcessEntityDataRequest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Utility class for {@link SaveProcessEntityDataRequest}
 */
public final class SaveProcessEntityDataRequestUtils {

    private SaveProcessEntityDataRequestUtils() {
        throw new RuntimeException("Utility class");
    }

    /**
     * Get a json property as String following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static String getEntityPropertyAsString(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsString(of(command), propertyPath);
    }

    /**
     * Try to find a json property as String following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<String> findEntityPropertyAsString(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsString(of(command), propertyPath);
    }

    /**
     * Get a json property as Integer following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Integer getEntityPropertyAsInteger(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsInteger(of(command), propertyPath);
    }

    /**
     * Try to find a json property as Integer following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Integer> findEntityPropertyAsInteger(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsInteger(of(command), propertyPath);
    }

    /**
     * Get a json property as Double following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Double getEntityPropertyAsDouble(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsDouble(of(command), propertyPath);
    }

    /**
     * Try to find a json property as Double following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Double> findEntityPropertyAsDouble(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsDouble(of(command), propertyPath);
    }

    /**
     * Get a json property as Boolean following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Boolean getEntityPropertyAsBoolean(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsBoolean(of(command), propertyPath);
    }

    /**
     * Try to find a json property as Double following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Boolean> findEntityPropertyAsBoolean(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsBoolean(of(command), propertyPath);
    }

    /**
     * Get a json property as Instant following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Instant getEntityPropertyAsInstant(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsInstant(of(command), propertyPath);
    }

    /**
     * Try to find a json property as Instant following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Instant> findEntityPropertyAsInstant(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsInstant(of(command), propertyPath);
    }

    /**
     * Get a json property as OffsetDateTime following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static OffsetDateTime getEntityPropertyAsOffsetDateTime(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsOffsetDateTime(of(command), propertyPath);
    }

    /**
     * Try to find a json property as OffsetDateTime following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<OffsetDateTime> findEntityPropertyAsOffsetDateTime(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsOffsetDateTime(of(command), propertyPath);
    }

    /**
     * Get a json property as LocalDate following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static LocalDate getEntityPropertyAsLocalDate(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsLocalDate(of(command), propertyPath);
    }

    /**
     * Try to find a json property as LocalDate following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<LocalDate> findEntityPropertyAsLocalDate(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsLocalDate(of(command), propertyPath);
    }

    /**
     * Get a json property as JsonFormsFile following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static JsonFormsFile getEntityPropertyAsJsonFormsFile(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsJsonFormsFile(of(command), propertyPath);
    }

    /**
     * Try to find a json property as JsonFormsFile following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<JsonFormsFile> findEntityPropertyAsJsonFormsFile(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsJsonFormsFile(of(command), propertyPath);
    }

    /**
     * Get a json property as JsonFormsPrincipal following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static JsonFormsPrincipal getEntityPropertyAsJsonFormsPrincipal(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsJsonFormsPrincipal(of(command), propertyPath);
    }

    /**
     * Try to find a json property as JsonFormsPrincipal following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<JsonFormsPrincipal> findEntityPropertyAsJsonFormsPrincipal(
        SaveProcessEntityDataRequest command,
        String propertyPath
    ) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsJsonFormsPrincipal(of(command), propertyPath);
    }

    /**
     * Get a json property as List following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist or is not a List
     */
    public static List<Object> getEntityPropertyAsList(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsList(of(command), propertyPath);
    }

    /**
     * Try to find a json property as List following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<List<Object>> findEntityPropertyAsList(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsList(of(command), propertyPath);
    }

    /**
     * Get a json property as Map following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist or is not a List
     */
    public static Map<String, Object> getEntityPropertyAsMap(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsMap(of(command), propertyPath);
    }

    /**
     * Try to find a json property as Map following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Map<String, Object>> findEntityPropertyAsMap(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsMap(of(command), propertyPath);
    }

    /**
     * Get a json property following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Object getEntityProperty(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsProperty(of(command), propertyPath);
    }

    /**
     * Try to find a json property following the 'propertyPath' passed.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Object> findEntityProperty(SaveProcessEntityDataRequest command, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsProperty(of(command), propertyPath);
    }

    /**
     * Update a json data property in the task passed following the 'propertyPath'.
     *
     * @param command Request command
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @param value Value to update
     * @throws KuFlowRestClientException If property parent path doesn't exist
     */
    public static void updateEntityProperty(SaveProcessEntityDataRequest command, String propertyPath, Object value) {
        if (command.getData() == null) {
            command.setData(new HashMap<>());
        }

        JsonFormsValueUtils.updateJsonFormsProperty(of(command), propertyPath, value);
    }

    private static JsonFormsValue of(SaveProcessEntityDataRequest command) {
        return new JsonFormsValue().setData(command.getData());
    }
}
