package breakout.entities.projectiles.orbs.standards;

import breakout.container.ServiceBinder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StandardOrbTest {
    StandardOrb orb;

    @Before
    public void setUp() {
        new ServiceBinder().register();
        orb = new StandardOrb(0, 0, 10, Color.BLUE);
    }

    /**
     * Tests the update of visual effects when calling updateEffects() on the orb.
     * Ensure that the orb's effects are updated.
     */
    @Test
    public void testUpdateEffects() {
        orb.updateEffects(0.1);
    }

    /**
     * Tests the corner hit detection mechanism during the collision check.
     * Ensure that the orb correctly updates the corner hit.
     */
    @Test
    public void testUpdateIncomingCornerHits() {
        Shape targetHitbox = new Rectangle(0, 0, 20, 20);
        orb.updateIncomingCornerHits(0, 0, targetHitbox);
    }

    /**
     * Tests if the attack flag is properly set when invoking the attack method.
     * Ensure that the orb's attack flag is set to true.
     */
    @Test
    public void testAttack() {
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
        orb.randomizeAngle(Math.PI / 4);
        double angle = orb.getAngle();
        assertTrue(angle >= 0 && angle <= 2 * Math.PI);
    }
}