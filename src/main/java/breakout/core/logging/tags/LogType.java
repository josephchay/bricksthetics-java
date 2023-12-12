package breakout.core.logging.tags;

public enum LogType {
    SYSTEM("system"),
    EVENT("event"),
    AUDIT("audit");

    private final String name;

    private LogType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
