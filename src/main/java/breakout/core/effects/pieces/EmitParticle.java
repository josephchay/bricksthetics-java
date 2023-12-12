package breakout.core.effects.pieces;

import breakout.container.DependenciesInjectable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A trail of particles that follows the moving object.
 */
public class EmitParticle extends Piece implements DependenciesInjectable {
    public EmitParticle(double x, double y) {
        this.x = x;
        this.y = y;
        vX = (Math.random() * 2 - 1);
        vY = (Math.random() * 2 - 1);

        radius = (int) (Math.random() * settings.orbRadius - 2);
        color = Color.DARKGRAY;
    }

    public EmitParticle(double x, double y, Color color) {
        this(x, y);

        this.color = color;
    }

    public EmitParticle(double x, double y, Color[] colors) {
        this(x, y);

        this.color = colors[(int) (Math.random() * colors.length)];
    }

    public void update(double delta) {
        x += vX;
        y += vY;
    }

//    public void update(double delta) {
//        // Subtract the delta from the life
//        life -= delta;
//        if (life <= 0) {
//            dead = true;
//            return;
//        }
//
//        vX *= 0.99;
//        vY += 0.1;
//
//        // Update position
//        x += vX * delta;
//        y += vY * delta;
//
//        // Shrink the particle over time
//        radius *= 0.97;
//    }

    public void draw(GraphicsContext graphics) {
        graphics.setFill(color);
        graphics.fillOval(x, y, radius, radius);
    }
}
