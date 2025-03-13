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
package com.kuflow.rest.operation;

import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.givenThat;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;

import com.kuflow.rest.model.VaultCodecPayloads;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VaultOperationTest extends AbstractOperationTest {

    @Test
    @DisplayName("GIVEN an authenticated user WHEN encode THEN authentication header is added")
    public void givenAnAuthenticatedUserWhenEncodeThenAuthenticationHeaderIsAdded() {
        givenThat(
            post("/v2024-06-14/vault/codec/~actions/encode")
                .withHeader("Authorization", equalTo("Bearer Q0xJRU5UX0lEOkNMSUVOVF9TRUNSRVQ="))
                .withHeader("Content-Type", containing("application/json"))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("vault-api.ok.json"))
        );

        VaultCodecPayloads payloads = new VaultCodecPayloads();
        this.kuFlowRestClient.getVaultOperations().codecEncode(payloads);
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN decode THEN authentication header is added")
    public void givenAnAuthenticatedUserWhenDecodeThenAuthenticationHeaderIsAdded() {
        givenThat(
            post("/v2024-06-14/vault/codec/~actions/decode")
                .withHeader("Authorization", equalTo("Bearer Q0xJRU5UX0lEOkNMSUVOVF9TRUNSRVQ="))
                .withHeader("Content-Type", containing("application/json"))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("vault-api.ok.json"))
        );

        VaultCodecPayloads payloads = new VaultCodecPayloads();
        this.kuFlowRestClient.getVaultOperations().codecDecode(payloads);
    }
}
