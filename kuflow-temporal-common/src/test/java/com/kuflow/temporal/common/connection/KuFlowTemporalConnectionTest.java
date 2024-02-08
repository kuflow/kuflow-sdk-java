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
package com.kuflow.temporal.common.connection;

import static org.assertj.core.api.Assertions.assertThat;

import com.kuflow.rest.KuFlowRestClient;
import io.temporal.activity.ActivityInterface;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class KuFlowTemporalConnectionTest {

    @Mock
    private KuFlowRestClient kuFlowRestClient;

    @Test
    @DisplayName("GIVEN KuFlowTemporalConnection WHEN getWorkflowTypes or getActivityTypes THEN get all the register types")
    public void givenKuFlowTemporalConnectionWhenGetWorkflowTypesOrGetActivityTypesThenGetAllTheRegisterTypes() {
        KuFlowTemporalConnection kuFlowTemporalConnection = KuFlowTemporalConnection
            .instance(this.kuFlowRestClient)
            .configureWorkflowServiceStubs(builder -> {
                // do nothing
            })
            .configureWorkflowClient(builder -> {
                // do nothing
            })
            .configureWorker(builder ->
                builder
                    .withTaskQueue("TASK_QUEUE")
                    .withWorkflowImplementationTypes(Test1WorkflowImpl.class)
                    .withWorkflowImplementationTypes(Test2WorkflowImpl.class)
                    .withActivitiesImplementations(new Test1ActivitiesImpl())
                    .withActivitiesImplementations(new Test2ActivitiesImpl())
            );

        assertThat(kuFlowTemporalConnection.getWorkerInformation()).isNotNull();

        WorkerInformation workerInformation = kuFlowTemporalConnection.getWorkerInformation();
        assertThat(workerInformation.getWorkflowTypes()).containsOnly("Test1Workflow", "Test2Workflow_name");
        assertThat(workerInformation.getActivityTypes()).containsOnly("Test1_Activity1", "Test1_Activity2", "Activity1", "Activity2");
    }

    @WorkflowInterface
    public interface Test1Workflow {
        @WorkflowMethod
        String runWorkflow(String request);
    }

    public static class Test1WorkflowImpl implements Test1Workflow {

        @Override
        public String runWorkflow(String request) {
            return "echo";
        }
    }

    @WorkflowInterface
    public interface Test2Workflow {
        @WorkflowMethod(name = "Test2Workflow_name")
        String runWorkflow(String request);
    }

    public static class Test2WorkflowImpl implements Test2Workflow {

        @Override
        public String runWorkflow(String request) {
            return "echo";
        }
    }

    @ActivityInterface(namePrefix = "Test1_")
    public interface Test1Activities {
        String activity1(String input);
        String activity2(String input);
    }

    public static class Test1ActivitiesImpl implements Test1Activities {

        @Override
        public String activity1(String input) {
            return "echo";
        }

        @Override
        public String activity2(String input) {
            return "echo";
        }
    }

    @ActivityInterface
    public interface Test2Activities {
        String activity1(String input);
        String activity2(String input);
    }

    public static class Test2ActivitiesImpl implements Test2Activities {

        @Override
        public String activity1(String input) {
            return "echo";
        }

        @Override
        public String activity2(String input) {
            return "echo";
        }
    }
}
