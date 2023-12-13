package breakout.core.entities;

import breakout.core.contracts.Collidable;
import breakout.core.contracts.DefaultDependenciesInjectable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

abstract public class Entity implements Collidable, DefaultDependenciesInjectable {
    protected double x, y, targetX, targetY, targetXCenter, targetYCenter;
    protected double width, height, targetWidth, targetHeight;

    /** The change in position */
    protected double dx, dy = 0;

    /** Velocity */
    protected double vx, vy;

    /** Future destination of the entity */
    protected double xDest, yDest;

    protected Color color;

    protected int health;
    protected boolean alive = true;

    // Movement direction
    public boolean movingUp = false;
    public boolean movingDown = false;
    public boolean movingLeft = false;
    public boolean movingRight = false;

    // Collision detection
    public boolean topHit = false;
    public boolean topLeftHit = false;
    public boolean topRightHit = false;
    public boolean bottomHit = false;
    public boolean bottomLeftHit = false;
    public boolean bottomRightHit = false;
    public boolean leftHit = false;
    public boolean rightHit = false;

    public Entity(double x, double y, double width, double height) {
        this(x, y, width, height, 0, null);
    }

    /**
     * Constructor
     *
     * @param x The initial x position
     * @param y The initial y position
     * @param width The initial width
     * @param height The initial height
     */
    public Entity(double x, double y, double width, double height, double velocity) {
        this(x, y, width, height, velocity, velocity, null);
    }

    public Entity(double x, double y, double width, double height, double vx, double vy) {
        this(x, y, width, height, vx, vy, null);
    }

    public Entity(double x, double y, double width, double height, double velocity, int health) {
        this(x, y, width, height, velocity, health, null);
    }

    public Entity(double x, double y, double width, double height, double vx, double vy, int health) {
        this(x, y, width, height, vx, vy, health, null);
    }

    public Entity(double x, double y, double width, double height, int health) {
        this(x, y, width, height, 0, 0, health, null);
    }

    public Entity(double x, double y, double width, double height, int health, Color color) {
        this(x, y, width, height, 0, 0, health, color);
    }

    public Entity(double x, double y, double width, double height, double velocity, int health, Color color) {
        this(x, y, width, height, velocity, velocity, color);

        this.health = health;
    }

    public Entity(double x, double y, double width, double height, double vx, double vy, int health, Color color) {
        this(x, y, width, height, vx, vy, color);

        this.health = health;
    }

    public Entity(double x, double y, double width, double height, double velocity, Color color) {
        this(x, y, width, height, velocity, velocity, color);
    }

    /**
     * Constructor with color
     *
     * @param x The initial x position
     * @param y The initial y position
     * @param width The initial width
     * @param height The initial height
     * @param color The color
     */
    public Entity(double x, double y, double width, double height, double vx, double vy, Color color) {
        this.x = x;
        this.y = y;
        this.targetX = x;
        this.targetY = y;
        this.width = width;
        this.height = height;
        this.color = color;

        this.vx = vx;
        this.vy = vy;

        xDest = x;
        yDest = y;
    }

    /**
     * Update the entity
     */
//    public void update() {
//        updateDirection();
//        move();
//        checkBoundaries();
//    }

    /**
     * Update the movement directions of the entity based on the current direction vector
     */
    public void update(double delta, int boundedWidth, int boundedHeight) {
        updateDx(delta);
        updateDy(delta);
        updateX(boundedWidth);
        updateY(boundedHeight);
    }

    protected void updateDx(double delta) {
        dx = 0;

        if (movingLeft) {
            dx -= vx * delta;
        }
        if (movingRight) {
            dx += vx * delta;
        }
    }

    protected void updateDy(double delta) {
        dy = 0;

        if (movingUp) {
            dy -= vx * delta;
        }
        if (movingDown) {
            dy += vx * delta;
        }
    }

    protected void updateX(int boundedWith) {
        x += dx;

        ensureXInBoundary(boundedWith);
    }

    protected void updateY(int boundedHeight) {
        y += dy;

        ensureYInBoundary(boundedHeight);
    }

    /**
     * Keep the entity within the given width boundary.
     *
     * @param width The width boundary.
     */
    protected void ensureXInBoundary(int width) {
        if (x < 0) {
            swapHorizontalDirection();
            x = 0;
        } else if (x + this.width > width) {
            swapHorizontalDirection();
            x = width - this.width;
        }
    }

    protected void swapHorizontalDirection() {
        if (movingLeft) {
            movingLeft = false;
            movingRight = true;
        } else {
            movingLeft = true;
            movingRight = false;
        }
    }

    /**
     * Keep the entity within the given height boundary.
     *
     * @param height The height boundary.
     */
    protected void ensureYInBoundary(int height) {
        if (y < 0) {
            swapVerticalDirection();
            y = 0;
        } else if (y + this.height > height) {
            swapVerticalDirection();
            y = height - this.height;
        }
    }

    protected void swapVerticalDirection() {
        if (movingUp) {
            movingUp = false;
            movingDown = true;
        } else {
            movingUp = true;
            movingDown = false;
        }
    }

    /**
     * Blit the entity to the screen
     *
     * @param graphics The graphics context
     */
    abstract public void draw(GraphicsContext graphics);

    /**
     * The Shape node which acts as the bounding box of the entity.
     *
     * @return The specified Shape node bounds
     */
    abstract public Shape hitbox();

    /**
     * Increase the health count of the entity
     */
    public void heal() {
        health++;
    }

    /**
     * Reduce the health count of the entity
     */
    public void damage() {
        health--;
        if (health <= 0) {
            die();
        }
    }

    public void respawn() {
        alive = true;
    }

    public void die() {
        alive = false;
    }

    public boolean dead() {
        return !alive;
    }

    /**
     * Reset the Entity to stationary
     */
    public void immobilize() {
        movingUp = false;
        movingDown = false;
        movingLeft = false;
        movingRight = false;
    }

    public void nullifyHits() {
        topHit = false;
        bottomHit = false;
        leftHit = false;
        rightHit = false;
        nullifyCornerHits();
    }

    public void nullifyCornerHits() {
        topLeftHit = false;
        topRightHit = false;
        bottomLeftHit = false;
        bottomRightHit = false;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVX(double velocity) {
        this.vx = velocity;
    }

    public void setVY(double velocity) {
        this.vy = velocity;
    }

    public void setV(double velocity) {
        this.vx = velocity;
        this.vy = velocity;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
