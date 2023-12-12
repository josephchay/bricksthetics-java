package breakout.container;

import breakout.configs.Settings;
import breakout.configs.Environment;
import breakout.core.contracts.DefaultDependenciesInjectable;
import breakout.entities.enemies.bricks.collectibles.drops.CollectibleFactory;
import breakout.entities.enemies.bricks.collectibles.rectangle.RectangleCollectibleBrickFactory;
import breakout.entities.enemies.bricks.hazards.rectangle.RectangleHazardBrickFactory;
import breakout.entities.enemies.bricks.standards.rectangle.RectangleStandardBrickFactory;
import breakout.entities.players.PlayerFactory;
import breakout.entities.projectiles.orbs.standards.StandardOrbFactory;

/**
 * Dependencies that can be injected into the class with constant values.
 * Re-injected services will hide but not change the default original services.
 */
public interface DependenciesInjectable extends DefaultDependenciesInjectable {
    /* Inject or re-inject custom services into the class. */

    Environment appEnv = (Environment) container.get("env");

    Settings settings = (Settings) container.get("settings");

    RectangleCollectibleBrickFactory rectangleCollectibleBrickFactory = (RectangleCollectibleBrickFactory) container.get("rectangleCollectibleBrickFactory");
    RectangleHazardBrickFactory rectangleHazardBrickFactory = (RectangleHazardBrickFactory) container.get("rectangleHazardBrickFactory");
    RectangleStandardBrickFactory rectangleStandardBrickFactory = (RectangleStandardBrickFactory) container.get("rectangleStandardBrickFactory");

    CollectibleFactory collectibleFactory = (CollectibleFactory) container.get("collectibleFactory");

    PlayerFactory playerFactory = (PlayerFactory) container.get("playerFactory");
    StandardOrbFactory orbFactory = (StandardOrbFactory) container.get("orbFactory");
}
