package breakout.core.screens;

import breakout.container.DependenciesInjectable;
import breakout.core.screens.views.ArenaView;
import breakout.helpers.AutomatedLog;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Represents the main gameplay screen in the Breakout game.
 * This class manages the entire game arena, including player entities, enemy bricks, collectibles, and projectiles.
 * It is responsible for initializing the game state, managing game loops, rendering the game, and handling user inputs.
 */
abstract public class ArenaScreen extends Screen implements DependenciesInjectable {
    public ArenaScreen(Stage stage) {
        super(stage);
    }

    /**
     * Load the given static UI template.
     */
    @Override
    protected void loadTemplate() {
        AutomatedLog.wrapEvent("Loading template for arena.", () -> {
            view = new ArenaView();
            view.build(stage);
        });
    }

    /**
     * Load all assets for the arena.
     * The loaded assets are done in the context of backend preparation.
     */
    @Override
    protected void loadAssets() {
        AutomatedLog.wrapEvent("Loading assets for arena.", () -> {
            // Loaded in order of most important first
            loadAudio();
            loadProps();
            loadEntities();
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

    /**
     * Load any props that acts more like doodads for the arena.
     */
    protected void loadProps() {
        eventlog.info("No props assigned.");
    }

    /**
     * Load the entities for the arena
     * Load in order of most important first
     */
    protected void loadEntities() {
        AutomatedLog.wrapEvent("Loading all entities for arena.", () -> {
            loadProjectiles();
            loadEnemies();
            loadPlayers();
        });
    }

    /**
     * Load the enemy entities for the arena.
     * Method not made abstract to make it optional, however should be overridden if required load necessary enemies.
     */
    protected void loadEnemies() {
        eventlog.info("No enemies assigned.");
    }

    /**
     * Load the projectile entities for the arena.
     * Method not made abstract to make it optional, however should be overridden if required to load necessary projectile.
     */
    protected void loadProjectiles() {
        eventlog.info("No projectiles assigned.");
    }

    /**
     * Load the player entities for the arena.
     * Method not made abstract to make it optional, however should be overridden if required to load necessary players.
     */
    protected void loadPlayers() {
        eventlog.info("No players assigned.");
    }

    /**
     * Method to load anything that needs to be loaded or generated again
     */
    protected void reload() {
        AutomatedLog.wrapEvent("Reloading arena.", () -> {
            loadAssets();
        });
    }

    /**
     * Configure listeners for the arena.
     */
    protected void configureListeners() {
        AutomatedLog.wrapEvent("Configuring listeners for arena.", () -> {
            view.listen();
        });
    }

    /**
     * Render all loaded elements to screen and perform state updates.
     */
    @Override
    public void render() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                loopCount++;

                AutomatedLog.wrapEvent("Rendering screen for arena at loop number " + loopCount, () -> {
                    if (lastUpdate == 0) {
                        lastUpdate = now;
                        return;
                    }

                    delta = (now - lastUpdate) / 1_000_000_000.0; // Convert nanoseconds to seconds
                    lastUpdate = now;

                    clearCanvas();

                    updateProps();

                    updateGraphics();

                    updateEntities();
                });
            }
        }.start();
    }

    /**
     * Clear the rectangular region of the canvas.
     * Done to erase the previous frame of the canvas.
     */
    protected void clearCanvas() {
        AutomatedLog.wrapEvent("Updating graphics for arena.", () -> {
            graphics.clearRect(0, 0, settings.appWidth, settings.appHeight);
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

    /**
     * Update the state of the props
     * Method not made abstract to make it optional, however should be overridden if required to load props.
     */
    protected void updateProps() {
        eventlog.info("No props assigned.");
    }

    /**
     * Update the state of the entities.
     */
    protected void updateEntities() {
        AutomatedLog.wrapEvent("Updating the state of all entities for arena.", () -> {
            updateEnemies();
            updatePlayers();
            updateProjectiles();
        });
    }

    /**
     * Update the state of the enemies
     * Method not made abstract to make it optional, however should be overridden if required to load enemies.
     */
    protected void updateEnemies() {
        eventlog.info("No enemies assigned.");
    }

    /**
     * Update the state of the players
     * Method not made abstract to make it optional, however should be overridden if required to load players.
     */
    protected void updatePlayers() {
        eventlog.info("No players assigned.");
    }

    /**
     * Update the state of the projectiles
     * Method not made abstract to make it optional, however should be overridden if required to load projectiles.
     */
    protected void updateProjectiles() {
        eventlog.info("No projectiles assigned.");
    }
}
