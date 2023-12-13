package breakout.entities.enemies.bricks.standards;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Mock test class for {@link StandardBrickFactory}.
 */
public class StandardBrickFactoryTest implements StandardBrickFactory {

    /**
     * Mock implementation of {@link StandardBrickFactory#createStandard(int, int)}.
     */
    @Override
    public StandardBrick createStandard(int x, int y) {
        return new MockStandardBrick(x, y);
    }

    /**
     * Mock implementation of {@link StandardBrickFactory#createStandard(int, int)}.
     */
    @Test
    void createStandardTest() {
        StandardBrickFactory factory = new StandardBrickFactoryTest();
        StandardBrick standardBrick = factory.createStandard(10, 20);
        assertEquals(10, standardBrick.getX());
        assertEquals(20, standardBrick.getY());
    }

    /**
     * Mock implementation of {@link StandardBrick}.
     */
    private static class MockStandardBrick extends StandardBrick {
        public MockStandardBrick(int x, int y) {
            super(x, y);
        }
    }
}