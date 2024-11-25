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
package com.kuflow.temporal.worker.jackson;

import com.azure.core.implementation.ReflectionSerializable;
import com.azure.json.JsonSerializable;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.Arrays;

public class AutorestJsonDeserializer extends JsonDeserializer<JsonSerializable<?>> {

    private final JavaType type;

    public AutorestJsonDeserializer(JavaType type) {
        this.type = type;
    }

    @Override
    public JsonSerializable<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonLocation currentLocation = jsonParser.currentLocation();
        Object rawContent = currentLocation.contentReference().getRawContent();
        if (currentLocation.getByteOffset() == -1 || !(rawContent instanceof byte[])) {
            throw new JsonParseException(
                jsonParser,
                "Unsupported raw content " + (rawContent != null ? rawContent.getClass().getName() : "<null>")
            );
        }

        Class<?> jsonSerializable = this.type.getRawClass();

        long begin = jsonParser.currentLocation().getByteOffset();
        jsonParser.skipChildren();
        long end = jsonParser.currentLocation().getByteOffset();

        byte[] json = Arrays.copyOfRange((byte[]) rawContent, (int) begin - 1, (int) end);

        return (JsonSerializable<?>) ReflectionSerializable.deserializeAsJsonSerializable(jsonSerializable, json);
    }
}
