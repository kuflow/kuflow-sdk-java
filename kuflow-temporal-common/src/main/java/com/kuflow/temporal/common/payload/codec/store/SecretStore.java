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
package com.kuflow.temporal.common.payload.codec.store;

import com.kuflow.temporal.common.error.KuFlowTemporalException;
import com.kuflow.temporal.common.payload.codec.encryption.EncryptionInfo;
import io.temporal.api.common.v1.Payload;
import javax.crypto.SecretKey;

/**
 * Service interface to allow to get secret keys.
 */
public interface SecretStore {
    /**
     * Get the secreted key id related to the payload passed.
     *
     * @param payload the payload
     * @return the secreted key id
     */
    String getSecretKeyId(Payload payload);

    /**
     * Get the secreted key related to the encryption passed if it doesn't exist a secret key for the {@code encryptionInfo} passed, then
     * a {@link KuFlowTemporalException} exception is thrown.
     *
     * @param encryptionInfo the encryption info
     * @return the secreted key found
     */
    SecretKey getSecretKey(EncryptionInfo encryptionInfo);
}
