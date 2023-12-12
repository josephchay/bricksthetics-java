package breakout.core.database;

import breakout.core.contracts.DefaultDependenciesInjectable;
import breakout.core.contracts.Queryable;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Refactored mainly from the LoadSave class.
 * @see <a href="https://github.com/kooitt/CourseworkGame/tree/master/src/main/java/brickGame">Original LoadSave Class</a>
 *
 * This class builds the query making respective files in the local acting as a database.
 * This class is mainly tailored to database migration in the future, whereby only small changes are required.
 */
public class QueryBuilder implements DefaultDependenciesInjectable, Queryable {
    private String dbPath;

    private final Function<String, ObjectInputStream> inputStreamFunction;
    private final Function<String, ObjectOutputStream> outputStreamFunction;

    public QueryBuilder(Function<String, ObjectInputStream> inputStreamFunction, Function<String, ObjectOutputStream> outputStreamFunction) {
        this.inputStreamFunction = inputStreamFunction;
        this.outputStreamFunction = outputStreamFunction;
    }

    public QueryBuilder(String dbPath, Function<String, ObjectInputStream> inputStreamFunction, Function<String, ObjectOutputStream> outputStreamFunction) {
        this.dbPath = dbPath;
        this.inputStreamFunction = inputStreamFunction;
        this.outputStreamFunction = outputStreamFunction;
    }

    private String tablePath;

    /**
     * This method should always be called first before any query statements.
     *
     * @param table The table to be queried.
     * @return The query builder instance.
     */
    @Override
    public QueryBuilder table(String table) {
        this.tablePath = "/" + table + ".ser";

        return this;
    }

    @Override
    public Object select() {
        switch (this.tablePath) {
            case "/levels.ser":
                return null;
            case "/progresses.ser":
                return selectAllSavedState();
            default:
                return null;
        }
    }

    /**
     * Retrieve all the data from the `progresses` db file.
     *
     * @return The state data.
     */
    public Map<String, Object> selectAllSavedState() {
        if (isTableEmpty(2)) {
            return null;
        }

        try (ObjectInputStream in = inputStreamFunction.apply(tablePath)) {
            Map<String, Object> state = new HashMap<>();

            state.put("self", in.readObject());
            state.put("bricks", in.readObject());
            state.put("player", in.readObject());
            state.put("leveler", in.readObject());
            state.put("projectile", in.readObject());

            return state;
        } catch (IOException | ClassNotFoundException e) {
            syslog.warning(e.getMessage());
        }

        return null;
    }

    @Override
    public boolean insert() {
        return false;
    }

    public boolean insertAllSavedState(Map<String, Object> state) {
        try (ObjectOutputStream out = outputStreamFunction.apply(tablePath)) {
            out.writeObject(state.get("self"));
            out.writeObject(state.get("bricks"));
            out.writeObject(state.get("player"));
            out.writeObject(state.get("leveler"));
            out.writeObject(state.get("projectile"));

            return true;
        } catch (IOException e) {
            syslog.warning(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    /**
     * NOTE: current functionality is similar to insert due to the "overwriting" feature of files.
     *
     * @param state The state to be updated with.
     * @return True if the state is updated, false otherwise.
     */
    public boolean updateAllSavedState(Map<String, Object> state) {
        if (isTableEmpty(2)) {
            return false;
        }

        try (ObjectOutputStream out = outputStreamFunction.apply(tablePath)) {
            Map<String, Object> oldState = selectAllSavedState();
            Map<String, Object> newState = new HashMap<>();

            // Update data if not equivalent
            newState.put("self", getLatestRowData(oldState.get("self"), state.get("self")));
            newState.put("bricks", getLatestRowData(oldState.get("bricks"), state.get("bricks")));
            newState.put("player", getLatestRowData(oldState.get("player"), state.get("player")));
            newState.put("leveler", getLatestRowData(oldState.get("leveler"), state.get("leveler")));
            newState.put("projectile", getLatestRowData(oldState.get("projectile"), state.get("projectile")));

            return insertAllSavedState(newState);
        } catch (IOException e) {
            syslog.warning(e.getMessage());
        }

        return false;
    }

    private Object getLatestRowData(Object oldData, Object newData) {
        if (oldData != null && !oldData.equals(newData)) {
            return newData;
        }

        return oldData;
    }

    @Override
    public boolean delete() {
        return false;
    }

    public boolean isTableEmpty() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(tablePath))) {
            try {
                Object nextObj = in.readObject();
                if (nextObj != null) {
                    return false;
                }
            } catch (EOFException e) {
                return true;
            }
        } catch (EOFException e) {
            return true;
        } catch (IOException | ClassNotFoundException e) {
            syslog.warning(e.getMessage());
        }

        return true;
    }

    /**
     * Overload
     * Check if the table is empty from a specific line.
     *
     * @param fromLine The line to start checking from.
     * @return True if the table is empty, false otherwise.
     */
    public boolean isTableEmpty(int fromLine) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(tablePath))) {
            for (int i = 1; i < fromLine; i++) {
                in.readObject();
            }

            try {
                Object nextObj = in.readObject(); // Attempt to read the next object.
                if (nextObj != null) {
                    return false;
                }
            } catch (EOFException e) {
                return true;
            }
        } catch (EOFException e) {
            return true;
        } catch (IOException | ClassNotFoundException e) {
            syslog.warning(e.getMessage());
        }

        return true;
    }
}
