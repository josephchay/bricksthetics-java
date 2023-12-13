package breakout.core.imaging;

import breakout.core.contracts.DefaultDependenciesInjectable;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Involves the handling of all images in the application.
 */
public class ImagerySystem implements DefaultDependenciesInjectable {
    private static ImagerySystem instance;

    public static synchronized ImagerySystem getInstance() {
        if (instance == null) {
            instance = new ImagerySystem();
        }

        return instance;
    }

    private ImagerySystem() {}

    /**
     * Image the loaded image in the environment from resource.
     *
     * @param name The name of the image.
     * @return The image.
     */
    public Image image(String name) {
        return new Image(env.getResourceImage(name));
    }

    /**
     * Image the loaded image with blur in the environment from resource.
     *
     * @param name The name of the image.
     * @param blur The blur radius.
     * @return The image.
     */
    public Image imageBlur(String name, int blur) {
        Image image = image(name);
        ImageView view = new ImageView(image);

        GaussianBlur gaussian = new GaussianBlur(blur);
        view.setEffect(gaussian);

        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        // Take a snapshot of the ImageView with the effect applied
        return view.snapshot(params, null);
    }

    /**
     * Image the loaded image with brightness in the environment from resource.
     * Alternatively, can be used to darken an image with a negative brightness.
     *
     * @param name The name of the image.
     * @param blur The blur radius.
     * @param brightness The brightness.
     * @return The image.
     */
    public Image imageBlurBrighten(String name, int blur, double brightness) {
        Image image = image(name);
        ImageView view = new ImageView(image);

        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(brightness);
        GaussianBlur gaussian = new GaussianBlur(blur);

        colorAdjust.setInput(gaussian); // Chain the effects
        view.setEffect(colorAdjust);

        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        // Take a snapshot of the ImageView with the effect applied
        return view.snapshot(params, null);
    }
}
