package breakout.entities.players;

import breakout.entities.players.cannons.Cannon;
import breakout.entities.players.paddles.Paddle;
import javafx.scene.paint.Color;

public class PlayerFactory implements breakout.core.entities.actors.PlayerFactory {
    public Paddle createPaddle(double x, double y, int width, int height, Color color) {
        return new Paddle(x, y, width, height, color);
    }

    public Cannon createCannon(double y, int width, int height, Color color) {
        return new Cannon(y, width, height, color);
    }
}
