package breakout.core.skeletons;

import breakout.core.contracts.DefaultDependenciesInjectable;

/**
 * Provides a gateway to access another class in the form of proxy.
 */
abstract public class Accessor implements DefaultDependenciesInjectable {
    abstract public Object access();
}
