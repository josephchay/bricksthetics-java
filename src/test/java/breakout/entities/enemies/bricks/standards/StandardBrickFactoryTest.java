package breakout.entities.enemies.bricks.standards;

import breakout.container.ServiceBinder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Mock test class for {@link StandardBrickFactory}.
 */
public class StandardBrickFactoryTest implements StandardBrickFactory {
    StandardBrickFactory factory;

    @Before
    public void setUp() throws Exception {
        new ServiceBinder().register();
        factory = new StandardBrickFactoryTest();
    }


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
    public void createStandardTest() {
        StandardBrick standardBrick = factory.createStandard(10, 20);
        assertEquals(10, (int) standardBrick.getX());
        assertEquals(20, (int) standardBrick.getY());
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