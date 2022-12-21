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

    private String autoRunHtml;

    private String macro;

    private String logDirectory;

    private String closeBrowser = "1";

    private String closeRpa = "1";

    private String cmdVar1;

    private String cmdVar2;

    private String cmdVar3;

    public String getAutoRunHtml() {
        return this.autoRunHtml;
    }

    public String getMacro() {
        return this.macro;
    }

    public String getLogDirectory() {
        return this.logDirectory;
    }

    public String getCloseBrowser() {
        return this.closeBrowser;
    }

    public String getCloseRpa() {
        return this.closeRpa;
    }

    public String getCmdVar1() {
        return this.cmdVar1;
    }

    public String getCmdVar2() {
        return this.cmdVar2;
    }

    public String getCmdVar3() {
        return this.cmdVar3;
    }

    public UIVisionArgumentDto(
        String autoRunHtml,
        String macro,
        String logDirectory,
        String closeBrowser,
        String closeRpa,
        String cmdVar1,
        String cmdVar2,
        String cmdVar3
    ) {
        Objects.requireNonNull(autoRunHtml, "autoRunHtml is required");
        Objects.requireNonNull(macro, "macro is required");
        Objects.requireNonNull(logDirectory, "logDirectory is required");

        this.autoRunHtml = autoRunHtml;
        this.macro = macro;
        this.logDirectory = logDirectory;

        this.closeBrowser = closeBrowser;
        this.closeRpa = closeRpa;
        this.cmdVar1 = cmdVar1;
        this.cmdVar2 = cmdVar2;
        this.cmdVar3 = cmdVar3;
    }

    public static class Builder {

        private String autoRunHtml;

        private String macro;

        private String logDirectory;

        private String closeBrowser;

        private String closeRpa;

        private String cmdVar1;

        private String cmdVar2;

        private String cmdVar3;

        public Builder setAutoRunHtml(String autoRunHtml) {
            this.autoRunHtml = autoRunHtml;
            return this;
        }

        public Builder setMacro(String macro) {
            this.macro = macro;
            return this;
        }

        public Builder setLogDirectory(String logDirectory) {
            this.logDirectory = logDirectory;
            return this;
        }

        public Builder setCloseBrowser(String closeBrowser) {
            this.closeBrowser = closeBrowser;
            return this;
        }

        public Builder setCloseRpa(String closeRpa) {
            this.closeRpa = closeRpa;
            return this;
        }

        public Builder setCmdVar1(String cmdVar1) {
            this.cmdVar1 = cmdVar1;
            return this;
        }

        public Builder setCmdVar2(String cmdVar2) {
            this.cmdVar2 = cmdVar2;
            return this;
        }

        public Builder setCmdVar3(String cmdVar3) {
            this.cmdVar3 = cmdVar3;
            return this;
        }

        public UIVisionArgumentDto build() {
            return new UIVisionArgumentDto(
                this.autoRunHtml,
                this.macro,
                this.logDirectory,
                this.closeBrowser,
                this.closeRpa,
                this.cmdVar1,
                this.cmdVar2,
                this.cmdVar3
            );
        }
    }
}
