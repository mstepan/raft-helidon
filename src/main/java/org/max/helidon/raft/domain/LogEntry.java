package org.max.helidon.raft.domain;

public class LogEntry {

    private final String key;
    private final String value;

    public LogEntry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String key() {
        return key;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return "key='" + key + ", value='" + value;
    }
}
