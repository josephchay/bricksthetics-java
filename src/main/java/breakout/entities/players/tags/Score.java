package breakout.entities.players.tags;

import breakout.container.DependenciesInjectable;

public enum Score implements DependenciesInjectable {
    HIT(settings.brickHitScore),
    DESTROYED(settings.brickDestroyedScore);

    private final int score;

    Score(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
