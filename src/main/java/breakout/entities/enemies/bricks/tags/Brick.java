package breakout.entities.enemies.bricks.tags;

public enum Brick {
    STANDARD(1),
    MULTI_ORB(8),
    LARGE_PADDLE(8),
    LIFE(14);

    private final int rarity;

    Brick(int rarity) {
        this.rarity = rarity;
    }

    public int rarity() {
        return rarity;
    }
}
