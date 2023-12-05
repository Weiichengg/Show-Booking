package showbooking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.stream.Collectors;

public class BookingService {
    private Map<String, Booking> bookings = new HashMap<String, Booking>();
    private ShowService showService;
    private Set<String> bookedPhonesForShows;

    public BookingService(ShowService showService) {
        this.showService = showService;
        this.bookedPhonesForShows = new HashSet<>();
    }

    public Booking getBookings(String bookingId) {
        return this.bookings.getOrDefault(bookingId, null);

    }

    public List<Seat> checkAvailability(int showNumber) {
        Show show = showService.viewShow(showNumber);
        return show.getSeatMap().values().stream()
                .filter(seat -> !seat.isBooked())
                .collect(Collectors.toList());
    }

    public Result bookSeats(int showNumber, String phone, List<String> seatIds) {
        Show show = showService.viewShow(showNumber);
        if (show == null) {
            return new Result(false, "Invalid show number");
        }

        String bookingKey = createBookingKey(showNumber, phone);

        if (bookedPhonesForShows.contains(bookingKey)) {
            return new Result(false, String.format("%s has an existing booking for this show - %s", phone, showNumber));
        }
        if (!show.isValidSeat(seatIds)) {
            return new Result(false, "Invalid seat");
        }

        List<Seat> seatsToBook = seatIds.stream()
                .map(show.getSeatMap()::get)
                .collect(Collectors.toList());

        if (seatsToBook.contains(null) || seatsToBook.stream().anyMatch(Seat::isBooked)) {
            return new Result(false, "Invalid seat selection.");
        }

        Booking booking = new Booking(seatsToBook, showNumber, phone);
        bookings.put(booking.getTicketNumber(), booking);
        seatsToBook.forEach(seat -> seat.setBooked(true));
        showService.addBooking(showNumber, booking);
        bookedPhonesForShows.add(bookingKey);
        return new Result(true, String.format("Your ticket number is - %s", booking.getTicketNumber()));
    }

    public Result cancelBooking(String bookingId, String phoneNumber) {
        Booking booking = this.getBookings(bookingId);
        if (booking == null) {

            return new Result(false, "Invalid Booking Id"); // Booking not found
        }

        Show show = showService.viewShow(booking.getShowNumber());
        if (show == null) {
            return new Result(false, "The ticket's show does not exist");
            // Show not found, should not happen if data integrity is maintained
        }

        LocalDateTime now = LocalDateTime.now();
        long minutesBetween = ChronoUnit.MINUTES.between(booking.getTimestamp(), now);

        if (minutesBetween > show.getCancellationWindowInMinutes()) {
            return new Result(false, String.format("You are not within the cancellation window of %d",
                    show.getCancellationWindowInMinutes())); // Cancellation window has passed
        }

        String bookingKey = createBookingKey(show.getShowNumber(), phoneNumber);
        System.out.println(bookedPhonesForShows);
        if (!bookedPhonesForShows.contains(bookingKey)) {
            return new Result(false, "Please cancel the ticket with the valid phone number");
        }

        bookedPhonesForShows.remove(bookingKey);
        // Process cancellation: mark seats as available and remove the booking
        booking.getBookedSeats().forEach(seat -> seat.setBooked(false));
        showService.removeBooking(booking.getShowNumber(), booking);
        bookings.remove(bookingId);

        return new Result(true, booking.getTicketNumber());
    }

    private String createBookingKey(int showNumber, String phone) {
        return showNumber + "-" + phone;
    }
}
