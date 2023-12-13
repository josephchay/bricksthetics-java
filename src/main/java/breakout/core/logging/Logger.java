package breakout.core.logging;

import breakout.core.contracts.DefaultDependenciesInjectable;
import breakout.core.logging.tags.LogLevel;
import breakout.core.logging.tags.LogType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Outlines the entire functionality of the logger.
 */
abstract public class Logger extends SeverableLogger implements DefaultDependenciesInjectable {
    /**
     * Logs a message with a specific level and context.
     *
     * @param level The level of the log.
     * @param message The message to log.
     * @param context The context of the log.
     */
    @Override
    public void log(LogLevel level, String message, Map<String, String> context) {
        String contextString = formatContext(context);
        String stackTraceString = formatContext(getStackTrace());
        String record = String.format("[%s] [%s] [%s] %s %s", getCurrentTimestamp(), level, stackTraceString, message, contextString);

        prepareAndWriteRecord(record);
    }

    @Override
    public void log(LogLevel level, String message) {
        String stackTraceString = formatContext(getStackTrace());
        String record = String.format("[%s] [%s] [%s] %s", getCurrentTimestamp(), level, stackTraceString, message);

        prepareAndWriteRecord(record);
    }

    /**
     * Prepares the record to be written to the log file.
     *
     * @param record The record to be written.
     */
    protected void prepareAndWriteRecord(String record) {
        String logPath = env.getLogPath() + "/" + defineType().toString();
        String yearPath = logPath + "/" + getCurrentYear();
        String monthPath = yearPath + "/" + getCurrentMonthName();
        String filePath = monthPath + "/" + getCurrentDate() + ".log";

        files.createDirectoryIfNotExist("audit")
                .createDirectoryIfNotExist(yearPath)
                .createDirectoryIfNotExist(monthPath)
                .createFileIfNotExist(filePath)
                .write(filePath, record);
    }

    /**
     * Defines the type of the log.
     *
     * @return The type of the log.
     */
    abstract protected LogType defineType();

    /**
     * Retrieves the current year.
     *
     * @return The current year.
     */
    public int getCurrentYear() {
        return LocalDate.now().getYear();
    }

    public String getCurrentMonthName() {
        Month currentMonth = LocalDate.now().getMonth();

        return currentMonth.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    /**
     * Retrieve the current date in the format of ddMMyyyy.
     *
     * @return The current date in the given format.
     */
    public String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

        return LocalDate.now().format(formatter);
    }

    /**
     * Trace the location where the log is executed.
     *
     * @return The class and method which called the log.
     */
    public Map<String, String> getStackTrace() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        StackTraceElement caller = stackTraceElements[5]; // Index specified for the location of the log execution.
        String className = caller.getClassName();
        String methodName = caller.getMethodName();

        Map<String, String> hashMap = new HashMap<>();

        hashMap.put("class", className);
        hashMap.put("method", methodName);

        return hashMap;
    }

    /**
     * Retrieve the current timestamp in the format of yyyy-MM-dd HH:mm:ss.
     *
     * @return The current timestamp in the given format.
     */
    public static String getCurrentTimestamp() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return currentDateTime.format(formatter);
    }

    /**
     * Retrieve a specific timestamp in the format of yyyy-MM-dd HH:mm:ss.
     * Values will be calculated based on the given integers.
     *
     * @param year The year.
     * @param month The month.
     * @param day The day.
     * @param hour The hour.
     * @param minute The minute.
     * @param second The second.
     * @return The timestamp in the given format.
     */
    public static String getTimestamp(int year, int month, int day, int hour, int minute, int second) {
        LocalDateTime specificDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return specificDateTime.format(formatter);
    }

    /**
     * Properly format the given context into a readable string
     *
     * @param context The context of the log.
     * @return The formatted context.
     */
    private static String formatContext(Map<String, String> context) {
        StringBuilder contextBuilder = new StringBuilder();
        contextBuilder.append("[");
        for (Map.Entry<String, String> entry : context.entrySet()) {
            contextBuilder.append(String.format("%s: %s, ", entry.getKey(), entry.getValue()));
        }
        if (!context.isEmpty()) {
            contextBuilder.setLength(contextBuilder.length() - 2); // Remove the trailing comma and space
        }
        contextBuilder.append("]");
        return contextBuilder.toString();
    }
}
