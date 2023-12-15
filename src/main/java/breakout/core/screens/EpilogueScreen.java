package breakout.core.screens;

import breakout.core.screens.views.EpilogueView;
import breakout.helpers.AutomatedLog;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The screen which handles all the logic and rendering for the epilogue or end.
 */
abstract public class EpilogueScreen extends Screen {
    public EpilogueScreen(Stage stage, double width, double height) {
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
        AutomatedLog.wrapEvent("Loading assets for epilogue screen.", () -> {
            // Loaded in order of most important first
            loadAudio();
        });
    }

    /**
     * Load the audio for the arena.
     */
    protected void loadAudio() {
        AutomatedLog.wrapEvent("Loading audio for arena", () -> {
            loadMusic();
            loadSoundEffects();
        });
    }

    /**
     * Load the music for the arena.
     */
    protected void loadMusic() {
        AutomatedLog.wrapEvent("Loading music for arena", () -> {
            eventlog.info("No music assigned.");
        });
    }

    /**
     * Load the sound effects for the arena.
     */
    protected void loadSoundEffects() {
        AutomatedLog.wrapEvent("Loading sound effects for arena", () -> {
            eventlog.info("No sound effects assigned.");
        });
    }
}
