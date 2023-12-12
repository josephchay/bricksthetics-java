package breakout.core.file;

import breakout.core.contracts.DefaultDependenciesInjectable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Filesystem implements DefaultDependenciesInjectable {
    private static Filesystem instance;

    public static synchronized Filesystem getInstance() {
        if (instance == null) {
            instance = new Filesystem();
        }

        return instance;
    }

    private Filesystem() {}

    public Filesystem createDirectoryIfNotExist(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            return createDirectory(path);
        }

        return this;
    }

    public Filesystem createDirectory(String path) {
        File directory = new File(path);

        directory.mkdirs();

        return this;
    }

    public Filesystem createFileIfNotExist(String path) {
        File file = new File(path);

        if (file.exists()) {
            return this;
        }

        createFile(path);

        return this;
    }

    public Filesystem createFile(String path) {
        File file = new File(path);

        try {
            file.createNewFile();
        } catch (IOException e) {
            syslog.debug(e.getMessage());
        }

        return this;
    }

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
