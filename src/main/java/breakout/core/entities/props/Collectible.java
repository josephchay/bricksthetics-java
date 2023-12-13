package breakout.core.entities.props;

import breakout.core.contracts.CollisionInitiable;
import breakout.core.entities.Entity;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

/**
 * Defines the structure and behavior of any collectible entity.
 */
abstract public class Collectible extends Entity implements CollisionInitiable {
    protected boolean collected = false;

    public Collectible(double x, double y, double width, double height, double velocity) {
        super(x, y, width, height, velocity);
    }

    public Collectible(double x, double y, double width, double height, double vx, double vy) {
        super(x, y, width, height, vx, vy, null);
    }

    public Collectible(double x, double y, double width, double height, double velocity, Color color) {
        super(x, y, width, height, velocity, color);
    }

    public void collect() {
        collected = true;
        alive = false;
    }

    public boolean collected() {
        return collected;
    }
}
