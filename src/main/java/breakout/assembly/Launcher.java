package breakout.assembly;

import breakout.container.DependenciesInjectable;
import breakout.container.ServiceBinder;
import breakout.core.controllers.Controller;
import breakout.helpers.AutomatedLog;
import breakout.screens.tags.Screen;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class Launcher extends Application implements DependenciesInjectable {
    private static boolean providersRegistered = false;

    public static void launch(String[] args) {
        registerProvidersIfUnregistered();

        AutomatedLog.wrapSys("Application launch", () -> {
            Application.launch(args);
        });
    }

    public static void registerProvidersIfUnregistered() {
        if (!providersRegistered) {
            new ServiceBinder().register();

            syslog.info("Providers registered");
            providersRegistered = true;
        }
    }

    @Override
    public void start(Stage stage) {
        AutomatedLog.wrapSys("Application startup", () -> {
            routes.setStage(stage, settings.appWindowStyle, settings.appTitle, settings.appResizable);
            routes.dispatch();
        });
    }

    public static void routes(ArrayList<Screen> screens) {
        registerProvidersIfUnregistered();

        AutomatedLog.wrapSys("Registering application routes", () -> {
            HashMap<String, Controller> routeMap = new HashMap<>();

            for (Screen screen : screens) {
                routeMap.put(screen.label, screen.controller);
            }

            routes.setRoutes(routeMap);
        });
    }

    @Override
    public void stop() {
        AutomatedLog.wrapSys("Application shutdown", () -> {
            try {
                super.stop();
            } catch (Exception e) {
                syslog.error(e.getMessage());
            }
        });
    }
}
