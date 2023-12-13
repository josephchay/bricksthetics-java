package breakout.screens.views;

import breakout.core.contracts.DefaultDependenciesInjectable;
import breakout.helpers.AutomatedLog;
import javafx.stage.Stage;

public class MenuView extends breakout.core.screens.views.MenuView implements DefaultDependenciesInjectable {
    @Override
    public void build(Stage stage) {
        AutomatedLog.wrapSys("Building view template for menu", () -> {
            /* Build view template for menu here */
        });
    }
}
