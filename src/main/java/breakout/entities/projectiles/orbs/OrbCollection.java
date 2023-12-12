package breakout.entities.projectiles.orbs;

import breakout.container.DependenciesInjectable;
import breakout.entities.projectiles.orbs.standards.StandardOrb;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class OrbCollection implements DependenciesInjectable {
    private List<Orb> orbs;

    public OrbCollection(ArrayList<Orb> orbs) {
        this.orbs = orbs;
    }

    public void each(Consumer<Orb> action) {
        orbs.forEach(action);
    }

    public void update(double delta, int boundedWith, int boundedHeight) {
        orbs.forEach(orb -> {
            if (orb instanceof StandardOrb standard) {
                standard.update(delta, boundedWith, boundedHeight);
            }
        });
        orbs.removeIf(Orb::dead);
    }

    public void draw(GraphicsContext graphics) {
        orbs.stream().filter(orb -> !orb.dead()).forEach(orb -> orb.draw(graphics));
    }

    public void add(Orb orb) {
        orbs.add(orb);
    }

    public List<Orb> getOrbs() {
        return orbs;
    }

    public Orb get(int index) {
        return orbs.get(index);
    }

    public int size() {
        return orbs.size();
    }
}
