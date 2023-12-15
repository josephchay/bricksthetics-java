package breakout.screens;

import breakout.container.DependenciesInjectable;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EpilogueScreen extends breakout.core.screens.EpilogueScreen implements DependenciesInjectable {
    public EpilogueScreen(Stage stage, double width, double height) {
        super(stage, width, height);
    }

    @Override
    protected void loadListeners() {
        // TODO Implement listeners
    }
}
