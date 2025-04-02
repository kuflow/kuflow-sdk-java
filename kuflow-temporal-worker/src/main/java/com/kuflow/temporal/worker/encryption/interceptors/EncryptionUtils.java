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
package com.kuflow.temporal.worker.encryption.interceptors;

import com.kuflow.temporal.worker.encryption.EncryptionConstant;
import com.kuflow.temporal.worker.encryption.EncryptionState;
import com.kuflow.temporal.worker.encryption.converter.EncryptionWrapper;
import io.temporal.api.common.v1.Payload;
import io.temporal.common.converter.DefaultDataConverter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class EncryptionUtils {

    private static final Payload HEADER_VALUE_KUFLOW_ENCODING_ENCRYPTED_PAYLOAD = DefaultDataConverter.STANDARD_INSTANCE.toPayload(
        EncryptionConstant.HEADER_VALUE_KUFLOW_ENCODING_ENCRYPTED_NAME
    ).orElseThrow();

    @Nonnull
    public static EncryptionState retrieveEncryptionState(@Nonnull io.temporal.common.interceptors.Header header) {
        if (!EncryptionUtils.isEncryptionRequired(header)) {
            return EncryptionState.empty();
        }

        Payload keyIdPayload = header.getValues().get(EncryptionConstant.HEADER_KEY_KUFLOW_ENCODING_ENCRYPTED_KEY_ID);
        String keyId = DefaultDataConverter.STANDARD_INSTANCE.fromPayload(keyIdPayload, String.class, String.class);

        return EncryptionState.of(keyId);
    }

    public static io.temporal.common.interceptors.Header addEncryptionEncoding(
        @Nonnull EncryptionState encryptionState,
        @Nullable io.temporal.common.interceptors.Header header
    ) {
        if (!encryptionState.isEncryptionNeeded()) {
            return header;
        }

        Map<String, Payload> headerValues = EncryptionUtils.populateEncryptionEncoding(
            encryptionState,
            header != null ? header.getValues() : null
        );

        return new io.temporal.common.interceptors.Header(headerValues);
    }

    public static io.temporal.api.common.v1.Header addEncryptionEncoding(
        @Nonnull EncryptionState encryptionState,
        @Nullable io.temporal.api.common.v1.Header header
    ) {
        if (!encryptionState.isEncryptionNeeded()) {
            return header;
        }

        Map<String, Payload> headerValues = EncryptionUtils.populateEncryptionEncoding(
            encryptionState,
            header != null ? header.getFieldsMap() : null
        );

        return io.temporal.api.common.v1.Header.newBuilder().putAllFields(headerValues).build();
    }

    @Nonnull
    private static Map<String, Payload> populateEncryptionEncoding(@Nonnull EncryptionState encryptionState, Map<String, Payload> headers) {
        String keyId = encryptionState.getKeyIdRequired();

        Payload headerEncodingKeyId = DefaultDataConverter.STANDARD_INSTANCE.toPayload(keyId).orElseThrow();

        Map<String, Payload> headersResult = new HashMap<>();
        if (headers != null) {
            headersResult.putAll(headers);
        }

        headersResult.put(EncryptionConstant.HEADER_KEY_KUFLOW_ENCODING, HEADER_VALUE_KUFLOW_ENCODING_ENCRYPTED_PAYLOAD);
        headersResult.put(EncryptionConstant.HEADER_KEY_KUFLOW_ENCODING_ENCRYPTED_KEY_ID, headerEncodingKeyId);

        return headersResult;
    }

    public static Map<String, String> addEncryptionEncoding(
        @Nonnull EncryptionState encryptionState,
        @Nullable Map<String, String> headers
    ) {
        if (!encryptionState.isEncryptionNeeded()) {
            return headers;
        }

        Map<String, String> headerValues = new HashMap<>();
        if (headers != null) {
            headerValues.putAll(headers);
        }

        String keyId = encryptionState.getKeyIdRequired();

        headerValues.put(EncryptionConstant.HEADER_KEY_KUFLOW_ENCODING, EncryptionConstant.HEADER_VALUE_KUFLOW_ENCODING_ENCRYPTED_NAME);
        headerValues.put(EncryptionConstant.HEADER_KEY_KUFLOW_ENCODING_ENCRYPTED_KEY_ID, keyId);

        return headerValues;
    }

    public static Object[] markObjectsToBeEncrypted(@Nonnull EncryptionState encryptionState, Object[] arguments) {
        if (arguments == null) {
            return null;
        }

        if (!encryptionState.isEncryptionNeeded()) {
            return arguments;
        }

        arguments = Arrays.copyOf(arguments, arguments.length);

        for (int i = 0; i < arguments.length; i++) {
            arguments[i] = EncryptionWrapper.of(encryptionState, arguments[i]);
        }

        return arguments;
    }

    private static boolean isEncryptionRequired(io.temporal.common.interceptors.Header header) {
        return (
            header != null &&
            header.getValues() != null &&
            header.getValues().containsKey(EncryptionConstant.HEADER_KEY_KUFLOW_ENCODING) &&
            header.getValues().containsKey(EncryptionConstant.HEADER_KEY_KUFLOW_ENCODING_ENCRYPTED_KEY_ID) &&
            HEADER_VALUE_KUFLOW_ENCODING_ENCRYPTED_PAYLOAD.equals(header.getValues().get(EncryptionConstant.HEADER_KEY_KUFLOW_ENCODING))
        );
    }

    private EncryptionUtils() {
        throw new IllegalStateException("Utility class");
    }
}
