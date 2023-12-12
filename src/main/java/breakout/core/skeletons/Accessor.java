package breakout.core.skeletons;

import breakout.core.contracts.DefaultDependenciesInjectable;

abstract public class Accessor implements DefaultDependenciesInjectable {
    abstract public Object access();
}
