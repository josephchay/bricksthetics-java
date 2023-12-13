package breakout.core.contracts;

/**
 * Ensure that the class is routable to a specific Controller.
 */
public interface Routable {
    /**
     * Dispatch to a specific Controller.
     */
    void dispatch();
}
