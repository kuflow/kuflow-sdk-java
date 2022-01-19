/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.Encoder;
import feign.form.ContentType;
import feign.form.MultipartFormContentProcessor;
import feign.form.spring.SpringFormEncoder;

public class KuFlowFormEncoder extends SpringFormEncoder {

    public KuFlowFormEncoder(ObjectMapper objectMapper, Encoder delegate) {
        super(delegate);
        MultipartFormContentProcessor contentProcessor = (MultipartFormContentProcessor) getContentProcessor(ContentType.MULTIPART);
        contentProcessor.addFirstWriter(new JacksonPojoWriter(objectMapper));
    }
}
