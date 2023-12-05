import org.junit.jupiter.api.Test;

import showbooking.Booking;
import showbooking.Seat;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class BookingTest {

    @Test
    public void testBookingCreation() {
        String buyerPhone = "1234567890";
        int showNumber = 100;
        List<Seat> seats = Arrays.asList(new Seat("A1"), new Seat("A2"));

        Booking booking = new Booking(seats, showNumber, buyerPhone);

        assertEquals(buyerPhone, booking.getBuyerPhone());
        assertEquals(showNumber, booking.getShowNumber());
        assertEquals(seats, booking.getBookedSeats());
    }
}
