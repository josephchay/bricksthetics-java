package breakout.entities.enemies.bricks.collectibles;

import breakout.entities.enemies.bricks.tags.BrickType;
import breakout.entities.enemies.bricks.tags.Collectible;

abstract public class MultiorbBrick extends CollectibleBrick {
    protected static BrickType type = BrickType.MULTIORB;

    public MultiorbBrick(int x, int y) {
        super(x, y, Collectible.MULTIORB);
    }

    @Override
    public void drop() {
        collectible = collectibleFactory.createMultiball(x, y);
    }
}
