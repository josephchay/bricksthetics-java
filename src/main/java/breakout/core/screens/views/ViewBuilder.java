package breakout.core.screens.views;

import breakout.core.contracts.DefaultDependenciesInjectable;
import breakout.core.utils.Helper;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class ViewBuilder implements DefaultDependenciesInjectable {
    private static ViewBuilder instance;
    public Pane root;
    public Canvas canvas;
    public GraphicsContext graphics;

    public static synchronized ViewBuilder getInstance() {
        if (instance == null) {
            instance = new ViewBuilder();
        }

        return instance;
    }

    private ViewBuilder() {
        root = new Pane();
        root.setFocusTraversable(true);
    }

    /**
     * Applies to only the root pane node in the scene graph.
     *
     * @return this.
     */
    public ViewBuilder stylesheet() {
        root.getStylesheets().add(env.getResourceStylesheet());

        return this;
    }

    public ViewBuilder focusable() {
        root.requestFocus();

        return this;
    }

    public ViewBuilder canvas(int width, int height) {
        canvas = new Canvas(width, height);
        root.getChildren().add(canvas);

        return this;
    }

    public ViewBuilder graphics(String fontName, FontPosture fontPosture, int size) {
        if (canvas != null) {
            graphics = canvas.getGraphicsContext2D();
            graphics.setFont(Font.font(fontName, fontPosture, size));
        }

        return this;
    }

    public ViewBuilder clearBackground() {
        root.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        return this;
    }

    public ViewBuilder listenKeyPressed(EventHandler<KeyEvent> handler) {
        root.setOnKeyPressed(handler);

        return this;
    }

    public ViewBuilder listenKeyReleased(EventHandler<KeyEvent> handler) {
        root.setOnKeyReleased(handler);

        return this;
    }

    public ViewBuilder listenMouseMoved(EventHandler<MouseEvent> handler) {
        root.setOnMouseMoved(handler);

        return this;
    }

    public ViewBuilder listenMouseClick(EventHandler<MouseEvent> handler) {
        root.setOnMouseClicked(handler);

        return this;
    }

    public void destroy() {
        root = null;
        graphics = null;
        canvas = null;
    }
}
