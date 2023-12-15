package breakout.core.screens;

import breakout.core.screens.views.EpilogueView;
import breakout.helpers.AutomatedLog;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The screen which handles all the logic and rendering for the menu.
 */
abstract public class MenuScreen extends Screen {
    public MenuScreen(Stage stage, double width, double height) {
        super(stage, width, height);
    }

    @Override
    protected void loadTemplate() {
        AutomatedLog.wrapEvent("Loading template for epilogue screen.", () -> {
            view = new EpilogueView();
            view.build(stage);
        });
    }

    @Override
    protected void loadAssets() {
        eventlog.info("No assets to load for menu screen.");
    }
}
