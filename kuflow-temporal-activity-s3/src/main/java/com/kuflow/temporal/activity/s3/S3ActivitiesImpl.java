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

import static java.util.stream.Collectors.toList;

import com.azure.core.util.BinaryData;
import com.kuflow.rest.KuFlowRestClient;
import com.kuflow.rest.KuFlowRestClientException;
import com.kuflow.rest.model.JsonFormsFile;
import com.kuflow.rest.model.Task;
import com.kuflow.rest.model.TaskElementValueDocumentItem;
import com.kuflow.rest.operation.TaskOperations;
import com.kuflow.rest.util.TaskUtils;
import com.kuflow.temporal.activity.s3.model.CopyElementFile;
import com.kuflow.temporal.activity.s3.model.CopyJsonFormsFile;
import com.kuflow.temporal.activity.s3.model.CopyTaskElementFilesRequest;
import com.kuflow.temporal.activity.s3.model.CopyTaskElementFilesResponse;
import com.kuflow.temporal.activity.s3.model.CopyTaskJsonFormsFilesRequest;
import com.kuflow.temporal.activity.s3.model.CopyTaskJsonFormsFilesResponse;
import com.kuflow.temporal.common.error.KuFlowTemporalException;
import io.temporal.failure.ApplicationFailure;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Nonnull;
import org.apache.commons.io.FilenameUtils;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class S3ActivitiesImpl implements S3Activities {

    public static S3ActivitiesImplBuilder builder() {
        return new S3ActivitiesImplBuilder();
    }

    private final TaskOperations taskOperations;

    private final S3Client s3Client;

    private final String defaultBucket;

    private S3ActivitiesImpl(KuFlowRestClient kuFlowRestClient, S3Client s3Client, String defaultBucket) {
        Objects.requireNonNull(kuFlowRestClient, "'kuFlowRestClient' is required");
        Objects.requireNonNull(s3Client, "'s3Client' is required");
        Objects.requireNonNull(defaultBucket, "'defaultBucket' is required");

        this.taskOperations = kuFlowRestClient.getTaskOperations();
        this.s3Client = s3Client;
        this.defaultBucket = defaultBucket;
    }

    @Nonnull
    @Override
    public CopyTaskElementFilesResponse copyTaskElementFiles(@Nonnull CopyTaskElementFilesRequest request) {
        CopyTaskElementFilesResponse result = new CopyTaskElementFilesResponse();

        Task task = this.taskOperations.retrieveTask(request.getSourceTaskId());

        String elementDefinitionCode = request.getSourceElementDefinitionCode();

        List<TaskElementValueDocumentItem> elementValues = TaskUtils.getElementValueAsDocumentList(task, elementDefinitionCode);

        if (elementValues == null || elementValues.isEmpty()) {
            return result;
        }

        String targetBucket = this.getTargetBucket(request);

        List<CopyElementFile> targetFiles = elementValues
            .stream()
            .map(elementValueDocument -> {
                String targetKey = this.getTargetKey(request, elementValues, elementValueDocument);

                this.putObject(task, elementValueDocument, targetBucket, targetKey);

                CopyElementFile targetFile = new CopyElementFile();
                targetFile.setElementValueId(elementValueDocument.getId());
                targetFile.setElementDefinitionCode(elementDefinitionCode);
                targetFile.setBucket(targetBucket);
                targetFile.setKey(targetKey);

                return targetFile;
            })
            .collect(toList());

        result.setFiles(targetFiles);

        return result;
    }

    @Nonnull
    @Override
    public CopyTaskJsonFormsFilesResponse copyTaskJsonFormsFiles(@Nonnull CopyTaskJsonFormsFilesRequest request) {
        CopyTaskJsonFormsFilesResponse result = new CopyTaskJsonFormsFilesResponse();

        Task task = this.taskOperations.retrieveTask(request.getSourceTaskId());

        String propertyPath = request.getSourceJsonFormsPropertyPath();

        List<Object> jsonFormsFiles = this.getJsonFormsFiles(task, propertyPath);

        if (jsonFormsFiles == null || jsonFormsFiles.isEmpty()) {
            return result;
        }

        String targetBucket = this.getTargetBucket(request);

        List<CopyJsonFormsFile> targetFiles = jsonFormsFiles
            .stream()
            .map(sourceJsonFormsFile -> {
                Optional<JsonFormsFile> jsonFormsFileOptional = JsonFormsFile.from(sourceJsonFormsFile.toString());
                if (jsonFormsFileOptional.isEmpty()) {
                    return null;
                }
                JsonFormsFile jsonFormsFile = jsonFormsFileOptional.get();

                String targetKey = this.getTargetKey(request, jsonFormsFiles, jsonFormsFile);

                this.putObject(task, jsonFormsFile, targetBucket, targetKey);

                CopyJsonFormsFile targetFile = new CopyJsonFormsFile();
                targetFile.setPropertyPath(propertyPath);
                targetFile.setBucket(targetBucket);
                targetFile.setKey(targetKey);

                return targetFile;
            })
            .filter(Objects::nonNull)
            .collect(toList());

        result.setFiles(targetFiles);

        return result;
    }

    private List<Object> getJsonFormsFiles(Task task, String propertyPath) {
        try {
            Optional<List<Object>> jsonFormsPropertyAsList = TaskUtils.findJsonFormsPropertyAsList(task, propertyPath);
            if (jsonFormsPropertyAsList.isPresent()) {
                return jsonFormsPropertyAsList.get();
            }
        } catch (KuFlowRestClientException ignored) {}

        try {
            Optional<String> jsonFormsPropertyAsString = TaskUtils.findJsonFormsPropertyAsString(task, propertyPath);
            if (jsonFormsPropertyAsString.isPresent()) {
                return List.of(jsonFormsPropertyAsString.get());
            }
        } catch (KuFlowRestClientException ignored) {}

        return List.of();
    }

    private void putObject(Task task, TaskElementValueDocumentItem elementValueDocument, String targetBucket, String targetKey) {
        if (elementValueDocument.getContentLength() == null) {
            throw ApplicationFailure.newNonRetryableFailure("ContentLength required", "KuFlowActivities.validation");
        }
        if (elementValueDocument.getName() == null) {
            throw ApplicationFailure.newNonRetryableFailure("Name required", "KuFlowActivities.validation");
        }

        BinaryData sourceFile = this.taskOperations.actionsTaskDownloadElementValueDocument(task.getId(), elementValueDocument.getId());
        try (InputStream sourceInputStream = sourceFile.toStream()) {
            RequestBody requestBody = RequestBody.fromInputStream(sourceInputStream, elementValueDocument.getContentLength());
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(targetBucket)
                .contentType(elementValueDocument.getContentType())
                .contentDisposition(String.format("attachment; filename=\"%s\"", elementValueDocument.getName()))
                .key(targetKey)
                .build();

            this.s3Client.putObject(putObjectRequest, requestBody);
        } catch (IOException e) {
            throw new KuFlowTemporalException("Input stream error", e);
        }
    }

    private void putObject(Task task, JsonFormsFile jsonFormsFile, String targetBucket, String targetKey) {
        if (jsonFormsFile.getSize() == null) {
            throw ApplicationFailure.newNonRetryableFailure("Size required", "KuFlowActivities.validation");
        }
        if (jsonFormsFile.getName() == null) {
            throw ApplicationFailure.newNonRetryableFailure("Name required", "KuFlowActivities.validation");
        }

        BinaryData sourceFile = this.taskOperations.actionsTaskDownloadJsonFormsValueDocument(task.getId(), jsonFormsFile.getUri());
        try (InputStream sourceInputStream = sourceFile.toStream()) {
            RequestBody requestBody = RequestBody.fromInputStream(sourceInputStream, jsonFormsFile.getSize());
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(targetBucket)
                .contentType(jsonFormsFile.getType())
                .contentDisposition(String.format("attachment; filename=\"%s\"", jsonFormsFile.getName()))
                .key(targetKey)
                .build();

            this.s3Client.putObject(putObjectRequest, requestBody);
        } catch (IOException e) {
            throw new KuFlowTemporalException("Input stream error", e);
        }
    }

    private String getTargetKey(
        CopyTaskElementFilesRequest request,
        List<TaskElementValueDocumentItem> elementValues,
        TaskElementValueDocumentItem elementValueDocument
    ) {
        String targetKey = elementValueDocument.getContentPath();
        if (request.getTargetKey() != null) {
            targetKey = request.getTargetKey();

            int sourceElementValueDocumentIndex = elementValues.indexOf(elementValueDocument);
            if (sourceElementValueDocumentIndex > 0) {
                String path = FilenameUtils.getPathNoEndSeparator(targetKey);
                String baseName = FilenameUtils.getBaseName(targetKey);
                String extension = FilenameUtils.getExtension(targetKey);
                targetKey = String.format("%s/%s_%d_%s", path, baseName, sourceElementValueDocumentIndex, extension);
            }
        }
        return targetKey;
    }

    private String getTargetKey(CopyTaskJsonFormsFilesRequest request, List<Object> jsonFormsFiles, JsonFormsFile jsonFormsFile) {
        String targetKey = jsonFormsFile.getUri().replace("ku:", "");
        if (request.getTargetKey() != null) {
            targetKey = request.getTargetKey();

            int sourceJsonFormsFileIndex = jsonFormsFiles.indexOf(jsonFormsFile.toString());
            if (sourceJsonFormsFileIndex > 0) {
                String path = FilenameUtils.getPathNoEndSeparator(targetKey);
                String baseName = FilenameUtils.getBaseName(targetKey);
                String extension = FilenameUtils.getExtension(targetKey);
                targetKey = String.format("%s/%s_%d_%s", path, baseName, sourceJsonFormsFileIndex, extension);
            }
        }
        return targetKey;
    }

    private String getTargetBucket(CopyTaskElementFilesRequest request) {
        if (request.getTargetBucket() == null) {
            return this.defaultBucket;
        }

        return request.getTargetBucket();
    }

    private String getTargetBucket(CopyTaskJsonFormsFilesRequest request) {
        if (request.getTargetBucket() == null) {
            return this.defaultBucket;
        }

        return request.getTargetBucket();
    }

    public static final class S3ActivitiesImplBuilder {

        private KuFlowRestClient kuFlowRestClient;
        private S3Client s3Client;
        private String defaultBucket;

        private S3ActivitiesImplBuilder() {}

        public S3ActivitiesImplBuilder withKuFlowRestClient(KuFlowRestClient kuFlowRestClient) {
            this.kuFlowRestClient = kuFlowRestClient;
            return this;
        }

        public S3ActivitiesImplBuilder withS3Client(S3Client s3Client) {
            this.s3Client = s3Client;
            return this;
        }

        public S3ActivitiesImplBuilder withDefaultBucket(String defaultBucket) {
            this.defaultBucket = defaultBucket;
            return this;
        }

        public S3ActivitiesImpl build() {
            return new S3ActivitiesImpl(this.kuFlowRestClient, this.s3Client, this.defaultBucket);
        }
    }
}
