package breakout.core.entities.projectiles;

import javafx.scene.paint.Color;

/**
 * Projectile that is capable of altering its path during flight to hit a target.
 * The projectile is guided by an angle rather than static movement (up, down, left, right) although they can still be implemented.
 */
abstract public class GuidedProjectile extends Projectile {
    /** The aimed or guided x and y coordinates of the orb. */
    protected double guideX, guideY;

    /** Angle of the projectile */
    protected double angle;

    /**
     * Whether the projectile is moving or not.
     * In replacement of the static movement (up, down, left, right) of the projectile.
     */
    protected boolean moving = false;

    public GuidedProjectile(double x, double y, double width, double height, double speed) {
        super(x, y, width, height, speed);
    }

    public GuidedProjectile(double x, double y, double width, double height, double speed, Color color) {
        super(x, y, width, height, speed, color);
    }

    @Override
    protected void updateDx(double delta) {
        dx = Math.cos(angle) * vx * delta;
    }

    @Override
    protected void updateDy(double delta) {
        dy = Math.sin(angle) * vy * delta;
    }

    @Override
    protected void swapHorizontalDirection() {
        vx = -vx;
    }

    @Override
    protected void swapVerticalDirection() {
        vy = -vy;
    }

    public void guide(double x, double y) {
        guideX = x;
        guideY = y;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getGuideX() {
        return guideX;
    }

    public double getGuideY() {
        return guideY;
    }

    public void mobilize() {
        moving = true;
    }

    @Override
    public void immobilize() {
        moving = false;
    }
}
