/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.payload.codec.store;

import java.util.Map;
import javax.crypto.SecretKey;

/**
 * Factory for commonly used {@link SecretStore}.
 */
public final class SecretStores {

    private SecretStores() {}

    /**
     * Creates a memory {@link SecretStore}.
     * @param defaultSecretKeyId the default secret key id to use when new payloads needs to be encrypted.
     * @param secretKeys the secret keys to use.
     *
     * @return A secret stored implemented in memory
     */
    public static SecretStore memory(String defaultSecretKeyId, Map<String, SecretKey> secretKeys) {
        return new InMemorySecretStore(defaultSecretKeyId, secretKeys);
    }
}
