package breakout.core.contracts;

import breakout.core.database.QueryBuilder;

/**
 * Marks a class as queryable to the database
 */
public interface Queryable {
    QueryBuilder table(String table);

    Object select();

    boolean insert();

    boolean update();

    boolean delete();
}
