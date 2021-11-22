/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.worker.sample.bootstrap;

import com.kuflow.engine.activity.api.task.service.KuFlowActivities;
import com.kuflow.engine.activity.api.task.service.KuFlowActivitiesDelegate;
import com.kuflow.engine.activity.api.task.service.KuFlowDetachedActivities;
import com.kuflow.engine.activity.api.task.service.KuFlowDetachedActivitiesDelegate;
import com.kuflow.engine.worker.sample.config.property.ApplicationProperties;
import com.kuflow.engine.worker.sample.workflow.sample.SampleWorkflowImpl;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class TemporalBootstrap implements InitializingBean, DisposableBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemporalBootstrap.class);

    private final WorkerFactory factory;

    private final KuFlowActivities kuflowActivities;

    private final KuFlowDetachedActivities kuflowDetachedActivities;

    private final ApplicationProperties applicationProperties;

    public TemporalBootstrap(
        ApplicationProperties applicationProperties,
        WorkerFactory factory,
        KuFlowActivities kuflowActivities,
        KuFlowDetachedActivities kuflowDetachedActivities
    ) {
        this.applicationProperties = applicationProperties;
        this.factory = factory;
        this.kuflowActivities = kuflowActivities;
        this.kuflowDetachedActivities = kuflowDetachedActivities;
    }

    @Override
    public void afterPropertiesSet() {
        this.startWorkers();
        LOGGER.info("Temporal connection initialized");
    }

    @Override
    public void destroy() {
        this.factory.shutdown();
        this.factory.awaitTermination(1, TimeUnit.MINUTES);
        LOGGER.info("Temporal connection shutdown");
    }

    private void startWorkers() {
        Worker worker = this.factory.newWorker(this.applicationProperties.getTemporal().getKuflowQueue());
        worker.registerWorkflowImplementationTypes(SampleWorkflowImpl.class);
        worker.registerActivitiesImplementations(new KuFlowActivitiesDelegate(this.kuflowActivities));
        worker.registerActivitiesImplementations(new KuFlowDetachedActivitiesDelegate(this.kuflowDetachedActivities));

        this.factory.start();
    }
}
