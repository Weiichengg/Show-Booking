import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import showbooking.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class BuyerTest {

    @Mock
    private BookingService bookingService;

    private Buyer buyer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        buyer = new Buyer(bookingService);
    }

    @Test
    void testCheckAvailabilityWithAvailableSeats() {
        List<Seat> mockSeats = Arrays.asList(new Seat("A1"), new Seat("A2"));
        when(bookingService.checkAvailability(1)).thenReturn(mockSeats);

        buyer.checkAvailability(1);

        verify(bookingService).checkAvailability(1);
    }

    @Test
    void testCheckAvailabilityWithNoAvailableSeats() {
        when(bookingService.checkAvailability(1)).thenReturn(Collections.emptyList());

        buyer.checkAvailability(1);

        verify(bookingService).checkAvailability(1);
    }

    @Test
    void testBookSeatsSuccess() {
        when(bookingService.bookSeats(anyInt(), anyString(), anyList()))
                .thenReturn(new Result(true, "Booking successful"));

        buyer.bookSeats(1, "1234567890", Arrays.asList("A1", "A2"));

        verify(bookingService).bookSeats(anyInt(), anyString(), anyList());
    }

    @Test
    void testBookSeatsFailure() {
        when(bookingService.bookSeats(anyInt(), anyString(), anyList()))
                .thenReturn(new Result(false, "Booking failed"));

        buyer.bookSeats(1, "1234567890", Arrays.asList("A1", "A2"));

        verify(bookingService).bookSeats(anyInt(), anyString(), anyList());
    }

    @Test
    void testCancelBookingSuccess() {
        when(bookingService.cancelBooking("ticket123", "123"))
                .thenReturn(new Result(true, "Cancellation successful"));

        buyer.cancelBooking("ticket123", "123");

        verify(bookingService).cancelBooking("ticket123", "123");
    }

    @Test
    void testCancelBookingFailure() {
        when(bookingService.cancelBooking("ticket123", "123"))
                .thenReturn(new Result(false, "Cancellation failed"));

        buyer.cancelBooking("ticket123", "123");

        verify(bookingService).cancelBooking("ticket123", "123");
    }
}
