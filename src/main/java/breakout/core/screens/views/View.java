package breakout.core.screens.views;

import breakout.core.contracts.DefaultDependenciesInjectable;
import breakout.core.utils.Helper;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines and implement common functionalities for all views.
 */
abstract public class View implements DefaultDependenciesInjectable {
    protected Map<Class<?>, EventHandler<?>> eventHandlers = new HashMap<>();

    protected GraphicsContext graphics;

    abstract public void build(Stage stage);

    /**
     * Listen for events on the view based on the handlers added to eventHandler.
     * @see breakout.core.screens.views.View#eventHandlers
     */
    public void listen() {
        eventlog.info("Listening for events on arena view initiated");

        EventHandler<?> keyEventHandler = eventHandlers.get(KeyEvent.class);
        EventHandler<?> mouseEventHandler = eventHandlers.get(MouseEvent.class);

        if (keyEventHandler != null) {
            vb.listenKeyPressed((EventHandler<KeyEvent>) keyEventHandler);
        }

        if (mouseEventHandler != null) {
            vb.listenMouseMoved((EventHandler<MouseEvent>) mouseEventHandler);
            vb.listenMouseClick((EventHandler<MouseEvent>) mouseEventHandler);
        }

        eventlog.info("Listening for events on arena view completed");
    }

    public void addEventHandler(Class<?> event, EventHandler<?> handler) {
        eventHandlers.put(event, handler);
    }

    public GraphicsContext getGraphics() {
        return vb.graphics;
    }

    public Canvas getCanvas() {
        return vb.canvas;
    }

    public Pane getRoot() {
        return vb.root;
    }
}
