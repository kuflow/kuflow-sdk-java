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

import java.time.Duration;
import java.util.Objects;

public class UIVisionActivityConfiguration {

    public static Builder newBuilder() {
        return new Builder();
    }

    private String launchCommand;

    private String autoRunHtml;

    private String macro;

    private String logDirectory;

    private Duration executionTimeout;

    private String closeBrowser = "1";

    private String closeRpa = "1";

    public UIVisionActivityConfiguration(
        String launchCommand,
        String autoRunHtml,
        String macro,
        String logDirectory,
        String closeBrowser,
        String closeRpa,
        Duration executionTimeout
    ) {
        Objects.requireNonNull(autoRunHtml, "launchCommand is required");
        Objects.requireNonNull(autoRunHtml, "autoRunHtml is required");
        Objects.requireNonNull(macro, "macro is required");
        Objects.requireNonNull(logDirectory, "logDirectory is required");
        Objects.requireNonNull(executionTimeout, "executionTimeout is required");

        this.launchCommand = launchCommand;
        this.autoRunHtml = autoRunHtml;
        this.macro = macro;
        this.logDirectory = logDirectory;
        this.executionTimeout = executionTimeout;

        this.closeBrowser = closeBrowser;
        this.closeRpa = closeRpa;
    }

    public String getLaunchCommand() {
        return this.launchCommand;
    }

    public String getAutoRunHtml() {
        return this.autoRunHtml;
    }

    public String getMacro() {
        return this.macro;
    }

    public String getLogDirectory() {
        return this.logDirectory;
    }

    public Duration getExecutionTimeout() {
        return this.executionTimeout;
    }

    public String getCloseBrowser() {
        return this.closeBrowser;
    }

    public String getCloseRpa() {
        return this.closeRpa;
    }

    public static class Builder {

        private String launchCommand;

        private String autoRunHtml;

        private String macro;

        private String logDirectory;

        private String uicloseBrowser;

        private String closeRpa;

        private Duration executionTimeout;

        public Builder withLaunchCommand(String launchCommand) {
            this.launchCommand = launchCommand;
            return this;
        }

        public Builder withUiVisionAutoRunHtml(String uiVisionAutoRunHtml) {
            this.autoRunHtml = uiVisionAutoRunHtml;
            return this;
        }

        public Builder withUiVisionMacro(String uiVisionMacro) {
            this.macro = uiVisionMacro;
            return this;
        }

        public Builder withUiVisionLogDirectory(String uiVisionlogDirectory) {
            this.logDirectory = uiVisionlogDirectory;
            return this;
        }

        public Builder withExecutionTimeout(Duration executionTimeout) {
            this.executionTimeout = executionTimeout;
            return this;
        }

        public Builder withUiVisionCloseBrowser(String uiVisionCloseBrowser) {
            this.uicloseBrowser = uiVisionCloseBrowser;
            return this;
        }

        public Builder withUiVisionCloseRPA(String uiVisionCloseRpa) {
            this.closeRpa = uiVisionCloseRpa;
            return this;
        }

        public UIVisionActivityConfiguration build() {
            return new UIVisionActivityConfiguration(
                this.launchCommand,
                this.autoRunHtml,
                this.macro,
                this.logDirectory,
                this.uicloseBrowser,
                this.closeRpa,
                this.executionTimeout
            );
        }
    }
}
