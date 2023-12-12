package breakout.core.routing;

import breakout.core.contracts.DefaultDependenciesInjectable;
import breakout.core.contracts.Routable;
import breakout.core.controllers.Controller;
import breakout.core.utils.Helper;
import breakout.helpers.AutomatedLog;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.HashMap;
import java.util.Map;

public class Router implements Routable, DefaultDependenciesInjectable {
    private static Router instance;

    private RouteCollection routes;

    public static synchronized Router getInstance() {
        if (instance == null) {
            instance = new Router();
        }

        return instance;
    }

    private Router() {}

    private Controller screenController;

    private Scene scene;

    public void setStage(Stage stage, StageStyle style, String title, boolean resizable) {
        screenController.setStage(stage, style, title, resizable);
    }

    public void dispatch() {
        AutomatedLog.wrapSys("Dispatching route", () -> {
            screenController.index();
        });
    }

    /**
     * Collect the given routes into a collection.
     * Assign the first given route controller to be the screen controller.
     *
     * @param routes The routes to collect
     */
    public void setRoutes(HashMap<String, Controller> routes) {
        this.routes = new RouteCollection(routes);

//        Map.Entry<String, Controller> entry = this.routes.getCollected().entrySet().iterator().next();

//        screenController = routes.get(entry.getKey());
        screenController = routes.get("arena");
    }
}
