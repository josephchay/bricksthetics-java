package breakout.container;

import breakout.configs.Environment;
import breakout.configs.Settings;
import breakout.core.container.DefaultServiceBinder;
import breakout.core.screens.views.ViewBuilder;
import breakout.entities.enemies.bricks.collectibles.drops.CollectibleFactory;
import breakout.entities.enemies.bricks.collectibles.rectangle.RectangleCollectibleBrickFactory;
import breakout.entities.enemies.bricks.standards.rectangle.RectangleStandardBrickFactory;
import breakout.entities.players.PlayerFactory;
import breakout.entities.projectiles.orbs.standards.StandardOrbFactory;

public class ServiceBinder extends DefaultServiceBinder {
    @Override
    public void register() {
        super.register();

        /* Register custom application dependencies. */
        registerEnv();
        registerSettings();
        registerViewBuilder();
        registerFactories();
    }

    private void registerEnv() {
        container.registerSingleton("env", Environment.getInstance());
    }

    private void registerSettings() {
        container.registerSingleton("settings", Settings.getInstance());
    }

    private void registerViewBuilder() {
        container.registerSingleton("vb", ViewBuilder.getInstance());
    }

    private void registerFactories() {
        // Enemies
        container.registerRegular("rectangleCollectibleBrickFactory", RectangleCollectibleBrickFactory.class);
        container.registerRegular("rectangleStandardBrickFactory", RectangleStandardBrickFactory.class);

        // Collectibles
        container.registerRegular("collectibleFactory", CollectibleFactory.class);

        // Players
        container.registerRegular("playerFactory", PlayerFactory.class);

        // Projectiles
        container.registerRegular("orbFactory", StandardOrbFactory.class);
    }
}
