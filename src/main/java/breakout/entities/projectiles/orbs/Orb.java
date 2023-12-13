package breakout.entities.projectiles.orbs;

import breakout.core.entities.projectiles.GuidedProjectile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Orb is a projectile that can be launched at an angle.
 */
abstract public class Orb extends GuidedProjectile {
    /** Whether the orb has hit the bottom of the screen. */
    protected boolean attack = false;

    public Orb(double x, double y, double width, double height, double speed) {
        super(x, y, width, height, speed);
    }

    public Orb(double x, double y, double width, double height, double speed, Color color) {
        super(x, y, width, height, speed, color);
    }

    @Override
    protected void ensureYInBoundary(int height) {
        if (y < 0) {
            swapVerticalDirection();
            y = 0;
        } else if (y + this.height > height) {
            swapVerticalDirection();
            y = height - this.height;
            attack = true;
        }
    }

    public void attack() {
        attack = true;
    }

    /**
     * Randomizes the angle of movement to prefer square-like directions.
     *
     * This method adjusts the angle of movement so that it aligns closely to
     * one of the cardinal four directions (right, down, left, or up).
     * The angle is normalized to be within the range of 0 to 2π radians.
     *
     * @param range The range (in radians) to randomize the angle.
     */
    public void randomizeAngle(double range) {
        double[] baseAngles = {0, Math.PI / 2, Math.PI, 3 * Math.PI / 2}; // Base angles for square directions
        double halfRange = range / 2.0;

        // Select a random base angle
        int index = (int) (Math.random() * baseAngles.length);
        double baseAngle = baseAngles[index];

        // Add a small random deflection to the base angle
        double randomDeflection = halfRange * Math.random() - halfRange;
        angle = baseAngle + randomDeflection;

        angle = (angle + 2 * Math.PI) % (2 * Math.PI);
    }

    public boolean hasAttacked() {
        return attack;
    }
}
