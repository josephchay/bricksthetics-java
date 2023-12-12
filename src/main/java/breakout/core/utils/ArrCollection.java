package breakout.core.utils;

import breakout.core.contracts.DefaultDependenciesInjectable;

import java.util.Arrays;

public class ArrCollection<T> implements DefaultDependenciesInjectable {
    private Object[] items;
    private int[] dimensions;
    private int totalSize;

    /**
     * Constructs an initially 1-dimensional collection with a default capacity.
     */
    public ArrCollection() {
        // Start with a 1D array with an initial capacity
        this.dimensions = new int[]{1};
        this.totalSize = 1;
        this.items = new Object[totalSize];
    }

    /**
     * Constructs a collection with the provided initial elements. The collection will be 1-dimensional,
     * with a size equal to the number of provided elements.
     *
     * @param items The initial elements to be added to the collection.
     */
    @SafeVarargs
    public ArrCollection(T... items) {
        this.dimensions = new int[]{items.length};
        this.totalSize = items.length;
        this.items = Arrays.copyOf(items, totalSize);
    }

    @SuppressWarnings("unchecked")
    public T get(int... indices) {
        int flatIndex = calculateFlatIndex(indices);
        if (flatIndex >= totalSize) {
            throw new IndexOutOfBoundsException();
        }
        return (T) items[flatIndex];
    }

    public void set(T value, int... indices) {
        ensureCapacity(indices);
        int flatIndex = calculateFlatIndex(indices);
        items[flatIndex] = value;
    }

    private void ensureCapacity(int[] indices) {
        if (indices.length > dimensions.length) {
            // Increase the number of dimensions
            dimensions = Arrays.copyOf(dimensions, indices.length);
            for (int i = dimensions.length - 1; i >= 0; i--) {
                dimensions[i] = (i < dimensions.length - 1) ? dimensions[i] : 1;
            }
        }

        for (int i = 0; i < indices.length; i++) {
            if (indices[i] >= dimensions[i]) {
                dimensions[i] = indices[i] + 1;
            }
        }

        int newTotalSize = Arrays.stream(dimensions).reduce(1, (a, b) -> a * b);
        if (newTotalSize > totalSize) {
            items = Arrays.copyOf(items, newTotalSize);
            totalSize = newTotalSize;
        }
    }

    private int calculateFlatIndex(int[] indices) {
        int flatIndex = 0;
        int accumulatedMultiplier = 1;
        for (int i = dimensions.length - 1; i >= 0; i--) {
            int index = (i < indices.length) ? indices[i] : 0;
            flatIndex += index * accumulatedMultiplier;
            accumulatedMultiplier *= dimensions[i];
        }
        return flatIndex;
    }
}
