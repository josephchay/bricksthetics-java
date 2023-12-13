package breakout.core.container;

import breakout.core.audio.AudioSystem;
import breakout.core.configs.Environment;
import breakout.core.database.Connector;
import breakout.core.file.Filesystem;
import breakout.core.imaging.ImagerySystem;
import breakout.core.logging.AuditLogger;
import breakout.core.logging.EventLogger;
import breakout.core.logging.SystemLogger;
import breakout.core.routing.Router;
import breakout.core.screens.views.ViewBuilder;

/**
 * Registers default service bindings for the application.
 * Can be further extended to register custom bindings.
 */
public class DefaultServiceBinder {
    protected final Container container = Container.getInstance();

    public void register() {
        registerEnv();
        registerSystems();
        registerLoggers();
        registerDB();
        registerRouter();
//        registerLeveler();
        registerViewBuilder();
    }

    private void registerEnv() {
        container.registerSingleton("env", Environment.getInstance());
    }

    private void registerSystems() {
        container.registerSingleton("files", Filesystem.getInstance());
        container.registerSingleton("imagery", ImagerySystem.getInstance());
        container.registerSingleton("audios", AudioSystem.getInstance());
    }

    private void registerLoggers() {
        container.registerSingleton("syslog", SystemLogger.getInstance());
        container.registerSingleton("auditlog", AuditLogger.getInstance());
        container.registerSingleton("eventlog", EventLogger.getInstance());
    }

    private void registerDB() {
        container.registerSingleton("database", Connector.getInstance());
    }

    private void registerRouter() {
        container.registerSingleton("routes", Router.getInstance());
    }
//    private void registerLeveler() {
//        Leveler leveler = new Leveler();
//        Settings settings = (Settings) container.make("settings");
//
//        container.registerSingleton("leveler", leveler);
//    }

    private void registerViewBuilder() {
        container.registerSingleton("vb", ViewBuilder.getInstance());
    }
}
