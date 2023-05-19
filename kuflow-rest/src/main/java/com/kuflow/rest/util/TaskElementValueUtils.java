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

import static java.util.stream.Collectors.toList;

import com.kuflow.rest.KuFlowRestClientException;
import com.kuflow.rest.model.TaskElementValue;
import com.kuflow.rest.model.TaskElementValueDocument;
import com.kuflow.rest.model.TaskElementValueDocumentItem;
import com.kuflow.rest.model.TaskElementValueNumber;
import com.kuflow.rest.model.TaskElementValueObject;
import com.kuflow.rest.model.TaskElementValuePrincipal;
import com.kuflow.rest.model.TaskElementValuePrincipalItem;
import com.kuflow.rest.model.TaskElementValueString;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Utility class for {@link TaskElementValue}
 */
public final class TaskElementValueUtils {

    private TaskElementValueUtils() {
        throw new RuntimeException("Utility class");
    }

    public static Boolean getElementValueValid(TaskElementValueAccessor taskElementValueAccessor) {
        List<TaskElementValue> elementValues = getElementValues(taskElementValueAccessor);
        if (elementValues.isEmpty()) {
            return null;
        }

        return elementValues.stream().filter(v -> Boolean.FALSE.equals(v.isValid())).findAny().isEmpty();
    }

    public static Boolean getElementValueValidAt(TaskElementValueAccessor taskElementValueAccessor, int index) {
        List<TaskElementValue> elementValues = getElementValues(taskElementValueAccessor);

        return elementValues.get(index).isValid();
    }

    public static void setElementValueValid(TaskElementValueAccessor taskElementValueAccessor, Boolean valid) {
        List<TaskElementValue> elementValues = getElementValues(taskElementValueAccessor);
        elementValues.forEach(it -> it.setValid(valid));
    }

    public static void setElementValueValidAt(TaskElementValueAccessor taskElementValueAccessor, Boolean valid, int index) {
        List<TaskElementValue> elementValues = getElementValues(taskElementValueAccessor);
        elementValues.get(index).setValid(valid);
    }

    public static void addElementValue(TaskElementValueAccessor taskElementValueAccessor, Object elementValue) {
        List<Object> elementValueList = new LinkedList<>();
        if (elementValue != null) {
            elementValueList.add(elementValue);
        }

        addElementValues(taskElementValueAccessor, elementValueList);
    }

    public static void addElementValues(TaskElementValueAccessor taskElementValueAccessor, List<?> elementValues) {
        List<Object> elementValueList = new LinkedList<>();
        if (elementValues != null) {
            elementValueList.addAll(elementValues);
        }

        List<TaskElementValue> taskElementValues = elementValueList
            .stream()
            .map(TaskElementValueUtils::toTaskElementValue)
            .collect(toList());

        List<TaskElementValue> taskElementValueList = taskElementValueAccessor.getElementValues();

        if (taskElementValueList == null) {
            taskElementValueList = new LinkedList<>();
        }
        taskElementValueList.addAll(taskElementValues);

        taskElementValueAccessor.setElementValues(taskElementValueList);
    }

    public static void setElementValue(TaskElementValueAccessor taskElementValueAccessor, Object elementValue) {
        List<Object> valueList = new LinkedList<>();
        if (elementValue != null) {
            valueList.add(elementValue);
        }

        setElementValues(taskElementValueAccessor, valueList);
    }

    public static void setElementValues(TaskElementValueAccessor taskElementValueAccessor, List<?> elementValues) {
        if (elementValues == null) {
            elementValues = new LinkedList<>();
        }

        List<TaskElementValue> taskElementValues = elementValues.stream().map(TaskElementValueUtils::toTaskElementValue).collect(toList());

        taskElementValueAccessor.setElementValues(taskElementValues);
    }

    @SuppressWarnings("unchecked")
    private static TaskElementValue toTaskElementValue(Object value) {
        if (value instanceof String) {
            return toElementValueResourceString((String) value);
        } else if (value instanceof Double) {
            return toElementValueResourceNumber((Double) value);
        } else if (value instanceof LocalDate) {
            return toElementValueResourceString(value.toString());
        } else if (value instanceof Map) {
            return toElementValueResourceObject((Map<String, Object>) value);
        } else if (value instanceof TaskElementValueDocumentItem) {
            return toElementValueResourceDocument((TaskElementValueDocumentItem) value);
        } else if (value instanceof TaskElementValuePrincipalItem) {
            return toElementValueResourcePrincipal((TaskElementValuePrincipalItem) value);
        } else {
            throw new IllegalArgumentException(String.format("Unknown type %s", value.getClass().getName()));
        }
    }

    private static TaskElementValueString toElementValueResourceString(String value) {
        return new TaskElementValueString().setValue(value);
    }

    private static TaskElementValueNumber toElementValueResourceNumber(Double value) {
        return new TaskElementValueNumber().setValue(value);
    }

    private static TaskElementValueObject toElementValueResourceObject(Map<String, Object> value) {
        return new TaskElementValueObject().setValue(value);
    }

    private static TaskElementValueDocument toElementValueResourceDocument(TaskElementValueDocumentItem value) {
        return new TaskElementValueDocument().setValue(value);
    }

    private static TaskElementValuePrincipal toElementValueResourcePrincipal(TaskElementValuePrincipalItem value) {
        return new TaskElementValuePrincipal().setValue(value);
    }

    public static String getElementValueAsString(TaskElementValueAccessor taskElementValueAccessor) {
        return findElementValueAsString(taskElementValueAccessor)
            .orElseThrow(() -> new KuFlowRestClientException("Element value doesn't exist"));
    }

    public static Optional<String> findElementValueAsString(TaskElementValueAccessor taskElementValueAccessor) {
        Optional<TaskElementValue> taskElementValue = findElementValue(taskElementValueAccessor);

        return taskElementValue.map(TaskElementValueUtils::getElementValueAsString);
    }

    public static List<String> getElementValueAsStringList(TaskElementValueAccessor taskElementValueAccessor) {
        List<TaskElementValue> taskElementValues = getElementValues(taskElementValueAccessor);

        return taskElementValues.stream().map(TaskElementValueUtils::getElementValueAsString).collect(toList());
    }

    private static String getElementValueAsString(TaskElementValue elementValue) {
        if (elementValue instanceof TaskElementValueString) {
            TaskElementValueString valueString = (TaskElementValueString) elementValue;

            return valueString.getValue();
        }

        if (elementValue instanceof TaskElementValueNumber) {
            TaskElementValueNumber valueNumber = (TaskElementValueNumber) elementValue;

            return valueNumber.getValue() != null ? valueNumber.getValue().toString() : null;
        }

        throw new KuFlowRestClientException(String.format("elementValue %s is not a String", elementValue));
    }

    public static Optional<Double> findElementValueAsDouble(TaskElementValueAccessor taskElementValueAccessor) {
        Optional<TaskElementValue> taskElementValue = findElementValue(taskElementValueAccessor);

        return taskElementValue.map(TaskElementValueUtils::getElementValueAsDouble);
    }

    public static Double getElementValueAsDouble(TaskElementValueAccessor taskElementValueAccessor) {
        return findElementValueAsDouble(taskElementValueAccessor)
            .orElseThrow(() -> new KuFlowRestClientException("Element value doesn't exist"));
    }

    public static List<Double> getElementValueAsDoubleList(TaskElementValueAccessor taskElementValueAccessor) {
        List<TaskElementValue> taskElementValues = getElementValues(taskElementValueAccessor);

        return taskElementValues.stream().map(TaskElementValueUtils::getElementValueAsDouble).collect(toList());
    }

    private static Double getElementValueAsDouble(TaskElementValue elementValue) {
        if (elementValue instanceof TaskElementValueNumber) {
            TaskElementValueNumber valueNumber = (TaskElementValueNumber) elementValue;

            return valueNumber.getValue();
        }

        if (elementValue instanceof TaskElementValueString) {
            TaskElementValueString valueString = (TaskElementValueString) elementValue;

            try {
                if (valueString.getValue() == null) {
                    return null;
                }
                return Double.valueOf(valueString.getValue());
            } catch (NumberFormatException e) {
                throw new KuFlowRestClientException(String.format("elementValue %s is not a number", valueString), e);
            }
        }

        throw new KuFlowRestClientException(String.format("elementValue %s is not a Number", elementValue));
    }

    public static Optional<LocalDate> findElementValueAsLocalDate(TaskElementValueAccessor taskElementValueAccessor) {
        Optional<TaskElementValue> taskElementValue = findElementValue(taskElementValueAccessor);

        return taskElementValue.map(TaskElementValueUtils::getElementValueAsLocalDate);
    }

    public static LocalDate getElementValueAsLocalDate(TaskElementValueAccessor taskElementValueAccessor) {
        return findElementValueAsLocalDate(taskElementValueAccessor)
            .orElseThrow(() -> new KuFlowRestClientException("Element value doesn't exist"));
    }

    public static List<LocalDate> getElementValueAsLocalDateList(TaskElementValueAccessor taskElementValueAccessor) {
        List<TaskElementValue> taskElementValues = getElementValues(taskElementValueAccessor);

        return taskElementValues.stream().map(TaskElementValueUtils::getElementValueAsLocalDate).collect(toList());
    }

    private static LocalDate getElementValueAsLocalDate(TaskElementValue elementValue) {
        String valueString = getElementValueAsString(elementValue);

        if (valueString == null) {
            return null;
        }

        try {
            return LocalDate.parse(valueString, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new KuFlowRestClientException(String.format("elementValue %s is not a date", valueString), e);
        }
    }

    public static Optional<Map<String, Object>> findElementValueAsMap(TaskElementValueAccessor taskElementValueAccessor) {
        Optional<TaskElementValue> taskElementValue = findElementValue(taskElementValueAccessor);

        return taskElementValue.map(TaskElementValueUtils::getElementValueAsMap);
    }

    public static Map<String, Object> getElementValueAsMap(TaskElementValueAccessor taskElementValueAccessor) {
        return findElementValueAsMap(taskElementValueAccessor)
            .orElseThrow(() -> new KuFlowRestClientException("Element value doesn't exist"));
    }

    public static List<Map<String, Object>> getElementValueAsMapList(TaskElementValueAccessor taskElementValueAccessor) {
        List<TaskElementValue> taskElementValues = getElementValues(taskElementValueAccessor);

        return taskElementValues.stream().map(TaskElementValueUtils::getElementValueAsMap).collect(toList());
    }

    private static Map<String, Object> getElementValueAsMap(TaskElementValue elementValue) {
        if (!(elementValue instanceof TaskElementValueObject)) {
            throw new KuFlowRestClientException(String.format("elementValue %s is not an Object", elementValue));
        }

        TaskElementValueObject elementValueObject = (TaskElementValueObject) elementValue;

        if (elementValueObject.getValue() == null) {
            return null;
        }

        return elementValueObject.getValue();
    }

    public static Optional<TaskElementValueDocumentItem> findElementValueAsDocument(TaskElementValueAccessor taskElementValueAccessor) {
        Optional<TaskElementValue> taskElementValue = findElementValue(taskElementValueAccessor);

        return taskElementValue.map(TaskElementValueUtils::getElementValueAsDocument);
    }

    public static TaskElementValueDocumentItem getElementValueAsDocument(TaskElementValueAccessor taskElementValueAccessor) {
        return findElementValueAsDocument(taskElementValueAccessor)
            .orElseThrow(() -> new KuFlowRestClientException("Element value doesn't exist"));
    }

    public static List<TaskElementValueDocumentItem> getElementValueAsDocumentList(TaskElementValueAccessor taskElementValueAccessor) {
        List<TaskElementValue> taskElementValues = getElementValues(taskElementValueAccessor);

        return taskElementValues.stream().map(TaskElementValueUtils::getElementValueAsDocument).collect(toList());
    }

    private static TaskElementValueDocumentItem getElementValueAsDocument(TaskElementValue elementValue) {
        if (!(elementValue instanceof TaskElementValueDocument)) {
            throw new KuFlowRestClientException(String.format("elementValue %s is not a Document", elementValue));
        }

        TaskElementValueDocument elementValueDocument = (TaskElementValueDocument) elementValue;

        return elementValueDocument.getValue();
    }

    public static Optional<TaskElementValuePrincipalItem> findElementValueAsPrincipal(TaskElementValueAccessor taskElementValueAccessor) {
        Optional<TaskElementValue> taskElementValue = findElementValue(taskElementValueAccessor);

        return taskElementValue.map(TaskElementValueUtils::getElementValueAsPrincipal);
    }

    public static TaskElementValuePrincipalItem getElementValueAsPrincipal(TaskElementValueAccessor taskElementValueAccessor) {
        return findElementValueAsPrincipal(taskElementValueAccessor)
            .orElseThrow(() -> new KuFlowRestClientException("Element value doesn't exist"));
    }

    public static List<TaskElementValuePrincipalItem> getElementValueAsPrincipalList(TaskElementValueAccessor taskElementValueAccessor) {
        List<TaskElementValue> taskElementValues = getElementValues(taskElementValueAccessor);

        return taskElementValues.stream().map(TaskElementValueUtils::getElementValueAsPrincipal).collect(toList());
    }

    private static TaskElementValuePrincipalItem getElementValueAsPrincipal(TaskElementValue elementValue) {
        if (!(elementValue instanceof TaskElementValuePrincipal)) {
            throw new KuFlowRestClientException(String.format("elementValue %s is not a Principal", elementValue));
        }

        TaskElementValuePrincipal elementValuePrincipal = (TaskElementValuePrincipal) elementValue;

        return elementValuePrincipal.getValue();
    }

    private static Optional<TaskElementValue> findElementValue(TaskElementValueAccessor taskElementValueAccessor) {
        List<TaskElementValue> taskElementValues = taskElementValueAccessor.getElementValues();
        if (taskElementValues == null || taskElementValues.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(taskElementValues.get(0));
    }

    private static List<TaskElementValue> getElementValues(TaskElementValueAccessor taskElementValueAccessor) {
        List<TaskElementValue> taskElementValues = taskElementValueAccessor.getElementValues();
        if (taskElementValues == null || taskElementValues.isEmpty()) {
            return new ArrayList<>();
        }

        return taskElementValues;
    }
}
