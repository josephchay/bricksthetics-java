package breakout.entities;

import javafx.scene.paint.Color;

public class BombModifier extends Modifier {
    public BombModifier(int x, int y) {
        super(x, y);
        color = Color.DARKGRAY;
    }
}
