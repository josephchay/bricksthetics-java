package breakout.core.database;

import breakout.core.contracts.DefaultDependenciesInjectable;

import java.io.*;

/**
 * Provides connection access to the database.
 * Connects to the database schema.
 */
public class Connector implements DefaultDependenciesInjectable {
    private static Connector instance = null;

    public static synchronized Connector getInstance() {
        if (instance == null) {
            instance = new Connector();
        }

        return instance;
    }

    private Connector() {}

    public SchemaBuilder connect() {
        return SchemaBuilder.getInstance(this::createInputStream, this::createOutputStream);
    }

    /**
     * Retrieve the input stream for the table file.
     *
     * @return The input stream.
     */
    private ObjectInputStream createInputStream(String tablePath) {
        try {
            return new ObjectInputStream(new FileInputStream(tablePath));
        } catch (IOException e) {
            syslog.warning(e.getMessage());
        }

        return null;
    }

    /**
     * Retrieve the output stream for the table file.
     *
     * @return The output stream.
     */
    private ObjectOutputStream createOutputStream(String tablePath) {
        try {
            return new ObjectOutputStream(new FileOutputStream(tablePath));
        } catch (IOException e) {
            syslog.warning(e.getMessage());
        }

        return null;
    }
}
