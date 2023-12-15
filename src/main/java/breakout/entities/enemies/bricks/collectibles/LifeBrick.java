package breakout.entities.enemies.bricks.collectibles;

import breakout.entities.enemies.bricks.tags.Brick;
import breakout.entities.enemies.bricks.tags.Collectible;

abstract public class LifeBrick extends CollectibleBrick {
    protected static Brick type = Brick.LIFE;

    public LifeBrick(double x, double y) {
        super(x, y, Collectible.LIFE);
    }

    @Override
    public void drop() {
        collectible = collectibleFactory.createLife(x, y);
    }
}
