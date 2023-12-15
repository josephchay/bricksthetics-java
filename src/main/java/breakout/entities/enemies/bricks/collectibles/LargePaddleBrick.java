package breakout.entities.enemies.bricks.collectibles;

import breakout.entities.enemies.bricks.tags.Brick;
import breakout.entities.enemies.bricks.tags.Collectible;

abstract public class LargePaddleBrick extends CollectibleBrick {
    protected static Brick type = Brick.LARGE_PADDLE;

    public LargePaddleBrick(double x, double y) {
        super(x, y, Collectible.LARGE_PADDLE);
    }

    @Override
    public void drop() {
        collectible = collectibleFactory.createLargePaddle(x, y);
    }
}
