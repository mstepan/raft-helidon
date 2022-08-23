package org.max.helidon.raft;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;
import org.max.helidon.raft.domain.RaftStateMachine;
import org.max.helidon.raft.domain.TickUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class RaftAppEventListener implements ApplicationEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Inject
    private RaftStateMachine stateMachine;

    private final ScheduledThreadPoolExecutor scheduledPool = new ScheduledThreadPoolExecutor(1);

    @Override
    public void onEvent(ApplicationEvent applicationEvent) {
        if (isInit(applicationEvent.getType())) {
            LOG.info("TickUpdater scheduled with initial delay {}ms at fixed rate {}ms", 2000L, 300L);
            scheduledPool.scheduleAtFixedRate(new TickUpdater(stateMachine), 2000L, 300L, TimeUnit.MILLISECONDS);
        }
        else if (isDestroy(applicationEvent.getType())) {
            scheduledPool.shutdownNow();
            LOG.info("TickUpdater shutdown completed");
        }
    }

    @Override
    public RequestEventListener onRequest(RequestEvent requestEvent) {
        return null;
    }

    private boolean isInit(ApplicationEvent.Type eventType) {
        return ApplicationEvent.Type.INITIALIZATION_FINISHED.equals(eventType);
    }

    private boolean isDestroy(ApplicationEvent.Type eventType) {
        return ApplicationEvent.Type.DESTROY_FINISHED.equals(eventType);
    }
}
