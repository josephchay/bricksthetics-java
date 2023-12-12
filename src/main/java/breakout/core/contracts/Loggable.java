package breakout.core.contracts;

import breakout.core.logging.tags.LogLevel;

import java.util.Map;

public interface Loggable {
    public void log(LogLevel level, String message, Map<String, String> context);

    // Log without additional context
    public void log(LogLevel level, String message);
}
