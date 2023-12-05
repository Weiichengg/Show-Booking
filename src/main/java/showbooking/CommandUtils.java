package showbooking;

import java.util.HashMap;
import java.util.Map;

public class CommandUtils {

    private static final Map<String, String> commandMap = new HashMap<>();

    static {
        commandMap.put("setup",
                "Setup <Show Number> <Number of Rows> <Number of seats per row> <Cancellation window in minutes>");
        commandMap.put("view", "View <Show Number>");
        commandMap.put("availability", "Availability <Show Number>");
        commandMap.put("book", "Book <Show Number> <Phone#> <Comma separated list of seats>");
        commandMap.put("cancel", "Cancel <Ticket#> <Phone#>");
    }

    public static Map<String, String> getCommandMap() {
        return commandMap;
    }

}
