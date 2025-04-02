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
package com.kuflow.temporal.common.crypto;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class CipherUtils {

    public static final CipherAlgorithm AES_256_GCM = new AES256GCM();

    private static byte[] generateIV(int size) {
        byte[] nonce = new byte[size];
        new SecureRandom().nextBytes(nonce);

        return nonce;
    }

    public interface CipherAlgorithm {
        String getAlgorithm();

        byte[] encrypt(SecretKey secretKey, byte[] plainText);

        byte[] decrypt(SecretKey secretKey, byte[] cipherText);
    }

    private static class AES256GCM implements CipherAlgorithm {

        private static final String AES_256_GCM = "AES-256-GCM";

        private static final String CIPHER = "AES/GCM/NoPadding";

        private static final int GCM_NONCE_LENGTH_BYTE = 12;

        private static final int GCM_TAG_LENGTH_BIT = 128;

        @Override
        public String getAlgorithm() {
            return AES_256_GCM;
        }

        @Override
        public byte[] encrypt(SecretKey secretKey, byte[] plainText) {
            try {
                byte[] nonce = CipherUtils.generateIV(GCM_NONCE_LENGTH_BYTE);

                Cipher cipher = Cipher.getInstance(CIPHER);
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, new GCMParameterSpec(GCM_TAG_LENGTH_BIT, nonce));

                byte[] encryptedData = cipher.doFinal(plainText);

                return ByteBuffer.allocate(nonce.length + encryptedData.length).put(nonce).put(encryptedData).array();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public byte[] decrypt(SecretKey secretKey, byte[] cipherText) {
            try {
                ByteBuffer byteBuffer = ByteBuffer.wrap(cipherText);

                byte[] nonce = new byte[GCM_NONCE_LENGTH_BYTE];
                byteBuffer.get(nonce);

                byte[] encryptedData = new byte[byteBuffer.remaining()];
                byteBuffer.get(encryptedData);

                Cipher cipher = Cipher.getInstance(CIPHER);
                cipher.init(Cipher.DECRYPT_MODE, secretKey, new GCMParameterSpec(GCM_TAG_LENGTH_BIT, nonce));

                return cipher.doFinal(encryptedData);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
