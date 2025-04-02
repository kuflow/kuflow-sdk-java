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
package com.kuflow.temporal.worker.encryption;

import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EncryptionState {

    public static EncryptionState empty() {
        return new EncryptionState(null);
    }

    public static EncryptionState of(@Nullable String keyId) {
        return new EncryptionState(keyId);
    }

    private String keyId;

    private EncryptionState(String keyId) {
        this.keyId = keyId;
    }

    public boolean isEncryptionNeeded() {
        return this.keyId != null;
    }

    @Nullable
    public String getKeyId() {
        return this.keyId;
    }

    @Nonnull
    public String getKeyIdRequired() {
        return Objects.requireNonNull(this.keyId, "KeyId is required");
    }

    public void merge(@Nullable EncryptionState other) {
        if (other != null && other.isEncryptionNeeded()) {
            this.keyId = other.keyId;
        } else {
            this.keyId = null;
        }
    }
}
