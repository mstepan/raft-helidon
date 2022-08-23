package org.max.helidon.raft.domain;

import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TickUpdater implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final RaftStateMachine stateMachine;

    public TickUpdater(RaftStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public void run() {
        LOG.info("<========== tick with {} ", stateMachine.getState());
    }
}
