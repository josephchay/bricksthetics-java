package breakout.core.routing;

import breakout.core.controllers.Controller;
import breakout.core.utils.DictCollection;

import java.util.HashMap;

/**
 * Stores all the routes of the application.
 */
public class RouteCollection extends DictCollection<Controller> {
    public RouteCollection(HashMap<String, Controller> items) {
        super(items);
    }

    @Override
    public void set(String name, Controller value) {
        super.set(name, value);
    }
}
