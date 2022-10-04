/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.payload.codec.encryption;

/**
 * Class to hold encryption data information
 */
public class EncryptionInfo {

    private final String secretKeyId;

    private final String cipher;

    public EncryptionInfo(String secretKeyId, String cipher) {
        this.secretKeyId = secretKeyId;
        this.cipher = cipher;
    }

    /**
     * Get the secret key used for encrypt/decrypt a payload
     * @return the secret key id
     */
    public String getSecretKeyId() {
        return this.secretKeyId;
    }

    /**
     * Get the cipher used for encrypt/decrypt a payload
     * @return the cipher ma,e
     */
    public String getCipher() {
        return this.cipher;
    }
}
