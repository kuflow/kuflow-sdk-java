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

import com.google.protobuf.ByteString;
import com.kuflow.rest.KuFlowRestClient;
import com.kuflow.rest.model.VaultCodecPayload;
import com.kuflow.rest.model.VaultCodecPayloads;
import com.kuflow.rest.operation.VaultOperations;
import com.kuflow.temporal.worker.encryption.EncryptionConstant;
import io.temporal.api.common.v1.Payload;
import io.temporal.common.converter.EncodingKeys;
import io.temporal.payload.codec.PayloadCodec;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;

public class EncryptionPayloadCodec implements PayloadCodec {

    private final UUID tenantId;

    private final VaultOperations vaultOperations;

    public EncryptionPayloadCodec(UUID tenantId, KuFlowRestClient kuFlowRestClient) {
        this.tenantId = tenantId;
        this.vaultOperations = kuFlowRestClient.getVaultOperations();
    }

    @Nonnull
    @Override
    public List<Payload> encode(@Nonnull List<Payload> payloads) {
        List<Payload> payloadsToEncrypt = payloads.stream().filter(this::needPayloadBeEncrypted).toList();

        List<Payload> payloadsEncrypted = this.encrypt(payloadsToEncrypt);

        return payloads
            .stream()
            .map(it -> {
                if (!this.needPayloadBeEncrypted(it)) {
                    return it;
                }

                int index = payloadsToEncrypt.indexOf(it);
                return payloadsEncrypted.get(index);
            })
            .toList();
    }

    @Nonnull
    @Override
    public List<Payload> decode(@Nonnull List<Payload> payloads) {
        List<Payload> payloadsToDecrypt = payloads.stream().filter(this::isPayloadEncrypted).toList();

        List<Payload> payloadsDecrypted = this.decrypt(payloadsToDecrypt);

        return payloads
            .stream()
            .map(it -> {
                if (!this.isPayloadEncrypted(it)) {
                    return it;
                }

                int index = payloadsToDecrypt.indexOf(it);
                return payloadsDecrypted.get(index);
            })
            .toList();
    }

    private boolean needPayloadBeEncrypted(Payload payload) {
        try {
            return (
                EncryptionConstant.METADATA_KUFLOW_ENCODING_ENCRYPTED_BYTE_STRING.equals(
                    payload.getMetadataOrDefault(EncryptionConstant.METADATA_KUFLOW_ENCODING_KEY, ByteString.EMPTY)
                )
            );
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isPayloadEncrypted(Payload payload) {
        try {
            return (
                EncryptionConstant.METADATA_KUFLOW_ENCODING_ENCRYPTED_BYTE_STRING.equals(
                    payload.getMetadataOrDefault(EncodingKeys.METADATA_ENCODING_KEY, ByteString.EMPTY)
                )
            );
        } catch (Exception e) {
            return false;
        }
    }

    private List<Payload> encrypt(List<Payload> payloads) {
        if (payloads.isEmpty()) {
            return List.of();
        }

        List<VaultCodecPayload> requestPayloads = payloads.stream().map(this::transform).toList();

        VaultCodecPayloads request = new VaultCodecPayloads().setTenantId(this.tenantId).setPayloads(requestPayloads);

        VaultCodecPayloads response = this.vaultOperations.codecEncode(request);

        return response.getPayloads().stream().map(this::transform).toList();
    }

    private List<Payload> decrypt(List<Payload> payloads) {
        if (payloads.isEmpty()) {
            return List.of();
        }

        List<VaultCodecPayload> requestPayloads = payloads.stream().map(this::transform).toList();

        VaultCodecPayloads request = new VaultCodecPayloads().setTenantId(this.tenantId).setPayloads(requestPayloads);

        VaultCodecPayloads response = this.vaultOperations.codecDecode(request);

        return response.getPayloads().stream().map(this::transform).toList();
    }

    private VaultCodecPayload transform(Payload payload) {
        VaultCodecPayload codecPayload = new VaultCodecPayload();
        if (!payload.getMetadataMap().isEmpty()) {
            Map<String, byte[]> metadata = payload
                .getMetadataMap()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toByteArray()));

            codecPayload.setMetadata(metadata);
        }

        codecPayload.setData(payload.getData().toByteArray());

        return codecPayload;
    }

    private Payload transform(VaultCodecPayload codecPayload) {
        Payload.Builder builder = Payload.newBuilder();
        if (codecPayload.getMetadata() != null && !codecPayload.getMetadata().isEmpty()) {
            Map<String, ByteString> metadata = codecPayload
                .getMetadata()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> ByteString.copyFrom(e.getValue())));

            builder.putAllMetadata(metadata);
        }

        builder.setData(ByteString.copyFrom(codecPayload.getData()));

        return builder.build();
    }
}
