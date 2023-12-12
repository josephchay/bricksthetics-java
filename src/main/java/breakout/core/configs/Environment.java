package breakout.core.configs;

import breakout.core.contracts.DefaultDependenciesInjectable;

import java.net.URL;
import java.util.Map;

/**
 * Provides access to environment variables and resources information.
 */
public class Environment implements DefaultDependenciesInjectable {
    private static Environment instance;

    public static synchronized Environment getInstance() {
        if (instance == null) {
            instance = new Environment();
        }

        return instance;
    }

    protected Environment() {}

    protected String dbPath;
    protected Map<String, String> tablesPath;

    protected String logPath;

    protected Map<String, String> resourceImagesPath;
    protected Map<String, String> resourceSFXPath;
    protected String resourceStylesheetPath;

    public String getDbPath() {
        return dbPath;
    }
    public void setDbPath(String path) {
        dbPath = path;
    }

    public Map<String, String> getTablesPath() {
        return tablesPath;
    }

    public void setTablesPath(Map<String, String> paths) {
        tablesPath = paths;
    }

    public void addTablePath(String name, String path) {
        if (tablesPath.containsKey(name)) {
            return;
        }

        tablesPath.put(name, path);
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String path) {
        logPath = path;
    }

    /**
     * Retrieves the image from the resource based on the specified name.
     *
     * @param name The resource image ID.
     * @return The resource.
     */
    public String getResourceImage(String name) {
        URL resource = getClass().getResource(resourceImagesPath.get(name));
        if (resource == null) {
            syslog.debug("The resource file " + name + " cannot be found.");
        }

        return resource.toExternalForm();
    }

    /**
     * Retrieves the SFX audio from the resource based on the specified name.
     *
     * @param name The resource SFX ID.
     * @return The SFX resource.
     */
    public String getResourceSFX(String name) {
        URL resource = getClass().getResource(resourceSFXPath.get(name));
        if (resource == null) {
            syslog.debug("The resource file " + name + " cannot be found.");
        }

        return resource.toExternalForm();
    }

    public Map<String, String> getResourceImages() {
        return resourceImagesPath;
    }

    public Map<String, String> getResourceSFXs() {
        return resourceSFXPath;
    }

    public void setResourceImagesPath(Map<String, String> paths) {
        resourceImagesPath = paths;
    }

    public void addResourceImagesPath(String name, String path) {
        if (resourceImagesPath.containsKey(name)) {
            return;
        }

        resourceImagesPath.put(name, path);
    }

    public void setResourceSFXPath(Map<String, String> paths) {
        resourceSFXPath = paths;
    }

    public void addResourceSFXPath(String name, String path) {
        if (resourceSFXPath.containsKey(name)) {
            return;
        }

        resourceSFXPath.put(name, path);
    }

    public void setResourceStylesheetPath(String name) {
        resourceStylesheetPath = name;
    }

    public String getResourceStylesheet() {
        URL resource = getClass().getResource(resourceStylesheetPath);
        if (resource == null) {
            syslog.debug("The resource file " + resourceStylesheetPath + " cannot be found.");
        }

        return resource.toExternalForm();
    }
}
