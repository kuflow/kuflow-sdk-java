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
package com.kuflow.temporal.activity.uivision.dto;

import java.util.Objects;

public class UIVisionArgumentDto {

    public static Builder newBuilder() {
        return new Builder();
    }

    private String kuFlowRestApiEndpoint;

    private String kuFlowApplicationIdentifier;

    private String kuFlowApplicationToken;

    private String kuFlowTaskId;

    private String uiVisionAutoRunHtml;

    private String uiVisionMacro;

    private String uiVisionLogDirectory;

    private String uiVisionCloseBrowser = "1";

    private String uiVisionCloseRPA = "1";

    private String uiVisionCmdVar1;

    private String uiVisionCmdVar2;

    private String uiVisionCmdVar3;

    public String getKuFlowRestApiEndpoint() {
        return this.kuFlowRestApiEndpoint;
    }

    public String getKuFlowApplicationIdentifier() {
        return this.kuFlowApplicationIdentifier;
    }

    public String getKuFlowApplicationToken() {
        return this.kuFlowApplicationToken;
    }

    public String getKuFlowTaskId() {
        return this.kuFlowTaskId;
    }

    public String getUiVisionAutoRunHtml() {
        return this.uiVisionAutoRunHtml;
    }

    public String getUiVisionMacro() {
        return this.uiVisionMacro;
    }

    public String getUiVisionLogDirectory() {
        return this.uiVisionLogDirectory;
    }

    public String getUiVisionCloseBrowser() {
        return this.uiVisionCloseBrowser;
    }

    public String getUiVisionCloseRPA() {
        return this.uiVisionCloseRPA;
    }

    public String getUiVisionCmdVar1() {
        return this.uiVisionCmdVar1;
    }

    public String getUiVisionCmdVar2() {
        return this.uiVisionCmdVar2;
    }

    public String getUiVisionCmdVar3() {
        return this.uiVisionCmdVar3;
    }

    public UIVisionArgumentDto(
        String kuFlowRestApiEndpoint,
        String kuFlowApplicationIdentifier,
        String kuFlowApplicationToken,
        String kuFlowTaskId,
        String uiVisionAutoRunHtml,
        String uiVisionMacro,
        String uiVisionLogDirectory,
        String uiVisionCloseBrowser,
        String uiVisionCloseRPA,
        String uiVisionCmdVar1,
        String uiVisionCmdVar2,
        String uiVisionCmdVar3
    ) {
        Objects.requireNonNull(kuFlowRestApiEndpoint, "kuFlowRestApiEndpoint is required");
        Objects.requireNonNull(kuFlowApplicationIdentifier, "kuFlowApplicationIdentifier is required");
        Objects.requireNonNull(kuFlowApplicationToken, "kuFlowApplicationToken is required");
        Objects.requireNonNull(kuFlowTaskId, "kuFlowTaskId is required");
        Objects.requireNonNull(uiVisionAutoRunHtml, "uiVisionAutoRunHtml is required");
        Objects.requireNonNull(uiVisionMacro, "uiVisionMacro is required");
        Objects.requireNonNull(uiVisionLogDirectory, "uiVisionLogDirectory is required");

        this.kuFlowRestApiEndpoint = kuFlowRestApiEndpoint;
        this.kuFlowApplicationIdentifier = kuFlowApplicationIdentifier;
        this.kuFlowApplicationToken = kuFlowApplicationToken;
        this.kuFlowTaskId = kuFlowTaskId;
        this.uiVisionAutoRunHtml = uiVisionAutoRunHtml;
        this.uiVisionMacro = uiVisionMacro;
        this.uiVisionLogDirectory = uiVisionLogDirectory;

        this.uiVisionCloseBrowser = uiVisionCloseBrowser;
        this.uiVisionCloseRPA = uiVisionCloseRPA;
        this.uiVisionCmdVar1 = uiVisionCmdVar1;
        this.uiVisionCmdVar2 = uiVisionCmdVar2;
        this.uiVisionCmdVar3 = uiVisionCmdVar3;
    }

    public static class Builder {

        private String kuFlowRestApiEndpoint;

        private String kuFlowApplicationIdentifier;

        private String kuFlowApplicationToken;

        private String kuFlowTaskId;

        private String uiVisionAutoRunHtml;

        private String uiVisionMacro;

        private String uiVisionLogDirectory;

        private String uiVisionCloseBrowser;

        private String uiVisionCloseRPA;

        private String uiVisionCmdVar1;

        private String uiVisionCmdVar2;

        private String uiVisionCmdVar3;

        public Builder setKuFlowRestApiEndpoint(String kuFlowRestApiEndpoint) {
            this.kuFlowRestApiEndpoint = kuFlowRestApiEndpoint;
            return this;
        }

        public Builder setKuFlowApplicationIdentifier(String kuFlowApplicationIdentifier) {
            this.kuFlowApplicationIdentifier = kuFlowApplicationIdentifier;
            return this;
        }

        public Builder setKuFlowApplicationToken(String kuFlowApplicationToken) {
            this.kuFlowApplicationToken = kuFlowApplicationToken;
            return this;
        }

        public Builder setKuFlowTaskId(String kuFlowTaskId) {
            this.kuFlowTaskId = kuFlowTaskId;
            return this;
        }

        public Builder setUiVisionAutoRunHtml(String uiVisionAutoRunHtml) {
            this.uiVisionAutoRunHtml = uiVisionAutoRunHtml;
            return this;
        }

        public Builder setUiVisionMacro(String uiVisionMacro) {
            this.uiVisionMacro = uiVisionMacro;
            return this;
        }

        public Builder setUiVisionLogDirectory(String uiVisionlogDirectory) {
            this.uiVisionLogDirectory = uiVisionlogDirectory;
            return this;
        }

        public Builder setUiVisionCloseBrowser(String uiVisionCloseBrowser) {
            this.uiVisionCloseBrowser = uiVisionCloseBrowser;
            return this;
        }

        public Builder setUiVisionCloseRPA(String uiVisionCloseRPA) {
            this.uiVisionCloseRPA = uiVisionCloseRPA;
            return this;
        }

        public Builder setUiVisionCmdVar1(String uiVisionCmdVar1) {
            this.uiVisionCmdVar1 = uiVisionCmdVar1;
            return this;
        }

        public Builder setUiVisionCmdVar2(String uiVisionCmdVar2) {
            this.uiVisionCmdVar2 = uiVisionCmdVar2;
            return this;
        }

        public Builder setUiVisionCmdVar3(String uiVisionCmdVar3) {
            this.uiVisionCmdVar3 = uiVisionCmdVar3;
            return this;
        }

        public UIVisionArgumentDto build() {
            return new UIVisionArgumentDto(
                this.kuFlowRestApiEndpoint,
                this.kuFlowApplicationIdentifier,
                this.kuFlowApplicationToken,
                this.kuFlowTaskId,
                this.uiVisionAutoRunHtml,
                this.uiVisionMacro,
                this.uiVisionLogDirectory,
                this.uiVisionCloseBrowser,
                this.uiVisionCloseRPA,
                this.uiVisionCmdVar1,
                this.uiVisionCmdVar2,
                this.uiVisionCmdVar3
            );
        }
    }
}
