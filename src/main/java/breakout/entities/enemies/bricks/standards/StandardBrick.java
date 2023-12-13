package breakout.entities.enemies.bricks.standards;

import breakout.entities.enemies.bricks.Brick;
import breakout.entities.enemies.bricks.tags.BrickType;

abstract public class StandardBrick extends Brick {
    protected static BrickType type = BrickType.STANDARD;

    public StandardBrick(double x, double y) {
        super(x, y);
    }
}
