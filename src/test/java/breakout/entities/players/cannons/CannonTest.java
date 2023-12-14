package breakout.entities.players.cannons;

import breakout.configs.Settings;
import breakout.container.ServiceBinder;
import breakout.entities.projectiles.orbs.Orb;
import breakout.entities.projectiles.orbs.OrbCollection;
import breakout.entities.projectiles.orbs.standards.StandardOrb;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CannonTest {
    Cannon cannon;

    @Before
    public void setUp() throws Exception {
        new ServiceBinder().register();
        cannon = new Cannon(0, 0, 50, 30, Color.BLUE);
    }

    /**
     * Tests that the initial ammo count is correct.
     */
    @Test
    public void initialAmmoTest() {
        Cannon cannon = new Cannon(0, 0, 50, 30, Color.BLUE);
        assertEquals(0, cannon.getAmmo());
    }

    /**
     * Tests randomizing the cannon's position within the specified bounds.
     */
    @Test
    public void randomizePositionTest() {
        cannon.randomizePosition(800); // Assuming the screen width is 800
        assertTrue(cannon.getX() >= cannon.getWidth() / 2);
        assertTrue(cannon.getX() <= 800 - cannon.getWidth() / 2);
    }

    /**
     * Tests that the cannon runs out of ammo after firing.
     */
    @Test
    public void runsOutOfAmmoAfterFiringTest() {
        StandardOrb orb = new StandardOrb(0, 0, 10, Color.BLUE);
        cannon.reload(new OrbCollection(new ArrayList<>(List.of(orb))));
        cannon.fire(orb);
        assertFalse(cannon.hasAmmo());
    }

    /**
     * Tests the deployment and retraction of the cannon.
     */
    @Test
    public void deployAndRetractTest() {
        assertFalse(cannon.deployed());
        cannon.deploy();
        assertTrue(cannon.deployed());
        cannon.retract();
        assertFalse(cannon.deployed());
    }

    /**
     * Tests the reloading of orbs into the cannon.
     */
    @Test
    public void reloadTest() {
        Cannon cannon = new Cannon(0, 0, 50, 30, Color.BLUE);
        OrbCollection orbs = new OrbCollection(new ArrayList<>());
        cannon.reload(orbs);
        assertEquals(orbs.size(), cannon.getAmmo());
    }

    /**
     * Tests the addition of an orb to the cannon's ammo.
     */
    @Test
    public void addAmmoTest() {
        StandardOrb orb = new StandardOrb(0, 0, 10, Color.BLUE);
        cannon.addAmmo(orb);
        assertEquals(1, cannon.getAmmo());
    }

    /**
     * Tests the behavior of the cannon when retracting after firing.
     */
    @Test
    public void retractAfterFiringTest() {
        Orb orb = new StandardOrb(0, 0, 10, Color.BLUE);
        cannon.reload(new OrbCollection(new ArrayList<>(List.of(orb))));
        cannon.fire(orb);
        cannon.retract();
        assertFalse(cannon.deployed());
    }

    /**
     * Tests that the cannon can be deployed after retracting.
     */
    @Test
    public void deployAfterRetractingTest() {
        cannon.deploy();
        cannon.retract();
        cannon.deploy();
        assertTrue(cannon.deployed());
    }
}