package breakout.controllers;

import breakout.screens.ArenaScreen;

/**
 * Handles all the game logic and the rendering of a static view on the arena screen.
 */
public class ArenaController extends breakout.core.controllers.ArenaController {
    @Override
    public void index() {
        screen = new ArenaScreen(stage);
        screen.render();
    }
}
