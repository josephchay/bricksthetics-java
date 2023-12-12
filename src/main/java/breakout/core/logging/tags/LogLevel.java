package breakout.core.logging.tags;

public enum LogLevel {
    /**
     * Represents an emergency log level. This is the most severe level, indicating
     * critical system issues requiring immediate attention, such as system failures.
     */
    EMERGENCY,

    /**
     * Represents an alert log level. Used for messages that require immediate action,
     * such as when a critical component is down or when urgent notifications are needed.
     */
    ALERT,

    /**
     * Represents a fatal log level. This level is used for messages indicating
     * fatal conditions like the unavailability of an application component or unexpected exceptions.
     */
    FATAL,

    /**
     * Represents an error log level. Used for recording runtime errors that do not require
     * immediate attention but should be logged and monitored for debugging and analysis.
     */
    ERROR,

    /**
     * Represents a warning log level. Used for exceptional occurrences that are not errors
     * but may indicate issues, such as deprecated API usage or suboptimal conditions.
     */
    WARNING,

    /**
     * Represents a notice log level. This level is for normal but significant events
     * providing information about important system activities.
     */
    NOTICE,

    /**
     * Represents an informational log level. Used for informative messages about regular
     * system events, such as user login events or general system status information.
     */
    INFO,

    /**
     * Represents a debug log level. Used for detailed debugging information, typically during
     * development. Debug messages are usually disabled in production to reduce log volume.
     */
    DEBUG,

    /**
     * Represents a log level with arbitrary or custom semantics. This level allows logging
     * with an arbitrary severity level. The specific meaning of "LOG" may vary by context.
     */
    LOG,
}
