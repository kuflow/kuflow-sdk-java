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

import static org.assertj.core.api.Assertions.assertThat;

import com.kuflow.rest.model.TaskElementValue;
import com.kuflow.rest.model.TaskElementValueString;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TaskElementValueUtilsTest {

    @Test
    public void when_addElementValuesInAUnmodifiableTaskElementValueAccesor_expect_newValuesAdded() {
        // Prepare mock
        TaskElementValueAccessorMock accessorMock = new TaskElementValueAccessorMock();
        List<TaskElementValue> existingValues = new LinkedList<>();
        {
            TaskElementValueString tValue = new TaskElementValueString();
            tValue.setValue("existing_value1");
            existingValues.add(tValue);
        }
        {
            TaskElementValueString tValue = new TaskElementValueString();
            tValue.setValue("existing_value2");
            existingValues.add(tValue);
        }
        accessorMock.setElementValues(Collections.unmodifiableList(existingValues));

        // Do
        List<String> elementValues = Arrays.asList("new_value1", "new_value2");
        TaskElementValueUtils.addElementValues(accessorMock, elementValues);

        // Assert
        List<TaskElementValue> result = accessorMock.getElementValues();

        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(4);
        assertThat(((TaskElementValueString) result.get(0)).getValue()).isEqualTo("existing_value1");
        assertThat(((TaskElementValueString) result.get(1)).getValue()).isEqualTo("existing_value2");
        assertThat(((TaskElementValueString) result.get(2)).getValue()).isEqualTo("new_value1");
        assertThat(((TaskElementValueString) result.get(3)).getValue()).isEqualTo("new_value2");
    }
}
