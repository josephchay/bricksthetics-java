package breakout.core.file;

import breakout.core.contracts.DefaultDependenciesInjectable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles and provides management to the local file storage system.
 */
public class Filesystem implements DefaultDependenciesInjectable {
    private static Filesystem instance;

    public static synchronized Filesystem getInstance() {
        if (instance == null) {
            instance = new Filesystem();
        }

        return instance;
    }

    private Filesystem() {}

    /**
     * Creates a directory if it does not exist.
     *
     * @param path The path of the directory to create.
     * @return The Filesystem instance.
     */
    public Filesystem createDirectoryIfNotExist(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            return createDirectory(path);
        }

        return this;
    }

    /**
     * Creates a directory.
     *
     * @param path The path of the directory to create.
     * @return The Filesystem instance.
     */
    public Filesystem createDirectory(String path) {
        File directory = new File(path);

        directory.mkdirs();

        return this;
    }

    /**
     * Creates a file if it does not exist.
     *
     * @param path The path of the file to create.
     * @return The Filesystem instance.
     */
    public Filesystem createFileIfNotExist(String path) {
        File file = new File(path);

        if (file.exists()) {
            return this;
        }

        createFile(path);

        return this;
    }

    /**
     * Creates a file.
     *
     * @param path The path of the file to create.
     * @return The Filesystem instance.
     */
    public Filesystem createFile(String path) {
        File file = new File(path);

        try {
            file.createNewFile();
        } catch (IOException e) {
            syslog.debug(e.getMessage());
        }

        return this;
    }

    /**
     * Writes content to a file.
     *
     * @param path The path of the file to write to.
     * @param content The content to write to the file.
     * @return True if the file was written to successfully, false otherwise.
     */
    public boolean write(String path, String content) {
        try (FileWriter writer = new FileWriter(path, true)) {
            writer.write(content + "\n");
            return true;
        } catch (IOException e) {
            syslog.debug(e.getMessage());
            return false;
        }
    }
}
