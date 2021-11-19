/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.common.feign;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuflow.engine.common.error.SystemException;
import feign.codec.EncodeException;
import feign.form.multipart.AbstractWriter;
import feign.form.multipart.Output;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

public class JacksonPojoWriter extends AbstractWriter {

    private static final String CRLF = StringUtils.CR + StringUtils.LF;

    private final ObjectMapper objectMapper;

    public JacksonPojoWriter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean isApplicable(Object object) {
        return this.isUserPojo(object);
    }

    @Override
    protected void write(Output output, String key, Object value) throws EncodeException {
        String data;
        try {
            data = this.objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new SystemException("Unable to marshall", e);
        }

        String string = new StringBuilder()
            .append("Content-Disposition: form-data; name=\"")
            .append(key)
            .append('"')
            .append(CRLF)
            .append("Content-Type: application/json; charset=")
            .append(output.getCharset().name())
            .append(CRLF)
            .append(CRLF)
            .append(data)
            .toString();

        output.write(string);
    }

    private boolean isUserPojo(@NonNull Object object) {
        Class<?> type = object.getClass();
        Package typePackage = type.getPackage();

        return typePackage != null && typePackage.getName().startsWith("com.kuflow.engine.api.");
    }
}
