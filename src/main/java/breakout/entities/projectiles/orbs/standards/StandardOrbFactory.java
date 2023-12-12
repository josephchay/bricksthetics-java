package breakout.entities.projectiles.orbs.standards;

import breakout.entities.projectiles.orbs.Orb;
import breakout.entities.projectiles.orbs.OrbFactory;
import javafx.scene.paint.Color;

public class StandardOrbFactory implements OrbFactory {
    public Orb createOrb(double x, double y, int diameter, Color color) {
        return new StandardOrb(x, y, diameter, color);
    }

    /**
     * Creates an orb that has not been loaded to the cannon.
     * Unloaded orbs do not have a fixed x position yet.
     *
     * @param y The y position of the orb.
     * @param diameter The diameter of the orb.
     * @param color The color of the orb.
     * @return The orb.
     */
    public Orb createOrbUnloaded(double y, int diameter, Color color) {
        return new StandardOrb(y, diameter, color);
    }
}
