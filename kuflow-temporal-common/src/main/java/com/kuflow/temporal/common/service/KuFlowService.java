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
package com.kuflow.temporal.common.service;

import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.kuflow.rest.KuFlowRestClient;
import com.kuflow.rest.operation.TaskOperations;
import com.kuflow.temporal.common.error.KuFlowTemporalException;
import com.kuflow.temporal.common.model.File;
import com.kuflow.temporal.common.util.HeaderUtils;
import com.kuflow.temporal.common.util.StreamUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import javax.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KuFlowService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KuFlowService.class);

    private final TaskOperations taskOperations;

    public KuFlowService(KuFlowRestClient kuFlowRestClient) {
        this.taskOperations = kuFlowRestClient.getTaskOperations();
    }

    public File downloadTaskDocument(@Nonnull UUID taskId, @Nonnull UUID documentId) {
        Response<BinaryData> response =
            this.taskOperations.actionsTaskDownloadElementValueDocumentWithResponse(taskId, documentId, Context.NONE);
        this.checkResponse(response);

        File result = new File();
        result.setContentType(HeaderUtils.extractContentType(response));
        result.setContentLength(HeaderUtils.extractContentLength(response));
        result.setName(HeaderUtils.extractFileName(response));
        result.setInputStream(response.getValue().toStream());

        return result;
    }

    public File downloadTaskElementForm(@Nonnull UUID taskId, @Nonnull String elementDefinitionCode) {
        Response<BinaryData> response =
            this.taskOperations.actionsTaskDownloadElementValueRenderedWithResponse(taskId, elementDefinitionCode, Context.NONE);
        this.checkResponse(response);

        File result = new File();
        result.setContentType(HeaderUtils.extractContentType(response));
        result.setContentLength(HeaderUtils.extractContentLength(response));
        result.setName(HeaderUtils.extractFileName(response));
        result.setInputStream(response.getValue().toStream());

        return result;
    }

    private void checkResponse(Response<BinaryData> response) {
        if (response.getStatusCode() >= 400) {
            if (LOGGER.isDebugEnabled()) {
                String body = this.getResponseBodyString(response);
                LOGGER.error("Http response body received: {}", body);
            }

            String message = String.format("Unexpected API response. [Status: %s]", response.getStatusCode());
            throw new KuFlowTemporalException(message);
        }
    }

    private String getResponseBodyString(Response<BinaryData> response) {
        try {
            return StreamUtils.copyToString(response.getValue().toStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return "error read response body. " + e.getMessage();
        }
    }
}
