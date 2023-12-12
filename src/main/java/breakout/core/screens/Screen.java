package breakout.core.screens;

import breakout.core.contracts.DefaultDependenciesInjectable;
import breakout.core.screens.views.View;
import breakout.helpers.AutomatedLog;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

abstract public class Screen implements DefaultDependenciesInjectable {
    protected Stage stage;
    protected Scene scene;

    /**
     * The timestamp of the last frame update.
     * This is used to calculate the time difference between frames
     * for smooth animations and consistent game logic updates.
     * It is measured in nanoseconds and obtained from the system's high-resolution time source.
     */
    protected long lastUpdate = 0;

    /**
     * The time elapsed since the last update, expressed in seconds.
     * This value is used to ensure frame-rate independent movement and animation.
     * It is calculated by subtracting `{@code lastUpdate}` from the current frame's timestamp
     * and converting the result from nanoseconds to seconds.
     */
    protected double delta = 0.0;

    protected View view;
    protected GraphicsContext graphics;

    protected int loopCount = 0;

    public Screen(Stage stage) {
        this.stage = stage;

        loadTemplate();
        loadAssets();

        configureListeners();

        showStage();
    }

    /**
     * Load the given static UI template.
     */
    abstract protected void loadTemplate();

    /**
     * Load all assets for the screen.
     */
    abstract protected void loadAssets();

    /**
     * Configure all event handler listeners for the screen.
     */
    abstract protected void configureListeners();

    private void showStage() {
        AutomatedLog.wrapEvent("Updating stage for arena.", () -> {
            stage.show();
        });
    }

    /**
     * Render the entire screen to the client.
     */
    abstract public void render();
}
