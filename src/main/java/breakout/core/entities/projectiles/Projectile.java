package breakout.core.entities.projectiles;

import breakout.core.contracts.CollisionInitiable;
import breakout.core.entities.Entity;
import breakout.core.utils.Helper;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * Class extended by all projectiles in the game.
 */
abstract public class Projectile extends Entity implements CollisionInitiable {
    public Projectile(double x, double y, double width, double height, double speed) {
        super(x, y, width, height, speed);
    }

    public Projectile(double x, double y, double width, double height, double speed, Color color) {
        super(x, y, width, height, speed, color);
    }
}
