package breakout.entities.projectiles.orbs.standards;

import breakout.container.DependenciesInjectable;
import breakout.core.effects.Effect;
import breakout.core.effects.Trail;
import breakout.core.entities.Entity;
import breakout.entities.projectiles.orbs.Orb;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Shadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class StandardOrb extends Orb implements DependenciesInjectable {
    private List<Effect> effects = new ArrayList<>();

    private int radius = settings.orbRadius;

    public StandardOrb(double y, int radius, Color color) {
        super(0, y, radius, radius, settings.orbVelocity, color);
    }

    public StandardOrb(double x, double y, int radius, Color color) {
        super(x, y, radius, radius, settings.orbVelocity, color);
    }

    @Override
    public Rectangle hitbox() {
        return new Rectangle((int) x, (int) y, radius, radius);
    }

    public void update(double delta, int boundedWith, int boundedHeight) {
        if (attack) {
            attack = false;
        }

        if (moving) {
            super.update(delta, boundedWith, boundedHeight);
            effects.add(new Trail(x, y, settings.orbTrailColors, settings.orbTrailPiecesNum));
            updateEffects(delta);
        }
    }

    public void updateEffects(double delta) {
        for (Effect effect : effects) {
            effect.update(delta);
        }
    }

    @Override
    public void draw(GraphicsContext graphics) {
        graphics.save();

        // Glow effect
        GaussianBlur blur = new GaussianBlur(2);
        Shadow glow = new Shadow();
        glow.setColor(color.brighter());
        glow.setRadius(8);
        glow.setInput(blur);

        graphics.setEffect(glow);
        graphics.setFill(color);
        graphics.fillOval(x, y, radius, radius);

        graphics.setEffect(null);

        Color borderColor = color.darker().darker();
        double borderWidth = 1.8;
        graphics.setStroke(borderColor);
        graphics.setLineWidth(borderWidth);
        graphics.strokeOval(x - borderWidth / 2, y - borderWidth / 2, radius + borderWidth, radius + borderWidth);

        graphics.restore();

        drawEffects(graphics);
    }

    public void drawEffects(GraphicsContext graphics) {
        for (Effect effect: effects) {
            effect.draw(graphics);
        }
    }

    @Override
    public boolean collidesWith(Entity target) {
        Shape targetHitbox = target.hitbox();

        boolean detected = false;

        xDest = x + dx;
        yDest = y + dy;

        // Horizontal collisions
        updateIncomingCornerHits(xDest, y, targetHitbox);

        if (dx > 0) {
            if (topRightHit || bottomRightHit) {
                swapHorizontalDirection();
                detected = true;
            }
        }

        if (dx < 0) {
            if (topLeftHit || bottomLeftHit) {
                swapHorizontalDirection();
                detected = true;
            }
        }

        // Vertical collisions
        updateIncomingCornerHits(x, yDest, targetHitbox);

        if (dy > 0) {
            if (bottomRightHit || bottomLeftHit) {
                swapVerticalDirection();
                detected = true;
            }
        }

        if (dy < 0) {
            if (topRightHit || topLeftHit) {
                swapVerticalDirection();
                detected = true;
            }
        }

        return detected;
    }

    /**
     * Updates the corner hit flags of the orb based on a given position and a hitbox.
     * This method checks if each corner of the object (represented by a square of given diameter)
     * intersects with the provided hitbox.
     * Sets the corresponding corner hit flags to true if an intersection is detected.
     *
     * @param x The x-coordinate of the top-left corner.
     * @param y The y-coordinate of the top-left corner.
     * @param targetHitbox The bounding node (shape) against which to check for intersections.
     */
    public void updateIncomingCornerHits(double x, double y, Shape targetHitbox) {
        Point2D topLeft = new Point2D(x, y);
        Point2D topRight = new Point2D(x + radius, y);
        Point2D bottomLeft = new Point2D(x, y + radius);
        Point2D bottomRight = new Point2D(x + radius, y + radius);

        nullifyCornerHits();

        this.topLeftHit = targetHitbox.contains(topLeft.getX(), topLeft.getY());
        this.topRightHit = targetHitbox.contains(topRight.getX(), topRight.getY());
        this.bottomLeftHit = targetHitbox.contains(bottomLeft.getX(), bottomLeft.getY());
        this.bottomRightHit = targetHitbox.contains(bottomRight.getX(), bottomRight.getY());
    }
}
