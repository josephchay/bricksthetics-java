package breakout.core.effects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The common properties of any particle effect.
 */
abstract public class Effect {
    protected double x, y;
    protected double width, height;

    /**
     * Color of the effect.
     * If colors is not null, then color is the first color in colors.
     */
    protected Color color;

    /** Colors of the effect. */
    protected Color[] colors;
    protected float alpha = 1f;
    protected double decay = 0.0;
    protected boolean alive = true;

    public Effect(double x, double y, Color color, double fadeSpeed) {
        this.x = x;
        this.y = y;
        this.color = color;

        this.decay = fadeSpeed;
    }

    public Effect(double x, double y, Color[] colors, double fadeSpeed) {
        this.x = x;
        this.y = y;
        this.colors = colors;
        color = colors[0];

        this.decay = fadeSpeed;
    }

    public Effect(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    abstract public void update(double delta);

    abstract public void draw(GraphicsContext graphics);

    public boolean dead() {
        return !alive;
    }
}
