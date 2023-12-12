package breakout.screens.tags;

import breakout.core.contracts.DefaultDependenciesInjectable;
import breakout.controllers.ArenaController;
import breakout.core.controllers.Controller;
import breakout.controllers.EpilogueController;
import breakout.controllers.MenuController;

public enum Screen implements DefaultDependenciesInjectable {
    MENU(new MenuController()),
    ARENA(new ArenaController()),
    EPILOGUE(new EpilogueController());

    public final String label;
    public final Controller controller;

    Screen(Controller controller) {
        this.label = this.name().toLowerCase();
        this.controller = controller;
    }
}
