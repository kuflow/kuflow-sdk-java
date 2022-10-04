/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.payload.codec.encryption;

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
