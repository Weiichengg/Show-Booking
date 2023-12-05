package showbooking;

import java.util.stream.Collectors;

import java.util.Arrays;
import java.util.List;

public interface Command {
    void execute(String[] args);
}

class SetupShowCommand implements Command {
    private Admin admin;

    public SetupShowCommand(Admin admin) {
        this.admin = admin;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Provide exactly 4 parameters - show number, rows and cols only");
        }
        int showId, rows, cols, cancellationWindow;

        try {
            showId = Integer.parseInt(args[0]);
            rows = Integer.parseInt(args[1]);
            cols = Integer.parseInt(args[2]);
            cancellationWindow = Integer.parseInt(args[3]);
        } catch (Exception e) {
            throw new IllegalArgumentException("All provided values are strictly numeric ");
        }

        admin.setupShow(showId, rows, cols, cancellationWindow);

    }
}

class ViewShowCommand implements Command {
    private Admin admin;

    public ViewShowCommand(Admin admin) {
        this.admin = admin;
    }

    @Override
    public void execute(String[] args) {
        // args should contain: showNumber
        if (args.length != 1) {
            throw new IllegalArgumentException("Provide exactly 1 parameters - show number");
        }
        int showNumber;
        try {
            showNumber = Integer.parseInt(args[0]);
        } catch (Exception e) {
            throw new IllegalArgumentException("show number has to be numeric");
        }
        if (showNumber <= 0) {
            throw new IllegalArgumentException("show number has to be positive");
        }
        admin.viewShow(showNumber);
    }
}

class CheckAvailabilityCommand implements Command {
    private Buyer buyer;

    public CheckAvailabilityCommand(Buyer buyer) {
        this.buyer = buyer;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Provide exactly 1 parameters - show number");
        }
        int showNumber;
        try {
            showNumber = Integer.parseInt(args[0]);
        } catch (Exception e) {
            throw new IllegalArgumentException("show number has to be numeric");
        }
        if (showNumber <= 0) {
            throw new IllegalArgumentException("show number has to be positive");
        }
        buyer.checkAvailability(showNumber);
    }
}

class BookSeatsCommand implements Command {
    private Buyer buyer;

    public BookSeatsCommand(Buyer buyer) {
        this.buyer = buyer;
    }

    @Override
    public void execute(String[] args) {
        // args should contain: showNumber, phone, seatIds
        if (args.length < 3) {
            System.out.println("Provide at least 3 parameter - the show number, phone number and seatIds");
            return;
        }
        int showNumber;
        String phone;
        List<String> seatIds;
        try {
            showNumber = Integer.parseInt(args[0]);
            phone = args[1];
            seatIds = Arrays.asList(args[2].split(",")).stream()
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());
            ;
        } catch (Exception e) {
            throw new IllegalArgumentException("Ensure all the inputs are valid");
        }
        buyer.bookSeats(showNumber, phone, seatIds);
    }
}

class CancelBookingCommand implements Command {
    private Buyer buyer;

    public CancelBookingCommand(Buyer buyer) {
        this.buyer = buyer;
    }

    @Override
    public void execute(String[] args) {
        // args should contain: ticketNumber, phone
        if (args.length != 2) {
            throw new IllegalArgumentException("Provide exactly 1 parameters - ticketNumber, phoneNumbers");
        }
        String ticketNumber = args[0];
        String phoneNumbers = args[1];
        buyer.cancelBooking(ticketNumber, phoneNumbers);
    }
}
