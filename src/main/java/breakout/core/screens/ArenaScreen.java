package breakout.core.screens;

import breakout.core.screens.views.ArenaView;
import breakout.helpers.AutomatedLog;
import javafx.stage.Stage;

/**
 * Represents the main gameplay screen in the Breakout game.
 * This class manages the entire game arena, including player entities, enemy bricks, collectibles, and projectiles.
 * It is responsible for initializing the game state, managing game loops, rendering the game, and handling user inputs.
 */
abstract public class ArenaScreen extends Screen {
    public ArenaScreen(Stage stage, double width, double height) {
        super(stage, width, height);
    }

    @Override
    protected void loadTemplate() {
        AutomatedLog.wrapEvent("Loading template for arena.", () -> {
            view = new ArenaView();
            view.build(stage);
        });
    }

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

    @Override
    protected void updateAssets() {
        AutomatedLog.wrapEvent("Updating assets for arena.", () -> {
            clearCanvas();
            updateProps();
            updateGraphics();
            updateEntities();
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
