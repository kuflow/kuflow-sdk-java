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
package com.kuflow.temporal.common.payload.codec.encryption;

import io.temporal.api.common.v1.Payload;

/**
 * Service interface for symmetric data encryption.
 */
public interface PayloadEncryptor {
    /**
     * Get the {@link EncryptionInfo} computed for the playload passed.
     */
    EncryptionInfo getEncryptionInfo(Payload payload);

    /**
     * Encrypt the plainData byte array using the encryptionInfo passed.
     */
    byte[] encrypt(byte[] plainData, EncryptionInfo encryptionInfo);

    /**
     * Decrypt the encryptedData byte array using the encryptionInfo passed.
     */
    byte[] decrypt(byte[] encryptedData, EncryptionInfo encryptionInfo);
}
