package breakout.entities.enemies.bricks.collectibles.drops;

import breakout.container.DependenciesInjectable;

public class MultiorbCollectible extends Collectible implements DependenciesInjectable {
    public MultiorbCollectible(double x, double y) {
        super(x, y, settings.collectibleMaxVY);

        setColor(breakout.entities.enemies.bricks.tags.Collectible.MULTIORB.color());
    }
}
