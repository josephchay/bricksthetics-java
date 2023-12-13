package breakout.core.database;

import breakout.core.skeletons.Accessor;

/**
 * Proxy acting as a access gateway to the database connection.
 */
public class DB extends Accessor {
    @Override
    public Connector access() {
        return (Connector) container.get("database");
    }
}
