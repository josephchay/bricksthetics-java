package breakout.entities.players.cannons;

import breakout.configs.Settings;
import breakout.entities.projectiles.orbs.Orb;
import breakout.entities.projectiles.orbs.OrbCollection;
import breakout.entities.projectiles.orbs.standards.StandardOrb;
import javafx.scene.paint.Color;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CannonTest {
    /**
     * Tests that the initial ammo count is correct.
     */
    @Test
    void initialAmmoTest() {
        Cannon cannon = new Cannon(0, 0, 50, 30, Color.BLUE);
        assertEquals(1, cannon.getAmmo());
    }

    /**
     * Tests randomizing the cannon's position within the specified bounds.
     */
    @Test
    void randomizePositionTest() {
        Cannon cannon = new Cannon(0, 0, 50, 30, Color.BLUE);
        OrbCollection orbs = new OrbCollection(new ArrayList<>());
        cannon.randomizePosition(800); // Assuming the screen width is 800
        assertTrue(cannon.getX() >= cannon.getWidth() / 2);
        assertTrue(cannon.getX() <= 800 - cannon.getWidth() / 2);
    }

    /**
     * Tests that the cannon runs out of ammo after firing.
     */
    @Test
    void runsOutOfAmmoAfterFiringTest() {
        Cannon cannon = new Cannon(0, 0, 50, 30, Color.BLUE);
        OrbCollection orbs = new OrbCollection(new ArrayList<>());
        StandardOrb orb = new StandardOrb(0, 0, 10, Color.BLUE);
        cannon.reload(new OrbCollection(new ArrayList<>(List.of(orb))));
        cannon.fire(orb);
        assertFalse(cannon.hasAmmo());
    }

    /**
     * Tests the deployment and retraction of the cannon.
     */
    @Test
    void deployAndRetractTest() {
        Cannon cannon = new Cannon(0, 0, 50, 30, Color.BLUE);
        OrbCollection orbs = new OrbCollection(new ArrayList<>());
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
    void reloadTest() {
        Cannon cannon = new Cannon(0, 0, 50, 30, Color.BLUE);
        OrbCollection orbs = new OrbCollection(new ArrayList<>());
        cannon.reload(orbs);
        assertEquals(orbs.size(), cannon.getAmmo());
    }

    /**
     * Tests the addition of an orb to the cannon's ammo.
     */
    @Test
    void addAmmoTest() {
        Cannon cannon = new Cannon(0, 0, 50, 30, Color.BLUE);
        OrbCollection orbs = new OrbCollection(new ArrayList<>());
        StandardOrb orb = new StandardOrb(0, 0, 10, Color.BLUE);
        cannon.addAmmo(orb);
        assertEquals(1, cannon.getAmmo());
    }

    /**
     * Tests the behavior of the cannon when retracting after firing.
     */
    @Test
    void retractAfterFiringTest() {
        Cannon cannon = new Cannon(0, 0, 50, 30, Color.BLUE);
        OrbCollection orbs = new OrbCollection(new ArrayList<>());
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
    void deployAfterRetractingTest() {
        Cannon cannon = new Cannon(0, 0, 50, 30, Color.BLUE);
        OrbCollection orbs = new OrbCollection(new ArrayList<>());
        cannon.deploy();
        cannon.retract();
        cannon.deploy();
        assertTrue(cannon.deployed());
    }
}