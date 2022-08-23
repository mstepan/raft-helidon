package org.max.helidon.raft.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.invoke.MethodHandles;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("raft")
public class RaftController {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Timed
    @GET
    @Path("debug-info")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test() {
        LOG.info("");
        return Response.status(Response.Status.OK).entity(new FiniteMachineStateResponse(1L, 133L, "Finite State for node: "))
            .build();

    }

    public static class FiniteMachineStateResponse {

        private final long term;

        private final long logId;

        private final String details;

        @JsonCreator
        public FiniteMachineStateResponse(@JsonProperty("term") long term, @JsonProperty("logId") long logId,
                                          @JsonProperty("details") String details) {
            this.term = term;
            this.logId = logId;
            this.details = details;
        }

        public long getTerm() {
            return term;
        }

        public long getLogId() {
            return logId;
        }

        public String getDetails() {
            return details;
        }
    }
}
