package breakout.screens;

import breakout.container.DependenciesInjectable;
import breakout.core.screens.views.View;
import breakout.screens.tags.ShakeMagnitude;

public class Shaker implements DependenciesInjectable {
    private boolean shake = false;
    private double magnitude = 0.0;

    /** Determines how quickly the shake fades out */
    private double decay = 0.9;

    /** Determines how quickly the shake oscillates */
    private double frequency = 2.0;
    private int shakeCount = 0;

    private final View view;

    public Shaker(View view) {
        this.view = view;
    }

    public void trigger(ShakeMagnitude magnitude) {
        shake = true;
        this.magnitude = magnitude.value();
    }

    public void update() {
        if (shake) {
            // Calculate the next shake magnitude
            magnitude *= decay;
            double xShake = Math.sin(frequency * shakeCount) * magnitude;
            double yShake = Math.sin(frequency * shakeCount * 2) * magnitude;

            // Apply the shake to the canvas
            view.getCanvas().setTranslateX(xShake);
            view.getCanvas().setTranslateY(yShake);

            shakeCount++;

            // Stop the shake if the magnitude is below a certain threshold
            if (magnitude < 0.1) {
                shake = false;
                magnitude = 0;
                shakeCount = 0;
            }
        } else {
            // Smoothly reset the canvas position
            double resetSpeed = 0.1; // Adjust as needed for smoothness
            double translateX = view.getCanvas().getTranslateX();
            double translateY = view.getCanvas().getTranslateY();

            if (Math.abs(translateX) > resetSpeed || Math.abs(translateY) > resetSpeed) {
                view.getCanvas().setTranslateX(translateX * (1 - resetSpeed));
                view.getCanvas().setTranslateY(translateY * (1 - resetSpeed));
            } else {
                view.getCanvas().setTranslateX(0);
                view.getCanvas().setTranslateY(0);
            }
        }
    }
}
