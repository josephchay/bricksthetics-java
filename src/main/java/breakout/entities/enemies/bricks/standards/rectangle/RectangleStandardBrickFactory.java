package breakout.entities.enemies.bricks.standards.rectangle;

import breakout.entities.enemies.bricks.standards.StandardBrick;
import breakout.entities.enemies.bricks.standards.StandardBrickFactory;

public class RectangleStandardBrickFactory implements StandardBrickFactory {
    @Override
    public StandardBrick createStandard(int x, int y) {
        return new StandardRectangleBrick(x, y);
    }
}
