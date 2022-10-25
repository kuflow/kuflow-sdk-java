/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common;

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
     *
     * We recommend using this method to create UUIDs instead of the one provided by Temporal
     * ({@see io.temporal.workflow.Workflow#randomUUID()}) because the generation remains consistent
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
     *
     * We recommend using this method to create UUIDs instead of the one provided by Temporal
     * ({@see io.temporal.workflow.Workflow#randomUUID()}) because the generation remains consistent
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
