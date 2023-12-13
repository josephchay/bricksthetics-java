package breakout.controllers;

import breakout.screens.MenuScreen;

/**
 * Handles all the game logic and the rendering of a static view on the menu screen.
 */
public class MenuController extends breakout.core.controllers.MenuController {
    @Override
    public void index() {
        screen = new MenuScreen(stage);
        screen.render();
    }
}
