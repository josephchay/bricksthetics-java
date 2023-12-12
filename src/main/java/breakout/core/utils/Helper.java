package breakout.core.utils;

import breakout.core.contracts.DefaultDependenciesInjectable;

public class Helper implements DefaultDependenciesInjectable {
    /**
     * Substitute for System.out.println()
     *
     * @param args Arguments to be printed to console.
     */
    public static void print(Object... args) {
        StringBuilder builder = new StringBuilder();

        for (Object arg : args) {
            System.out.println(arg);
            builder.append(arg);
        }

        syslog.debug(builder.toString());
    }
}
