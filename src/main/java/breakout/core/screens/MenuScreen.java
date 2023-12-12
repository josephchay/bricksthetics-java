package breakout.core.screens;

import breakout.core.screens.views.EpilogueView;
import breakout.helpers.AutomatedLog;
import javafx.scene.Scene;
import javafx.stage.Stage;

abstract public class MenuScreen extends Screen {
    public MenuScreen(Stage stage) {
        super(stage);
    }

    /**
     * Load the static UI template
     */
    @Override
    protected void loadTemplate() {
        AutomatedLog.wrapEvent("Loading template for epilogue screen.", () -> {
            view = new EpilogueView();
            view.build(stage);
        });
    }

    /**
     * Load all assets for the menu.
     */
    @Override
    protected void loadAssets() {
        AutomatedLog.wrapEvent("Loading assets for epilogue screen.", () -> {
            syslog.info("No assets assigned.");
        });
    }

    @Override
    public void render() {
        AutomatedLog.wrapEvent("Rendering screen for menu.", () -> {

        });
    }
}
