package org.max.helidon.raft.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.invoke.MethodHandles;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.max.helidon.raft.domain.NodeRole;
import org.max.helidon.raft.domain.RaftStateMachine;
import org.max.helidon.raft.domain.StateSnapshot;
import org.max.helidon.raft.domain.vote.VoteRequest;
import org.max.helidon.raft.domain.vote.VoteResponse;
import org.max.helidon.raft.domain.vote.VoteService;
import org.max.helidon.raft.util.NetworkUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("raft")
public class RaftController {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Inject
    private RaftStateMachine machineState;


    @Inject
    private VoteService voteService;

    @Timed
    @GET
    @Path("dump-state")
    @Produces(MediaType.APPLICATION_JSON)
    public Response debugInfo() {
        LOG.info("");

        StateSnapshot snapshot = machineState.getStateSnapshot();

        return Response.status(Response.Status.OK).
            entity(new FiniteMachineStateResponse(snapshot.persistentState().currentTerm(), snapshot.role(),
                                                  NetworkUtils.hostAddress(), "Finite Machine State for node snapshot"))
            .build();
    }

    @POST
    @Path("vote")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response vote(VoteRequest voteRequest) {
        LOG.info("vote request received for candidate: {}", voteRequest.getCandidateId());

        VoteResponse response = voteService.processVote(voteRequest);

        return Response.status(Response.Status.OK).entity(response).build();
    }

    @POST
    @Path("append")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response append() {

        LOG.info("");
        //TODO:
        return Response.status(Response.Status.OK)
            .build();

    }


    public static class FiniteMachineStateResponse {

        private final long currentTerm;

        private final NodeRole state;

        private final String hostAddress;

        private final String details;

        @JsonCreator
        public FiniteMachineStateResponse(@JsonProperty("currentTerm") long currentTerm, @JsonProperty("state") NodeRole state,
                                          @JsonProperty("hostAddress") String hostAddress,
                                          @JsonProperty("details") String details) {
            this.currentTerm = currentTerm;
            this.state = state;
            this.hostAddress = hostAddress;
            this.details = details;
        }

        public long getCurrentTerm() {
            return currentTerm;
        }

        public NodeRole getState() {
            return state;
        }

        public String getHostAddress() {
            return hostAddress;
        }

        public String getDetails() {
            return details;
        }
    }
}
