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
package com.kuflow.temporal.activity.s3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.reset;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.when;

import com.azure.core.util.BinaryData;
import com.kuflow.rest.KuFlowRestClient;
import com.kuflow.rest.model.JsonValue;
import com.kuflow.rest.model.ProcessItem;
import com.kuflow.rest.model.ProcessItemTask;
import com.kuflow.rest.model.ProcessItemType;
import com.kuflow.rest.operation.ProcessItemOperations;
import com.kuflow.temporal.activity.s3.model.ProcessItemTaskDataDocumentsCopyRequest;
import com.kuflow.temporal.activity.s3.model.ProcessItemTaskDataDocumentsCopyResponse;
import com.kuflow.temporal.common.util.UUIDUtils;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class S3ActivitiesTest {

    private static final KuFlowRestClient kuFlowRestClient = mock(KuFlowRestClient.class);

    private static final ProcessItemOperations processItemOperations = mock(ProcessItemOperations.class);

    private static final S3Client s3Client = mock(S3Client.class);

    @BeforeEach
    public void setupTest() {
        reset(kuFlowRestClient, processItemOperations, s3Client);
    }

    @Test
    @DisplayName("GIVEN A process item WHEN copy document to S3 THEN document is uploaded")
    public void givenAProcessItemWhenCopyDocumentToS3ThenDocumentIsUploaded() {
        // GIVEN
        JsonValue data = new JsonValue();
        data.setValue(
            Map.of(
                "document",
                "kuflow-file:uri=ku:dummy/xxx-ssss-yyyy;type=application/pdf;size=11111;name=dummy.pdf;original-name=myname.doc;"
            )
        );

        ProcessItemTask processItemTask = new ProcessItemTask();
        processItemTask.setData(data);

        ProcessItem processItem = new ProcessItem();
        processItem.setId(UUIDUtils.generateUUIDv7());
        processItem.setType(ProcessItemType.TASK);
        processItem.setTask(processItemTask);

        when(kuFlowRestClient.getProcessItemOperations()).thenReturn(processItemOperations);
        when(processItemOperations.retrieveProcessItem(eq(processItem.getId()))).thenReturn(processItem);
        when(processItemOperations.downloadProcessItemTaskDataDocument(eq(processItem.getId()), any())).thenReturn(
            BinaryData.fromBytes("Dummy".getBytes())
        );

        S3Activities s3Activities = S3ActivitiesImpl.builder()
            .withKuFlowRestClient(kuFlowRestClient)
            .withS3Client(s3Client)
            .withDefaultBucket("default-bucket")
            .build();

        // WHEN
        ProcessItemTaskDataDocumentsCopyRequest request = new ProcessItemTaskDataDocumentsCopyRequest();
        request.setSourceProcessItemId(processItem.getId());
        request.setSourceDataValuePath("/document");
        request.setTargetBucket("target-bucket");

        ProcessItemTaskDataDocumentsCopyResponse response = s3Activities.copyProcessItemTaskDataDocuments(request);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getFiles()).hasSize(1);
        assertThat(response.getFiles().get(0).getKey()).isEqualTo("dummy/xxx-ssss-yyyy");

        verify(s3Client, times(1)).putObject(any(PutObjectRequest.class), any(RequestBody.class));
    }

    @Test
    @DisplayName("GIVEN A process item WHEN copy documents to S3 THEN documents are uploaded")
    public void givenAProcessItemWhenCopyDocumentsToS3ThenDocumentsAreUploaded() {
        // GIVEN
        JsonValue data = new JsonValue();
        data.setValue(
            Map.of(
                "documents",
                List.of(
                    "kuflow-file:uri=ku:dummy/xxx-ssss-yyyy;type=application/pdf;size=11111;name=dummy1.pdf;original-name=myname1.doc;",
                    "kuflow-file:uri=ku:dummy/xxx-ssss-zzzz;type=application/pdf;size=11111;name=dummy2.pdf;original-name=myname2.doc;"
                )
            )
        );

        ProcessItemTask processItemTask = new ProcessItemTask();
        processItemTask.setData(data);

        ProcessItem processItem = new ProcessItem();
        processItem.setId(UUIDUtils.generateUUIDv7());
        processItem.setType(ProcessItemType.TASK);
        processItem.setTask(processItemTask);

        when(kuFlowRestClient.getProcessItemOperations()).thenReturn(processItemOperations);
        when(processItemOperations.retrieveProcessItem(eq(processItem.getId()))).thenReturn(processItem);
        when(processItemOperations.downloadProcessItemTaskDataDocument(eq(processItem.getId()), any())).thenReturn(
            BinaryData.fromBytes("Dummy".getBytes())
        );

        S3Activities s3Activities = S3ActivitiesImpl.builder()
            .withKuFlowRestClient(kuFlowRestClient)
            .withS3Client(s3Client)
            .withDefaultBucket("default-bucket")
            .build();

        // WHEN
        ProcessItemTaskDataDocumentsCopyRequest request = new ProcessItemTaskDataDocumentsCopyRequest();
        request.setSourceProcessItemId(processItem.getId());
        request.setSourceDataValuePath("/documents");
        request.setTargetBucket("target-bucket");

        ProcessItemTaskDataDocumentsCopyResponse response = s3Activities.copyProcessItemTaskDataDocuments(request);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getFiles()).hasSize(2);
        assertThat(response.getFiles().get(0).getKey()).isEqualTo("dummy/xxx-ssss-yyyy");
        assertThat(response.getFiles().get(1).getKey()).isEqualTo("dummy/xxx-ssss-zzzz");

        verify(s3Client, times(2)).putObject(any(PutObjectRequest.class), any(RequestBody.class));
    }

    @Test
    @DisplayName("GIVEN A process item WHEN copy document from list to S3 THEN document is uploaded")
    public void givenAProcessItemWhenCopyDocumentFromListToS3ThenDocumentIsUploaded() {
        // GIVEN
        JsonValue data = new JsonValue();
        data.setValue(
            Map.of(
                "documents",
                List.of(
                    "kuflow-file:uri=ku:dummy/xxx-ssss-yyyy;type=application/pdf;size=11111;name=dummy1.pdf;original-name=myname1.doc;",
                    "kuflow-file:uri=ku:dummy/xxx-ssss-zzzz;type=application/pdf;size=11111;name=dummy2.pdf;original-name=myname2.doc;"
                )
            )
        );

        ProcessItemTask processItemTask = new ProcessItemTask();
        processItemTask.setData(data);

        ProcessItem processItem = new ProcessItem();
        processItem.setId(UUIDUtils.generateUUIDv7());
        processItem.setType(ProcessItemType.TASK);
        processItem.setTask(processItemTask);

        when(kuFlowRestClient.getProcessItemOperations()).thenReturn(processItemOperations);
        when(processItemOperations.retrieveProcessItem(eq(processItem.getId()))).thenReturn(processItem);
        when(processItemOperations.downloadProcessItemTaskDataDocument(eq(processItem.getId()), any())).thenReturn(
            BinaryData.fromBytes("Dummy".getBytes())
        );

        S3Activities s3Activities = S3ActivitiesImpl.builder()
            .withKuFlowRestClient(kuFlowRestClient)
            .withS3Client(s3Client)
            .withDefaultBucket("default-bucket")
            .build();

        // WHEN
        ProcessItemTaskDataDocumentsCopyRequest request = new ProcessItemTaskDataDocumentsCopyRequest();
        request.setSourceProcessItemId(processItem.getId());
        request.setSourceDataValuePath("/documents/0");
        request.setTargetBucket("target-bucket");

        ProcessItemTaskDataDocumentsCopyResponse response = s3Activities.copyProcessItemTaskDataDocuments(request);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getFiles()).hasSize(1);
        assertThat(response.getFiles().get(0).getKey()).isEqualTo("dummy/xxx-ssss-yyyy");

        verify(s3Client, times(1)).putObject(any(PutObjectRequest.class), any(RequestBody.class));
    }

    @Test
    @DisplayName("GIVEN A process item WHEN copy document from list to S3 using un wrong path THEN document is not uploaded")
    public void givenAProcessItemWhenCopyDocumentFromListToS3UsingUnWrongPathThenDocumentIsNotUploaded() {
        // GIVEN
        JsonValue data = new JsonValue();
        data.setValue(
            Map.of(
                "documents",
                List.of(
                    "kuflow-file:uri=ku:dummy/xxx-ssss-yyyy;type=application/pdf;size=11111;name=dummy1.pdf;original-name=myname1.doc;",
                    "kuflow-file:uri=ku:dummy/xxx-ssss-zzzz;type=application/pdf;size=11111;name=dummy2.pdf;original-name=myname2.doc;"
                )
            )
        );

        ProcessItemTask processItemTask = new ProcessItemTask();
        processItemTask.setData(data);

        ProcessItem processItem = new ProcessItem();
        processItem.setId(UUIDUtils.generateUUIDv7());
        processItem.setType(ProcessItemType.TASK);
        processItem.setTask(processItemTask);

        when(kuFlowRestClient.getProcessItemOperations()).thenReturn(processItemOperations);
        when(processItemOperations.retrieveProcessItem(eq(processItem.getId()))).thenReturn(processItem);
        when(processItemOperations.downloadProcessItemTaskDataDocument(eq(processItem.getId()), any())).thenReturn(
            BinaryData.fromBytes("Dummy".getBytes())
        );

        S3Activities s3Activities = S3ActivitiesImpl.builder()
            .withKuFlowRestClient(kuFlowRestClient)
            .withS3Client(s3Client)
            .withDefaultBucket("default-bucket")
            .build();

        // WHEN
        ProcessItemTaskDataDocumentsCopyRequest request = new ProcessItemTaskDataDocumentsCopyRequest();
        request.setSourceProcessItemId(processItem.getId());
        request.setSourceDataValuePath("/documents/dummy");
        request.setTargetBucket("target-bucket");

        ProcessItemTaskDataDocumentsCopyResponse response = s3Activities.copyProcessItemTaskDataDocuments(request);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getFiles()).hasSize(0);

        verify(s3Client, times(0)).putObject(any(PutObjectRequest.class), any(RequestBody.class));
    }
}
