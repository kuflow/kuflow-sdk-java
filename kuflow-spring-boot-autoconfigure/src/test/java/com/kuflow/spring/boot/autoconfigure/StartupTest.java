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
package com.kuflow.spring.boot.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;

import com.kuflow.spring.boot.autoconfigure.properties.EmailActivitiesProperties;
import com.kuflow.temporal.activity.email.EmailActivities;
import com.kuflow.temporal.activity.kuflow.KuFlowActivities;
import com.kuflow.temporal.activity.s3.S3Activities;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StartupTest {

    @Autowired
    private EmailActivities emailActivities;

    @Autowired
    private EmailActivitiesProperties emailActivitiesProperties;

    @Autowired
    private S3Activities s3Activities;

    @Autowired
    private KuFlowActivities kuFlowActivities;

    @Test
    public void testThatEveryThingStartWell() {
        assertThat(this.emailActivities).isNotNull();

        Map<String, Map<String, Object>> expectedTemplateVariables = Map.of("template1", Map.of("var1", "value1"));
        assertThat(this.emailActivitiesProperties.getTemplateVariables()).containsExactlyEntriesOf(expectedTemplateVariables);

        assertThat(this.s3Activities).isNotNull();

        assertThat(this.kuFlowActivities).isNotNull();
    }
}
