package breakout.entities.projectiles.orbs.standards;

import breakout.container.ServiceBinder;
import breakout.entities.projectiles.orbs.Orb;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Mock test class for {@link StandardOrbFactory}.
 */
public class StandardOrbFactoryTest {
    StandardOrbFactory factory;

    @Before
    public void setUp() throws Exception {
        new ServiceBinder().register();
        factory = new StandardOrbFactory();
    }

    /**
     * Mock implementation of {@link StandardOrbFactory#createOrb(double, double, int, Color)}.
     */
    @Test
    public void createOrbTest() {
        Orb orb = factory.createOrb(10.0, 20.0, 15, Color.BLUE);
        assertEquals(10, (int) orb.getX());
        assertEquals(20, (int) orb.getY());
        assertEquals(30, (int) orb.getHeight() * 2);
        assertEquals(Color.BLUE, orb.getColor());
    }

    /**
     * Mock implementation of {@link StandardOrbFactory#createOrbUnloaded(double, int, Color)}.
     */
    @Test
    public void createOrbUnloadedTest() {
        Orb orb = factory.createOrbUnloaded(30.0, 25, Color.RED);
        assertEquals(30, (int) orb.getY());
        assertEquals(50, (int) orb.getHeight() * 2);
        assertEquals(Color.RED, orb.getColor());
    }

    /**
     * Mock implementation of {@link StandardOrb}.
     */
    private static class MockStandardOrb extends StandardOrb {
        public MockStandardOrb(double x, double y, int diameter, Color color) {
            super(x, y, diameter, color);
        }

        public MockStandardOrb(double y, int diameter, Color color) {
            super(y, diameter, color);
        }
    }
}