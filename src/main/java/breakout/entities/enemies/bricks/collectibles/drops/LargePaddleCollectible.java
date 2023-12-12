package breakout.entities.enemies.bricks.collectibles.drops;

import breakout.container.DependenciesInjectable;

public class LargePaddleCollectible extends Collectible implements DependenciesInjectable {
    public LargePaddleCollectible(double x, double y) {
        super(x, y, settings.collectibleMaxVY);

        setColor(breakout.entities.enemies.bricks.tags.Collectible.LARGE_PADDLE.color());
    }
}
