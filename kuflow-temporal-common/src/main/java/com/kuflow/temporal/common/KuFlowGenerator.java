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
package com.kuflow.temporal.common;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class KuFlowGenerator {

    private long counter = 0;

    private final String seed;

    public KuFlowGenerator(UUID seed) {
        this.seed = seed.toString();
    }

    /**
     * Replay safe way to generate UUID.
     * <br>
     * We recommend using this method to create UUIDs instead of the one provided by Temporal
     * (io.temporal.workflow.Workflow#randomUUID()) because the generation remains consistent
     * in new executions of the same WorkFlow. To do this you simply have to initialize the
     * seed with the same value, for example we recommend the ProcessId of the process in KuFlow.
     *
     * @return deterministic UUID
     */
    public UUID randomUUID() {
        return this.nameUUID(String.valueOf(this.counter++));
    }

    /**
     * Replay safe way to generate UUID. Unlike the ({@link KuFlowGenerator#randomUUID()}) method,
     * it is passed as an argument a marker that always guarantees the same UUID regardless of its
     * calling order in the WorkFlow.
     * <br>
     * We recommend using this method to create UUIDs instead of the one provided by Temporal
     * (io.temporal.workflow.Workflow#randomUUID()) because the generation remains consistent
     * in new executions of the same WorkFlow. To do this you simply have to initialize the
     * seed with the same value, for example we recommend the ProcessId of the process in KuFlow.
     *
     * @param marker string in order to get the same UUID
     * @return deterministic UUID
     */
    public UUID fixedUUID(String marker) {
        return this.nameUUID(marker);
    }

    private UUID nameUUID(String marker) {
        String id = this.seed + ":" + marker;
        byte[] bytes = id.getBytes(StandardCharsets.UTF_8);

        return UUID.nameUUIDFromBytes(bytes);
    }
}
