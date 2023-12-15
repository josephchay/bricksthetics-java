package breakout.entities.enemies.bricks.collectibles.drops;

import breakout.container.DependenciesInjectable;

public class MultiOrbCollectible extends Collectible implements DependenciesInjectable {
    public MultiOrbCollectible(double x, double y) {
        super(x, y, settings.collectibleMaxVY);

        setColor(breakout.entities.enemies.bricks.tags.Collectible.MULTI_ORB.color());
    }
}
