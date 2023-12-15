package breakout.entities.enemies.bricks.standards;

import breakout.entities.enemies.bricks.tags.Brick;

abstract public class StandardBrick extends breakout.entities.enemies.bricks.Brick {
    protected static Brick type = Brick.STANDARD;

    public StandardBrick(double x, double y) {
        super(x, y);
    }
}
