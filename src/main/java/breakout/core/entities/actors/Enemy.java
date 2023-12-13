package breakout.core.entities.actors;

import breakout.core.entities.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * The common properties of any enemy.
 * Currently used for polymorphic purposes.
 */
abstract public class Enemy extends Entity {
    public Enemy(double x, double y, double width, double height, int health, Color color) {
        super(x, y, width, height, 0, health, color);
    }

    public Enemy(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(GraphicsContext graphics) {

    }

    @Override
    public Shape hitbox() {
        return null;
    }
}
