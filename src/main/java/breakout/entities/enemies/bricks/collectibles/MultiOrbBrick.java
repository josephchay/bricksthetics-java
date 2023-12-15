package breakout.entities.enemies.bricks.collectibles;

import breakout.entities.enemies.bricks.tags.Brick;
import breakout.entities.enemies.bricks.tags.Collectible;

abstract public class MultiOrbBrick extends CollectibleBrick{
    protected static Brick type = Brick.MULTI_ORB;

    public MultiOrbBrick(double x, double y) {
        super(x, y, Collectible.LARGE_PADDLE);
    }

    @Override
    public void drop() {
        collectible = collectibleFactory.createMultiorb(x, y);
    }
}
