/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.payload.codec.store;

import com.kuflow.engine.client.common.error.KuFlowEngineClientException;
import com.kuflow.engine.client.common.payload.codec.encryption.EncryptionInfo;
import io.temporal.api.common.v1.Payload;
import javax.crypto.SecretKey;

/**
 * Service interface to allow to get secret keys.
 */
public interface SecretStore {
    /**
     * Get the secreted key id related to the payload passed.
     *
     * @param payload the payload
     * @return the secreted key id
     */
    String getSecretKeyId(Payload payload);

    /**
     * Get the secreted key related to the encryption passed if it doesn't exist a secret key for the {@code encryptionInfo} passed, then
     * a {@link KuFlowEngineClientException} exception is thrown.
     *
     * @param encryptionInfo the encryption info
     * @return the secreted key found
     */
    SecretKey getSecretKey(EncryptionInfo encryptionInfo);
}
