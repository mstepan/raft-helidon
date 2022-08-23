package org.max.helidon.raft.domain;

import java.util.ArrayList;
import java.util.List;

public final class VolatileState {

    //index of highest log entry known to be committed (initialized to 0, increases monotonically)
    private final int commitIndex;

    //index of highest log entry applied to state machine (initialized to 0, increases monotonically)
    private final int lastApplied;

    public VolatileState(int commitIndex, int lastApplied) {
        this.commitIndex = commitIndex;
        this.lastApplied = lastApplied;
    }

    public int commitIndex() {
        return commitIndex;
    }

    public int lastApplied() {
        return lastApplied;
    }
}
