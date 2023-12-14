package breakout.entities.players;

import breakout.container.ServiceBinder;
import breakout.entities.players.cannons.Cannon;
import breakout.entities.players.paddles.Paddle;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Mock test class for {@link PlayerFactory}.
 */
public class PlayerFactoryTest {
    PlayerFactory factory;

    @Before
    public void setUp() throws Exception {
        new ServiceBinder().register();
        factory = new PlayerFactory();
    }

    /**
     * Mock implementation of {@link PlayerFactory#createPaddle(double, double, int, int, Color)}.
     */
    @Test
    public void createPaddleTest() {
        Paddle paddle = factory.createPaddle(10.0, 20.0, 30, 40, Color.BLUE);
        assertEquals(10, (int) paddle.getX());
        assertEquals(20, (int) paddle.getY());
        assertEquals(30, (int) paddle.getWidth());
        assertEquals(40, (int) paddle.getHeight());
        assertEquals(Color.BLUE, paddle.getColor());
    }

    /**
     * Mock implementation of {@link PlayerFactory#createCannon(double, int, int, Color)}.
     */
    @Test
    public void createCannonTest() {
        Cannon cannon = factory.createCannon(50.0, 60, 70, Color.RED);
        assertEquals(50, (int) cannon.getY());
        assertEquals(60, (int) cannon.getWidth());
        assertEquals(70, (int) cannon.getHeight());
        assertEquals(Color.RED, cannon.getColor());
    }

    /**
     * Mock implementation of {@link Paddle}.
     */
    private static class MockPaddle extends Paddle {
        public MockPaddle(double x, double y, int width, int height, Color color) {
            super(x, y, width, height, color);
        }
    }

    /**
     * Mock implementation of {@link Cannon}.
     */
    private static class MockCannon extends Cannon {
        public MockCannon(double y, int width, int height, Color color) {
            super(y, width, height, color);
        }
    }
}