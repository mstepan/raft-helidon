package org.max.helidon.raft;

import io.helidon.microprofile.server.Server;
import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private Main() {
    }

    public static void main(final String[] args) {
        // To see the JUL log output in the SLF4J logs
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        Server server = Server.builder()
            .addApplication(RaftHelidonApplication.class)
            .build();

        LOG.info("Server started at: {}:{}", server.host(), server.port());
    }


}
