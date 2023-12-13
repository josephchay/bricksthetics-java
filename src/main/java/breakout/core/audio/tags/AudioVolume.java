package breakout.core.audio.tags;

public enum AudioVolume {
    MUTE(0.0),
    LOW(0.25),
    MEDIUM(0.5),
    HIGH(0.75),
    MAX(1.0);

    private final double value;

    AudioVolume(double value) {
        this.value = value;
    }

    public double value() {
        return value;
    }
}
