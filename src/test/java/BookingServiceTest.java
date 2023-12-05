import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import showbooking.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class BookingServiceTest {

    @Mock
    private ShowService showService;

    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        bookingService = new BookingService(showService);
    }

    @Test
    void testCheckAvailabilityWithAvailableSeats() {
        Show mockShow = mock(Show.class);
        when(mockShow.getSeatMap()).thenReturn(new HashMap<>()); // Populate with mock seat data
        when(showService.viewShow(anyInt())).thenReturn(mockShow);

        List<Seat> availableSeats = bookingService.checkAvailability(1);
        assertNotNull(availableSeats);
        // Further assertions based on mock seat data
    }

    @Test
    void testBookSeatsWithValidData() {
        Show mockShow = mock(Show.class);
        when(mockShow.isValidSeat(anyList())).thenReturn(true);
        when(mockShow.getSeatMap()).thenReturn(new HashMap<String, Seat>() {
            {
                put("A1", new Seat("A1"));
                put("A2", new Seat("A2"));
            }
        }); // Populate with mock seat data
        when(showService.viewShow(anyInt())).thenReturn(mockShow);

        Result result = bookingService.bookSeats(1, "1234567890", Arrays.asList("A1", "A2"));
        assertTrue(result.getIsSuccess());
        // Additional assertions for the result message
    }

    @Test
    void testBookSeatsWithInvalidShow() {
        when(showService.viewShow(anyInt())).thenReturn(null);

        Result result = bookingService.bookSeats(1, "1234567890", Collections.singletonList("A1"));
        assertFalse(result.getIsSuccess());
        assertEquals("Invalid show number", result.getMessage());
    }

    @Test
    void testCancelBookingWithInvalidBookingId() {
        Result result = bookingService.cancelBooking("invalidId", "123");
        assertFalse(result.getIsSuccess());
        assertEquals("Invalid Booking Id", result.getMessage());
    }

}
