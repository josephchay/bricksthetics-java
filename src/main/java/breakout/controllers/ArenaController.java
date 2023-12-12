package breakout.controllers;

import breakout.screens.ArenaScreen;

public class ArenaController extends breakout.core.controllers.ArenaController {
    @Override
    public void index() {
        screen = new ArenaScreen(stage);
        screen.render();
    }
}
