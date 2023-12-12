package breakout.entities.enemies.bricks.collectibles;

import breakout.entities.enemies.bricks.Brick;
import breakout.entities.enemies.bricks.tags.Collectible;
import javafx.scene.canvas.GraphicsContext;

/**
 * Brick that drops a powerup collectible when destroyed
 */
abstract public class CollectibleBrick extends Brick {
    protected Collectible type;

    protected breakout.entities.enemies.bricks.collectibles.drops.Collectible collectible;

    /** Whether the collectible has been dropped */
    protected boolean drop = false;

    public CollectibleBrick(double x, double y, Collectible type) {
        super(x, y);

        this.type = type;
    }

    @Override
    public void draw(GraphicsContext graphics) {
        super.draw(graphics);
        graphics.setStroke(type.color());
        graphics.setLineWidth(settings.brickCollectibleIndicatorSize);
        graphics.strokeRoundRect(x, y, width, height, arc, arc);
    }

    /**
     * Drop the collectible
     */
    abstract public void drop();

    public breakout.entities.enemies.bricks.collectibles.drops.Collectible getCollectible() {
        return collectible;
    }

    public Collectible getType() {
        return type;
    }
}
