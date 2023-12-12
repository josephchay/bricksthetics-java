package breakout.core.container;

import breakout.core.contracts.HashMappable;
import breakout.core.contracts.Providable;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class Container implements Providable, HashMappable {
    private static Container instance;

    private Map<String, Map<String, Object>> bindings = new HashMap<>();

    private Container() {

    }

    /**
     * Singleton pattern.
     *
     * @return The instance of the container.
     */
    public static synchronized Container getInstance() {
        if (instance == null) {
            instance = new Container();
        }

        return instance;
    }

    @Override
    public void registerRegular(String name, Class<?> implementation) {
        Map<String, Object> binding = new HashMap<>();
        binding.put("shared", false);
        binding.put("class", implementation);

        if (!bindings.containsKey(name)) {
            bindings.put(name, binding);
            return;
        }

        bindings.replace(name, binding);
    }
//    /**
//     * Update the current binding key with the given class.
//     *
//     * @param name The ID.
//     * @param implementation The concrete implementation to be bound.
//     */
//    public void registerRegularWithRebind(String name, Class<?> implementation) {
//        Map<String, Object> binding = new HashMap<>();
//        binding.put("shared", false);
//        binding.put("class", implementation);
//
//        bindings.put(name, binding);

//    }

    @Override
    public void registerSingleton(String name, Object object) {
        Map<String, Object> shared = new HashMap<>();
        shared.put("shared", true);
        shared.put("object", object);

        if (!bindings.containsKey(name)) {
            bindings.put(name, shared);
            return;
        }

        bindings.replace(name, shared);
    }

    /**
     * Overload
     * Register a class implementation instead of an object.
     *
     * @param name The ID.
     * @param implementation The class implementation.
     */
    public void registerSingleton(String name, Class<?> implementation) {
        Map<String, Object> shared = new HashMap<>();
        shared.put("shared", true);
        shared.put("class", implementation);

        if (!bindings.containsKey(name)) {
            bindings.put(name, shared);
            return;
        }

        bindings.replace(name, shared);
    }

//    /**
//     * Similar to {@link #registerRegularWithRebind(String, Class)}
//     */
//    public void registerSingletonWithRebind(String name, Object object) {
//        Map<String, Object> shared = new HashMap<>();
//        shared.put("shared", true);
//        shared.put("object", object);
//
//        bindings.put(name, shared);
//    }
//
//    /**
//     * Similar to {@link #registerRegularWithRebind(String, Class)}
//     */
//    public void registerSingletonWithRebind(String name, Class<?> implementation) {
//        Map<String, Object> shared = new HashMap<>();
//        shared.put("shared", true);
//        shared.put("class", implementation);
//
//        bindings.put(name, shared);

//    }

    @Override
    public Object make(String name) {
        if (!bindings.containsKey(name)) {
//            resolve(name, null);
            return null;
        }

        Map<String, Object> concrete = bindings.get(name);

        if ((boolean) concrete.get("shared")) { // if singleton
            if (concrete.containsKey("object")) {
                return concrete.get("object");
            }

            registerSingleton(name, resolve(name, (Class<?>) concrete.get("class")));
        }

        return resolve(name, (Class<?>) concrete.get("class"));
    }

    /**
     * Instantiates the concrete class implementation.
     *
     * @param name The ID of the concrete.
     * @param concrete Class object that represents the class to be resolved.
     * @return The resolved service.
     */
    private <T> T resolve(String name, Class<T> concrete) {
        try {
            Constructor<?> constructor = concrete.getConstructor();

            return concrete.cast(constructor.newInstance());
        } catch (Exception e) {
            throw new RuntimeException("Error creating an instance of the implementation.", e);
        }
    }

    /**
     * Methods implemented from HashMappable are for syntactic sugar.
     */
    @Override
    public Object get(String name) {
        Object obj = make(name);
        Class<?> clazz = obj.getClass();

        return clazz.cast(obj);
    }

    @Override
    public void set(String name, Object value) {
        if (value != null) {
            registerSingleton(name, value);
        }
    }
    // Overload of the set method.

    public <T> void set(String name, Class<T> value) {
        if (value != null && Class.class.isAssignableFrom(value.getClass())) {
            Class<T> classValue = (Class<T>) value;
            registerRegular(name, classValue);
        }
    }

    @Override
    public int size() {
        return bindings.size();
    }

    @Override
    public void remove(String name) {
        bindings.remove(name);
    }
}
