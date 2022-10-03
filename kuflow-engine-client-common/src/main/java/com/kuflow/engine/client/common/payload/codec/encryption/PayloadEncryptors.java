/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.payload.codec.encryption;

import com.kuflow.engine.client.common.payload.codec.store.SecretStore;

/**
 * Factory for commonly used {@link PayloadEncryptor}.
 */
public final class PayloadEncryptors {

    private PayloadEncryptors() {}

    /**
     * Creates an AES/GCM payload encryptor.
     * @param secretStore the secretStore used to get the secrets keys.
     *
     * @return The payload encryptor
     */
    public static AesGcmPayloadEncryptor aesGcm(SecretStore secretStore) {
        return new AesGcmPayloadEncryptor(secretStore);
    }
}
