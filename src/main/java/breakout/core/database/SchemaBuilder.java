package breakout.core.database;

import breakout.core.contracts.DefaultDependenciesInjectable;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.function.Function;

public class SchemaBuilder implements DefaultDependenciesInjectable {
    private static SchemaBuilder instance;

    private final Function<String, ObjectInputStream> inputStreamFunction;
    private final Function<String, ObjectOutputStream> outputStreamFunction;

    public static synchronized SchemaBuilder getInstance(Function<String, ObjectInputStream> inputStreamFunction, Function<String, ObjectOutputStream> outputStreamFunction) {
        if (instance == null) {
            instance = new SchemaBuilder(inputStreamFunction, outputStreamFunction);
        }

        return instance;
    }

    private SchemaBuilder(Function<String, ObjectInputStream> inputStreamFunction, Function<String, ObjectOutputStream> outputStreamFunction) {
        this.inputStreamFunction = inputStreamFunction;
        this.outputStreamFunction = outputStreamFunction;
    }

    /**
     * @see SchemaBuilder#createDatabase(String)
     */
    public SchemaBuilder createDatabaseIfNotExist(String name) {
        File directory = new File(name);
        if (!directory.exists()) {
            return createDatabase(name);
        }

        return this;
    }

    /**
     * NOTE: The current database is in the local filesystem.
     *
     * @param name The database name (from the project root).
     * @return True if the database is created, false otherwise.
     */
    public SchemaBuilder createDatabase(String name) {
        File directory = new File(name);

        if (directory.mkdirs()) {
            env.setDbPath(name);
        }

        return this;
    }

    public SchemaBuilder createTableIfNotExist(String name) {
        String path = env.getDbPath() + "/" + name + ".ser";
        File file = new File(path);

        if (!file.exists()) {
            return createTable(name);
        }

        return this;
    }

    /**
     * NOTE: The current table is stored in the local filesystem.
     *
     * @param name The table name.
     * @return True if the table is created, false otherwise.
     */
    public SchemaBuilder createTable(String name) {
        String path = env.getDbPath() + "/" + name + ".ser";
        File file = new File(path);

        try {
            if (file.createNewFile()) {
                ObjectOutputStream in = outputStreamFunction.apply(path);
                if (getColumnData(name) != null) {
                    in.writeUTF(getColumnData(name));
                }

                env.addTablePath(name, path);
                return this;
            }
        } catch (IOException e) {
            syslog.warning(e.getMessage());
        }

        return this;
    }

    /**
     * Retrieve the column names (if needed for the table).
     *
     * @param table The table name.
     * @return The column names.
     */
    private String getColumnData(String table) {
        return switch (table) {
            case "progresses" -> null; // no row-like structure columns for this table.
            default -> null;
        };
    }

    public QueryBuilder statement() {
        return new QueryBuilder("database/", inputStreamFunction, outputStreamFunction);
    }
}
