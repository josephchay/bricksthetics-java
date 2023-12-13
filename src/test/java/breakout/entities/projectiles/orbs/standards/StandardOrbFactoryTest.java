package breakout.entities.projectiles.orbs.standards;

import breakout.entities.projectiles.orbs.Orb;
import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Mock test class for {@link StandardOrbFactory}.
 */
public class StandardOrbFactoryTest {
    /**
     * Mock implementation of {@link StandardOrbFactory#createOrb(double, double, int, Color)}.
     */
    @Test
    void createOrbTest() {
        StandardOrbFactory orbFactory = new StandardOrbFactory();
        Orb orb = orbFactory.createOrb(10.0, 20.0, 15, Color.BLUE);
        assertEquals(10.0, orb.getX());
        assertEquals(20.0, orb.getY());
        assertEquals(15, orb.getHeight() * 2);
        assertEquals(Color.BLUE, orb.getColor());
    }

    /**
     * Mock implementation of {@link StandardOrbFactory#createOrbUnloaded(double, int, Color)}.
     */
    @Test
    void createOrbUnloadedTest() {
        StandardOrbFactory orbFactory = new StandardOrbFactory();
        Orb orb = orbFactory.createOrbUnloaded(30.0, 25, Color.RED);
        assertEquals(30.0, orb.getY());
        assertEquals(15, orb.getHeight() * 2);
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