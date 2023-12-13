package breakout.controllers;

import breakout.screens.EpilogueScreen;

/**
 * Handles all the game logic and the rendering of a static view on the epilogue screen.
 */
public class EpilogueController extends breakout.core.controllers.EpilogueController {
    @Override
    public void index() {
        screen = new EpilogueScreen(stage);
        screen.render();
    }
}
