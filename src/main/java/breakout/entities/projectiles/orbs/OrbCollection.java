package breakout.entities.projectiles.orbs;

import breakout.container.DependenciesInjectable;
import breakout.entities.projectiles.orbs.standards.StandardOrb;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class OrbCollection implements DependenciesInjectable {
    private final List<Orb> items;

    public OrbCollection(ArrayList<Orb> orbs) {
        this.items = orbs;
    }
    
    public OrbCollection() {
        items = new ArrayList<>();
    }

    public void each(Consumer<Orb> action) {
        items.forEach(action);
    }

    public void update(double delta, int boundedWith, int boundedHeight) {
        items.forEach(orb -> {
            if (orb instanceof StandardOrb standard) {
                standard.update(delta, boundedWith, boundedHeight);
            }
        });
        items.removeIf(Orb::dead);
    }

    public void draw(GraphicsContext graphics) {
        items.stream().filter(orb -> !orb.dead()).forEach(orb -> orb.draw(graphics));
    }

    public void add(Orb orb) {
        items.add(orb);
    }

    public List<Orb> get() {
        return items;
    }

    public Orb get(int index) {
        return items.get(index);
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
