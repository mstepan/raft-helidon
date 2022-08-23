package org.max.helidon.raft.domain.vote;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VoteResponse {

    //currentTerm, for candidate to update itself
    private final int term;

    //true means candidate received vote
    private final boolean voteGranted;

    @JsonCreator
    public VoteResponse(@JsonProperty("term") int term, @JsonProperty("voteGranted") boolean voteGranted) {
        this.term = term;
        this.voteGranted = voteGranted;
    }

    public int getTerm() {
        return term;
    }

    public boolean isVoteGranted() {
        return voteGranted;
    }
}
