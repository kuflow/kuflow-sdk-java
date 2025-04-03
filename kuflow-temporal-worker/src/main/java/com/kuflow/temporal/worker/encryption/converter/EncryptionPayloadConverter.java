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
package com.kuflow.temporal.worker.encryption.converter;

import com.google.protobuf.ByteString;
import com.kuflow.temporal.worker.encryption.EncryptionConstant;
import com.kuflow.temporal.worker.encryption.EncryptionState;
import io.temporal.api.common.v1.Payload;
import io.temporal.common.converter.DataConverterException;
import io.temporal.common.converter.PayloadConverter;
import java.lang.reflect.Type;
import java.util.Optional;

public class EncryptionPayloadConverter implements PayloadConverter {

    private final PayloadConverter delegate;

    public EncryptionPayloadConverter(PayloadConverter delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getEncodingType() {
        return this.delegate.getEncodingType();
    }

    @Override
    public Optional<Payload> toData(Object value) throws DataConverterException {
        EncryptionState encryptionState = EncryptionState.empty();
        if (value instanceof EncryptionWrapper encryptionWrapper) {
            encryptionState = encryptionWrapper.getEncryptionState();
            value = encryptionWrapper.getValue();
        }

        Optional<Payload> data = this.delegate.toData(value);

        if (data.isPresent() && encryptionState.isEncryptionNeeded()) {
            ByteString keyId = ByteString.copyFromUtf8(encryptionState.getKeyIdRequired());

            data = Optional.of(
                data.get().toBuilder().putMetadata(EncryptionConstant.METADATA_KEY_ENCODING_ENCRYPTED_KEY_ID, keyId).build()
            );
        }

        return data;
    }

    @Override
    public <T> T fromData(Payload content, Class<T> valueType, Type valueGenericType) throws DataConverterException {
        return this.delegate.fromData(content, valueType, valueGenericType);
    }
}
