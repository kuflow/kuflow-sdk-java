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

import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableMap;

import com.kuflow.rest.KuFlowRestClientException;
import com.kuflow.rest.model.JsonFormsFile;
import com.kuflow.rest.model.JsonFormsPrincipal;
import com.kuflow.rest.model.JsonFormsValue;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Utility class for {@link JsonFormsValue}
 */
public final class JsonFormsValueUtils {

    private JsonFormsValueUtils() {
        throw new RuntimeException("Utility class");
    }

    private static final Pattern NUMBER_PATTER = Pattern.compile("^\\d+(\\.\\d+)?$");

    /**
     * Get a json property as String following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static String getJsonFormsPropertyAsString(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsPropertyAsString(jsonFormsValue, propertyPath)
            .orElseThrow(() -> new KuFlowRestClientException("Property value doesn't exist"));
    }

    /**
     * Try to find a json property as String following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<String> findJsonFormsPropertyAsString(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsProperty(jsonFormsValue, propertyPath).map(Object::toString);
    }

    /**
     * Get a json property as Integer following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Integer getJsonFormsPropertyAsInteger(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsPropertyAsInteger(jsonFormsValue, propertyPath)
            .orElseThrow(() -> new KuFlowRestClientException("Property value doesn't exist"));
    }

    /**
     * Try to find a json property as Integer following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Integer> findJsonFormsPropertyAsInteger(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsProperty(jsonFormsValue, propertyPath).map(value -> Integer.valueOf(value.toString()));
    }

    /**
     * Get a json property as Double following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Double getJsonFormsPropertyAsDouble(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsPropertyAsDouble(jsonFormsValue, propertyPath)
            .orElseThrow(() -> new KuFlowRestClientException("Property value doesn't exist"));
    }

    /**
     * Try to find a json property as Double following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Double> findJsonFormsPropertyAsDouble(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsProperty(jsonFormsValue, propertyPath).map(it -> Double.valueOf(it.toString()));
    }

    /**
     * Get a json property as Boolean following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Boolean getJsonFormsPropertyAsBoolean(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsPropertyAsBoolean(jsonFormsValue, propertyPath)
            .orElseThrow(() -> new KuFlowRestClientException("Property value doesn't exist"));
    }

    /**
     * Try to find a json property as Double following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Boolean> findJsonFormsPropertyAsBoolean(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsProperty(jsonFormsValue, propertyPath)
            .map(value -> {
                if (value instanceof Boolean) {
                    return (Boolean) value;
                }

                throw new KuFlowRestClientException("Property value is not a boolean: " + value);
            });
    }

    /**
     * Get a json property as Instant following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Instant getJsonFormsPropertyAsInstant(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsPropertyAsInstant(jsonFormsValue, propertyPath)
            .orElseThrow(() -> new KuFlowRestClientException("Property value doesn't exist"));
    }

    /**
     * Try to find a json property as Instant following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Instant> findJsonFormsPropertyAsInstant(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsProperty(jsonFormsValue, propertyPath)
            .map(value -> {
                try {
                    return Instant.parse(value.toString());
                } catch (DateTimeParseException e) {
                    throw new KuFlowRestClientException(
                        String.format("Wrong value format, expected something like '2007-12-03T10:15:30.00Z' but was %s", value)
                    );
                }
            });
    }

    /**
     * Get a json property as OffsetDateTime following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static OffsetDateTime getJsonFormsPropertyAsOffsetDateTime(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsPropertyAsOffsetDateTime(jsonFormsValue, propertyPath)
            .orElseThrow(() -> new KuFlowRestClientException("Property value doesn't exist"));
    }

    /**
     * Try to find a json property as OffsetDateTime following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<OffsetDateTime> findJsonFormsPropertyAsOffsetDateTime(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsProperty(jsonFormsValue, propertyPath)
            .map(value -> {
                try {
                    return OffsetDateTime.parse(value.toString());
                } catch (DateTimeParseException e) {
                    throw new KuFlowRestClientException(
                        String.format("Wrong value format, expected something like '2007-12-03T10:15:30+01:00' but was %s", value)
                    );
                }
            });
    }

    /**
     * Get a json property as LocalDate following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static LocalDate getJsonFormsPropertyAsLocalDate(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsPropertyAsLocalDate(jsonFormsValue, propertyPath)
            .orElseThrow(() -> new KuFlowRestClientException("Property value doesn't exist"));
    }

    /**
     * Try to find a json property as LocalDate following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<LocalDate> findJsonFormsPropertyAsLocalDate(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsProperty(jsonFormsValue, propertyPath)
            .map(value -> {
                try {
                    return LocalDate.parse(value.toString());
                } catch (DateTimeParseException e) {
                    throw new KuFlowRestClientException(
                        String.format("Wrong value format, expected something like '2011-12-03' but was %s", value)
                    );
                }
            });
    }

    /**
     * Get a json property as JsonFormsFile following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static JsonFormsFile getJsonFormsPropertyAsJsonFormsFile(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsPropertyAsJsonFormsFile(jsonFormsValue, propertyPath)
            .orElseThrow(() -> new KuFlowRestClientException("Property value doesn't exist"));
    }

    /**
     * Try to find a json property as JsonFormsFile following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<JsonFormsFile> findJsonFormsPropertyAsJsonFormsFile(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsProperty(jsonFormsValue, propertyPath).flatMap(value -> JsonFormsFile.from(value.toString()));
    }

    /**
     * Get a json property as JsonFormsPrincipal following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static JsonFormsPrincipal getJsonFormsPropertyAsJsonFormsPrincipal(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsPropertyAsJsonFormsPrincipal(jsonFormsValue, propertyPath)
            .orElseThrow(() -> new KuFlowRestClientException("Property value doesn't exist"));
    }

    /**
     * Try to find a json property as JsonFormsPrincipal following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<JsonFormsPrincipal> findJsonFormsPropertyAsJsonFormsPrincipal(
        JsonFormsValue jsonFormsValue,
        String propertyPath
    ) {
        return findJsonFormsProperty(jsonFormsValue, propertyPath).flatMap(value -> JsonFormsPrincipal.from(value.toString()));
    }

    /**
     * Get a json property as List following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist or is not a List
     */
    public static List<Object> getJsonFormsPropertyAsList(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsPropertyAsList(jsonFormsValue, propertyPath)
            .orElseThrow(() -> new KuFlowRestClientException("Property value doesn't exist"));
    }

    /**
     * Try to find a json property as List following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    @SuppressWarnings("unchecked")
    public static Optional<List<Object>> findJsonFormsPropertyAsList(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsProperty(jsonFormsValue, propertyPath)
            .map(value -> {
                if (value instanceof List) {
                    return unmodifiableList((List<Object>) value);
                }

                throw new KuFlowRestClientException("Property value is not a list");
            });
    }

    /**
     * Get a json property as Map following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist or is not a List
     */
    public static Map<String, Object> getJsonFormsPropertyAsMap(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsPropertyAsMap(jsonFormsValue, propertyPath)
            .orElseThrow(() -> new KuFlowRestClientException("Property value doesn't exist"));
    }

    /**
     * Try to find a json property as Map following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    @SuppressWarnings("unchecked")
    public static Optional<Map<String, Object>> findJsonFormsPropertyAsMap(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsProperty(jsonFormsValue, propertyPath)
            .map(value -> {
                if (value instanceof Map) {
                    return unmodifiableMap((Map<String, Object>) value);
                }

                throw new KuFlowRestClientException("Property value is not a map");
            });
    }

    /**
     * Get a json property following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     * @throws KuFlowRestClientException If property value doesn't exist
     */
    public static Object getJsonFormsProperty(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsProperty(jsonFormsValue, propertyPath)
            .orElseThrow(() -> new KuFlowRestClientException("Property value doesn't exist"));
    }

    /**
     * Try to find a json property following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @return the property value if exists.
     */
    public static Optional<Object> findJsonFormsProperty(JsonFormsValue jsonFormsValue, String propertyPath) {
        return findJsonFormsProperty(jsonFormsValue, propertyPath, false).map(JsonFormsProperty::getValue);
    }

    /**
     * Update a json forms data property in the task passed following the 'propertyPath'.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @param value Value to update
     * @throws com.kuflow.rest.KuFlowRestClientException If property parent path doesn't exist
     */
    @SuppressWarnings("unchecked")
    public static void updateJsonFormsProperty(JsonFormsValue jsonFormsValue, String propertyPath, Object value) {
        Objects.requireNonNull(jsonFormsValue, "jsonFormsValue is required");
        Objects.requireNonNull(propertyPath, "propertyPath is required");

        value = transformJsonFormsPropertyValue(value);

        if (jsonFormsValue.getData() == null) {
            jsonFormsValue.setData(new HashMap<>());
        }

        Optional<JsonFormsProperty> property = findJsonFormsProperty(jsonFormsValue, propertyPath, true);
        if (property.isEmpty()) {
            throw new KuFlowRestClientException(String.format("Property %s doesn't exist", propertyPath));
        }

        Object jsonFormsPropertyContainer = property.get().getContainer();
        String jsonFormsPropertyPath = property.get().getPath();
        if (jsonFormsPropertyContainer instanceof List) {
            List<Object> jsonFormsPropertyContainerList = (List<Object>) jsonFormsPropertyContainer;
            if (!isNumeric(jsonFormsPropertyPath)) {
                throw new KuFlowRestClientException(
                    String.format("Incorrect property path %s, parent path is not a List", jsonFormsPropertyPath)
                );
            }

            int jsonFormsPropertyPathIndex = Integer.parseInt(jsonFormsPropertyPath);
            if (value != null) {
                if (jsonFormsPropertyPathIndex < jsonFormsPropertyContainerList.size()) {
                    jsonFormsPropertyContainerList.set(jsonFormsPropertyPathIndex, value);
                } else if (jsonFormsPropertyPathIndex == jsonFormsPropertyContainerList.size()) {
                    jsonFormsPropertyContainerList.add(value);
                } else {
                    throw new KuFlowRestClientException(String.format("Wrong list index %s", jsonFormsPropertyPath));
                }
            } else {
                if (jsonFormsPropertyPathIndex >= 0 && jsonFormsPropertyPathIndex < jsonFormsPropertyContainerList.size()) {
                    jsonFormsPropertyContainerList.remove(jsonFormsPropertyPathIndex);
                } else {
                    throw new KuFlowRestClientException(String.format("Wrong list index %s", jsonFormsPropertyPath));
                }
            }
        } else if (jsonFormsPropertyContainer instanceof Map) {
            Map<String, Object> jsonFormsPropertyContainerMap = (Map<String, Object>) jsonFormsPropertyContainer;
            if (value != null) {
                jsonFormsPropertyContainerMap.put(jsonFormsPropertyPath, value);
            } else {
                jsonFormsPropertyContainerMap.remove(jsonFormsPropertyPath);
            }
        } else {
            throw new KuFlowRestClientException(String.format("Incorrect property path %s", jsonFormsPropertyPath));
        }
    }

    /**
     * Try to find a json property following the 'propertyPath' passed.
     *
     * @param jsonFormsValue JsonFormsValue
     * @param propertyPath Property path to find. ie: "user.name" or "users.0.name"
     * @param createMissingParents If true, missing parents paths will be created
     * @return the property if exists.
     */
    @SuppressWarnings("unchecked")
    public static Optional<JsonFormsProperty> findJsonFormsProperty(
        JsonFormsValue jsonFormsValue,
        String propertyPath,
        boolean createMissingParents
    ) {
        Objects.requireNonNull(jsonFormsValue, "jsonFormsValue is required");
        Objects.requireNonNull(propertyPath, "propertyPath is required");

        Optional<Object> dataCurrentOptional = getJsonFormsValueData(jsonFormsValue, createMissingParents);
        if (dataCurrentOptional.isEmpty()) {
            return Optional.empty();
        }

        Object jsonFormsPropertyContainer = dataCurrentOptional.get();
        String jsonFormsPropertyPath = null;
        Object jsonFormsPropertyValue = null;

        String[] paths = propertyPath.split("\\.");
        for (int i = 0; i < paths.length; i++) {
            jsonFormsPropertyPath = paths[i];
            int jsonFormsPropertyPathAsInteger = -1;
            if (jsonFormsPropertyPath.isEmpty()) {
                continue;
            }

            if (jsonFormsPropertyContainer instanceof List) {
                List<Object> propertyContainerList = (List<Object>) jsonFormsPropertyContainer;
                if (!isNumeric(jsonFormsPropertyPath)) {
                    throw new KuFlowRestClientException(String.format("Wrong list index %s", jsonFormsPropertyPath));
                }

                jsonFormsPropertyPathAsInteger = Integer.parseInt(jsonFormsPropertyPath);
                if (jsonFormsPropertyPathAsInteger < 0 || jsonFormsPropertyPathAsInteger > propertyContainerList.size()) {
                    return Optional.empty();
                } else if (jsonFormsPropertyPathAsInteger == propertyContainerList.size()) {
                    if (!createMissingParents) {
                        return Optional.empty();
                    }

                    jsonFormsPropertyValue = null;
                } else {
                    jsonFormsPropertyValue = propertyContainerList.get(jsonFormsPropertyPathAsInteger);
                }
            } else if (jsonFormsPropertyContainer instanceof Map) {
                Map<String, Object> propertyContainerMap = (Map<String, Object>) jsonFormsPropertyContainer;
                if (!propertyContainerMap.containsKey(jsonFormsPropertyPath)) {
                    if (!createMissingParents) {
                        return Optional.empty();
                    }
                }
                jsonFormsPropertyValue = propertyContainerMap.get(jsonFormsPropertyPath);
            } else {
                return Optional.empty();
            }

            if (jsonFormsPropertyValue == null) {
                if (!createMissingParents) {
                    return Optional.empty();
                }

                if (i + 1 < paths.length) {
                    String pathNext = paths[i + 1];
                    if (isNumeric(pathNext)) {
                        jsonFormsPropertyValue = new ArrayList<>();
                    } else {
                        jsonFormsPropertyValue = new HashMap<>();
                    }

                    if (jsonFormsPropertyContainer instanceof List) {
                        List<Object> propertyContainerList = (List<Object>) jsonFormsPropertyContainer;
                        if (jsonFormsPropertyPathAsInteger != propertyContainerList.size()) {
                            throw new KuFlowRestClientException(String.format("Wrong list index %s", jsonFormsPropertyPath));
                        }

                        propertyContainerList.add(jsonFormsPropertyPathAsInteger, jsonFormsPropertyValue);
                    } else if (jsonFormsPropertyContainer instanceof Map) {
                        Map<String, Object> propertyContainerMap = (Map<String, Object>) jsonFormsPropertyContainer;
                        propertyContainerMap.put(jsonFormsPropertyPath, jsonFormsPropertyValue);
                    }
                }
            }

            if (i + 1 < paths.length) {
                jsonFormsPropertyContainer = jsonFormsPropertyValue;
                jsonFormsPropertyValue = null;
            }
        }

        return Optional.of(new JsonFormsProperty(jsonFormsPropertyContainer, jsonFormsPropertyPath, jsonFormsPropertyValue));
    }

    private static Optional<Object> getJsonFormsValueData(JsonFormsValue jsonFormsValue, boolean createMissingParents) {
        if (jsonFormsValue.getData() == null) {
            if (!createMissingParents) {
                return Optional.empty();
            }

            jsonFormsValue.setData(new HashMap<>());
        }

        return Optional.of(jsonFormsValue.getData());
    }

    private static Object transformJsonFormsPropertyValue(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof JsonFormsPrincipal || value instanceof JsonFormsFile) {
            return value.toString();
        } else if (value instanceof LocalDate) {
            return DateTimeFormatter.ISO_LOCAL_DATE.format((TemporalAccessor) value);
        } else if (value instanceof Instant) {
            return value.toString();
        } else if (value instanceof OffsetDateTime) {
            return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format((TemporalAccessor) value);
        } else if (value instanceof Number || value instanceof String || value instanceof Boolean) {
            return value;
        }

        throw new KuFlowRestClientException(String.format("Unsupported value '%s' of class '%s'", value, value.getClass()));
    }

    public static class JsonFormsProperty {

        private final Object container;

        private final String path;

        private final Object value;

        private JsonFormsProperty(Object container, String path, Object value) {
            this.container = container;
            this.path = path;
            this.value = value;
        }

        public Object getContainer() {
            return this.container;
        }

        public String getPath() {
            return this.path;
        }

        public Object getValue() {
            return this.value;
        }
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }

        return NUMBER_PATTER.matcher(strNum).matches();
    }
}
