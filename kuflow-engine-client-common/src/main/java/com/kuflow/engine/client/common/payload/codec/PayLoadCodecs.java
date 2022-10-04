/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.payload.codec;

import com.kuflow.engine.client.common.payload.codec.encryption.PayloadEncryptor;
import io.temporal.payload.codec.PayloadCodec;

/**
 * Factory for commonly used {@link PayloadCodec}.
 */
public final class PayLoadCodecs {

    private PayLoadCodecs() {}

    /**
     * Creates an encrypted payload coded.
     * @param payloadEncryptor the payloadEncryptor used to encrypt/decrypt the payload.
     *
     * @return The payload codec
     */
    public PayloadCodec encrypted(PayloadEncryptor payloadEncryptor) {
        return new EncryptionPayloadCodec(payloadEncryptor);
    }
}
