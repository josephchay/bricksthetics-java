package breakout.entities.projectiles.orbs;

import breakout.core.entities.projectiles.ProjectileFactory;
import javafx.scene.paint.Color;

public interface OrbFactory extends ProjectileFactory {
    Orb createOrb(double x, double y, int diameter, Color color);
}
