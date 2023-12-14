package breakout.entities.enemies.bricks.collectibles.drops;

public class CollectibleFactory implements breakout.core.entities.props.CollectibleFactory {
    public Collectible createLargePaddle(double x, double y) {
        return new LargePaddleCollectible(x, y);
    }

    public Collectible createLife(double x, double y) {
        return new LifeCollectible(x, y);
    }

    public Collectible createMultiorb(double x, double y) {
        return new MultiorbCollectible(x, y);
    }
}
