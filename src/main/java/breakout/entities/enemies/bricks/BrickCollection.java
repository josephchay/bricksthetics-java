package breakout.entities.enemies.bricks;

import breakout.container.DependenciesInjectable;
import javafx.scene.canvas.GraphicsContext;

import java.util.function.Consumer;

public class BrickCollection implements DependenciesInjectable {
    private Brick[][] bricks;
    private int destroyedCount = 0;
    private final ComboTracker comboTracker = new ComboTracker();

    public BrickCollection(Brick[][] items) {
        this.bricks = items;
    }

    public void each(Consumer<Brick> action) {
        for (Brick[] row : bricks) {
            for (Brick brick : row) {
                action.accept(brick);
            }
        }
    }

    public void update(double delta) {
        for (Brick[] row : bricks) {
            for (Brick brick : row) {
                brick.update(delta);
            }
        }

        comboTracker.update(delta);
    }

    public void draw(GraphicsContext graphics) {
        for (Brick[] row : bricks) {
            for (Brick brick : row) {
                if (!brick.dead()) {
                    brick.draw(graphics);
                }
            }
        }
    }

    public boolean isEmpty() {
        return destroyedCount == bricks.length * bricks[0].length;
    }

    public Brick getItem(int row, int col) {
        return bricks[row][col];
    }

    public void trackDestroyed() {
        destroyedCount++;
    }

    public Brick[][] getBricks() {
        return bricks;
    }

    public int getDestroyedCount() {
        return destroyedCount;
    }

    public void add(Brick brick, int row, int col) {
        bricks[row][col] = brick;
    }

    // Combo-related methods
    public void triggerCombo() {
        comboTracker.trigger();
    }

    public int getComboCount() {
        return comboTracker.getCount();
    }

    public double getComboTimer() {
        return comboTracker.getTimer();
    }

    /**
     * Tracks the combo count and timer for the bricks destroyed.
     */
    private class ComboTracker {
        private int count = 0;
        private double timer = 0.0;
        private final double duration = settings.brickComboDuration; // in seconds

        public void trigger() {
            count++;
            if (timer == 0) {
                timer = duration;
            }
        }

        public void update(double deltaTime) {
            if (timer > 0) {
                timer -= deltaTime;
            } else if (count > 0) {
                reset();
            }
        }

        public int getCount() {
            return count;
        }

        public double getTimer() {
            return timer;
        }

        private void reset() {
            timer = 0;
            count = 0;
        }
    }
}
