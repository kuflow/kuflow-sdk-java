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
package com.kuflow.rest;

import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.util.BinaryData;
import com.kuflow.rest.model.Document;
import com.kuflow.rest.model.DocumentReference;
import com.kuflow.rest.model.JsonValue;
import com.kuflow.rest.model.Process;
import com.kuflow.rest.model.ProcessCreateParams;
import com.kuflow.rest.model.ProcessItem;
import com.kuflow.rest.model.ProcessItemCreateParams;
import com.kuflow.rest.model.ProcessItemTaskCreateParams;
import com.kuflow.rest.model.ProcessItemTaskDataUpdateParams;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class KuFlowRestClientTest {

    @Test
    @Disabled
    public void dummy() {
        HttpLogOptions logOptions = new HttpLogOptions();
        logOptions.setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS);

        KuFlowRestClient kuFlowRestClient = new KuFlowRestClientBuilder()
            .clientId("CLIENT_ID")
            .clientSecret("CLIENT_SECRET")
            .httpLogOptions(logOptions)
            .buildClient();

        ProcessCreateParams params = new ProcessCreateParams()
            .setId(UUID.fromString("cf9b199e-18b7-489a-811a-0132edef21b3"))
            .setProcessDefinitionId(UUID.fromString("01911390-4cda-7d06-9746-66152148bc8b"));
        Process process = kuFlowRestClient.getProcessOperations().createProcess(params);

        System.out.println(process);

        ProcessItemTaskCreateParams processItemTaskCreateParams = new ProcessItemTaskCreateParams();

        ProcessItemCreateParams processItemCreateParams = new ProcessItemCreateParams()
            .setId(UUID.fromString("01911390-9435-7c2d-a566-ff2907bf4253"))
            .setProcessId(process.getId())
            .setProcessItemDefinitionCode("TASK_0001")
            .setTask(processItemTaskCreateParams);

        ProcessItem processItem = kuFlowRestClient.getProcessItemOperations().createProcessItem(processItemCreateParams);

        System.out.println(processItem);

        BinaryData file = BinaryData.fromStream(this.getClass().getResourceAsStream("kuflow.png"));
        Document document = new Document().setFileContent(file).setFileName("kuflow.png").setContentType("image/png");
        DocumentReference documentReference = kuFlowRestClient
            .getProcessOperations()
            .uploadProcessDocument(processItem.getProcessId(), document);

        ProcessItemTaskDataUpdateParams updateParams = new ProcessItemTaskDataUpdateParams().setData(
            new JsonValue().setValue(Map.of("TEXT_002", List.of("Value 1", "Value 2", "DOC_001", documentReference.getDocumentUri())))
        );

        kuFlowRestClient.getProcessItemOperations().updateProcessItemTaskData(processItem.getId(), updateParams);
    }
}
