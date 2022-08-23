package org.max.helidon.raft.domain.vote;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.max.helidon.raft.domain.RaftStateMachine;
import org.max.helidon.raft.domain.StateSnapshot;

@ApplicationScoped
public class VoteService {

    @Inject
    private RaftStateMachine state;

    /**
     * Receiver implementation:
     * 1. Reply false if term < currentTerm
     * 2. If votedFor is null or candidateId, and candidate’s log is at least as up-to-date as receiver’s log, grant vote.
     */
    public VoteResponse processVote(VoteRequest request) {

        final StateSnapshot snapshot = state.getStateSnapshot();
        final int currentTerm = snapshot.persistentState().currentTerm();
        final Integer votedFor = snapshot.persistentState().votedFor();

        final int lastAppliedLogIndex = snapshot.volatileState().lastApplied();

        // 1
        if (request.getTerm() < currentTerm) {
            return new VoteResponse(currentTerm, false);
        }
        // 2
        if ((votedFor == null || votedFor == request.getCandidateId()) && request.getLastLogIndex() >= lastAppliedLogIndex) {
            return new VoteResponse(currentTerm, true);
        }

        return new VoteResponse(currentTerm, false);
    }

}
