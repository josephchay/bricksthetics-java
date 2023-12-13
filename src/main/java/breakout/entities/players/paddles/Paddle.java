package breakout.entities.players.paddles;

import breakout.container.DependenciesInjectable;
import breakout.core.entities.actors.Player;

import breakout.core.utils.Helper;
import breakout.entities.enemies.bricks.tags.BrickScore;
import breakout.screens.tags.ShakeMagnitude;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Paddle extends Player implements DependenciesInjectable {
    private int arc = settings.playerArc;

    private double initialY;
    private int initialWidth, initialHeight;
    private boolean enlarging = false;
    private boolean enlargingJustCompleted = false;
    private int largeDuration = settings.playerResizedDuration * 10000;
    private long enlargeTimer;

    /** Whether the paddle has been deployed for user input usage. */
    private boolean deployed = false;

    /** Whether the paddle has been pulled back and denied for user input acting as a sufficient wall. */
    private boolean walled = false;

    /** Whether the paddle is currently undergoing the walling process. */
    private boolean walling = false;

    private int orbStack = 5;

    private Knockback knockback = new Knockback();

    public Paddle(double x, double y, int width, int height, Color color) {
        super(x, y, width, height, 0, settings.playerInitialLife, color);

        initialY = y;
        initialWidth = width;
        initialHeight = height;

//        this.width = settings.appWidth;
//        this.x = 0;
//        this.y = settings.appHeight - height;
    }

    public void deploy() {
        walled = false;
        walling = false;
        deployed = true;
    }

    @Override
    public Rectangle hitbox() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public void update(double delta, int boundedWith, int boundedHeight) {
        x += (targetX - x) * settings.playerResizeVelocity * delta;
        y += (targetY - y) * settings.playerResizeVelocity * delta;

        knockback.update(delta);

        if (enlarging) {
            if ((System.nanoTime() - enlargeTimer) / 1000 > largeDuration) {
                enlarging = false;
                targetWidth = width / 2;
                targetXCenter = x + width / 4;

                audios.play("playerShrink");
            }

            width += (targetWidth - width) * settings.playerResizeVelocity * delta;
            targetX = targetXCenter - width / 2;
        } else if (walling) {
            width += (targetWidth - width) * settings.playerResizeVelocity * delta;
            targetX = targetXCenter - width / 2;
//            height += (targetHeight - height) * settings.playerResizeVelocity * delta;
//            targetY = targetYCenter - height / 2;
        } else {
            // Additional condition to shrink the width once
            if (width > initialWidth) {
                targetWidth = initialWidth;
                width += (targetWidth - width) * settings.playerResizeVelocity * delta;
                targetX = x + (width - initialWidth) / 2;
                targetY = initialY;
            }
        }

        ensureXInBoundary(settings.appWidth);
    }

    @Override
    public void draw(GraphicsContext graphics) {
        // Apply the vibration offset to the drawing position
        double drawX = x;
        double drawY = y;

        if (knockback instanceof KnockbackShake shake) {
            drawX += shake.vibrationOffsetX;
            drawY += shake.vibrationOffsetY;
        }

        graphics.setFill(color);
        graphics.fillRoundRect(drawX, drawY, width, height, arc, arc);

        if (enlarging) {
            drawEnlarge(graphics, drawX, drawY);
        }
    }

    private void drawEnlarge(GraphicsContext graphics, double drawX, double drawY) {
        // Elapsed time in seconds since largeActive was set
        double elapsedTimeSeconds = (System.nanoTime() - enlargeTimer) / 1_000_000_000.0;

        int totalDurationInSeconds = largeDuration / 1000000 + 1;
        int countdownSeconds = (int) (totalDurationInSeconds - elapsedTimeSeconds);

        // make sure countdown does not go below 0
        countdownSeconds = Math.max(countdownSeconds, 0);

        String countdownText = String.valueOf(countdownSeconds);
        Text textNode = new Text(countdownText);
        textNode.setFont(graphics.getFont());

        double textWidth = textNode.getLayoutBounds().getWidth();
        double textHeight = textNode.getLayoutBounds().getHeight();
        double textX = drawX + (width - textWidth) / 2;
        double textY = drawY + (height / 2) + (textHeight / 4);

        graphics.setFill(Color.BLACK);
        graphics.fillText(countdownText, textX, textY);

        if (totalDurationInSeconds - elapsedTimeSeconds <= 0) {
            enlarging = false;
            enlargeTimer = 0;
        }
    }

    public void moveInDirection(int mouseX, int boundedWith) {
        targetXCenter = mouseX;
        targetX = targetXCenter - width / 2;
        targetX = Math.max(targetX, 0);
        targetX = Math.min(targetX, boundedWith - width);
    }

    public void moveLeft(double delta, int boundedWith) {
        targetXCenter -= settings.playerMoveVelocity * delta;
        targetX = targetXCenter - width / 2;
        targetX = Math.max(targetX, 0);
        targetX = Math.min(targetX, boundedWith - width);
    }

    public void moveRight(double delta, int boundedWith) {
        targetXCenter += settings.playerMoveVelocity * delta;
        targetX = targetXCenter - width / 2;
        targetX = Math.max(targetX, 0);
        targetX = Math.min(targetX, boundedWith - width);
    }

    public void wall(int boundedHeight) {
        if (!walling) {
            walling = true;
            targetWidth = initialWidth * 6;
            targetXCenter = x + width / 2;
            targetY = initialY + 40;
            targetYCenter = y + height / 2;
            walled = true;
            deployed = false;
        }
    }

    public void enlarge() {
        if (!enlarging && !walling) {
            enlarging = true;
            targetWidth = initialWidth * 2;
            targetXCenter = x + width / 2;
        }

        enlargeTimer = System.nanoTime();
    }

    /**
     * Uses predefined scores to add score to the player
     *
     * @param score The score to add
     *
     * @see Player#addScore(int) method overloaded
     */
    public void addScore(BrickScore score) {
        this.score += score.getScore();
    }

    @Override
    public void heal() {
        super.heal();
    }

    public boolean deployed() {
        return deployed;
    }

    public boolean walled() {
        return walled;
    }

    public boolean isEnlarging() {
        return enlarging;
    }

    /**
     * Triggers the knockback effect when projectile hits it.
     */
    public void triggerKnockback() {
        if (knockback instanceof KnockbackShake) {
            knockback = new Knockback();
        }
        knockback.trigger();
    }

    /**
     * Triggers the knockback with shake vibration effect when projectile hits it.
     *
     * @param magnitude
     */
    public void triggerKnockbackShake(ShakeMagnitude magnitude) {
        if (!(knockback instanceof KnockbackShake)) {
            knockback = new KnockbackShake();
        }
        ((KnockbackShake) knockback).trigger(magnitude);
    }

    public boolean isKnockbackActive() {
        return knockback.active;
    }

    /**
     * The knockback effect when projectile hits it.
     */
    private class Knockback {
        protected boolean active = false;
        protected final double initialIntensity = settings.playerKnockbackIntensity;
        protected double intensity = initialIntensity;
        protected final double duration = 0.4; // in seconds
        protected double elapsedTime = 0;
        protected double initialY;

        public Knockback() {
            initialY = Paddle.this.y; // Capture the initial Y position of the paddle
        }

        public void update(double delta) {
            if (active) {
                elapsedTime += delta;
                if (elapsedTime >= duration) {
                    active = false;
                    Paddle.this.y = initialY; // Reset to the original Y position
                } else {
                    // Calculate a smooth transition back to the initial position
                    double factor = elapsedTime / duration;
                    Paddle.this.y = initialY + intensity * (1 - factor);
                }
            }
        }

        public void trigger() {
            if (!active) {
                active = true;
                elapsedTime = 0;
                intensity = initialIntensity;
            }
        }
    }

    /**
     * The knockback with shake vibration effect when projectile hits it
     */
    private class KnockbackShake extends Knockback {
        private double initialX;
        private double vibrationOffsetX = 0, vibrationOffsetY = 0;

        // Attributes for the vibration effect
        private double vibrationMagnitude;
        private double vibrationFrequency = 2.0;
        private int vibrationCount = 0;
        private final int maxVibrationCount = 10;

        public KnockbackShake() {
            initialX = Paddle.this.x;
            initialY = Paddle.this.y;
        }

        public void update(double delta) {
            if (active) {
                elapsedTime += delta;
                if (elapsedTime >= duration) {
                    active = false;
                    vibrationOffsetX = 0;
                    vibrationOffsetY = 0;
                    vibrationCount = 0; // Reset the vibration counter
                } else {
                    if (vibrationCount < maxVibrationCount) {
                        vibrationOffsetX = Math.sin(vibrationFrequency * vibrationCount) * vibrationMagnitude;
                        vibrationOffsetY = Math.sin(vibrationFrequency * vibrationCount * 2) * vibrationMagnitude;
                        vibrationCount++;
                    }
                }
            }
        }

        public void trigger(ShakeMagnitude magnitude) {
            this.vibrationMagnitude = magnitude.value();

            if (!active) {
                active = true;
                elapsedTime = 0;
                intensity = initialIntensity;

                // Reset the initial positions
                initialX = Paddle.this.x;
                initialY = Paddle.this.y;
            }
        }
    }
}
