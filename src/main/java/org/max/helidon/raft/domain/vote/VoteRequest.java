package org.max.helidon.raft.domain.vote;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VoteRequest {

    // candidate’s term
    private final int term;

    // candidate requesting vote
    private final int candidateId;

    // index of candidate’s last log entry
    private final int lastLogIndex;

    //term of candidate’s last log entry
    private final int lastLogTerm;


    @JsonCreator
    public VoteRequest(@JsonProperty("term") int term, @JsonProperty("candidateId") int candidateId,
                       @JsonProperty("lastLogIndex") int lastLogIndex, @JsonProperty("lastLogTerm") int lastLogTerm) {
        this.term = term;
        this.candidateId = candidateId;
        this.lastLogIndex = lastLogIndex;
        this.lastLogTerm = lastLogTerm;
    }

    public int getTerm() {
        return term;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public int getLastLogIndex() {
        return lastLogIndex;
    }

    public int getLastLogTerm() {
        return lastLogTerm;
    }
}
