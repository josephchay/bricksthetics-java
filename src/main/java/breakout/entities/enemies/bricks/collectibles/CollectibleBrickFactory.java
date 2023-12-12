package breakout.entities.enemies.bricks.collectibles;

import breakout.entities.enemies.bricks.BrickFactory;

public interface CollectibleBrickFactory extends BrickFactory {
    LargePaddleBrick createLargePaddle(int x, int y);
    LifeBrick createLife(int x, int y);
    MultiorbBrick createMultiorb(int x, int y);
}
