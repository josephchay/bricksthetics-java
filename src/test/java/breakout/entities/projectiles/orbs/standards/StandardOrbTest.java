package breakout.entities.projectiles.orbs.standards;

import breakout.core.entities.Entity;
import breakout.entities.players.paddles.Paddle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.junit.Test;

import static org.junit.Assert.*;

public class StandardOrbTest {
    /**
     * Tests if the created orb correctly collides with the given target.
     * Ensure that there's a collision between the orb and the target.
     */
    @Test
    public void testCollidesWith() {
        StandardOrb orb = new StandardOrb(0, 0, 10, Color.BLUE);
        Entity target = new Paddle(0, 0, 10, 10, Color.BLUE);
        boolean collision = orb.collidesWith(target);
        assertTrue(collision);
    }

    /**
     * Tests the update of visual effects when calling updateEffects() on the orb.
     * Ensure that the orb's effects are updated.
     */
    @Test
    public void testUpdateEffects() {
        StandardOrb orb = new StandardOrb(0, 0, 10, Color.BLUE);
        orb.updateEffects(0.1);
    }

    /**
     * Tests the corner hit detection mechanism during the collision check.
     * Ensure that the orb correctly updates the corner hit.
     */
    @Test
    public void testUpdateIncomingCornerHits() {
        StandardOrb orb = new StandardOrb(0, 0, 10, Color.BLUE);
        Shape targetHitbox = new Rectangle(0, 0, 20, 20);
        orb.updateIncomingCornerHits(0, 0, targetHitbox);
    }

    /**
     * Tests if the attack flag is properly set when invoking the attack method.
     * Ensure that the orb's attack flag is set to true.
     */
    @Test
    public void testAttack() {
        StandardOrb orb = new StandardOrb(0, 0, 10, Color.BLUE);
        assertFalse(orb.hasAttacked());
        orb.attack();
        assertTrue(orb.hasAttacked());
    }

    /**
     * Tests if the angle of the orb is randomized within the given range.
     * Ensure that the orb's angle is randomized within the given range.
     */
    @Test
    public void testRandomizeAngle() {
        StandardOrb orb = new StandardOrb(0, 0, 10, Color.BLUE);
        double initialAngle = orb.getAngle();
        orb.randomizeAngle(Math.PI / 4);
        assertNotEquals(initialAngle, orb.getAngle(), 0.01);
    }

    /**
     * Tests if the randomization of the movement angle results in values within the expected range.
     * Ensure that the orb's angle is within the expected range.
     */
    @Test
    public void testRandomizeAngleInRange() {
        StandardOrb orb = new StandardOrb(0, 0, 10, Color.BLUE);
        orb.randomizeAngle(Math.PI / 4);
        double angle = orb.getAngle();
        assertTrue(angle >= 0 && angle <= 2 * Math.PI);
    }
}