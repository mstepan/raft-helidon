package org.max.helidon.raft.domain;

import java.lang.invoke.MethodHandles;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Execute periodic task in background thread with random delays 150 or 300 ms.
 */
public class Ticker implements Runnable {

    private static final long[] DELAYS_IN_MS = {150L, 300L};

    private static final Random RAND = ThreadLocalRandom.current();

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final ScheduledThreadPoolExecutor scheduledPool;

    private final RaftStateMachine stateMachine;

    public Ticker(ScheduledThreadPoolExecutor scheduledPool, RaftStateMachine stateMachine) {
        this.scheduledPool = scheduledPool;
        this.stateMachine = stateMachine;
    }

    @Override
    public void run() {
        LOG.info("<========== tick with {} ", stateMachine.getStateSnapshot());
        scheduledPool.schedule(this, randomDelay(), TimeUnit.MILLISECONDS);
    }

    private long randomDelay() {
        int randIndex = RAND.nextInt(DELAYS_IN_MS.length);
        return DELAYS_IN_MS[randIndex];
    }
}
