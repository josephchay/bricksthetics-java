package breakout.entities.enemies.bricks.tags;

import breakout.container.DependenciesInjectable;
import javafx.scene.paint.Color;

public enum Collectible implements DependenciesInjectable {
    MULTIORB(settings.orbColor),
    LIFE(settings.playerLifeColor),
    LARGE_PADDLE(settings.playerColor);

    private final Color color;

    Collectible(Color color) {
        this.color = color;
    }

    public Color color() {
        return color;
    }
}
