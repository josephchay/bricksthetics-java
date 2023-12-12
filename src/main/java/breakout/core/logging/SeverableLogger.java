package breakout.core.logging;

import breakout.core.contracts.Loggable;
import breakout.core.logging.tags.LogLevel;

import java.util.Map;

/**
 * @see <a href="https://sematext.com/blog/logging-levels/">Logging Levels Explained</a>
 */
abstract public class SeverableLogger implements Loggable {
    public void emergency(String message, Map<String, String> context) {
        log(LogLevel.EMERGENCY, message, context);
    }
    public void emergency(String message) {
        log(LogLevel.EMERGENCY, message);
    }

    public void alert(String message, Map<String, String> context) {
        log(LogLevel.ALERT, message, context);
    }
    public void alert(String message) {
        log(LogLevel.ALERT, message);
    }

    public void fatal(String message, Map<String, String> context) {
        log(LogLevel.FATAL, message, context);
    }
    public void fatal(String message) {
        log(LogLevel.FATAL, message);
    }

    public void error(String message, Map<String, String> context) {
        log(LogLevel.ERROR, message, context);
    }
    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public void warning(String message, Map<String, String> context) {
        log(LogLevel.WARNING, message, context);
    }
    public void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    public void notice(String message, Map<String, String> context) {
        log(LogLevel.NOTICE, message, context);
    }
    public void notice(String message) {
        log(LogLevel.NOTICE, message);
    }

    public void info(String message, Map<String, String> context) {
        log(LogLevel.INFO, message, context);
    }
    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void debug(String message, Map<String, String> context) {
        log(LogLevel.DEBUG, message, context);
    }
    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }
}
