package breakout.core.effects.pieces;

import breakout.core.contracts.DefaultDependenciesInjectable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Particle extends Piece implements DefaultDependenciesInjectable {
    protected int arc;
    private double groundLevel;

    public Particle(double x, double y, double groundLevel, double gravity) {
        this(x, y, Color.DARKGRAY, groundLevel, gravity);
    }

    public Particle(double x, double y, Color color, double groundLevel, double gravity) {
        this(x, y, 0, color, groundLevel, gravity);
    }

    public Particle(double x, double y, int arc, Color color, double groundLevel, double gravity) {
        this(x, y, arc, new Color[]{color}, groundLevel, gravity);
    }

    public Particle(double x, double y, int arc, Color[] colors, double groundLevel, double gravity) {
        this.x = x;
        this.y = y;
        this.arc = arc;

        vX = Math.random() * 180 - 90;
        vY = Math.random() * 400 - 200;

        radius = Math.random() * 20 + 4;

        // Randomly select a color from the array
        this.color = colors[(int) (Math.random() * colors.length)];

        this.groundLevel = groundLevel;
        this.gravity = gravity;
        this.bounceFactor = -0.8;
    }

    @Override
    public void update(double delta) {
        // Fade out and deactivate
        life -= 0.08 * delta;
        if (life <= 0) {
            alive = false;
        }

        // Adjust gravity based on altitude
        double altitude = groundLevel - (y + radius);
        double altitudeFactor = Math.max(0, 1 - altitude / groundLevel); // Ranges from 0 to 1
        double adjustedGravity = gravity * (1 + altitudeFactor); // Gravity increases as altitude decreases

        // Physics update
        vX *= Math.pow(0.76, delta); // Air resistance
        vY += adjustedGravity * delta;

        // Update position
        x += vX * delta;
        y += vY * delta;

        // Ground collision and bounce
        if (y + radius > groundLevel) {
            y = groundLevel - radius; // Adjust to ground level
            vY *= bounceFactor; // Bounce effect

            // Reduce bounce intensity if needed
            if (Math.abs(vY) < 1) {
                vY = 0;
            }
        }

        // Shrink size over time
        radius *= Math.pow(0.94, delta);
    }

    @Override
    public void draw(GraphicsContext graphics) {
        if (dead()) return;

        graphics.setGlobalAlpha(life);
        graphics.setFill(color);
        if (arc == 0) {
            graphics.fillOval(x, y, radius, radius);
        } else {
            graphics.fillRoundRect(x, y, radius, radius, arc, arc);
        }
        graphics.setGlobalAlpha(1.0); // Reset alpha
    }

    public void setGroundLevel(double groundLevel) {
        this.groundLevel = groundLevel;
    }
}
