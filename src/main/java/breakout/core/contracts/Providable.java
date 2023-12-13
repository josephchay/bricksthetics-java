package breakout.core.contracts;

public interface Providable {
    /**
     * Registers a regular class representation binding.
     *
     * @param name The abstract ID
     * @param implementation The class to be created upon each request.
     */
    void registerRegular(String name, Class<?> implementation);

    /**
     * Registers a singleton object.
     *
     * @param name The abstract ID.
     * @param object The instantiated singleton object.
     */
    void registerSingleton(String name, Object object);

    /**
     * Make or resolve the object if present.
     *
     * @param name The abstract ID
     * @return The instantiated service.
     */
    Object make(String name);
}
