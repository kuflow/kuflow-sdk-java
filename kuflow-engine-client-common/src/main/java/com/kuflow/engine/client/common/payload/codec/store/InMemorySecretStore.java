/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.payload.codec.store;

import com.kuflow.engine.client.common.error.KuFlowEngineClientException;
import com.kuflow.engine.client.common.payload.codec.encryption.EncryptionInfo;
import io.temporal.api.common.v1.Payload;
import java.util.Collections;
import java.util.Map;
import javax.crypto.SecretKey;

/**
 * A simple in-memory implementation of {@link SecretStore}.
 */
public class InMemorySecretStore implements SecretStore {

    private final String defaultSecretKeyId;

    private final Map<String, SecretKey> secretKeys;

    public InMemorySecretStore(String defaultSecretKeyId, Map<String, SecretKey> secretKeys) {
        this.defaultSecretKeyId = defaultSecretKeyId;
        this.secretKeys = Collections.unmodifiableMap(secretKeys);
    }

    @Override
    public String getSecretKeyId(Payload payload) {
        return this.defaultSecretKeyId;
    }

    @Override
    public SecretKey getSecretKey(EncryptionInfo encryptionInfo) {
        SecretKey secretKey = this.secretKeys.get(encryptionInfo.getSecretKeyId());
        if (secretKey == null) {
            throw new KuFlowEngineClientException(String.format("Secret key ID %s not found", encryptionInfo.getSecretKeyId()));
        }

        return secretKey;
    }
}
