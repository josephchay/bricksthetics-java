package breakout.controllers;

import breakout.container.DependenciesInjectable;
import breakout.screens.ArenaScreen;

/**
 * Handles all the game logic and the rendering of a static view on the arena screen.
 */
public class ArenaController extends breakout.core.controllers.ArenaController implements DependenciesInjectable {
    @Override
    public void index() {
        screen = new ArenaScreen(stage, settings.appWidth, settings.appHeight);
        screen.render();
    }
}
