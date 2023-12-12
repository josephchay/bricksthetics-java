package breakout.entities.enemies.bricks.collectibles.drops;

public class CollectibleFactory implements breakout.core.entities.props.CollectibleFactory {
    public Collectible createBomb(double x, double y) {
        return new LifeCollectible(x, y);
    }

    public Collectible createLargePaddle(double x, double y) {
        return new LargePaddleCollectible(x, y);
    }

    public Collectible createMultiball(double x, double y) {
        return new MultiorbCollectible(x, y);
    }
}
