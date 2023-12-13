package breakout.core.logging;

import breakout.core.logging.tags.LogType;

/**
 * Involves in logging client-user events.
 * To be used for customer analysis and product improvement.
 */
public class EventLogger extends Logger {
    private static EventLogger instance;

    public static synchronized EventLogger getInstance() {
        if (instance == null) {
            instance = new EventLogger();
        }

        return instance;
    }

    private EventLogger() {}

    @Override
    protected LogType defineType() {
        return LogType.EVENT;
    }
}
