package breakout.controllers;

import breakout.container.DependenciesInjectable;
import breakout.screens.MenuScreen;

/**
 * Handles all the game logic and the rendering of a static view on the menu screen.
 */
public class MenuController extends breakout.core.controllers.MenuController implements DependenciesInjectable {
    @Override
    public void index() {
        screen = new MenuScreen(stage, settings.appWidth, settings.appHeight);
        screen.render();
    }
}
