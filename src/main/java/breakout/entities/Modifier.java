package breakout.entities;

import breakout.entities.enemies.bricks.standards.StandardBrick;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Modifier extends StandardBrick {
    protected boolean falling;
    protected double dy;
    protected boolean collidedWithPlayer = false;

    public Modifier(int x, int y) {
        super(x, y);
        dy = (Math.random() * 4 + 1);
        falling = false;
    }

    public boolean isFalling() {
        return falling;
    }

    public void fall() {
        falling = true;
    }

    public void stationary() {
        falling = false;
    }

    public void update() {
        if (falling) {
            y += dy;
        }

        if (y > settings.appHeight) {
            falling = false;
        }
    }

    @Override
    public void draw(GraphicsContext graphics) {
        super.draw(graphics);
        graphics.setStroke(Color.LIGHTGRAY); // JavaFX color
        graphics.setLineWidth(4);
        graphics.strokeRoundRect(x, y, width, height, settings.brickArc, settings.brickArc);
    }

    public void collected() {
        collidedWithPlayer = true;
    }

    public boolean isCollected() {
        return collidedWithPlayer;
    }
}
