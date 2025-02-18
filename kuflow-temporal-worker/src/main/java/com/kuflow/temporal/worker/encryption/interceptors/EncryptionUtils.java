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
import com.kuflow.temporal.worker.encryption.converter.EncryptionWrapper;
import io.temporal.api.common.v1.Payload;
import io.temporal.common.converter.DefaultDataConverter;
import io.temporal.common.interceptors.Header;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class EncryptionUtils {

    private static final Payload METADATA_KUFLOW_ENCODING_ENCRYPTED_PAYLOAD = DefaultDataConverter.STANDARD_INSTANCE.toPayload(
        EncryptionConstant.METADATA_KUFLOW_ENCODING_ENCRYPTED_NAME
    ).orElseThrow();

    public static boolean existEncryptionEncoding(Header header) {
        return (
            header != null &&
            header.getValues() != null &&
            header.getValues().containsKey(EncryptionConstant.METADATA_KUFLOW_ENCODING_KEY) &&
            METADATA_KUFLOW_ENCODING_ENCRYPTED_PAYLOAD.equals(header.getValues().get(EncryptionConstant.METADATA_KUFLOW_ENCODING_KEY))
        );
    }

    public static Header addEncryptionEncoding(Header header) {
        Map<String, Payload> headerValues = new HashMap<>(header.getValues());
        headerValues.put(EncryptionConstant.METADATA_KUFLOW_ENCODING_KEY, METADATA_KUFLOW_ENCODING_ENCRYPTED_PAYLOAD);

        return new Header(headerValues);
    }

    public static Object[] markObjectsToBeEncrypted(Object[] arguments) {
        arguments = Arrays.copyOf(arguments, arguments.length);

        for (int i = 0; i < arguments.length; i++) {
            arguments[i] = EncryptionWrapper.of(arguments[i]);
        }

        return arguments;
    }

    private EncryptionUtils() {
        throw new IllegalStateException("Utility class");
    }
}
