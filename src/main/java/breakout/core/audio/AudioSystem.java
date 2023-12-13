package breakout.core.audio;

import breakout.core.audio.tags.AudioVolume;
import breakout.core.contracts.DefaultDependenciesInjectable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages and handles all audio medias and players.
 */
public class AudioSystem implements DefaultDependenciesInjectable {
    private static AudioSystem instance;

    /** Store the media players for each respective media. */
    protected Map<String, MediaPlayer> players;

    /** Store the media for each respective sfx audio file. */
    protected Map<String, Media> medias;

    protected static boolean ran = false;

    public static synchronized AudioSystem getInstance() {
        if (instance == null) {
            instance = new AudioSystem();
        }

        return instance;
    }

    public AudioSystem() {
        players = new HashMap<>();
        medias = new HashMap<>();
    }

    /**
     * Prepares the audio system for the given audio file.
     *
     * @param name The name of the audio file.
     */
    public void prepare(String name) {
        Media media = medias.get(name);
        if (media == null) {
            media = new Media(env.getResourceSFX(name));
            medias.put(name, media);
        }

        MediaPlayer player = players.get(name);
        if (player == null) {
            player = new MediaPlayer(media);
            players.put(name, player);
        } else {
            player.stop();
        }
    }

    /**
     * Warm up the audio system to prevent future lags or delays
     */
    public void warmup() {
        MediaPlayer player = players.get("warmup");
        player.setVolume(0); // Mute
        player.play();
        player.setVolume(1); // Reset volume
    }

    /**
     * Play the audtio file of the given name.
     * .
     * @param name The name of the audio file.
     */
    public void play(String name) {
        MediaPlayer player = getPlayer(name);

        player.play();
    }

    /**
     * Play the audio file of the given name in a loop.
     *
     * @param name The name of the audio file.
     */
    public void playLoop(String name) {
        MediaPlayer player = getPlayer(name);

        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();
    }

    /**
     * Play the audio file of the given name with the given volume.
     *
     * @param name The name of the audio file.
     * @param volume The volume of the audio file.
     */
    public void playWithVolume(String name, AudioVolume volume) {
        MediaPlayer player = getPlayer(name);

        player.setVolume(volume.value());
        player.play();
    }

    /**
     * Retrieve the media player.
     * Ensure that the player retrieve is afresh by stopping it.
     *
     * @param name The name of the audio file.
     * @return The media player.
     */
    public MediaPlayer getPlayer(String name) {
        MediaPlayer player = players.get(name);
        player.stop();

        return player;
    }
}
