package org.max.helidon.raft.domain;

public final class MachineState {

    private final int term;
    private final NodeRole role;

    MachineState(int term, NodeRole role) {
        this.term = term;
        this.role = role;
    }

    public NodeRole getRole() {
        return role;
    }

    public int getTerm() {
        return term;
    }

    @Override
    public String toString() {
        return "role: " + role + ", term: " + term;
    }
}
