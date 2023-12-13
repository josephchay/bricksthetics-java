package breakout.configs;

import java.util.Map;

public class Environment extends breakout.core.configs.Environment {
    private static Environment instance;

    public static synchronized Environment getInstance() {
        if (instance == null) {
            instance = new Environment();
        }

        return instance;
    }

    private Environment() {
        setDbPath("database");
        setLogPath("src/main/storage/logs");
        setDbTablesPath(Map.ofEntries(
            Map.entry("progresses", dbPath + "/progresses.ser")
        ));
        setResourceImagesPath(Map.ofEntries(
            // Doodads
            Map.entry("background-level-1-5", "/images/doodads/backgrounds/level-1-5.png"),
            Map.entry("background-gif", "/images/doodads/backgrounds/background-gif.gif"),
            Map.entry("background-gif2", "/images/doodads/backgrounds/background-gif2.gif"),

            // Projectiles
            Map.entry("raisinOrb", "/images/entities/projectiles/raisin-good-orb.png"),
            Map.entry("grassOrb", "/images/entities/projectiles/grass-good-orb.png"),
            Map.entry("redOrb", "/images/entities/projectiles/red-good-orb.png"),
            Map.entry("sunOrb", "/images/entities/projectiles/sun-good-orb.png"),
            Map.entry("silverOrb", "/images/entities/projectiles/silver-good-orb.png"),
            Map.entry("goldOrb", "/images/entities/projectiles/gold-good-orb.png"),

            // Players
            Map.entry("player", "/images/entities/players/paddles/raisin-good-paddle.png"),

            // Enemies
            Map.entry("raisinBrick", "/images/entities/enemies/bricks/raisin-good-rectangle-brick.png"),
            Map.entry("grassBrick", "/images/entities/enemies/bricks/grass-good-rectangle-brick.png"),
            Map.entry("sunBrick", "/images/entities/enemies/bricks/sun-good-rectangle-brick.png"),
            Map.entry("silverBrick", "/images/entities/enemies/bricks/silver-good-rectangle-brick.png"),
            Map.entry("goldBrick", "/images/entities/enemies/bricks/gold-good-rectangle-brick.png")
        ));
        setResourceSFXPath(Map.ofEntries(
            // Warmup
            Map.entry("warmup", "/sfx/miscellaneous/warmup.wav"),
            // Projectiles

            // Players
            Map.entry("playerHit", "/sfx/entities/players/hit-liquid.wav"),
            Map.entry("playerLevelUp", "/sfx/entities/players/level-up.wav"),
            Map.entry("playerCollect", "/sfx/entities/players/collect.wav"),
            Map.entry("playerEnlarge", "/sfx/entities/players/enlarge.wav"),
            Map.entry("playerShrink", "/sfx/entities/players/shrink.wav"),
            Map.entry("playerHeal", "/sfx/entities/players/heal.wav"),
            Map.entry("playerDamage", "/sfx/entities/players/damage.wav"),

            // Enemies
            Map.entry("enemyHit", "/sfx/entities/enemies/hit.wav"),
            Map.entry("enemyDestroyed", "/sfx/entities/enemies/destroyed.wav")
        ));
        setResourceStylesheetPath("/stylesheets/style.css");
    }
}
