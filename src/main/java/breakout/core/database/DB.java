package breakout.core.database;

import breakout.core.skeletons.Accessor;

public class DB extends Accessor {
    @Override
    public Connector access() {
        return (Connector) container.get("database");
    }
}
