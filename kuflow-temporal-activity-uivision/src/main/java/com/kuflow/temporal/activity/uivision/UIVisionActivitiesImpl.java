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
package com.kuflow.temporal.activity.uivision;

import com.kuflow.temporal.activity.uivision.dto.UIVisionArgumentDto;
import com.kuflow.temporal.activity.uivision.model.ExecuteUIVisionMacroRequest;
import com.kuflow.temporal.activity.uivision.model.ExecuteUIVisionMacroResponse;
import com.kuflow.temporal.activity.uivision.service.UIVisionService;

public class UIVisionActivitiesImpl implements UIVisionActivities {

    private final UIVisionService uiVisionService;

    private final UIVisionActivityConfiguration uiVisionActivityConfiguration;

    public UIVisionActivitiesImpl(UIVisionService uiVisionService, UIVisionActivityConfiguration uiVisionActivityConfiguration) {
        this.uiVisionService = uiVisionService;
        this.uiVisionActivityConfiguration = uiVisionActivityConfiguration;
    }

    @Override
    public ExecuteUIVisionMacroResponse executeUIVisionMacro(ExecuteUIVisionMacroRequest request) {
        UIVisionArgumentDto arguments = UIVisionArgumentDto.newBuilder()
            .setAutoRunHtml(this.uiVisionActivityConfiguration.getAutoRunHtml())
            .setMacro(this.uiVisionActivityConfiguration.getMacro())
            .setLogDirectory(this.uiVisionActivityConfiguration.getLogDirectory())
            .setCloseBrowser(String.valueOf(this.uiVisionActivityConfiguration.getCloseBrowser()))
            .setCloseRpa(String.valueOf(this.uiVisionActivityConfiguration.getCloseRpa()))
            .setCmdVar1(request.getProcessItemId().toString())
            .setCmdVar2(request.getExtraCommandVariable2())
            .setCmdVar3(request.getExtraCommandVariable3())
            .build();

        this.uiVisionService.runMacro(
                this.uiVisionActivityConfiguration.getLaunchCommand(),
                arguments,
                this.uiVisionActivityConfiguration.getExecutionTimeout()
            );

        ExecuteUIVisionMacroResponse response = new ExecuteUIVisionMacroResponse();

        return response;
    }

    public static final class UIVisionActivitiesImplBuilder {

        private UIVisionService uIVisionService;

        private UIVisionActivityConfiguration uIVisionActivityConfiguration;

        public UIVisionActivitiesImplBuilder(UIVisionService uIVisionService, UIVisionActivityConfiguration uIVisionActivityConfiguration) {
            this.uIVisionService = uIVisionService;
            this.uIVisionActivityConfiguration = uIVisionActivityConfiguration;
        }

        public UIVisionActivitiesImplBuilder withUIVisionService(UIVisionService uIVisionService) {
            this.uIVisionService = uIVisionService;
            return this;
        }

        public UIVisionActivitiesImplBuilder withUIVisionActivityConfiguration(
            UIVisionActivityConfiguration uIVisionActivityConfiguration
        ) {
            this.uIVisionActivityConfiguration = uIVisionActivityConfiguration;
            return this;
        }

        public UIVisionActivitiesImplBuilder build() {
            return new UIVisionActivitiesImplBuilder(this.uIVisionService, this.uIVisionActivityConfiguration);
        }
    }
}
