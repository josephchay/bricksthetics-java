package breakout.core.contracts;

import breakout.core.database.QueryBuilder;

public interface Queryable {
    public QueryBuilder table(String table);

    public Object select();

    public boolean insert();

    public boolean update();

    public boolean delete();
}
