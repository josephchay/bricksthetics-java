package breakout.entities.enemies.bricks.tags;

import breakout.container.DependenciesInjectable;

public enum BrickScore implements DependenciesInjectable {
    HIT(settings.brickHitScore),
    DESTROYED(settings.brickDestroyedScore);

    private final int score;

    BrickScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
