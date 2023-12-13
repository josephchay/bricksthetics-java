package breakout.core.utils;

import breakout.core.contracts.DefaultDependenciesInjectable;
import breakout.core.contracts.HashMappable;

import java.util.HashMap;
import java.util.Map;

/**
 * A dictionary collection class that can be initialized with a HashMap.
 *
 * @param <T> The type of the elements in the collection.
 */
public class DictCollection<T> implements HashMappable<T>, DefaultDependenciesInjectable {
    protected final Map<String, T> items;

    /**
     * Constructs a new Collection instance using a HashMap.
     *
     * @param items The HashMap to initialize the collection with.
     */
    public DictCollection(HashMap<String, T> items) {
        this.items = items;
    }

    /**
     * Retrieves an item by its key.
     *
     * @param name The key of the item to retrieve.
     * @return The item associated with the specified key, or null if the key does not exist.
     */
    @Override
    public T get(String name) {
        return items.get(name);
    }

    /**
     * Adds or updates an item in the collection with the specified key.
     *
     * @param name  The key of the item to add or update.
     * @param value The item to be added or updated.
     */
    @Override
    public void set(String name, T value) {
        items.put(name, value);
    }

    /**
     * Removes an item from the collection by its key.
     *
     * @param name The key of the item to remove.
     */
    @Override
    public void remove(String name) {
        items.remove(name);
    }

    /**
     * Returns the size of the collection.
     *
     * @return The number of items in the collection.
     */
    @Override
    public int size() {
        return items.size();
    }

    /**
     * Returns the underlying collected items.
     *
     * @return The underlying HashMap.
     */
    public Map<String, T> getCollected() {
        return items;
    }
}
