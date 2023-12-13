package breakout.core.logging;

import breakout.core.logging.tags.LogType;

/**
 * Used to log events involving auditing, transaction and security.
 * For more information on audit logging, see <a href="https://www.datadoghq.com/knowledge-center/audit-logging/">DataDog Audit Logging</a> .
 */
public class AuditLogger extends Logger {
    private static AuditLogger instance;

    public static synchronized AuditLogger getInstance() {
        if (instance == null) {
            instance = new AuditLogger();
        }

        return instance;
    }

    private AuditLogger() {}

    @Override
    protected LogType defineType() {
        return LogType.AUDIT;
    }
}
