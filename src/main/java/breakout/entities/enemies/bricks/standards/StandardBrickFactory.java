package breakout.entities.enemies.bricks.standards;

import breakout.entities.enemies.bricks.BrickFactory;

public interface StandardBrickFactory extends BrickFactory {
    StandardBrick createStandard(int x, int y);
}
