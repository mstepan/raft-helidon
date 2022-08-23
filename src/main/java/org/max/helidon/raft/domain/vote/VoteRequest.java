package org.max.helidon.raft.domain.vote;


import com.fasterxml.jackson.annotation.JsonProperty;

public class VoteRequest {

    @JsonProperty("term")
    // candidate’s term
    private int term;

    @JsonProperty("candidateId")
    // candidate requesting vote
    private int candidateId;

    @JsonProperty("lastLogIndex")
    // index of candidate’s last log entry
    private int lastLogIndex;

    @JsonProperty("lastLogTerm")
    //term of candidate’s last log entry
    private int lastLogTerm;

    public VoteRequest() {
    }

    public VoteRequest(int term, int candidateId, int lastLogIndex, int lastLogTerm) {
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
