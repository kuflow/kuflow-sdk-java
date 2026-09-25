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

package com.kuflow.temporal.worker.connection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.kuflow.rest.KuFlowRestClient;
import com.kuflow.temporal.common.error.KuFlowTemporalException;
import com.kuflow.temporal.worker.encryption.interceptors.EncryptionWorkerInterceptor;
import io.temporal.activity.ActivityInterface;
import io.temporal.common.interceptors.WorkerInterceptor;
import io.temporal.common.interceptors.WorkerInterceptorBase;
import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkerFactoryOptions;
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
    @SuppressWarnings("deprecation")
    @DisplayName("GIVEN KuFlowTemporalConnection WHEN getWorkflowTypes or getActivityTypes THEN get all the register types")
    public void givenKuFlowTemporalConnectionWhenGetWorkflowTypesOrGetActivityTypesThenGetAllTheRegisterTypes() {
        KuFlowTemporalConnection kuFlowTemporalConnection = KuFlowTemporalConnection.instance(this.kuFlowRestClient)
            .configureWorkflowServiceStubs(builder -> {})
            .configureWorkflowClient(builder -> {})
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

    @Test
    @DisplayName("GIVEN workers with different task queues WHEN getWorkerInformation by task queue THEN get the types of each worker")
    public void givenWorkersWithDifferentTaskQueuesWhenGetWorkerInformationByTaskQueueThenGetTheTypesOfEachWorker() {
        KuFlowTemporalConnection kuFlowTemporalConnection = this.prepareKuFlowTemporalConnectionWithTwoWorkers();

        assertThat(kuFlowTemporalConnection.getWorkerInformationList())
            .extracting(WorkerInformation::getTaskQueue)
            .containsExactly("TASK_QUEUE_1", "TASK_QUEUE_2");

        WorkerInformation workerInformation1 = kuFlowTemporalConnection.getWorkerInformation("TASK_QUEUE_1");
        assertThat(workerInformation1.getWorkflowTypes()).containsOnly("Test1Workflow");
        assertThat(workerInformation1.getActivityTypes()).containsOnly("Test1_Activity1", "Test1_Activity2");

        WorkerInformation workerInformation2 = kuFlowTemporalConnection.getWorkerInformation("TASK_QUEUE_2");
        assertThat(workerInformation2.getWorkflowTypes()).containsOnly("Test2Workflow_name");
        assertThat(workerInformation2.getActivityTypes()).containsOnly("Activity1", "Activity2");

        assertThat(kuFlowTemporalConnection.getWorkerInformation("UNKNOWN_TASK_QUEUE")).isNull();
    }

    @Test
    @DisplayName("GIVEN a worker for a task queue WHEN configureWorker with the same task queue THEN fails")
    public void givenAWorkerForATaskQueueWhenConfigureWorkerWithTheSameTaskQueueThenFails() {
        KuFlowTemporalConnection kuFlowTemporalConnection = this.prepareKuFlowTemporalConnectionWithTwoWorkers();

        assertThatThrownBy(() -> kuFlowTemporalConnection.configureWorker(builder -> builder.withTaskQueue("TASK_QUEUE_1")))
            .isInstanceOf(KuFlowTemporalException.class)
            .hasMessageContaining("TASK_QUEUE_1");
        assertThat(kuFlowTemporalConnection.getWorkerInformationList()).hasSize(2);
    }

    @Test
    @DisplayName("GIVEN KuFlowTemporalConnection WHEN configureWorker without task queue THEN fails")
    public void givenKuFlowTemporalConnectionWhenConfigureWorkerWithoutTaskQueueThenFails() {
        KuFlowTemporalConnection kuFlowTemporalConnection = KuFlowTemporalConnection.instance(this.kuFlowRestClient);

        assertThatThrownBy(() -> kuFlowTemporalConnection.configureWorker(builder -> {})).isInstanceOf(KuFlowTemporalException.class);
        assertThatThrownBy(() -> kuFlowTemporalConnection.configureWorker(builder -> builder.withTaskQueue(" "))).isInstanceOf(
            KuFlowTemporalException.class
        );
    }

    @Test
    @SuppressWarnings("deprecation")
    @DisplayName("GIVEN several workers WHEN getWorker or getWorkerInformation without task queue THEN fails")
    public void givenSeveralWorkersWhenGetWorkerOrGetWorkerInformationWithoutTaskQueueThenFails() {
        KuFlowTemporalConnection kuFlowTemporalConnection = this.prepareKuFlowTemporalConnectionWithTwoWorkers();

        assertThatThrownBy(() -> kuFlowTemporalConnection.getWorker()).isInstanceOf(KuFlowTemporalException.class);
        assertThatThrownBy(() -> kuFlowTemporalConnection.getWorkerInformation()).isInstanceOf(KuFlowTemporalException.class);
    }

    @Test
    @DisplayName("GIVEN several workers WHEN getOrCreateWorkerFactory THEN a worker is created per task queue")
    public void givenSeveralWorkersWhenGetOrCreateWorkerFactoryThenAWorkerIsCreatedPerTaskQueue() {
        KuFlowTemporalConnection kuFlowTemporalConnection = this.prepareKuFlowTemporalConnectionWithTwoWorkers().configureWorkerFactory(
            builder -> builder.setWorkflowCacheSize(10)
        );

        WorkerFactory workerFactory = kuFlowTemporalConnection.getOrCreateWorkerFactory();
        kuFlowTemporalConnection.configureWorker(builder ->
            builder.withTaskQueue("TASK_QUEUE_3").withWorkflowImplementationTypes(Test1WorkflowImpl.class)
        );

        assertThat(kuFlowTemporalConnection.getWorkers()).hasSize(3);
        assertThat(kuFlowTemporalConnection.getWorker("TASK_QUEUE_1")).isNotNull().isSameAs(workerFactory.getWorker("TASK_QUEUE_1"));
        assertThat(kuFlowTemporalConnection.getWorker("TASK_QUEUE_2")).isNotNull().isSameAs(workerFactory.getWorker("TASK_QUEUE_2"));
        assertThat(kuFlowTemporalConnection.getWorker("TASK_QUEUE_3")).isNotNull().isSameAs(workerFactory.getWorker("TASK_QUEUE_3"));

        kuFlowTemporalConnection.getWorkflowServiceStubs().shutdownNow();
    }

    @Test
    @DisplayName("GIVEN KuFlowTemporalConnection WHEN workerFactoryOptions THEN only the encryption interceptor is set")
    public void givenKuFlowTemporalConnectionWhenWorkerFactoryOptionsThenOnlyTheEncryptionInterceptorIsSet() {
        KuFlowTemporalConnection kuFlowTemporalConnection = KuFlowTemporalConnection.instance(this.kuFlowRestClient);

        WorkerFactoryOptions workerFactoryOptions = kuFlowTemporalConnection.workerFactoryOptions();

        assertThat(workerFactoryOptions.getWorkerInterceptors()).hasSize(1);
        assertThat(workerFactoryOptions.getWorkerInterceptors()[0]).isInstanceOf(EncryptionWorkerInterceptor.class);
    }

    @Test
    @DisplayName(
        "GIVEN configured worker factory options WHEN workerFactoryOptions THEN keeps them and appends the encryption interceptor last"
    )
    public void givenConfiguredWorkerFactoryOptionsWhenWorkerFactoryOptionsThenKeepsThemAndAppendsTheEncryptionInterceptorLast() {
        WorkerInterceptor workerInterceptor1 = new WorkerInterceptorBase();
        WorkerInterceptor workerInterceptor2 = new WorkerInterceptorBase();
        KuFlowTemporalConnection kuFlowTemporalConnection = KuFlowTemporalConnection.instance(this.kuFlowRestClient).configureWorkerFactory(
            builder -> builder.setWorkflowCacheSize(10).setWorkerInterceptors(workerInterceptor1, workerInterceptor2)
        );

        WorkerFactoryOptions workerFactoryOptions = kuFlowTemporalConnection.workerFactoryOptions();

        assertThat(workerFactoryOptions.getWorkflowCacheSize()).isEqualTo(10);
        assertThat(workerFactoryOptions.getWorkerInterceptors()).hasSize(3).startsWith(workerInterceptor1, workerInterceptor2);
        assertThat(workerFactoryOptions.getWorkerInterceptors()[2]).isInstanceOf(EncryptionWorkerInterceptor.class);
    }

    @Test
    @DisplayName("GIVEN KuFlowTemporalConnection without workers WHEN start THEN fails")
    public void givenKuFlowTemporalConnectionWithoutWorkersWhenStartThenFails() {
        KuFlowTemporalConnection kuFlowTemporalConnection = KuFlowTemporalConnection.instance(this.kuFlowRestClient);

        assertThatThrownBy(kuFlowTemporalConnection::start).isInstanceOf(KuFlowTemporalException.class);
    }

    private KuFlowTemporalConnection prepareKuFlowTemporalConnectionWithTwoWorkers() {
        return KuFlowTemporalConnection.instance(this.kuFlowRestClient)
            .configureWorker(builder ->
                builder
                    .withTaskQueue("TASK_QUEUE_1")
                    .withWorkflowImplementationTypes(Test1WorkflowImpl.class)
                    .withActivitiesImplementations(new Test1ActivitiesImpl())
            )
            .configureWorker(builder ->
                builder
                    .withTaskQueue("TASK_QUEUE_2")
                    .withWorkflowImplementationTypes(Test2WorkflowImpl.class)
                    .withActivitiesImplementations(new Test2ActivitiesImpl())
            );
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
