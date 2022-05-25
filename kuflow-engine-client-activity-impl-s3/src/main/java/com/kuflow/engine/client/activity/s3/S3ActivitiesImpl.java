/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.s3;

import com.kuflow.engine.client.activity.s3.config.S3ActivitiesProperties;
import com.kuflow.engine.client.activity.s3.resource.CopyElementFileResource;
import com.kuflow.engine.client.activity.s3.resource.CopyTaskElementFilesRequestResource;
import com.kuflow.engine.client.activity.s3.resource.CopyTaskElementFilesResponseResource;
import com.kuflow.engine.client.common.error.KuFlowEngineClientException;
import com.kuflow.rest.client.controller.TaskApi;
import com.kuflow.rest.client.resource.TaskElementValueDocumentResource;
import com.kuflow.rest.client.resource.TaskElementValueWrapperResource;
import com.kuflow.rest.client.resource.TaskResource;
import feign.Response;
import io.temporal.failure.ApplicationFailure;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nonnull;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
public class S3ActivitiesImpl implements S3Activities {

    private final TaskApi taskApi;

    private final S3Client s3Client;

    private final S3ActivitiesProperties s3ActivitiesProperties;

    public S3ActivitiesImpl(TaskApi taskApi, S3Client s3Client, S3ActivitiesProperties s3ActivitiesProperties) {
        this.taskApi = taskApi;
        this.s3Client = s3Client;
        this.s3ActivitiesProperties = s3ActivitiesProperties;
    }

    @Nonnull
    @Override
    public CopyTaskElementFilesResponseResource copyTaskElementFiles(@Nonnull CopyTaskElementFilesRequestResource request) {
        List<CopyElementFileResource> targetFiles = new LinkedList<>();

        CopyTaskElementFilesResponseResource result = new CopyTaskElementFilesResponseResource();
        result.setFiles(targetFiles);

        TaskResource task = this.taskApi.retrieveTask(request.getSourceTaskId());
        if (task.getElementValues() == null) {
            return result;
        }

        String elementDefinitionCode = request.getSourceElementDefinitionCode();

        TaskElementValueWrapperResource elementValue = task.getElementValues().get(elementDefinitionCode);
        if (elementValue == null) {
            return result;
        }

        List<TaskElementValueDocumentResource> elementValues = elementValue.getValueAsDocumentList();

        String targetBucket = this.getTargetBucket(request);

        elementValues.forEach(elementValueDocument -> {
            String targetKey = this.getTargetKey(request, elementValues, elementValueDocument);

            this.putObject(task, elementValueDocument, targetBucket, targetKey);

            CopyElementFileResource targetFile = new CopyElementFileResource();
            targetFile.setElementValueId(elementValueDocument.getId());
            targetFile.setElementDefinitionCode(elementDefinitionCode);
            targetFile.setBucket(targetBucket);
            targetFile.setKey(targetKey);

            targetFiles.add(targetFile);
        });

        return result;
    }

    private void putObject(
        TaskResource task,
        TaskElementValueDocumentResource elementValueDocument,
        String targetBucket,
        String targetKey
    ) {
        if (elementValueDocument.getContentLength() == null) {
            throw ApplicationFailure.newNonRetryableFailure("ContentLength required", "KuFlowActivities.validation");
        }
        if (elementValueDocument.getName() == null) {
            throw ApplicationFailure.newNonRetryableFailure("Name required", "KuFlowActivities.validation");
        }

        try (
            Response sourceFile = this.taskApi.actionsDownloadElementDocument(task.getId(), elementValueDocument.getId());
            InputStream sourceInputStream = sourceFile.body().asInputStream()
        ) {
            RequestBody requestBody = RequestBody.fromInputStream(sourceInputStream, elementValueDocument.getContentLength());
            PutObjectRequest putObjectRequest = PutObjectRequest
                .builder()
                .bucket(targetBucket)
                .contentType(elementValueDocument.getContentType())
                .contentDisposition(String.format("attachment; filename=\"%s\"", elementValueDocument.getName()))
                .key(targetKey)
                .build();

            this.s3Client.putObject(putObjectRequest, requestBody);
        } catch (IOException e) {
            throw new KuFlowEngineClientException("Input stream error", e);
        }
    }

    private String getTargetKey(
        CopyTaskElementFilesRequestResource request,
        List<TaskElementValueDocumentResource> elementValues,
        TaskElementValueDocumentResource elementValueDocument
    ) {
        String targetKey = elementValueDocument.getContentPath();
        if (request.getTargetKey() != null) {
            targetKey = request.getTargetKey();

            int sourceElementValueDocumentIndex = elementValues.indexOf(elementValueDocument);
            if (sourceElementValueDocumentIndex > 0) {
                String path = FilenameUtils.getPath(targetKey);
                String baseName = FilenameUtils.getBaseName(targetKey);
                String extension = FilenameUtils.getExtension(targetKey);
                targetKey = String.format("%s/%s_%d_%s", path, baseName, sourceElementValueDocumentIndex, extension);
            }
        }
        return targetKey;
    }

    private String getTargetBucket(CopyTaskElementFilesRequestResource request) {
        if (request.getTargetBucket() == null) {
            return this.s3ActivitiesProperties.getDefaultBucket();
        }

        return request.getTargetBucket();
    }
}
