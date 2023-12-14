package breakout.entities.enemies.bricks.collectibles;

import breakout.entities.enemies.bricks.tags.BrickType;
import breakout.entities.enemies.bricks.tags.Collectible;

abstract public class MultiorbBrick extends CollectibleBrick{
    protected static BrickType type = BrickType.MULTIORB;

    public MultiorbBrick(double x, double y) {
        super(x, y, Collectible.LARGE_PADDLE);
    }

    @Override
    public void drop() {
        collectible = collectibleFactory.createMultiorb(x, y);
    }
}
