package breakout.screens.tags;

public enum ShakeMagnitude {
    NONE(0.0),
    LIGHT(6.0),
    MEDIUM(10.0),
    HEAVY(14.0),
    EXTREME(20.0),
    INSANE(40.0);

    private final double magnitude;

    ShakeMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public double value() {
        return magnitude;
    }
}
