package breakout.core.effects;

import breakout.core.effects.pieces.EmitParticle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Trail extends Effect {
    List<EmitParticle> pieces = new ArrayList<>();

    public Trail(double x, double y, Color color, int numPieces) {
        this(x, y, new Color[]{color}, numPieces);
    }

    public Trail(double x, double y, Color[] colors, int numPieces) {
        super(x, y, colors, 0.05);

        for (int i = 0; i < numPieces; i++) {
            pieces.add(new EmitParticle(x, y, colors));
        }
    }

    @Override
    public void update(double delta) {
        alpha -= (float) decay;

        if (alpha < 0) {
            alpha = 0;
            alive = false;
        }

        for (EmitParticle piece: pieces) {
            piece.update(delta);
        }

        pieces.removeIf(EmitParticle::dead);

        if (pieces.isEmpty()) {
            alive = false;
        }
    }

    @Override
    public void draw(GraphicsContext graphics) {
        graphics.setGlobalAlpha(alpha);

        for (EmitParticle piece: pieces) {
            piece.draw(graphics);
        }

        // Reset alpha to fully opaque
        graphics.setGlobalAlpha(1.0);
    }
}
