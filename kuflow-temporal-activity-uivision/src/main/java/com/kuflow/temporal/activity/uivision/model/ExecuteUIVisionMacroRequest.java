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
package com.kuflow.temporal.activity.uivision.model;

import com.kuflow.temporal.common.model.AbstractModel;
import java.util.UUID;

public class ExecuteUIVisionMacroRequest extends AbstractModel {

    private UUID taskId;

    /**
     * UI.Vision Internal Variable #2
     * (The internal variable #1 is reserved to pass KuFlow taskId)
     * See https://ui.vision/rpa/docs#cmd-src
     */
    private String extraCommandVariable2;

    /**
     * UI.Vision Internal Variable #3
     * (The internal variable #1 is reserved to pass KuFlow taskId)
     * See https://ui.vision/rpa/docs#cmd-src
     */
    private String extraCommandVariable3;

    public UUID getTaskId() {
        return this.taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public String getExtraCommandVariable2() {
        return this.extraCommandVariable2;
    }

    public void setExtraCommandVariable2(String extraCommandVariable2) {
        this.extraCommandVariable2 = extraCommandVariable2;
    }

    public String getExtraCommandVariable3() {
        return this.extraCommandVariable3;
    }

    public void setExtraCommandVariable3(String extraCommandVariable3) {
        this.extraCommandVariable3 = extraCommandVariable3;
    }
}
