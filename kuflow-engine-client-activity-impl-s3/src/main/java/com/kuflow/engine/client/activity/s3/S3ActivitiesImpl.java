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
import com.kuflow.rest.client.resource.ElementValueDocumentResource;
import com.kuflow.rest.client.resource.TaskResource;
import feign.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nonnull;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
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
        TaskResource task = this.taskApi.retrieveTask(request.getSourceTaskId());

        String elementDefinitionCode = request.getSourceElementDefinitionCode();
        List<ElementValueDocumentResource> elementValues = task.getElementValues().get(elementDefinitionCode).getValueAsDocumentList();

        String targetBucket = this.getTargetBucket(request);

        List<CopyElementFileResource> targetFiles = new LinkedList<>();

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

        CopyTaskElementFilesResponseResource result = new CopyTaskElementFilesResponseResource();
        result.setFiles(targetFiles);

        return result;
    }

    private void putObject(TaskResource task, ElementValueDocumentResource elementValueDocument, String targetBucket, String targetKey) {
        Assert.notNull(elementValueDocument.getContentLength(), "ContentLength required");
        Assert.notNull(elementValueDocument.getName(), "Name required");

        Response sourceFile = this.taskApi.actionsDownloadElementDocument(task.getId(), elementValueDocument.getId());

        try (InputStream sourceInputStream = sourceFile.body().asInputStream()) {
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
        List<ElementValueDocumentResource> elementValues,
        ElementValueDocumentResource elementValueDocument
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
