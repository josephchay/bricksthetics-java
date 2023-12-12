package breakout.entities.enemies.bricks.collectibles.drops;

import breakout.container.DependenciesInjectable;
import breakout.core.entities.Entity;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CollectibleCollection implements DependenciesInjectable {
    private List<Collectible> collectibles;

    /** Number of collectibles collected by the player. */
    private int collectedCount = 0;

    public CollectibleCollection(ArrayList<Collectible> collectibles) {
        this.collectibles = collectibles;
    }

    public void each(Consumer<Collectible> action) {
        collectibles.forEach(action);
    }

    public void update(double delta, int boundedHeight) {
        collectibles.forEach(collectible -> {
            collectible.update(delta, boundedHeight);
        });
        collectibles.removeIf(Collectible::dead);
    }

    public void draw(GraphicsContext graphics) {
        collectibles.stream().filter(collectible -> !collectible.dead()).forEach(collectible -> collectible.draw(graphics));
    }

    public void add(Collectible collectible) {
        collectibles.add(collectible);
    }

    public void trackCollected() {
        collectedCount++;
    }

    public List<Collectible> getCollectibles() {
        return collectibles;
    }

    public int getCollectedCount() {
        return collectedCount;
    }
}
