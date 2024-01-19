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

import com.kuflow.rest.KuFlowRestClientException;
import com.kuflow.rest.model.JsonFormsFile;
import com.kuflow.rest.model.JsonFormsPrincipal;
import com.kuflow.rest.model.JsonFormsValue;
import com.kuflow.rest.model.TenantUser;
import com.kuflow.rest.model.TenantUserMetadata;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Utility class for {@link TenantUser}
 */
public final class TenantUserUtils {

    private TenantUserUtils() {
        throw new RuntimeException("Utility class");
    }

    /**
     * Get a json property as String following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static String getMetadataPropertyAsString(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsString(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Try to find a json property as String following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<String> findMetadataPropertyAsString(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsString(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Get a json property as Integer following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Integer getMetadataPropertyAsInteger(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsInteger(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Try to find a json property as Integer following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Integer> findMetadataPropertyAsInteger(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsInteger(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Get a json property as Double following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Double getMetadataPropertyAsDouble(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsDouble(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Try to find a json property as Double following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Double> findMetadataPropertyAsDouble(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsDouble(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Get a json property as Boolean following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Boolean getMetadataPropertyAsBoolean(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsBoolean(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Try to find a json property as Double following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Boolean> findMetadataPropertyAsBoolean(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsBoolean(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Get a json property as Instant following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Instant getMetadataPropertyAsInstant(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsInstant(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Try to find a json property as Instant following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Instant> findMetadataPropertyAsInstant(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsInstant(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Get a json property as OffsetDateTime following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static OffsetDateTime getMetadataPropertyAsOffsetDateTime(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsOffsetDateTime(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Try to find a json property as OffsetDateTime following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<OffsetDateTime> findMetadataPropertyAsOffsetDateTime(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsOffsetDateTime(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Get a json property as LocalDate following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static LocalDate getMetadataPropertyAsLocalDate(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsLocalDate(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Try to find a json property as LocalDate following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<LocalDate> findMetadataPropertyAsLocalDate(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsLocalDate(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Get a json property as JsonFormsFile following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static JsonFormsFile getMetadataPropertyAsJsonFormsFile(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsJsonFormsFile(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Try to find a json property as JsonFormsFile following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<JsonFormsFile> findMetadataPropertyAsJsonFormsFile(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsJsonFormsFile(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Get a json property as JsonFormsPrincipal following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static JsonFormsPrincipal getMetadataPropertyAsJsonFormsPrincipal(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsJsonFormsPrincipal(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Try to find a json property as JsonFormsPrincipal following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<JsonFormsPrincipal> findMetadataPropertyAsJsonFormsPrincipal(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsJsonFormsPrincipal(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Get a json property as List following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist or is not a List
     */
    public static List<Object> getMetadataPropertyAsList(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsList(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Try to find a json property as List following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<List<Object>> findMetadataPropertyAsList(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsList(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Get a json property as Map following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist or is not a List
     */
    public static Map<String, Object> getMetadataPropertyAsMap(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsPropertyAsMap(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Try to find a json property as Map following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Map<String, Object>> findMetadataPropertyAsMap(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsPropertyAsMap(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Get a json property following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Object getMetadataProperty(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.getJsonFormsProperty(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Try to find a json property following the 'propertyPath' passed.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Object> findMetadataProperty(TenantUser tenantUser, String propertyPath) {
        return JsonFormsValueUtils.findJsonFormsProperty(toJsonFormsValue(tenantUser.getMetadata()), propertyPath);
    }

    /**
     * Update a json forms data property in the task passed following the 'propertyPath'.
     *
     * @param tenantUser TenantUser
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @param value Value to update
     * @throws KuFlowRestClientException If property parent path doesn't exist
     */
    public static void updateJsonFormsProperty(TenantUser tenantUser, String propertyPath, Object value) {
        if (tenantUser.getMetadata() == null) {
            tenantUser.setMetadata(new TenantUserMetadata());
        }
        if (tenantUser.getMetadata().getValue() == null) {
            tenantUser.getMetadata().setValue(new HashMap<>());
        }
        JsonFormsValue jsonFormsValue = toJsonFormsValue(tenantUser.getMetadata());

        JsonFormsValueUtils.updateJsonFormsProperty(jsonFormsValue, propertyPath, value);
    }

    private static JsonFormsValue toJsonFormsValue(TenantUserMetadata metadata) {
        JsonFormsValue jsonFormsValue = new JsonFormsValue();
        if (metadata != null) {
            jsonFormsValue.setValid(metadata.isValid());
            jsonFormsValue.setData(metadata.getValue());
        }

        return jsonFormsValue;
    }
}
