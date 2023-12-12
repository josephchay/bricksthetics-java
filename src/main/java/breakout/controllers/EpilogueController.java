package breakout.controllers;

import breakout.screens.EpilogueScreen;

public class EpilogueController extends breakout.core.controllers.EpilogueController {
    @Override
    public void index() {
        screen = new EpilogueScreen(stage);
        screen.render();
    }
}
