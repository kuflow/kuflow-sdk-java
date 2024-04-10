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
import com.kuflow.rest.model.ProcessElementValue;
import com.kuflow.rest.model.ProcessElementValueNumber;
import com.kuflow.rest.model.ProcessElementValueString;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Utility class for {@link ProcessElementValue}
 */
public final class ProcessElementValueUtils {

    private ProcessElementValueUtils() {
        throw new RuntimeException("Utility class");
    }

    public static Boolean getElementValueValid(ProcessElementValueAccessor processElementValueAccessor) {
        List<ProcessElementValue> elementValues = getElementValues(processElementValueAccessor);
        if (elementValues.isEmpty()) {
            return null;
        }

        return elementValues.stream().filter(v -> Boolean.FALSE.equals(v.isValid())).findAny().isEmpty();
    }

    public static Boolean getElementValueValidAt(ProcessElementValueAccessor processElementValueAccessor, int index) {
        List<ProcessElementValue> elementValues = getElementValues(processElementValueAccessor);

        return elementValues.get(index).isValid();
    }

    public static void setElementValueValid(ProcessElementValueAccessor processElementValueAccessor, Boolean valid) {
        List<ProcessElementValue> elementValues = getElementValues(processElementValueAccessor);
        elementValues.forEach(it -> it.setValid(valid));
    }

    public static void setElementValueValidAt(ProcessElementValueAccessor processElementValueAccessor, Boolean valid, int index) {
        List<ProcessElementValue> elementValues = getElementValues(processElementValueAccessor);
        elementValues.get(index).setValid(valid);
    }

    public static void addElementValue(ProcessElementValueAccessor processElementValueAccessor, Object elementValue) {
        List<Object> elementValueList = new LinkedList<>();
        if (elementValue != null) {
            elementValueList.add(elementValue);
        }

        addElementValues(processElementValueAccessor, elementValueList);
    }

    public static void addElementValues(ProcessElementValueAccessor processElementValueAccessor, List<?> elementValues) {
        List<Object> elementValueList = new LinkedList<>();
        if (elementValues != null) {
            elementValueList.addAll(elementValues);
        }

        List<ProcessElementValue> processElementValues = elementValueList
            .stream()
            .map(ProcessElementValueUtils::toProcessElementValue)
            .collect(toList());

        List<ProcessElementValue> taskElementValueList = processElementValueAccessor.getElementValues();

        if (taskElementValueList == null) {
            taskElementValueList = new LinkedList<>();
        }
        taskElementValueList.addAll(processElementValues);

        processElementValueAccessor.setElementValues(taskElementValueList);
    }

    public static void setElementValue(ProcessElementValueAccessor processElementValueAccessor, Object elementValue) {
        List<Object> valueList = new LinkedList<>();
        if (elementValue != null) {
            valueList.add(elementValue);
        }

        setElementValues(processElementValueAccessor, valueList);
    }

    public static void setElementValues(ProcessElementValueAccessor processElementValueAccessor, List<?> elementValues) {
        if (elementValues == null) {
            elementValues = new LinkedList<>();
        }

        List<ProcessElementValue> processElementValues = elementValues
            .stream()
            .map(ProcessElementValueUtils::toProcessElementValue)
            .collect(toList());

        processElementValueAccessor.setElementValues(processElementValues);
    }

    private static ProcessElementValue toProcessElementValue(Object value) {
        if (value instanceof String) {
            return toElementValueResourceString((String) value);
        } else if (value instanceof Double) {
            return toElementValueResourceNumber((Double) value);
        } else if (value instanceof LocalDate) {
            return toElementValueResourceString(value.toString());
        } else {
            throw new IllegalArgumentException(String.format("Unknown type %s", value.getClass().getName()));
        }
    }

    private static ProcessElementValueString toElementValueResourceString(String value) {
        return new ProcessElementValueString().setValue(value);
    }

    private static ProcessElementValueNumber toElementValueResourceNumber(Double value) {
        return new ProcessElementValueNumber().setValue(value);
    }

    public static String getElementValueAsString(ProcessElementValueAccessor processElementValueAccessor) {
        return findElementValueAsString(processElementValueAccessor).orElseThrow(
            () -> new KuFlowRestClientException("Element value doesn't exist")
        );
    }

    public static Optional<String> findElementValueAsString(ProcessElementValueAccessor processElementValueAccessor) {
        Optional<ProcessElementValue> taskElementValue = findElementValue(processElementValueAccessor);

        return taskElementValue.map(ProcessElementValueUtils::getElementValueAsString);
    }

    public static List<String> getElementValueAsStringList(ProcessElementValueAccessor processElementValueAccessor) {
        List<ProcessElementValue> processElementValues = getElementValues(processElementValueAccessor);

        return processElementValues.stream().map(ProcessElementValueUtils::getElementValueAsString).collect(toList());
    }

    private static String getElementValueAsString(ProcessElementValue elementValue) {
        if (elementValue instanceof ProcessElementValueString) {
            ProcessElementValueString valueString = (ProcessElementValueString) elementValue;

            return valueString.getValue();
        }

        if (elementValue instanceof ProcessElementValueNumber) {
            ProcessElementValueNumber valueNumber = (ProcessElementValueNumber) elementValue;

            return valueNumber.getValue() != null ? valueNumber.getValue().toString() : null;
        }

        throw new KuFlowRestClientException(String.format("elementValue %s is not a String", elementValue));
    }

    public static Optional<Double> findElementValueAsDouble(ProcessElementValueAccessor processElementValueAccessor) {
        Optional<ProcessElementValue> taskElementValue = findElementValue(processElementValueAccessor);

        return taskElementValue.map(ProcessElementValueUtils::getElementValueAsDouble);
    }

    public static Double getElementValueAsDouble(ProcessElementValueAccessor processElementValueAccessor) {
        return findElementValueAsDouble(processElementValueAccessor).orElseThrow(
            () -> new KuFlowRestClientException("Element value doesn't exist")
        );
    }

    public static List<Double> getElementValueAsDoubleList(ProcessElementValueAccessor processElementValueAccessor) {
        List<ProcessElementValue> processElementValues = getElementValues(processElementValueAccessor);

        return processElementValues.stream().map(ProcessElementValueUtils::getElementValueAsDouble).collect(toList());
    }

    private static Double getElementValueAsDouble(ProcessElementValue elementValue) {
        if (elementValue instanceof ProcessElementValueNumber) {
            ProcessElementValueNumber valueNumber = (ProcessElementValueNumber) elementValue;

            return valueNumber.getValue();
        }

        if (elementValue instanceof ProcessElementValueString) {
            ProcessElementValueString valueString = (ProcessElementValueString) elementValue;

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

    public static Optional<LocalDate> findElementValueAsLocalDate(ProcessElementValueAccessor processElementValueAccessor) {
        Optional<ProcessElementValue> taskElementValue = findElementValue(processElementValueAccessor);

        return taskElementValue.map(ProcessElementValueUtils::getElementValueAsLocalDate);
    }

    public static LocalDate getElementValueAsLocalDate(ProcessElementValueAccessor processElementValueAccessor) {
        return findElementValueAsLocalDate(processElementValueAccessor).orElseThrow(
            () -> new KuFlowRestClientException("Element value doesn't exist")
        );
    }

    public static List<LocalDate> getElementValueAsLocalDateList(ProcessElementValueAccessor processElementValueAccessor) {
        List<ProcessElementValue> processElementValues = getElementValues(processElementValueAccessor);

        return processElementValues.stream().map(ProcessElementValueUtils::getElementValueAsLocalDate).collect(toList());
    }

    private static LocalDate getElementValueAsLocalDate(ProcessElementValue elementValue) {
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

    private static Optional<ProcessElementValue> findElementValue(ProcessElementValueAccessor processElementValueAccessor) {
        List<ProcessElementValue> processElementValues = processElementValueAccessor.getElementValues();
        if (processElementValues == null || processElementValues.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(processElementValues.get(0));
    }

    private static List<ProcessElementValue> getElementValues(ProcessElementValueAccessor processElementValueAccessor) {
        List<ProcessElementValue> processElementValues = processElementValueAccessor.getElementValues();
        if (processElementValues == null || processElementValues.isEmpty()) {
            return new ArrayList<>();
        }

        return processElementValues;
    }
}
