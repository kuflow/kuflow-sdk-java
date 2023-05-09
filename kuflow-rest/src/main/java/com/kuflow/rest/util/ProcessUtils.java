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

import com.kuflow.rest.model.Process;
import java.time.LocalDate;
import java.util.List;
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
}
