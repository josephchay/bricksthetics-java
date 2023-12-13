package breakout.entities.enemies.bricks;

import breakout.container.DependenciesInjectable;
import breakout.core.entities.actors.Enemy;
import breakout.entities.Modifier;
import breakout.entities.enemies.bricks.tags.BrickType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Mainly refactored from the original Block class
 * @see <a href="https://github.com/kooitt/CourseworkGame/blob/master/src/main/java/brickGame/Block.java">Original Block Class</a>
 * Most functionalities are respectively defined specifically in needed class that extends this abstract class.
 */
abstract public class Brick extends Enemy implements DependenciesInjectable {
    protected static BrickType type;
    protected int arc = settings.brickArc;

    public Brick(double x, double y) {
        super(x, y, settings.brickWidth, settings.brickHeight);
        setHealth((int) (Math.random() * settings.brickMaxPossibleHealth));
        setColor(Color.rgb(0, 255 - health * 100, 255 - health * 100));
    }

    /**
     * Damages the brick and changes its color accordingly.
     */
    @Override
    public void damage() {
        health--;
        if (health < 0) {
            if (!(this instanceof Modifier)) {
                die();
            }
        } else {
            deform();
        }
    }

    /**
     * Kills the brick only after its (explosion) pieces have also been destroyed.
     */
    @Override
    public void die() {
        super.die();
    }

    /**
     * Changes the color of the brick as its health decreases.
     */
    public void deform() {
        color = Color.rgb(0, 255 - health * 100, 255 - health * 100);
    }

    /**
     * Disregards the parent class's update method.
     * For usage on updates based on delta time only.
     *
     * @param delta time passed since last update
     */
    public void update(double delta) {

    }

    @Override
    public void draw(GraphicsContext graphics) {
        graphics.setFill(color);
        graphics.fillRoundRect(x, y, width, height, settings.brickArc, settings.brickArc);
    }

    @Override
    public Rectangle hitbox() {
        return new Rectangle(x, y, width, height);
    }

    public static BrickType type() {
        return type;
    }
}
