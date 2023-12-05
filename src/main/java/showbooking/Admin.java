package showbooking;

import java.util.List;

public class Admin {
    private ShowService showService;

    public Admin(ShowService showService) {
        this.showService = showService;
    }

    public void setupShow(int showNumber, int numberOfRows, int seatsPerRow, int cancellationWindow) {
        // Validate inputs as needed
        if (numberOfRows > 26 || seatsPerRow > 10) {
            throw new IllegalArgumentException("The max seats per row is 10 and max rows is 26");
        }

        showService.setupShow(showNumber, numberOfRows, seatsPerRow, cancellationWindow);
        System.out.println("Show setup successful for show number: " + showNumber);
    }

    public void viewShow(int showNumber) {
        Show show = showService.viewShow(showNumber);
        if (show == null) {
            throw new IllegalArgumentException("Show not found for show number: " + showNumber);
        }

        System.out.println("Show Details:");
        System.out.println("Show Number: " + show.getShowNumber());
        System.out.println("Cancellation Window: " + show.getCancellationWindowInMinutes() + " minutes");
        List<Booking> bookings = showService.getBookings(showNumber);
        System.out.println(String.format("Reserved Seats: %d", bookings.size()));
        for (Booking booking : bookings) {
            System.out.println(String.format("Show Number - %s, ticket No - %s, phone - %s, seats numbers - %s",
                    showNumber, booking.getTicketNumber(), booking.getBuyerPhone(), booking.getSeats()));
        }
    }

}
