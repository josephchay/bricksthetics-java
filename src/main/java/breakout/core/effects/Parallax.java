package breakout.core.effects;

import breakout.core.contracts.DefaultDependenciesInjectable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Provides the "parallax" effect physics for any given image.
 * Speed and movement are all customizable.
 */
public class Parallax implements DefaultDependenciesInjectable {
    private double x, y, targetX;
    private final Image image;
    private final double width, height, boundWidth, boundHeight;
    private double speed;
    private int padding;

    public Parallax(Image image, int width, int height, int padding, double speed) {
        y = 0;

        this.image = image;

        this.width = width + padding * 2;
        this.height = height + padding * 2;
        boundWidth = width;
        boundHeight = height;

        this.padding = padding;
        this.speed = speed;
    }

    public void update(int paddleX) {
        // set target
        targetX = (paddleX - boundWidth / 2) * padding / boundWidth / 2;

        // move towards target
        x += (targetX - x) * speed;
    }

    public void draw(GraphicsContext graphics) {
        int centerX = (int) (boundWidth / 2 - width / 2);
        int centerY = (int) (boundHeight / 2 - height / 2);

        if (image != null) {
            graphics.drawImage(image, centerX + x, centerY + y, width, height);
        }
    }
}
