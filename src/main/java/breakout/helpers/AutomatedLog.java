package breakout.helpers;

import breakout.core.contracts.DefaultDependenciesInjectable;

public class AutomatedLog implements DefaultDependenciesInjectable {
    public static void wrapSys(String message, Runnable action) {
        syslog.info(message + " initiated");
        action.run();
        syslog.info(message + " completed");
    }

    public static void wrapAudit(String message, Runnable action) {
        auditlog.info(message + " initiated");
        action.run();
        auditlog.info(message + " completed");
    }

    public static void wrapEvent(String message, Runnable action) {
        eventlog.info(message + " initiated");
        action.run();
        eventlog.info(message + " completed");
    }
}
