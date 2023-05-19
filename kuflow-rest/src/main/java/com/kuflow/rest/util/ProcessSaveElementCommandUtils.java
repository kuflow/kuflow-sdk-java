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

import static com.kuflow.rest.util.ProcessElementValueAccessorProcessSaveElementCommand.of;

import com.kuflow.rest.model.ProcessSaveElementCommand;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Utility class for {@link ProcessSaveElementCommand}
 */
public final class ProcessSaveElementCommandUtils {

    private ProcessSaveElementCommandUtils() {
        throw new RuntimeException("Utility class");
    }

    /**
     * Check if all related valid values are TRUE
     *
     * @return TRUE if all related valid values are TRUE else FALSE.
     */
    public static Boolean getElementValueValid(ProcessSaveElementCommand command) {
        return ProcessElementValueUtils.getElementValueValid(of(command));
    }

    /**
     * Check if all related valid values are TRUE
     *

     * @param index Element value index
     * @return The requested valid value
     */
    public static Boolean getElementValueValidAt(ProcessSaveElementCommand command, int index) {
        return ProcessElementValueUtils.getElementValueValidAt(of(command), index);
    }

    /**
     * Set valid to all values
     *

     * @param valid Valid value

     */
    public static void setElementValueValid(ProcessSaveElementCommand command, Boolean valid) {
        ProcessElementValueUtils.setElementValueValid(of(command), valid);
    }

    /**
     * Set valid to the selected value
     *

     * @param valid Valid value
     * @param index Element value index

     */
    public static void setElementValueValidAt(ProcessSaveElementCommand command, Boolean valid, int index) {
        ProcessElementValueUtils.setElementValueValidAt(of(command), valid, index);
    }

    /**
     * Set an element value
     *

     * @param elementValue Element value, if the value is null all current values are removed

     */
    public static void setElementValueAsString(ProcessSaveElementCommand command, String elementValue) {
        ProcessElementValueUtils.setElementValue(of(command), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *

     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsStringList(ProcessSaveElementCommand command, List<String> elementValues) {
        ProcessElementValueUtils.setElementValues(of(command), elementValues);
    }

    /**
     * Add a new element value
     *

     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsString(ProcessSaveElementCommand command, String elementValue) {
        ProcessElementValueUtils.addElementValue(of(command), elementValue);
    }

    /**
     * Add all element values passed
     *

     * @param elementValues Element values
     */
    public static void addElementValueAsStringList(ProcessSaveElementCommand command, List<String> elementValues) {
        ProcessElementValueUtils.addElementValues(of(command), elementValues);
    }

    /**
     * Get an element as String
     *

     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static String getElementValueAsString(ProcessSaveElementCommand command) {
        return ProcessElementValueUtils.getElementValueAsString(of(command));
    }

    /**
     * Get all elements as String
     *

     * @return the elements values.
     */
    public static List<String> getElementValueAsStringList(ProcessSaveElementCommand command) {
        return ProcessElementValueUtils.getElementValueAsStringList(of(command));
    }

    /**
     * Try to get an element as String
     *

     * @return the element value if exists.
     */
    public static Optional<String> findElementValueAsString(ProcessSaveElementCommand command) {
        return ProcessElementValueUtils.findElementValueAsString(of(command));
    }

    /**
     * Set an element value
     *

     * @param elementValue Element value, if the value is null all current values are removed
     */
    public static void setElementValueAsDouble(ProcessSaveElementCommand command, Double elementValue) {
        ProcessElementValueUtils.setElementValue(of(command), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *

     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsDoubleList(ProcessSaveElementCommand command, List<Double> elementValues) {
        ProcessElementValueUtils.setElementValues(of(command), elementValues);
    }

    /**
     * Add a new element value
     *

     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsDouble(ProcessSaveElementCommand command, Double elementValue) {
        ProcessElementValueUtils.addElementValue(of(command), elementValue);
    }

    /**
     * Add all element values passed
     *

     * @param elementValues Element values
     */
    public static void addElementValueAsDoubleList(ProcessSaveElementCommand command, List<Double> elementValues) {
        ProcessElementValueUtils.addElementValues(of(command), elementValues);
    }

    /**
     * Get an element as Double
     *

     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static Double getElementValueAsDouble(ProcessSaveElementCommand command) {
        return ProcessElementValueUtils.getElementValueAsDouble(of(command));
    }

    /**
     * Get all elements as Double
     *

     * @return the elements values.
     */
    public static List<Double> getElementValueAsDoubleList(ProcessSaveElementCommand command) {
        return ProcessElementValueUtils.getElementValueAsDoubleList(of(command));
    }

    /**
     * Try to get an element as Double
     *

     * @return the element value if exists.
     */
    public static Optional<Double> findElementValueAsDouble(ProcessSaveElementCommand command) {
        return ProcessElementValueUtils.findElementValueAsDouble(of(command));
    }

    /**
     * Set an element value
     *

     * @param elementValue Element value, if the value is null all current values are removed
     */
    public static void setElementValueAsLocalDate(ProcessSaveElementCommand command, LocalDate elementValue) {
        ProcessElementValueUtils.setElementValue(of(command), elementValue);
    }

    /**
     * Set all element values passed, previews values will be removed
     *

     * @param elementValues Element values, if the values are null all current values are removed
     */
    public static void setElementValueAsLocalDateList(ProcessSaveElementCommand command, List<LocalDate> elementValues) {
        ProcessElementValueUtils.setElementValues(of(command), elementValues);
    }

    /**
     * Add a new element value
     *

     * @param elementValue Element value, if the values is null the value is not added
     */
    public static void addElementValueAsLocalDate(ProcessSaveElementCommand command, LocalDate elementValue) {
        ProcessElementValueUtils.addElementValue(of(command), elementValue);
    }

    /**
     * Add all element values passed
     *

     * @param elementValues Element values
     */
    public static void addElementValueAsLocalDateList(ProcessSaveElementCommand command, List<LocalDate> elementValues) {
        ProcessElementValueUtils.addElementValues(of(command), elementValues);
    }

    /**
     * Get an element as LocalDate
     *

     * @return the element value.
     * @throws com.kuflow.rest.KuFlowRestClientException If element value doesn't exist
     */
    public static LocalDate getElementValueAsLocalDate(ProcessSaveElementCommand command) {
        return ProcessElementValueUtils.getElementValueAsLocalDate(of(command));
    }

    /**
     * Get all elements as LocalDate
     *

     * @return the elements values.
     */
    public static List<LocalDate> getElementValueAsLocalDateList(ProcessSaveElementCommand command) {
        return ProcessElementValueUtils.getElementValueAsLocalDateList(of(command));
    }

    /**
     * Try to get an element as LocalDate
     *

     * @return the element value if exists.
     */
    public static Optional<LocalDate> findElementValueAsLocalDate(ProcessSaveElementCommand command) {
        return ProcessElementValueUtils.findElementValueAsLocalDate(of(command));
    }
}
