package breakout.screens.views;

import breakout.container.DependenciesInjectable;
import breakout.helpers.AutomatedLog;
import javafx.stage.Stage;

public class EpilogueView extends breakout.core.screens.views.EpilogueView implements DependenciesInjectable {
    @Override
    public void build(Stage stage) {
        AutomatedLog.wrapSys("Building view template for epilogue", () -> {

        });
    }
}
