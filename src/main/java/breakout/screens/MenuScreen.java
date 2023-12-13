package breakout.screens;

import breakout.container.DependenciesInjectable;
import breakout.helpers.AutomatedLog;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuScreen extends breakout.core.screens.MenuScreen implements DependenciesInjectable {
    public MenuScreen(Stage stage) {
        super(stage);
    }

    protected void configureListeners() {
        AutomatedLog.wrapEvent("Configuring listeners for arena.", () -> {
            view.listen();
        });
    }
}
