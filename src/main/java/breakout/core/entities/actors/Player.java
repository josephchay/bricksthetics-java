package breakout.core.entities.actors;

import breakout.core.entities.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

abstract public class Player extends Entity {
    protected int score = 0;

    public Player(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public Player(double x, double y, double width, double height, double velocity) {
        super(x, y, width, height, velocity);
    }

    public Player(double x, double y, double width, double height, double velocity, Color color) {
        super(x, y, width, height, velocity, color);
    }

    public Player(double x, double y, double width, double height, double velocity, int health, Color color) {
        super(x, y, width, height, velocity, velocity, health, color);
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void minusScore(int score) {
        this.score -= score;
        if (score <= 0) {
            this.score = 0;
        }
    }

    public void resetScore() {
        score = 0;
    }

    public int getScore() {
        return score;
    }
}
