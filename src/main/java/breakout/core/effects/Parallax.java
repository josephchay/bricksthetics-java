package breakout.core.effects;

import breakout.core.contracts.DefaultDependenciesInjectable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Provides the "parallax" effect physics for any given image.
 * Speed and movement will take the user's input into consideration to generate the effect.
 */
public class Parallax extends Effect {
    private double x, y, targetX;
    private final Image image;
    private final double boundWidth, boundHeight;
    private double speed;
    private int padding;

    public Parallax(Image image, int width, int height, int padding, double speed) {
        super(0, 0, width + padding * 2, height + padding * 2);

        this.image = image;

        boundWidth = width;
        boundHeight = height;

        this.padding = padding;
        this.speed = speed;
    }

    /**
     * Update method utilizing delta time.
     * Useful for future implementations on animations that rely on the game loop's delta time.
     *
     * @param delta the game loop's delta time
     */
    @Override
    public void update(double delta) {

    }

    /**
     * Update method utilizing the user's input.
     *
     * @param paddleX the paddle's x position
     */
    public void update(int paddleX) {
        // set target
        targetX = (paddleX - boundWidth / 2) * padding / boundWidth / 2;

        // move towards target
        x += (targetX - x) * speed;
    }

    /**
     * Draw the image with the updated x and y coordinates.
     *
     * @param graphics the graphics context
     */
    public void draw(GraphicsContext graphics) {
        int centerX = (int) (boundWidth / 2 - width / 2);
        int centerY = (int) (boundHeight / 2 - height / 2);

        if (image != null) {
            graphics.drawImage(image, centerX + x, centerY + y, width, height);
        }
    }
}
