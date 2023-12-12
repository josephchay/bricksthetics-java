package breakout.core.effects.pieces;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

abstract public class Piece {
    protected double x, y, vX, vY, radius;
    protected Color color;

    protected double life = 1.0; // life span
    protected boolean alive = true;

    protected double gravity;

    /** Bounce effect (energy loss on bounce) */
    protected double bounceFactor;

    abstract public void update(double delta);

    abstract public void draw(GraphicsContext graphics);

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public boolean dead() {
        return !alive;
    }
}
