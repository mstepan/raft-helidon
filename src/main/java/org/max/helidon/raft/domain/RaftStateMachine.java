package org.max.helidon.raft.domain;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RaftStateMachine {

    private volatile NodeRole role = NodeRole.FOLLOWER;

    private volatile PersistentState persistentState = new PersistentState(1, null);

    private volatile VolatileState volatileState = new VolatileState(0, 0);

    public synchronized StateSnapshot getStateSnapshot() {
        return new StateSnapshot(role, persistentState, volatileState);
    }
}
