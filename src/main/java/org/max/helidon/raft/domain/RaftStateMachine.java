package org.max.helidon.raft.domain;

import java.util.concurrent.atomic.AtomicReference;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RaftStateMachine {

    private final AtomicReference<MachineState> state = new AtomicReference<>(new MachineState(1, NodeRole.FOLLOWER));

    public MachineState getState() {
        return state.get();
    }

}
