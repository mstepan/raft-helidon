package org.max.helidon.raft.domain;

import java.util.ArrayList;
import java.util.List;

public final class PersistentState {

    // latest term server has seen (initialized to 0 on first boot, increases monotonically)
    private final int currentTerm;

    // candidateId that received vote in current
    //term (or null if none)
    private final Integer votedFor;


    // log entries; each entry contains command
    //for state machine, and term when entry
    //was received by leader (first index is 1)
    private final List<LogEntry> log = new ArrayList<>();

    PersistentState(int currentTerm, Integer votedFor) {
        this.currentTerm = currentTerm;
        this.votedFor = votedFor;
    }

    public int currentTerm() {
        return currentTerm;
    }

    public Integer votedFor() {
        return votedFor;
    }


    @Override
    public String toString() {
        return "currentTerm: " + currentTerm + ", votedFor: " + votedFor;
    }
}
