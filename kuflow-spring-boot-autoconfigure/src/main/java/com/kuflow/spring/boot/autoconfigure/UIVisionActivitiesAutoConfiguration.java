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

import com.kuflow.spring.boot.autoconfigure.properties.UIVisionProperties;
import com.kuflow.temporal.activity.uivision.UIVisionActivities;
import com.kuflow.temporal.activity.uivision.UIVisionActivitiesImpl;
import com.kuflow.temporal.activity.uivision.UIVisionActivityConfiguration;
import com.kuflow.temporal.activity.uivision.service.UIVisionService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnClass(UIVisionActivitiesImpl.class)
@EnableConfigurationProperties(UIVisionProperties.class)
public class UIVisionActivitiesAutoConfiguration {

    @Bean
    public UIVisionService uIVisionService() {
        return new UIVisionService();
    }

    @Bean
    public UIVisionActivities uIVisionActivities(UIVisionService uIVisionService, UIVisionProperties uIVisionProperties) {
        UIVisionActivityConfiguration uIVisionActivityConfiguration = UIVisionActivityConfiguration.newBuilder()
            .withLaunchCommand(uIVisionProperties.getLaunchCommand())
            .withUiVisionAutoRunHtml(uIVisionProperties.getAutoRunHtml())
            .withUiVisionMacro(uIVisionProperties.getMacro())
            .withUiVisionLogDirectory(uIVisionProperties.getLogDirectory())
            .withExecutionTimeout(uIVisionProperties.getExecutionTimeout())
            .withUiVisionCloseBrowser(String.valueOf(uIVisionProperties.getCloseBrowserAsInt()))
            .withUiVisionCloseRPA(String.valueOf(uIVisionProperties.getCloseRpaAsInt()))
            .build();

        return new UIVisionActivitiesImpl(uIVisionService, uIVisionActivityConfiguration);
    }
}
