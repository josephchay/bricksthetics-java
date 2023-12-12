package breakout.entities.enemies.bricks.collectibles.drops;

import breakout.container.DependenciesInjectable;
import breakout.core.entities.Entity;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Shadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

abstract public class Collectible extends breakout.core.entities.props.Collectible implements DependenciesInjectable {
    protected Rotate rotate;
    protected Timeline timeline;

    protected int arc = settings.brickArc;

    /**
     * Collectible that drops from a brick and moves downwards only
     * @param x x position of the collectible
     * @param y y position of the collectible
     * @param vy velocity of the collectible
     */
    public Collectible(double x, double y, double vy) {
        super(x, y, settings.collectibleWidth, settings.collectibleHeight, 0, vy);

        rotate = new Rotate(0, x + width / 2, y + height / 2);
        initAnimation();
    }

    private void initAnimation() {
        timeline = new Timeline();

        // Spin animation
        KeyValue spinStart = new KeyValue(rotate.angleProperty(), 0, Interpolator.LINEAR);
        KeyValue spinEnd = new KeyValue(rotate.angleProperty(), 360, Interpolator.LINEAR);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(settings.collectibleSpinDurationPerCycle), spinStart, spinEnd);
        timeline.getKeyFrames().add(keyFrame);

        // Rotate continuously
        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();
    }

    public void update(double delta, int boundedHeight) {
        updateDy(delta);
        updateY(boundedHeight);
    }

    @Override
    protected void updateDy(double delta) {
        dy = vy * delta;
    }

    protected void updateY(int boundedHeight) {
        y += dy;

        checkOutOfBoundary(boundedHeight);
    }

    private void checkOutOfBoundary(int height) {
        if (y > height) {
            alive = false;
        }
    }

    @Override
    public void draw(GraphicsContext graphics) {
        graphics.save();

        graphics.translate(x + width / 2, y + height / 2);
        graphics.rotate(rotate.getAngle());
        graphics.translate(-width / 2, -height / 2);

        // Glow effect
        GaussianBlur blur = new GaussianBlur(6);
        Shadow glow = new Shadow();
        glow.setColor(color.brighter());
        glow.setRadius(18);
        glow.setInput(blur);

        graphics.setEffect(glow);

        graphics.setFill(color);
        graphics.fillRect(0, 0, width, height);

        graphics.setEffect(null);

        graphics.setStroke(color.darker());
        graphics.setLineWidth(settings.brickCollectibleIndicatorSize);
        graphics.strokeRoundRect(0, 0, width, height, arc, arc);

        graphics.restore();
    }

    @Override
    public Shape hitbox() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public boolean collidesWith(Entity target) {
        Shape targetHitbox = target.hitbox();

        boolean detected = false;

        if (targetHitbox instanceof Rectangle) {
            Rectangle targetRectangle = (Rectangle) targetHitbox;
            Rectangle thisRectangle = (Rectangle) this.hitbox();

            // Check for intersection
            detected = thisRectangle.getBoundsInParent().intersects(targetRectangle.getBoundsInParent());
        }

        return detected;
    }
}
