package showbooking;

import java.util.List;

public class Buyer {
    private BookingService bookingService;

    public Buyer(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void checkAvailability(int showNumber) {
        List<Seat> availableSeats = bookingService.checkAvailability(showNumber);
        if (availableSeats.isEmpty()) {
            System.out.println("No seats available.");
        } else {
            availableSeats.forEach(seat -> System.out.println(seat.getSeatId() + " is available."));
        }
    }

    public void bookSeats(int showNumber, String phone, List<String> seatIds) {
        Result bookingResult = bookingService.bookSeats(showNumber, phone, seatIds);
        if (bookingResult.getIsSuccess()) {
            System.out.println("Booking successful -" + bookingResult.getMessage());
        } else {
            System.out.println("Booking failed - " + bookingResult.getMessage());
        }
    }

    public void cancelBooking(String ticketNumber, String phoneNumbers) {
        Result bookingResult = bookingService.cancelBooking(ticketNumber, phoneNumbers);
        if (bookingResult.getIsSuccess()) {
            System.out.println("Booking cancelled successfully - " + bookingResult.getMessage());
        } else {
            System.out.println("Cancellation failed -" + bookingResult.getMessage());
        }
    }
}
