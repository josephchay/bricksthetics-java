package breakout.entities.enemies.bricks.collectibles.drops;

import breakout.container.DependenciesInjectable;

public class LifeCollectible extends Collectible implements DependenciesInjectable {
    public LifeCollectible(double x, double y) {
        super(x, y, settings.collectibleMaxVY);

        setColor(breakout.entities.enemies.bricks.tags.Collectible.LIFE.color());
    }
}
