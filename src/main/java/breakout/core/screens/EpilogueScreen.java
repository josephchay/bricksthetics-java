package breakout.core.screens;

import breakout.core.screens.views.EpilogueView;
import breakout.helpers.AutomatedLog;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The screen which handles all the logic and rendering for the epilogue or end.
 */
abstract public class EpilogueScreen extends Screen {
    public EpilogueScreen(Stage stage) {
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
     * Load all assets for the epilogue.
     */
    @Override
    protected void loadAssets() {
        AutomatedLog.wrapEvent("Loading assets for epilogue screen.", () -> {
            syslog.info("No assets assigned.");
        });
    }

    @Override
    public void render() {
        AutomatedLog.wrapEvent("Rendering screen for epilogue.", () -> {

        });
    }

    protected void configureListeners() {
        AutomatedLog.wrapEvent("Configuring listeners for arena.", () -> {
            view.listen();
        });
    }
}
