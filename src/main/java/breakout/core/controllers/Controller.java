package breakout.core.controllers;

import breakout.core.contracts.DefaultDependenciesInjectable;
import breakout.core.screens.Screen;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The main controller to be extended by all other controllers.
 */
abstract public class Controller implements DefaultDependenciesInjectable {
    protected Stage stage;
    protected Scene scene;
    protected Screen screen;

    /**
     * The view which the controller handles.
     */
    abstract public void index();

    /**
     * Set up the stage.
     *
     * @param stage The stage of the scene
     */
    public void setStage(Stage stage, StageStyle style,String title, boolean resizable) {
        stage.setTitle(title);
        stage.initStyle(style);
        stage.setResizable(resizable);
        this.stage = stage;
    }

    protected Scene getScene() {
        return scene;
    }
}
