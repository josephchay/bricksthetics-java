package breakout.entities.players.paddles;

import breakout.screens.tags.ShakeMagnitude;
import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaddleTest {
    /**
     * Tests the deploy method of the Paddle class.
     */
    @Test
    public void testDeploy() {
        Paddle paddle = new Paddle(100, 100, 50, 20, Color.BLUE);
        assertFalse("Paddle should not be deployed initially", paddle.deployed());
        paddle.deploy();
        assertTrue("Paddle should be deployed", paddle.deployed());
    }

    /**
     * Tests the moveLeft method of the Paddle class.
     */
    @Test
    public void testMoveLeft() {
        Paddle paddle = new Paddle(100, 100, 50, 20, Color.BLUE);
        paddle.moveLeft(0.016, 500);
        assertTrue("Paddle should move left", paddle.getX() < 100);
    }

    /**
     * Tests the moveRight method of the Paddle class.
     */
    @Test
    public void testMoveRight() {
        Paddle paddle = new Paddle(100, 100, 50, 20, Color.BLUE);
        paddle.moveRight(0.016, 500);
        assertTrue("Paddle should move right", paddle.getX() > 100);
    }

    /**
     * Tests the wall method of the Paddle class.
     */
    @Test
    public void testWall() {
        Paddle paddle = new Paddle(100, 100, 50, 20, Color.BLUE);
        paddle.wall(300);
        assertTrue("Paddle should be in the walled state", paddle.walled());
    }

    /**
     * Tests the enlarge method of the Paddle class.
     */
    @Test
    public void testEnlarge() {
        Paddle paddle = new Paddle(100, 100, 50, 20, Color.BLUE);
        paddle.enlarge();
        assertTrue("Paddle should be in the enlarging state", paddle.isEnlarging());
    }

    /**
     * Tests the triggerKnockback method of the Paddle class.
     */
    @Test
    public void testTriggerKnockback() {
        Paddle paddle = new Paddle(100, 100, 50, 20, Color.BLUE);
        paddle.triggerKnockback();
        assertTrue("Knockback should be triggered", paddle.isKnockbackActive());
    }

    /**
     * Tests the triggerKnockbackShake method of the Paddle class.
     */
    @Test
    public void testTriggerKnockbackShake() {
        Paddle paddle = new Paddle(100, 100, 50, 20, Color.BLUE);
        paddle.triggerKnockbackShake(ShakeMagnitude.MEDIUM);
        assertTrue("Knockback with shake should be triggered", paddle.isKnockbackActive());
    }

    /**
     * Tests the moveInDirection method of the Paddle class.
     */
    @Test
    public void testMoveInDirection() {
        Paddle paddle = new Paddle(100, 100, 50, 20, Color.BLUE);
        paddle.moveInDirection(200, 500);
        assertEquals("Paddle X position should be updated", 200 - paddle.getWidth() / 2, paddle.getX(), 0.1);
    }
}