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
package com.kuflow.temporal.worker.codec.store;

import com.kuflow.temporal.common.error.KuFlowTemporalException;
import com.kuflow.temporal.worker.codec.encryption.EncryptionInfo;
import io.temporal.api.common.v1.Payload;
import java.util.Collections;
import java.util.Map;
import javax.crypto.SecretKey;

/**
 * A simple in-memory implementation of {@link SecretStore}.
 */
public class InMemorySecretStore implements SecretStore {

    private final String defaultSecretKeyId;

    private final Map<String, SecretKey> secretKeys;

    public InMemorySecretStore(String defaultSecretKeyId, Map<String, SecretKey> secretKeys) {
        this.defaultSecretKeyId = defaultSecretKeyId;
        this.secretKeys = Collections.unmodifiableMap(secretKeys);
    }

    @Override
    public String getSecretKeyId(Payload payload) {
        return this.defaultSecretKeyId;
    }

    @Override
    public SecretKey getSecretKey(EncryptionInfo encryptionInfo) {
        SecretKey secretKey = this.secretKeys.get(encryptionInfo.getSecretKeyId());
        if (secretKey == null) {
            throw new KuFlowTemporalException(String.format("Secret key ID %s not found", encryptionInfo.getSecretKeyId()));
        }

        return secretKey;
    }
}
