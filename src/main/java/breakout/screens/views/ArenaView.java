package breakout.screens.views;

import breakout.container.DependenciesInjectable;
import breakout.helpers.AutomatedLog;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.FontPosture;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Map;

public class ArenaView extends breakout.core.screens.views.ArenaView implements DependenciesInjectable {
    @Override
    public void build(Stage stage) {
        AutomatedLog.wrapSys("Building view template for arena", () -> {
            vb.clearBackground()
                .canvas(settings.canvasWidth, settings.canvasHeight)
                .graphics("Arial", FontPosture.REGULAR, 14);

            Scene scene = new Scene(vb.root, settings.appWidth, settings.appHeight);
            scene.getStylesheets().add(env.getResourceStylesheet());
            scene.setCursor(settings.appCursor);
            stage.setScene(scene);

            vb.focusable();
        });
    }
}
