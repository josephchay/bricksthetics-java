package breakout.entities.players.cannons;

import breakout.container.DependenciesInjectable;
import breakout.core.entities.actors.Player;
import breakout.core.entities.projectiles.GuidedProjectile;
import breakout.core.utils.Helper;
import breakout.entities.players.paddles.Paddle;
import breakout.entities.projectiles.orbs.Orb;
import breakout.entities.projectiles.orbs.OrbCollection;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Random;

public class Cannon extends Player implements DependenciesInjectable {
    private int arc = settings.cannonArc;

    /** The number of orbs the cannon can shoot at one session. */
    private int ammo = 0;

    /** Maximum angle the orb can be launched at. */
    protected double maxAngle = 75;

    private OrbCollection orbs = new OrbCollection();

    /** Whether the orb has been launched. */
    protected boolean fired = false;

    private boolean deployed = false;

    private static final Random random = new Random();

    public Cannon(double x, double y, int width, int height, Color color) {
        super(x, y, width, height);

        this.color = color;
    }

    /**
     * Creates a cannon with a random x position bounded within the screen.
     *
     * @param y The y position of the cannon.
     * @param width The width of the cannon.
     * @param height The height of the cannon.
     * @param color The color of the cannon.
     */
    public Cannon(double y, int width, int height, Color color) {
        this(0, y, width, height, color);
    }

    public void deploy() {
        deployed = true;
    }

    public void retract() {
        if (fired) {
            fired = false;
        }

        deployed = false;
    }

    public void reload(OrbCollection orbs) {
        this.orbs = orbs;
        ammo = orbs.size();
    }

    public void addAmmo(Orb orb) {
        orbs.add(orb);
        ammo++;
    }

    public void aim(double x, double y) {
        orbs.each(orb -> orb.guide(x, y));
    }

    public void fire(Orb projectile) {
        projectile.setAngle(Math.atan2(projectile.getGuideY() - (y + projectile.getHeight() / 2.0), projectile.getGuideX() - (x + projectile.getWidth() / 2.0)));
        projectile.mobilize();
        ammo--;

        fired = true;
    }

    public void update(double delta) {

    }

    @Override
    public void draw(GraphicsContext graphics) {
        double drawX = x;
        double drawY = y;

        graphics.setFill(color);
        graphics.fillRoundRect(drawX, drawY, width, height, arc, arc);
        graphics.setStroke(color.darker());
        graphics.setLineWidth(4);
        graphics.strokeRoundRect(x, y, width, height, arc, arc);

        if (!fired) {
            drawAimLine(graphics, settings.appWidth, settings.appHeight);
        }
    }

    /**
     * Draws a dashed line from the center of the orb to the boundary of the screen or a collidable object.
     *
     * @param graphics The graphics context to draw on.
     * @param boundedWidth The width of the boundary.
     * @param boundedHeight The height of the boundary.
     */
    protected void drawAimLine(GraphicsContext graphics, int boundedWidth, int boundedHeight) {
        Orb projectile = orbs.get(orbs.size() - 1);

        double projectileCenterX = projectile.getX() + projectile.getWidth() / 2.0;
        double projectileCenterY = projectile.getY() + projectile.getHeight() / 2.0;

        // Calculate the direction of the aim
        double dirX = projectile.getGuideX() - projectileCenterX;
        double dirY = projectile.getGuideY() - projectileCenterY;

        // Normalize the direction vector
        double magnitude = Math.sqrt(dirX * dirX + dirY * dirY);
        dirX /= magnitude;
        dirY /= magnitude;

        // Offset to skip the first dashed segment
        double offset = 10; // Skip one dash length
        double startX = projectileCenterX + dirX * offset;
        double startY = projectileCenterY + dirY * offset;

        // Extend the line by a fixed large amount to ensure it goes off the screen
        double extension = Math.max(boundedWidth, boundedHeight);
        double endX = startX + dirX * extension;
        double endY = startY + dirY * extension;

        // Set the line properties
        graphics.setLineWidth(2);
        graphics.setLineDashes(10);
        graphics.setStroke(Color.rgb(200, 200, 200, 0.8));

        // Draw the aim line starting from the offset position
        graphics.strokeLine(startX, startY, endX, endY);

        // Reset
        graphics.setLineWidth(1);
        graphics.setLineDashes(null);
        graphics.setStroke(Color.BLACK);
    }

    public void randomizePosition(int boundedWith) {
        x = random.nextInt((int) (boundedWith - width)) + width / 2;
    }

    @Override
    public Shape hitbox() {
        return null;
    }

    public int getAmmo() {
        return ammo;
    }

    public boolean hasAmmo() {
        return ammo > 0;
    }

    public boolean deployed() {
        return deployed;
    }
}
