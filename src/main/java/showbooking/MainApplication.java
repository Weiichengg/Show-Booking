package showbooking;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainApplication {
    private enum Role {
        ADMIN, BUYER
    }

    private Map<String, Command> adminCommands;
    private Map<String, Command> buyerCommands;
    private Role currentRole;
    private Map<String, String> commandMap = CommandUtils.getCommandMap();

    public MainApplication() {
        ShowService showService = new ShowService();
        BookingService bookingService = new BookingService(showService);
        Admin admin = new Admin(showService);
        Buyer buyer = new Buyer(bookingService);

        // Setting up commands for admin
        adminCommands = new HashMap<>();
        adminCommands.put("setup", new SetupShowCommand(admin));
        adminCommands.put("view", new ViewShowCommand(admin));

        // Setting up commands for buyer
        buyerCommands = new HashMap<>();
        buyerCommands.put("availability", new CheckAvailabilityCommand(buyer));
        buyerCommands.put("book", new BookSeatsCommand(buyer));
        buyerCommands.put("cancel", new CancelBookingCommand(buyer));

        // Default role
        currentRole = Role.ADMIN;
    }

    private void listCommands() {
        Map<String, Command> commands = currentRole == Role.ADMIN ? adminCommands : buyerCommands;
        System.out.println("Available commands:");
        for (String command : commands.keySet()) {
            System.out.println(this.commandMap.get(command));
        }
    }

    public boolean executeCommandAndCheckIfTerminate(String input) {
        if ("exit".equalsIgnoreCase(input)) {
            return true;
        } else if ("switch".equalsIgnoreCase(input)) {
            switchRole();
            return false;
        } else if ("help".equalsIgnoreCase(input)) {
            listCommands();
            return false;
        }

        String[] parts = input.split("\\s+", 2);
        String commandKey = parts[0].toLowerCase();
        String[] args = parts.length > 1 ? parts[1].split("\\s+") : new String[0];

        Map<String, Command> commands = currentRole == Role.ADMIN ? adminCommands : buyerCommands;
        Command command = commands.get(commandKey);
        if (command == null) {
            System.out.println("Unknown command: " + commandKey);
            return false;
        }
        try {
            command.execute(args);
        } catch (Exception e) {
            System.err.println(e);
        }

        return false;
    }

    private void switchRole() {
        if (currentRole == Role.ADMIN) {
            currentRole = Role.BUYER;
            System.out.println("Switched to Buyer role.");
        } else {
            currentRole = Role.ADMIN;
            System.out.println("Switched to Admin role.");
        }
    }

    public static void main(String[] args) {
        MainApplication app = new MainApplication();
        Scanner scanner = new Scanner(System.in);
        Boolean isTerminate = false;
        System.out.println("Welcome to the Show Booking Application!");
        while (!isTerminate) {
            System.out.print(String.format("Hi %s, enter command (press 'help' for more info or 'exit' to quit): ",
                    app.currentRole));
            String input = scanner.nextLine();
            isTerminate = app.executeCommandAndCheckIfTerminate(input);
        }
        scanner.close();
    }
}
