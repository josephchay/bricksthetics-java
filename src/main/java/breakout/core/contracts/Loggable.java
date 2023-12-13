package breakout.core.contracts;

import breakout.core.logging.tags.LogLevel;

import java.util.Map;

/**
 * Defines the type of logging feature
 */
public interface Loggable {
    void log(LogLevel level, String message, Map<String, String> context);

    // Log without additional context
    void log(LogLevel level, String message);
}
