package breakout.entities.players;

import breakout.entities.players.cannons.Cannon;
import breakout.entities.players.paddles.Paddle;
import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Mock test class for {@link PlayerFactory}.
 */
public class PlayerFactoryTest {
    /**
     * Mock implementation of {@link PlayerFactory#createPaddle(double, double, int, int, Color)}.
     */
    @Test
    void createPaddleTest() {
        PlayerFactory playerFactory = new PlayerFactory();
        Paddle paddle = playerFactory.createPaddle(10.0, 20.0, 30, 40, Color.BLUE);
        assertEquals(10.0, paddle.getX());
        assertEquals(20.0, paddle.getY());
        assertEquals(30, paddle.getWidth());
        assertEquals(40, paddle.getHeight());
        assertEquals(Color.BLUE, paddle.getColor());
    }

    /**
     * Mock implementation of {@link PlayerFactory#createCannon(double, int, int, Color)}.
     */
    @Test
    void createCannonTest() {
        PlayerFactory playerFactory = new PlayerFactory();
        Cannon cannon = playerFactory.createCannon(50.0, 60, 70, Color.RED);
        assertEquals(50.0, cannon.getY());
        assertEquals(60, cannon.getWidth());
        assertEquals(70, cannon.getHeight());
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