package breakout.entities.enemies.bricks;

import breakout.container.DependenciesInjectable;
import breakout.core.entities.actors.Enemy;
import breakout.entities.Modifier;
import breakout.entities.enemies.bricks.tags.BrickType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

abstract public class Brick extends Enemy implements DependenciesInjectable {
    protected static BrickType type;
    protected int arc = settings.brickArc;

    public Brick(double x, double y) {
        super(x, y, settings.brickWidth, settings.brickHeight);
        setHealth((int) (Math.random() * settings.brickMaxPossibleHealth));
        setColor(Color.rgb(0, 255 - health * 100, 255 - health * 100));
    }

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

    public void deform() {
        color = Color.rgb(0, 255 - health * 100, 255 - health * 100);
    }

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
