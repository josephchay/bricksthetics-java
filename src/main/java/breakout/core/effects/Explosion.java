package breakout.core.effects;

import breakout.core.effects.pieces.Particle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Collection of particles.
 * Provides the physics to explode particles upon trigger.
 */
public class Explosion extends Effect {
    private List<Particle> pieces = new ArrayList<>();

    public Explosion(double x, double y, Color[] colors, int numParticles, double groundLevel, double gravity) {
        this(x, y, 0, colors, numParticles, groundLevel, gravity);
    }

    /**
     * Explosion with ground level and gravity.
     *
     * @param x Beginning x position
     * @param y Beginning y position
     * @param arc Arc of particles
     * @param colors Color of particles
     * @param numPieces Number of particles
     * @param groundLevel Ground level
     * @param gravity Gravity
     */
    public Explosion(double x, double y, int arc, Color[] colors, int numPieces, double groundLevel, double gravity) {
        super(x, y, colors, 0.05);

        for (int i = 0; i < numPieces; i++) {
            pieces.add(new Particle(x, y, arc, colors, groundLevel, gravity));
        }
    }

    @Override
    public void update(double delta) {
//        alpha -= (float) decay;
//
//        if (alpha < 0) {
//            alpha = 0;
//            alive = false;
//        }

        for (Particle piece: pieces) {
            piece.update(delta);
        }

        pieces.removeIf(Particle::dead);

        if (pieces.isEmpty()) {
            alive = false;
        }
    }

    @Override
    public void draw(GraphicsContext graphics) {
        graphics.setGlobalAlpha(alpha);

        for (Particle piece: pieces) {
            piece.draw(graphics);
        }

        // Reset alpha to fully opaque
        graphics.setGlobalAlpha(1.0);
    }

    public Rectangle hitbox() {
        return new Rectangle(0, 0, 0, 0);
    }
}
