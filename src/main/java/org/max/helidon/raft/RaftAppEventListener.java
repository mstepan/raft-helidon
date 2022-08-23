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
import org.max.helidon.raft.domain.Ticker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class RaftAppEventListener implements ApplicationEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final long INITIAL_DELAY_FOR_TICKER_IN_MS = 2000L;

    @Inject
    private RaftStateMachine stateMachine;

    private final ScheduledThreadPoolExecutor scheduledPool = new ScheduledThreadPoolExecutor(1);

    @Override
    public void onEvent(ApplicationEvent applicationEvent) {
        if (isInit(applicationEvent.getType())) {
            LOG.info("TickUpdater scheduled with initial delay {}ms", INITIAL_DELAY_FOR_TICKER_IN_MS);
            scheduledPool.schedule(new Ticker(scheduledPool, stateMachine), INITIAL_DELAY_FOR_TICKER_IN_MS,
                                   TimeUnit.MILLISECONDS);
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
