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
package com.kuflow.temporal.common.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.kuflow.temporal.common.error.KuFlowTemporalException;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TemporalUtilsTest {

    @Test
    @DisplayName("GIVEN @ActivityInterface and @ActivityMethod WHEN getActivityType THEN the activity type start with a capital letter")
    public void givenActivityInterfaceAndActivityMethodWhenGetActivityTypeThenTheActivityTypeStartWithACapitalLetter() {
        String activityType1 = TemporalUtils.getActivityType(ActivityA.class, "method1");
        String activityType2 = TemporalUtils.getActivityType(ActivityA.class, "method2");
        assertThat(activityType1).isEqualTo("Method1");
        assertThat(activityType2).isEqualTo("Method2");
    }

    @Test
    @DisplayName("GIVEN @ActivityInterface with prefix and @ActivityMethod WHEN getActivityType THEN the prefix is used")
    public void givenActivityInterfaceWithPrefixAndActivityMethodWhenGetActivityTypeThenThePrefixIsUsed() {
        String activityType1 = TemporalUtils.getActivityType(ActivityB.class, "method1");
        String activityType2 = TemporalUtils.getActivityType(ActivityB.class, "method2");
        assertThat(activityType1).isEqualTo("Prefix_Method1");
        assertThat(activityType2).isEqualTo("Prefix_Method2");
    }

    @Test
    @DisplayName("GIVEN @ActivityInterface with prefix and @ActivityMethod with name WHEN getActivityType THEN only the name is is used")
    public void givenActivityInterfaceWithPrefixAndActivityMethodWithNameWhenGetActivityTypeThenOnlyTheNameIsIsUsed() {
        String activityType = TemporalUtils.getActivityType(ActivityC.class, "method1");
        assertThat(activityType).isEqualTo("activityName");
    }

    @Test
    @DisplayName("GIVEN @ActivityInterface and @ActivityMethod and a non existing method name WHEN getActivityType THEN error is through")
    public void givenActivityInterfaceAndActivityMethodAndANonExistingMethodNameWhenGetActivityTypeThenErrorIsThrough() {
        Throwable thrown = catchThrowable(() -> TemporalUtils.getActivityType(ActivityA.class, "method999"));

        assertThat(thrown).isInstanceOf(KuFlowTemporalException.class);
    }

    @ActivityInterface
    public interface ActivityA {
        @ActivityMethod
        void method1();

        @ActivityMethod
        void method2();
    }

    @ActivityInterface(namePrefix = "Prefix_")
    public interface ActivityB {
        @ActivityMethod
        void method1();

        @ActivityMethod
        void method2();
    }

    @ActivityInterface(namePrefix = "Prefix_")
    public interface ActivityC {
        @ActivityMethod(name = "activityName")
        void method1();
    }
}
