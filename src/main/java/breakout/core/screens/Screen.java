package breakout.core.screens;

import breakout.core.contracts.DefaultDependenciesInjectable;
import breakout.core.screens.views.View;
import breakout.helpers.AutomatedLog;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The default implementation for all types of screen.
 * Involves creating and handling the static view UI components.
 */
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

    /**
     * The game loop that is executed on every frame update.
     */
    protected AnimationTimer loop;

    /**
     * The number of times the game loop has been executed.
     */
    protected int loopCount = 0;

    protected View view;
    protected GraphicsContext graphics;

    protected double screenWidth, screenHeight;

    public Screen(Stage stage, double width, double height) {
        this.stage = stage;

        this.screenWidth = width;
        this.screenHeight = height;
    }

    /**
     * Render the screen and the view to the client.
     */
    public void render() {
        load();
        update();
    }

    /**
     * Load all the assets for the screen.
     */
    protected void load() {
        loadTemplate();
        loadAssets();
        loadListeners();

        showStage();
    }

    /**
     * Load the given static UI template.
     */
    abstract protected void loadTemplate();

    /**
     * Load and prepare all assets for the arena.
     */
    abstract protected void loadAssets();

    /**
     * Configure all event handler listeners for the screen.
     */
    abstract protected void loadListeners();

    /**
     * Display the stage.
     */
    protected void showStage() {
        AutomatedLog.wrapEvent("Updating stage for arena", () -> {
            stage.show();
        });
    }

    /**
     * Update the entire screen to the client.
     */
    protected void update() {
        AutomatedLog.wrapEvent("Updating screen", () -> {
            loop = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    loopCount++;

                    AutomatedLog.wrapEvent("Updating screen at loop number " + loopCount, () -> {
                        if (lastUpdate == 0) {
                            lastUpdate = now;
                            return;
                        }

                        delta = (now - lastUpdate) / 1_000_000_000.0; // Convert nanoseconds to seconds
                        lastUpdate = now;

                        updateAssets();
                    });
                }
            };

            loop.start();
        });
    }

    /**
     * Update the states for all assets for the screen.
     */
    protected void updateAssets() {
        AutomatedLog.wrapEvent("Updating assets for arena.", () -> {
            clearCanvas();
            updateGraphics();
        });
    }

    /**
     * Clear the rectangular region of the canvas.
     * Done to erase the previous frame of the canvas.
     */
    protected void clearCanvas() {
        AutomatedLog.wrapEvent("Updating graphics for arena.", () -> {
            graphics.clearRect(0, 0, screenWidth, screenHeight);
        });
    }

    /**
     * Update the graphics of the arena.
     */
    protected void updateGraphics() {
        AutomatedLog.wrapEvent("Updating graphics for arena.", () -> {
            graphics.setFill(Color.BLACK);
        });
    }
}
