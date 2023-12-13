package breakout.entities.enemies.bricks.standards.rectangle;

import breakout.configs.Settings;
import breakout.entities.enemies.bricks.Brick;
import breakout.entities.enemies.bricks.tags.BrickType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for the {@link Brick} class.
 */
public class StandardRectangleBrickTest {

    /**
     * Tests that the color of the brick is initialized correctly based on its health.
     */
    @Test
    void initialColorTest() {
        Brick brick = new MockBrick(0, 0);
        int initialHealth = brick.getHealth();
        Color expectedColor = Color.rgb(0, 255 - initialHealth * 100, 255 - initialHealth * 100);
        assertEquals(expectedColor, brick.getColor());
    }

    /**
     * Tests the damage method, ensuring that health decreases and color changes accordingly.
     */
    @Test
    void damageTest() {
        Brick brick = new MockBrick(0, 0);
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
    void dieTest() {
        Brick brick = new MockBrick(0, 0);
        brick.die();
        assertTrue(brick.dead());
    }

    /**
     * Tests the deform method, checking that the color changes based on health.
     */
    @Test
    void deformTest() {
        Brick brick = new MockBrick(0, 0);
        int initialHealth = brick.getHealth();
        brick.deform();
        Color expectedColor = Color.rgb(0, 255 - initialHealth * 100, 255 - initialHealth * 100);
        assertEquals(expectedColor, brick.getColor());
    }

    /**
     * Tests that the hitbox method returns a rectangle with the correct dimensions.
     */
    @Test
    void hitboxTest() {
        Brick brick = new MockBrick(0, 0);
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
    void typeTest() {
        assertEquals(MockBrick.type(), BrickType.STANDARD);
    }

    /**
     * A mock implementation of the {@link Brick} class for testing purposes.
     */
    private static class MockBrick extends Brick {
        private static final BrickType type = BrickType.STANDARD;

        public MockBrick(double x, double y) {
            super(x, y);
        }

        public static BrickType type() {
            return type;
        }
    }
}
