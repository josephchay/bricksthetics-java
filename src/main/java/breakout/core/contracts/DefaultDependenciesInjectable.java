package breakout.core.contracts;

import breakout.core.audio.AudioSystem;
import breakout.core.configs.Environment;
import breakout.core.container.Container;
import breakout.core.database.DB;
import breakout.core.file.Filesystem;
import breakout.core.imaging.ImagerySystem;
import breakout.core.logging.AuditLogger;
import breakout.core.logging.EventLogger;
import breakout.core.logging.SystemLogger;
import breakout.core.routing.Router;
import breakout.core.screens.views.ViewBuilder;

public interface DefaultDependenciesInjectable {
    Container container = Container.getInstance();

    Environment env = (Environment) container.get("env");

    DB db = new DB();

    Filesystem files = (Filesystem) container.get("files");
    ImagerySystem imagery = (ImagerySystem) container.get("imagery");
    AudioSystem audios = (AudioSystem) container.get("audios");

    SystemLogger syslog = (SystemLogger) container.get("syslog");
    AuditLogger auditlog = (AuditLogger) container.get("auditlog");
    EventLogger eventlog = (EventLogger) container.get("eventlog");

    Router routes = (Router) container.get("routes");

    ViewBuilder vb = (ViewBuilder) container.get("vb");
}
