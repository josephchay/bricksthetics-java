package breakout.entities.enemies.bricks.collectibles;

import breakout.entities.enemies.bricks.BrickFactory;

public interface CollectibleBrickFactory extends BrickFactory {
    /**
     * Creates a Large Paddle collectible brick at the given coordinates
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the collectible brick
     */
    LargePaddleBrick createLargePaddle(int x, int y);

    /**
     * Creates a Life collectible brick at the given coordinates
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the collectible brick
     */
    LifeBrick createLife(int x, int y);

    /**
     * Creates a Multi Orb collectible brick at the given coordinates
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the collectible brick
     */
    MultiOrbBrick createMultiOrb(int x, int y);
}
