/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.payload.codec;

import static java.util.stream.Collectors.toList;

import com.google.protobuf.ByteString;
import com.kuflow.engine.client.common.payload.codec.encryption.EncryptionInfo;
import com.kuflow.engine.client.common.payload.codec.encryption.PayloadEncryptor;
import io.temporal.api.common.v1.Payload;
import io.temporal.common.converter.EncodingKeys;
import io.temporal.payload.codec.PayloadCodec;
import io.temporal.payload.codec.PayloadCodecException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.annotation.Nonnull;

/**
 * PayloadCodec that uses a {@link PayloadEncryptor}.
 */
public class EncryptionPayloadCodec implements PayloadCodec {

    private static final ByteString METADATA_ENCODING = ByteString.copyFrom("binary/encrypted", StandardCharsets.UTF_8);

    private static final String METADATA_ENCRYPTION_CIPHER_KEY = "encryption-cipher";

    private static final String METADATA_ENCRYPTION_KEY_ID_KEY = "encryption-key-id";

    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    private final PayloadEncryptor payloadEncryptor;

    /**
     * Constructs payloadCodec that uses a {@link PayloadEncryptor}.
     * @param payloadEncryptor the payloadEncryptor
     */
    public EncryptionPayloadCodec(PayloadEncryptor payloadEncryptor) {
        this.payloadEncryptor = payloadEncryptor;
    }

    @Nonnull
    @Override
    public List<Payload> encode(@Nonnull List<Payload> payloads) {
        return payloads.stream().map(this::encodePayload).collect(toList());
    }

    @Nonnull
    @Override
    public List<Payload> decode(@Nonnull List<Payload> payloads) {
        return payloads.stream().map(this::decodePayload).collect(toList());
    }

    private Payload encodePayload(Payload payload) {
        EncryptionInfo encryptionInfo = this.payloadEncryptor.getEncryptionInfo(payload);

        byte[] encryptedData = this.payloadEncryptor.encrypt(payload.toByteArray(), encryptionInfo);

        ByteString metadataEncryptionCipherKey = ByteString.copyFrom(encryptionInfo.getCipher(), StandardCharsets.UTF_8);
        ByteString metadataEncryptionKeyIdKey = ByteString.copyFromUtf8(encryptionInfo.getSecretKeyId());

        return Payload
            .newBuilder()
            .putMetadata(EncodingKeys.METADATA_ENCODING_KEY, METADATA_ENCODING)
            .putMetadata(METADATA_ENCRYPTION_CIPHER_KEY, metadataEncryptionCipherKey)
            .putMetadata(METADATA_ENCRYPTION_KEY_ID_KEY, metadataEncryptionKeyIdKey)
            .setData(ByteString.copyFrom(encryptedData))
            .build();
    }

    private Payload decodePayload(Payload payload) {
        ByteString payloadEncoding = payload.getMetadataOrDefault(EncodingKeys.METADATA_ENCODING_KEY, null);
        if (METADATA_ENCODING.equals(payloadEncoding)) {
            EncryptionInfo encryptionInfo = this.getPayloadMetadataSecretKeyInfoOrThrow(payload);

            byte[] plainData = this.payloadEncryptor.decrypt(payload.getData().toByteArray(), encryptionInfo);

            return this.parsePayloadFrom(plainData);
        } else {
            return payload;
        }
    }

    private Payload parsePayloadFrom(byte[] plainData) {
        try {
            return Payload.parseFrom(plainData);
        } catch (Exception e) {
            throw new PayloadCodecException(e);
        }
    }

    private EncryptionInfo getPayloadMetadataSecretKeyInfoOrThrow(Payload payload) {
        try {
            String keyId = payload.getMetadataOrThrow(METADATA_ENCRYPTION_KEY_ID_KEY).toString(UTF_8);
            String cipher = payload.getMetadataOrThrow(METADATA_ENCRYPTION_CIPHER_KEY).toString(UTF_8);

            return new EncryptionInfo(keyId, cipher);
        } catch (Exception e) {
            throw new PayloadCodecException(e);
        }
    }
}
