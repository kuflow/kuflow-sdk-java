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
package com.kuflow.rest.util;

import com.github.f4b6a3.uuid.UuidCreator;
import java.util.Optional;
import java.util.UUID;

public final class UUIDUtils {

    private UUIDUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Optional<UUID> tryParseUUID(String value) {
        try {
            if (value == null) {
                return Optional.empty();
            }

            return Optional.of(UuidCreator.fromString(value));
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }

    public static UUID fromString(String value) {
        return UuidCreator.fromString(value);
    }

    public static UUID generateUUIDv7() {
        return UuidCreator.getTimeOrderedEpoch();
    }
}
