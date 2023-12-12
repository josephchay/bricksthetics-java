package breakout.core.contracts;

/**
 * Enable collection functionality for objects.
 *
 * @param <T>
 */
public interface HashMappable<T> {
    /**
     * Get an object from the collection.
     *
     * @param name The name of the object.
     * @return The object.
     */
    T get(String name);

    /**
     * Set an object in the collection.
     *
     * @param name The name of the object.
     * @param value The value of given type respectively.
     */
    void set(String name, T value);

    /**
     * Remove an object from the collection.
     *
     * @param name The name of the object.
     */
    void remove(String name);

    /**
     * Get the size of the collection.
     *
     * @return The size of the collection.
     */
    int size();
}
