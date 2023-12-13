package breakout.entities.enemies.bricks.tags;

public enum BrickType {
    STANDARD(1),
    MULTIORB(8),
    LARGE_PADDLE(8),
    LIFE(14);

    private final int rarity;

    BrickType(int rarity) {
        this.rarity = rarity;
    }

    public int rarity() {
        return rarity;
    }
}
