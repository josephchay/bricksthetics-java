package breakout.entities.enemies.bricks.standards.rectangle;

import breakout.container.ServiceBinder;
import breakout.entities.enemies.bricks.tags.Brick;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import org.junit.Test;
import org.junit.Before;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for the {@link breakout.entities.enemies.bricks.Brick} class.
 */
public class StandardRectangleBrickTest {
    private breakout.entities.enemies.bricks.Brick brick;

    @Before
    public void setUp() {
        new ServiceBinder().register();
        brick = new MockBrick(0, 0);
    }

    /**
     * Tests that the color of the brick is initialized correctly based on its health.
     */
    @Test
    public void initialColorTest() {
        int initialHealth = brick.getHealth();
        Color expectedColor = Color.rgb(0, 255 - initialHealth * 100, 255 - initialHealth * 100);
        assertEquals(expectedColor, brick.getColor());
    }

    /**
     * Tests the damage method, ensuring that health decreases and color changes accordingly.
     */
    @Test
    public void damageTest() {
        int initialHealth = brick.getHealth();
        brick.damage();
        assertEquals(initialHealth - 1, brick.getHealth());
        Color expectedColor = Color.rgb(0, 255 - (initialHealth - 1) * 100, 255 - (initialHealth - 1) * 100);
        assertEquals(expectedColor, brick.getColor());
    }

    /**
     * Tests the die method, making sure that the brick is killed.
     */
    @Test
    public void dieTest() {
        brick.die();
        assertTrue(brick.dead());
    }

    /**
     * Tests the deform method, checking that the color changes based on health.
     */
    @Test
    public void deformTest() {
        int initialHealth = brick.getHealth();
        brick.deform();
        Color expectedColor = Color.rgb(0, 255 - initialHealth * 100, 255 - initialHealth * 100);
        assertEquals(expectedColor, brick.getColor());
    }

    /**
     * Tests that the hitbox method returns a rectangle with the correct dimensions.
     */
    @Test
    public void hitboxTest() {
        Rectangle hitbox = brick.hitbox();
        assertEquals(brick.getX(), hitbox.getX());
        assertEquals(brick.getY(), hitbox.getY());
        assertEquals(brick.getWidth(), hitbox.getWidth());
        assertEquals(brick.getHeight(), hitbox.getHeight());
    }

    /**
     * Tests the type method, ensuring it returns the correct brick type.
     */
    @Test
    public void typeTest() {
        assertEquals(MockBrick.type(), Brick.STANDARD);
    }

    /**
     * A mock implementation of the {@link breakout.entities.enemies.bricks.Brick} class for testing purposes.
     */
    private static class MockBrick extends breakout.entities.enemies.bricks.Brick {
        private static final Brick type = Brick.STANDARD;

        public MockBrick(double x, double y) {
            super(x, y);
        }

        public static Brick type() {
            return type;
        }
    }
}
