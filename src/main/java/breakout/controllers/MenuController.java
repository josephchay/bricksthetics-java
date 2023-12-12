package breakout.controllers;

import breakout.screens.MenuScreen;

public class MenuController extends breakout.core.controllers.MenuController {
    @Override
    public void index() {
        screen = new MenuScreen(stage);
        screen.render();
    }
}
