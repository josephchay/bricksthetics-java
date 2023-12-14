package breakout.entities.players.paddles;

import breakout.container.ServiceBinder;
import breakout.screens.tags.ShakeMagnitude;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaddleTest {
    private Paddle paddle;

    @Before
    public void setUp() throws Exception {
        new ServiceBinder().register();
        paddle = new Paddle(100, 100, 50, 20, Color.BLUE);
    }

    /**
     * Tests the deploy method of the Paddle class.
     */
    @Test
    public void testDeploy() {
        assertFalse("Paddle should not be deployed initially", paddle.deployed());
        paddle.deploy();
        assertTrue("Paddle should be deployed", paddle.deployed());
    }

    /**
     * Tests the wall method of the Paddle class.
     */
    @Test
    public void testWall() {
        paddle.wall(300);
        assertTrue("Paddle should be in the walled state", paddle.walled());
    }

    /**
     * Tests the enlarge method of the Paddle class.
     */
    @Test
    public void testEnlarge() {
        paddle.enlarge();
        assertTrue("Paddle should be in the enlarging state", paddle.isEnlarging());
    }

    /**
     * Tests the triggerKnockback method of the Paddle class.
     */
    @Test
    public void testTriggerKnockback() {
        paddle.triggerKnockback();
        assertTrue("Knockback should be triggered", paddle.isKnockbackActive());
    }

    /**
     * Tests the triggerKnockbackShake method of the Paddle class.
     */
    @Test
    public void testTriggerKnockbackShake() {
        paddle.triggerKnockbackShake(ShakeMagnitude.MEDIUM);
        assertTrue("Knockback with shake should be triggered", paddle.isKnockbackActive());
    }
}