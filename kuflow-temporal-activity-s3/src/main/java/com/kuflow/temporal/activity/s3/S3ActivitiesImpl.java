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
import com.kuflow.rest.model.JsonValue;
import com.kuflow.rest.model.KuFlowFile;
import com.kuflow.rest.model.ProcessItem;
import com.kuflow.rest.model.ProcessItemTask;
import com.kuflow.rest.model.ProcessItemType;
import com.kuflow.rest.operation.ProcessItemOperations;
import com.kuflow.rest.operation.ProcessOperations;
import com.kuflow.temporal.activity.s3.model.KuFlowFileCopy;
import com.kuflow.temporal.activity.s3.model.ProcessItemTaskDataDocumentsCopyRequest;
import com.kuflow.temporal.activity.s3.model.ProcessItemTaskDataDocumentsCopyResponse;
import com.kuflow.temporal.common.error.KuFlowTemporalException;
import io.temporal.failure.ApplicationFailure;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

    private final ProcessOperations processOperations;

    private final ProcessItemOperations processItemOperations;

    private final S3Client s3Client;

    private final String defaultBucket;

    private S3ActivitiesImpl(KuFlowRestClient kuFlowRestClient, S3Client s3Client, String defaultBucket) {
        Objects.requireNonNull(kuFlowRestClient, "'kuFlowRestClient' is required");
        Objects.requireNonNull(s3Client, "'s3Client' is required");
        Objects.requireNonNull(defaultBucket, "'defaultBucket' is required");

        this.processOperations = kuFlowRestClient.getProcessOperations();
        this.processItemOperations = kuFlowRestClient.getProcessItemOperations();
        this.s3Client = s3Client;
        this.defaultBucket = defaultBucket;
    }

    @Nonnull
    @Override
    public ProcessItemTaskDataDocumentsCopyResponse copyProcessItemTaskDataDocuments(
        @Nonnull ProcessItemTaskDataDocumentsCopyRequest request
    ) {
        ProcessItemTaskDataDocumentsCopyResponse result = new ProcessItemTaskDataDocumentsCopyResponse();

        ProcessItem processItem = this.processItemOperations.retrieveProcessItem(request.getSourceProcessItemId());
        if (!ProcessItemType.TASK.equals(processItem.getType())) {
            return result;
        }

        String sourceDataValuePath = request.getSourceDataValuePath();

        List<KuFlowFile> sourceFiles = this.extractKuFlowFiles(processItem, sourceDataValuePath);

        if (sourceFiles == null || sourceFiles.isEmpty()) {
            return result;
        }

        String targetBucket = this.getTargetBucket(request);

        List<KuFlowFileCopy> targetFiles = sourceFiles
            .stream()
            .map(sourceFile -> {
                String targetKey = this.getTargetKey(request, sourceFiles, sourceFile);

                this.putObject(processItem, sourceFile, targetBucket, targetKey);

                KuFlowFileCopy targetFile = new KuFlowFileCopy();
                targetFile.setDataValuePath(sourceDataValuePath);
                targetFile.setBucket(targetBucket);
                targetFile.setKey(targetKey);

                return targetFile;
            })
            .collect(toList());

        result.setFiles(targetFiles);

        return result;
    }

    private void putObject(ProcessItem processItem, KuFlowFile file, String targetBucket, String targetKey) {
        if (file.getSize() == null) {
            throw ApplicationFailure.newNonRetryableFailure("Size required", "KuFlowActivities.validation");
        }
        if (file.getName() == null) {
            throw ApplicationFailure.newNonRetryableFailure("Name required", "KuFlowActivities.validation");
        }

        BinaryData sourceFile = this.processOperations.downloadProcessDocument(processItem.getId(), file.getUri());
        try (InputStream sourceInputStream = sourceFile.toStream()) {
            RequestBody requestBody = RequestBody.fromInputStream(sourceInputStream, file.getSize());
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(targetBucket)
                .contentType(file.getType())
                .contentDisposition(String.format("attachment; filename=\"%s\"", file.getName()))
                .key(targetKey)
                .build();

            this.s3Client.putObject(putObjectRequest, requestBody);
        } catch (IOException e) {
            throw new KuFlowTemporalException("Input stream error", e);
        }
    }

    private String getTargetKey(ProcessItemTaskDataDocumentsCopyRequest request, List<KuFlowFile> files, KuFlowFile file) {
        String targetKey = file.getUri().replace("ku:", "");
        if (request.getTargetKey() != null) {
            targetKey = request.getTargetKey();

            int sourceJsonFormsFileIndex = files.indexOf(file);
            if (sourceJsonFormsFileIndex > 0) {
                String path = FilenameUtils.getPathNoEndSeparator(targetKey);
                String baseName = FilenameUtils.getBaseName(targetKey);
                String extension = FilenameUtils.getExtension(targetKey);
                targetKey = String.format("%s/%s_%d_%s", path, baseName, sourceJsonFormsFileIndex, extension);
            }
        }
        return targetKey;
    }

    private String getTargetBucket(ProcessItemTaskDataDocumentsCopyRequest request) {
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

    private List<KuFlowFile> extractKuFlowFiles(ProcessItem processItem, String valuePath) {
        String[] valueParts = valuePath.split("/");
        ProcessItemTask processItemTask = processItem.getTask();
        JsonValue data = processItemTask.getData();
        Object value = data.getValue();
        for (String valuePart : valueParts) {
            if (valuePart.isEmpty()) {
                continue;
            }
            if (valuePart.matches("^[0-9]+$")) {
                if (value instanceof List<?>) {
                    value = ((List<?>) value).get(Integer.parseInt(valuePart));
                } else {
                    value = null;
                }
            } else {
                if (value instanceof Map<?, ?>) {
                    value = ((Map<?, ?>) value).get(valuePart);
                } else {
                    value = null;
                }
            }
            if (value == null) {
                break;
            }
        }

        List<String> results = new LinkedList<>();
        if (value instanceof String) {
            results.add((String) value);
        } else if (value instanceof List<?>) {
            results.addAll(((List<?>) value).stream().map(Object::toString).collect(toList()));
        }

        return results.stream().map(KuFlowFile::from).filter(Optional::isPresent).map(Optional::get).collect(toList());
    }
}
