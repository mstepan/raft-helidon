package org.max.helidon.raft.domain;

public final class StateSnapshot {

    private final NodeRole role;
    private final PersistentState persistentState;
    private final VolatileState volatileState;

    StateSnapshot(NodeRole role, PersistentState persistentState, VolatileState volatileState) {
        this.role = role;
        this.persistentState = persistentState;
        this.volatileState = volatileState;
    }

    public NodeRole role() {
        return role;
    }

    public PersistentState persistentState() {
        return persistentState;
    }

    public VolatileState volatileState() {
        return volatileState;
    }
}
