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
package com.kuflow.temporal.worker.encryption.codec;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.protobuf.ByteString;
import com.kuflow.rest.KuFlowRestClient;
import com.kuflow.rest.model.KmsKey;
import com.kuflow.rest.operation.KmsOperations;
import com.kuflow.temporal.common.crypto.CipherUtils;
import com.kuflow.temporal.worker.encryption.EncryptionConstant;
import io.temporal.api.common.v1.Payload;
import io.temporal.common.converter.EncodingKeys;
import io.temporal.payload.codec.PayloadCodec;
import io.temporal.payload.codec.PayloadCodecException;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionPayloadCodec implements PayloadCodec {

    private final Cache<String, KmsKey> kmsKeyCache = CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.HOURS).build();

    private final KmsOperations kmsOperations;

    public EncryptionPayloadCodec(KuFlowRestClient kuFlowRestClient) {
        this.kmsOperations = kuFlowRestClient.getKmsOperations();
    }

    @Nonnull
    @Override
    public List<Payload> encode(@Nonnull List<Payload> payloads) {
        return payloads.stream().map(this::encrypt).toList();
    }

    @Nonnull
    @Override
    public List<Payload> decode(@Nonnull List<Payload> payloads) {
        return payloads.stream().map(this::decrypt).toList();
    }

    private Payload encrypt(Payload payload) {
        if (!this.needPayloadBeEncrypted(payload)) {
            return payload;
        }

        String keyId = payload.getMetadataOrThrow(EncryptionConstant.METADATA_KEY_ENCODING_ENCRYPTED_KEY_ID).toStringUtf8();

        SecretKey secretKey = this.retrieveSecretKey(keyId);

        byte[] cipherTextBytes = CipherUtils.AES_256_GCM.encrypt(secretKey, payload.toByteArray());

        String cipherTextValue = Base64.getEncoder().encodeToString(cipherTextBytes);

        String cipherText = "%s:%s".formatted(CipherUtils.AES_256_GCM.getAlgorithm(), cipherTextValue);

        return Payload.newBuilder()
            .putMetadata(EncodingKeys.METADATA_ENCODING_KEY, EncryptionConstant.METADATA_VALUE_KUFLOW_ENCODING_ENCRYPTED)
            .putMetadata(EncryptionConstant.METADATA_KEY_ENCODING_ENCRYPTED_KEY_ID, ByteString.copyFromUtf8(keyId))
            .setData(ByteString.copyFromUtf8(cipherText))
            .build();
    }

    private Payload decrypt(Payload payload) {
        if (!this.isPayloadEncrypted(payload)) {
            return payload;
        }

        String keyId = payload.getMetadataOrThrow(EncryptionConstant.METADATA_KEY_ENCODING_ENCRYPTED_KEY_ID).toStringUtf8();

        SecretKey secretKey = this.retrieveSecretKey(keyId);

        String cipherText = payload.getData().toStringUtf8();

        String[] cipherTextParts = cipherText.split(":");
        if (cipherTextParts.length != 2) {
            throw new PayloadCodecException("Invalid cipherText format: %s".formatted(cipherText.substring(0, 20)));
        }

        String cipherTextAlgorithm = cipherTextParts[0];
        String cipherTextValue = cipherTextParts[1];

        if (!CipherUtils.AES_256_GCM.getAlgorithm().equals(cipherTextAlgorithm)) {
            throw new PayloadCodecException("Invalid cipherText algorithm: %s".formatted(cipherTextAlgorithm));
        }

        byte[] cipherTextBytes = Base64.getDecoder().decode(cipherTextValue);

        byte[] plainText = CipherUtils.AES_256_GCM.decrypt(secretKey, cipherTextBytes);

        return this.parsePayloadFrom(plainText);
    }

    private boolean needPayloadBeEncrypted(Payload payload) {
        try {
            return (payload.containsMetadata(EncryptionConstant.METADATA_KEY_ENCODING_ENCRYPTED_KEY_ID));
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isPayloadEncrypted(Payload payload) {
        try {
            return (
                EncryptionConstant.METADATA_VALUE_KUFLOW_ENCODING_ENCRYPTED.equals(
                    payload.getMetadataOrDefault(EncodingKeys.METADATA_ENCODING_KEY, ByteString.EMPTY)
                ) &&
                payload.containsMetadata(EncryptionConstant.METADATA_KEY_ENCODING_ENCRYPTED_KEY_ID)
            );
        } catch (Exception e) {
            return false;
        }
    }

    private Payload parsePayloadFrom(byte[] plainData) {
        try {
            return Payload.parseFrom(plainData);
        } catch (Exception e) {
            throw new PayloadCodecException(e);
        }
    }

    private SecretKey retrieveSecretKey(String keyId) {
        try {
            KmsKey kmsKey = this.kmsKeyCache.get(keyId, () -> this.kmsOperations.retrieveKmsKey(keyId));

            return new SecretKeySpec(kmsKey.getValue(), "AES");
        } catch (ExecutionException e) {
            throw new PayloadCodecException("Unable to fetch the key %s".formatted(keyId), e);
        }
    }
}
