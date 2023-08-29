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

import static java.util.concurrent.TimeUnit.MINUTES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Percentage.withPercentage;
import static org.awaitility.Awaitility.await;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.mock;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.util.Context;
import com.kuflow.rest.KuFlowRestClient;
import com.kuflow.rest.model.Worker;
import com.kuflow.rest.operation.WorkerOperations;
import io.temporal.client.WorkflowClientOptions;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
public class WorkerInformationNotifierTest {

    @Mock
    private KuFlowRestClient kuFlowRestClient;

    @Test
    @DisplayName("GIVEN workerInformationNotifier WHEN is started THEN the required worker info is sent")
    public void givenWorkerInformationNotifierWhenIsStartedThenTheRequiredWorkerInfoIsSent() {
        ArgumentCaptor<Worker> workerArgumentCaptor = ArgumentCaptor.forClass(Worker.class);
        ArgumentCaptor<Context> contextArgumentCaptor = ArgumentCaptor.forClass(Context.class);

        WorkerOperations workerOperations = mock(WorkerOperations.class);
        when(this.kuFlowRestClient.getWorkerOperations()).thenReturn(workerOperations);
        when(workerOperations.createWorkerWithResponse(workerArgumentCaptor.capture(), contextArgumentCaptor.capture()))
            .thenAnswer(this.prepareCreateWorkerWithResponseAnswer(Duration.ofMinutes(5)));

        WorkerInformation workerInformation = this.prepareWorkerInfo();

        WorkerInformationNotifier workerInformationNotifier = new WorkerInformationNotifier(
            this.kuFlowRestClient,
            WorkflowClientOptions.newBuilder().validateAndBuildWithDefaults(),
            WorkerInformationNotifierConfigurationBuilder.instance().build(),
            List.of(workerInformation)
        );

        workerInformationNotifier.start();

        Worker worker = workerArgumentCaptor.getValue();
        assertThat(worker.getTaskQueue()).isEqualTo(workerInformation.getTaskQueue());
        assertThat(worker.getWorkflowTypes()).containsExactlyElementsOf(workerInformation.getWorkflowTypes());
        assertThat(worker.getActivityTypes()).containsExactlyElementsOf(workerInformation.getActivityTypes());
        assertThat(worker.getHostname()).isNotBlank();
        assertThat(worker.getIp()).isNotBlank();

        workerInformationNotifier.shutdown();
    }

    @Test
    @DisplayName("GIVEN workerInformationNotifier WHEN is started and the delay change THEN the notification interval change too")
    public void givenWorkerInformationNotifierWhenIsStartedAndTheDelayChangeThenTheNotificationIntervalChangeToo() {
        ArgumentCaptor<Worker> workerArgumentCaptor = ArgumentCaptor.forClass(Worker.class);
        ArgumentCaptor<Context> contextArgumentCaptor = ArgumentCaptor.forClass(Context.class);

        Duration firstDelayWindow = Duration.ofSeconds(1);
        Duration restDelayWindow = Duration.ofSeconds(3);

        WorkerOperations workerOperations = mock(WorkerOperations.class);
        when(this.kuFlowRestClient.getWorkerOperations()).thenReturn(workerOperations);
        when(workerOperations.createWorkerWithResponse(workerArgumentCaptor.capture(), contextArgumentCaptor.capture()))
            .then(this.prepareCreateWorkerWithResponseAnswer(firstDelayWindow))
            .then(this.prepareCreateWorkerWithResponseAnswer(restDelayWindow))
            .then(this.prepareCreateWorkerWithResponseAnswer(restDelayWindow))
            .thenThrow(RuntimeException.class);

        WorkerInformation workerInformation = this.prepareWorkerInfo();

        WorkerInformationNotifier workerInformationNotifier = new WorkerInformationNotifier(
            this.kuFlowRestClient,
            WorkflowClientOptions.newBuilder().validateAndBuildWithDefaults(),
            WorkerInformationNotifierConfigurationBuilder.instance().build(),
            List.of(workerInformation)
        );

        workerInformationNotifier.start();

        // First invocation
        long firstInvocation = System.currentTimeMillis();
        assertThat(workerArgumentCaptor.getAllValues()).hasSize(1);

        // After X Seconds we get the next
        await().pollDelay(firstDelayWindow).atMost(1, MINUTES).until(() -> workerArgumentCaptor.getAllValues().size() == 2);
        long secondInvocation = System.currentTimeMillis();
        assertThat(workerArgumentCaptor.getAllValues()).hasSize(2);

        // After Y Seconds we get the next
        await().pollDelay(restDelayWindow).atMost(1, MINUTES).until(() -> workerArgumentCaptor.getAllValues().size() == 3); // 5 Seconds
        long thirdInvocation = System.currentTimeMillis();
        assertThat(workerArgumentCaptor.getAllValues()).hasSize(3);

        workerInformationNotifier.shutdown();

        assertThat(secondInvocation - firstInvocation).isCloseTo(firstDelayWindow.toMillis(), withPercentage(20));
        assertThat(thirdInvocation - secondInvocation).isCloseTo(restDelayWindow.toMillis(), withPercentage(20));
    }

    @Test
    @DisplayName("GIVEN workerInformationNotifier WHEN is started and then fails THEN a backoff process is started")
    public void givenWorkerInformationNotifierWhenIsStartedAndThenFailsThenABackoffProcessIsStarted() {
        ArgumentCaptor<Worker> workerArgumentCaptor = ArgumentCaptor.forClass(Worker.class);
        ArgumentCaptor<Context> contextArgumentCaptor = ArgumentCaptor.forClass(Context.class);

        Duration delayWindow = Duration.ofSeconds(5);

        WorkerOperations workerOperations = mock(WorkerOperations.class);
        when(this.kuFlowRestClient.getWorkerOperations()).thenReturn(workerOperations);
        when(workerOperations.createWorkerWithResponse(workerArgumentCaptor.capture(), contextArgumentCaptor.capture()))
            .then(this.prepareCreateWorkerWithResponseAnswer(delayWindow))
            .thenThrow(RuntimeException.class)
            .thenThrow(RuntimeException.class)
            .then(this.prepareCreateWorkerWithResponseAnswer(delayWindow));

        WorkerInformation workerInformation = this.prepareWorkerInfo();

        WorkerInformationNotifier workerInformationNotifier = new WorkerInformationNotifier(
            this.kuFlowRestClient,
            WorkflowClientOptions.newBuilder().validateAndBuildWithDefaults(),
            WorkerInformationNotifierConfigurationBuilder.instance().build(),
            List.of(workerInformation)
        );

        workerInformationNotifier.start();

        // First invocation
        long firstInvocation = System.currentTimeMillis();
        assertThat(workerArgumentCaptor.getAllValues()).hasSize(1);

        // After X Seconds we get the next
        await().atMost(1, MINUTES).until(() -> workerArgumentCaptor.getAllValues().size() == 2);
        long secondInvocation = System.currentTimeMillis();

        // After Y Seconds we get the next
        await().atMost(1, MINUTES).until(() -> workerArgumentCaptor.getAllValues().size() == 3);
        long thirdInvocation = System.currentTimeMillis();

        await().atMost(1, MINUTES).until(() -> workerArgumentCaptor.getAllValues().size() == 4);
        long fourthInvocation = System.currentTimeMillis();

        workerInformationNotifier.shutdown();

        assertThat(secondInvocation - firstInvocation).isCloseTo(delayWindow.toMillis(), withPercentage(20));
        assertThat(thirdInvocation - secondInvocation).isCloseTo(2_500, withPercentage(20));
        assertThat(fourthInvocation - thirdInvocation).isCloseTo(delayWindow.toMillis(), withPercentage(20));
    }

    private Answer<Object> prepareCreateWorkerWithResponseAnswer(Duration delay) {
        return answer -> {
            Worker worker = answer.getArgument(0);

            Worker workerResponse = new Worker();
            workerResponse.setId(UUID.randomUUID());
            workerResponse.setIdentity(worker.getIdentity());
            workerResponse.setTaskQueue(worker.getTaskQueue());
            workerResponse.setWorkflowTypes(List.copyOf(worker.getWorkflowTypes()));
            workerResponse.setActivityTypes(List.copyOf(worker.getActivityTypes()));
            workerResponse.setHostname(worker.getHostname());
            workerResponse.setIp(worker.getIp());
            workerResponse.setCreatedAt(OffsetDateTime.now());
            workerResponse.setCreatedBy(UUID.randomUUID());
            workerResponse.setLastModifiedAt(OffsetDateTime.now());
            workerResponse.setLastModifiedBy(UUID.randomUUID());

            HttpHeaders createWorkerResponseHeaders = new HttpHeaders(
                Map.of(WorkerInformationNotifier.HEADER_X_KF_DELAY_WINDOW, String.valueOf(delay.toSeconds()))
            );

            return new SimpleResponse<>(null, 200, createWorkerResponseHeaders, workerResponse);
        };
    }

    private WorkerInformation prepareWorkerInfo() {
        String taskQueue = "TASK_QUEUE_" + RandomString.make();
        Set<String> workflowTypes = Set.of("Workflow_" + RandomString.make(), "Workflow_" + RandomString.make());
        Set<String> activityTypes = Set.of("Activity_" + RandomString.make(), "Activity_" + RandomString.make());

        return new WorkerInformation(taskQueue, workflowTypes, activityTypes);
    }
}
