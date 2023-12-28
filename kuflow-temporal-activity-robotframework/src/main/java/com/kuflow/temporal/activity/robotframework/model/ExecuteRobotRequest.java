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
package com.kuflow.temporal.activity.robotframework.model;

import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableMap;

import com.kuflow.temporal.common.model.AbstractModel;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ExecuteRobotRequest extends AbstractModel {

    /**
     * Paths to test case files/directories to be executed similarly
     * as when running the <code>robot</code> command on the command line.
     * If the <code>KUFLOW_ROBOT_PATH</code> environment variable defined in the
     * <b>python activity worker</b> exists, Its content is added as a prefix to the value
     * of this field.
     * @see <a href="https://github.com/kuflow/kuflow-sdk-python/blob/main/kuflow-temporal-activity-robotframework/kuflow_temporal_activity_robotframework/robot_framework_activities.py">KuFlow Python Robot Framework</a>
     */
    private String tests;

    /**
     * Options to configure and control execution.
     * Take precedence over a possible <code>variable</code> key present in
     * {@link ExecuteRobotRequest#getOptions() options}.
     * Take precedence over <code>default_options</code> in python class <code>RobotFrameworkActivities</code>.
     * Values are merged.
     * @see <a href="https://github.com/kuflow/kuflow-sdk-python/blob/main/kuflow-temporal-activity-robotframework/kuflow_temporal_activity_robotframework/robot_framework_activities.py">KuFlow Python Robot Framework</a>
     * @see <a href="https://robot-framework.readthedocs.io/en/latest/_modules/robot/run.html">Robot Framework: robot.run</a>
     */
    private List<Object> variables = new LinkedList<>();

    /**
     * Options to configure and control execution.
     * Take precedence over <code>default_options</code> in python class <code>RobotFrameworkActivities</code>.
     * Values are merged.
     * @see <a href="https://github.com/kuflow/kuflow-sdk-python/blob/main/kuflow-temporal-activity-robotframework/kuflow_temporal_activity_robotframework/robot_framework_activities.py">KuFlow Python Robot Framework</a>
     * @see <a href="https://robot-framework.readthedocs.io/en/latest/_modules/robot/run.html">Robot Framework: robot.run</a>
     */
    private Map<String, Object> options = new HashMap<>();

    /**
     * See {@link ExecuteRobotRequest#tests}
     * @return test property
     */
    public String getTests() {
        return this.tests;
    }

    /**
     * @param tests See {@link ExecuteRobotRequest#tests}
     */
    public void setTests(String tests) {
        this.tests = tests;
    }

    /**
     * See {@link ExecuteRobotRequest#variables}
     * @return variables properties
     */
    public List<Object> getVariables() {
        return unmodifiableList(this.variables);
    }

    /**
     * @param variables See {@link ExecuteRobotRequest#variables}
     */
    public void setVariables(List<Object> variables) {
        this.variables.clear();
        if (this.variables != null) {
            this.variables.addAll(variables);
        }
    }

    /**
     * See {@link ExecuteRobotRequest#options}
     * @return option properties
     */
    public Map<String, Object> getOptions() {
        return unmodifiableMap(this.options);
    }

    /**
     * @param options See {@link ExecuteRobotRequest#options}
     */
    public void setOptions(Map<String, Object> options) {
        this.options.clear();
        if (this.options != null) {
            this.options.putAll(options);
        }
    }
}
