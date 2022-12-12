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

import com.kuflow.temporal.common.payload.codec.store.SecretStore;
import io.temporal.api.common.v1.Payload;
import io.temporal.common.converter.DataConverterException;
import io.temporal.payload.codec.PayloadCodecException;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

/**
 * PayloadEncryptor that uses AES/GCM encryption.
 */
public class AesGcmPayloadEncryptor implements PayloadEncryptor {

    private static final int GCM_NONCE_LENGTH_BYTE = 16;

    private static final int GCM_TAG_LENGTH_BIT = 128;

    private static final String CIPHER = "AES/GCM/NoPadding";

    private final SecureRandom random = new SecureRandom();

    private final SecretStore secretStore;

    /**
     * Constructs a payloadEncryptor using the secretStore passed.
     * @param secretStore the secretStore value
     */
    public AesGcmPayloadEncryptor(SecretStore secretStore) {
        this.secretStore = secretStore;
    }

    @Override
    public EncryptionInfo getEncryptionInfo(Payload payload) {
        String secretKeyId = this.secretStore.getSecretKeyId(payload);
        return new EncryptionInfo(secretKeyId, CIPHER);
    }

    @Override
    public byte[] encrypt(byte[] plainData, EncryptionInfo encryptionInfo) {
        try {
            SecretKey secretKey = this.secretStore.getSecretKey(encryptionInfo);

            byte[] nonce = this.getNonce();

            Cipher cipher = Cipher.getInstance(CIPHER);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new GCMParameterSpec(GCM_TAG_LENGTH_BIT, nonce));

            byte[] encryptedData = cipher.doFinal(plainData);
            return ByteBuffer.allocate(nonce.length + encryptedData.length).put(nonce).put(encryptedData).array();
        } catch (Throwable e) {
            throw new DataConverterException(e);
        }
    }

    @Override
    public byte[] decrypt(byte[] encryptedDataWithNonce, EncryptionInfo encryptionInfo) {
        try {
            SecretKey secretKey = this.secretStore.getSecretKey(encryptionInfo);

            ByteBuffer buffer = ByteBuffer.wrap(encryptedDataWithNonce);

            byte[] nonce = new byte[GCM_NONCE_LENGTH_BYTE];
            buffer.get(nonce);
            byte[] encryptedData = new byte[buffer.remaining()];
            buffer.get(encryptedData);

            Cipher cipher = Cipher.getInstance(CIPHER);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new GCMParameterSpec(GCM_TAG_LENGTH_BIT, nonce));

            return cipher.doFinal(encryptedData);
        } catch (Throwable e) {
            throw new PayloadCodecException(e);
        }
    }

    private byte[] getNonce() {
        byte[] nonce = new byte[GCM_NONCE_LENGTH_BYTE];
        this.random.nextBytes(nonce);

        return nonce;
    }
}
