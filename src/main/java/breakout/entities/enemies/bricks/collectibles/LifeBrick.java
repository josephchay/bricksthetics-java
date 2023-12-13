package breakout.entities.enemies.bricks.collectibles;

import breakout.entities.enemies.bricks.tags.BrickType;
import breakout.entities.enemies.bricks.tags.Collectible;

abstract public class LifeBrick extends CollectibleBrick {
    protected static BrickType type = BrickType.LIFE;

    public LifeBrick(double x, double y) {
        super(x, y, Collectible.LIFE);
    }

    @Override
    public void drop() {
        collectible = collectibleFactory.createBomb(x, y);
    }
}
