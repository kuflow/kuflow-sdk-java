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
package com.kuflow.temporal.activity.kuflow.model;

import com.kuflow.rest.model.BusinessArtifactActionCreateParamsCreateArtifact;
import com.kuflow.rest.model.BusinessArtifactActionCreateParamsDownloadable;
import com.kuflow.rest.model.BusinessArtifactActionCreateParamsStartProcess;
import com.kuflow.rest.model.BusinessArtifactActionCreateParamsStartWorkflow;
import com.kuflow.temporal.common.model.AbstractModel;
import java.util.UUID;

public class BusinessArtifactActionCreateRequest extends AbstractModel {

    private UUID businessArtifactId;

    private UUID id;

    private String businessArtifactActionDefinitionCode;

    private BusinessArtifactActionCreateParamsStartWorkflow startWorkflow;

    private BusinessArtifactActionCreateParamsDownloadable downloadable;

    private BusinessArtifactActionCreateParamsStartProcess startProcess;

    private BusinessArtifactActionCreateParamsCreateArtifact createArtifact;

    public UUID getBusinessArtifactId() {
        return this.businessArtifactId;
    }

    public void setBusinessArtifactId(UUID businessArtifactId) {
        this.businessArtifactId = businessArtifactId;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBusinessArtifactActionDefinitionCode() {
        return this.businessArtifactActionDefinitionCode;
    }

    public void setBusinessArtifactActionDefinitionCode(String businessArtifactActionDefinitionCode) {
        this.businessArtifactActionDefinitionCode = businessArtifactActionDefinitionCode;
    }

    public BusinessArtifactActionCreateParamsStartWorkflow getStartWorkflow() {
        return this.startWorkflow;
    }

    public void setStartWorkflow(BusinessArtifactActionCreateParamsStartWorkflow startWorkflow) {
        this.startWorkflow = startWorkflow;
    }

    public BusinessArtifactActionCreateParamsDownloadable getDownloadable() {
        return this.downloadable;
    }

    public void setDownloadable(BusinessArtifactActionCreateParamsDownloadable downloadable) {
        this.downloadable = downloadable;
    }

    public BusinessArtifactActionCreateParamsStartProcess getStartProcess() {
        return this.startProcess;
    }

    public void setStartProcess(BusinessArtifactActionCreateParamsStartProcess startProcess) {
        this.startProcess = startProcess;
    }

    public BusinessArtifactActionCreateParamsCreateArtifact getCreateArtifact() {
        return this.createArtifact;
    }

    public void setCreateArtifact(BusinessArtifactActionCreateParamsCreateArtifact createArtifact) {
        this.createArtifact = createArtifact;
    }
}
