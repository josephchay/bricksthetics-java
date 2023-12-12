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

abstract public class Logger extends SeverableLogger implements DefaultDependenciesInjectable {
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

    abstract protected LogType defineType();

    public int getCurrentYear() {
        return LocalDate.now().getYear();
    }

    public String getCurrentMonthName() {
        Month currentMonth = LocalDate.now().getMonth();

        return currentMonth.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    public String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

        return LocalDate.now().format(formatter);
    }

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

    public static String getCurrentTimestamp() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return currentDateTime.format(formatter);
    }

    public static String getTimestamp(int year, int month, int day, int hour, int minute, int second) {
        LocalDateTime specificDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return specificDateTime.format(formatter);
    }

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
