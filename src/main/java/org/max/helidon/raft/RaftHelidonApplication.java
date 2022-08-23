package org.max.helidon.raft;

import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Application;
import org.max.helidon.raft.rest.RaftController;

@ApplicationScoped
public class RaftHelidonApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> context = new HashSet<>();

        // controllers
        context.add(RaftController.class);

        // application lifecycle listener
        context.add(RaftAppEventListener.class);

        return context;
    }
}


