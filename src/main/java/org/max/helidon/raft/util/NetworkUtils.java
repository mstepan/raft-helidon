package org.max.helidon.raft.util;

import java.lang.invoke.MethodHandles;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NetworkUtils {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private NetworkUtils() {
        throw new AssertionError("Can't instantiate utility-only class.");
    }

    public static String hostAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException ex) {
            LOG.error("Can't detect host name", ex);
        }
        return "<undefined>";
    }
}
