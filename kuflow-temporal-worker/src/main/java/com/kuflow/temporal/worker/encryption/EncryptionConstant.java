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

import com.google.protobuf.ByteString;
import io.temporal.common.converter.EncodingKeys;

public interface EncryptionConstant {
    String HEADER_KEY_KUFLOW_ENCODING = "x-kuflow-encoding";

    String HEADER_KEY_KUFLOW_ENCODING_ENCRYPTED_KEY_ID = "x-kuflow-encoding-encrypted-key-id";

    String HEADER_VALUE_KUFLOW_ENCODING_ENCRYPTED_NAME = "binary/encrypted?vendor=KuFlow";

    String METADATA_KEY_ENCODING = EncodingKeys.METADATA_ENCODING_KEY;

    String METADATA_KEY_ENCODING_ENCRYPTED_KEY_ID = EncodingKeys.METADATA_ENCODING_KEY + "-encrypted-key-id";

    String METADATA_VALUE_KUFLOW_ENCODING_ENCRYPTED_NAME = "binary/encrypted?vendor=KuFlow";

    ByteString METADATA_VALUE_KUFLOW_ENCODING_ENCRYPTED = ByteString.copyFromUtf8(METADATA_VALUE_KUFLOW_ENCODING_ENCRYPTED_NAME);
}
