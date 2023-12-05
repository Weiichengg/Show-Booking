import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import showbooking.*;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ShowTest {

    private Show show;

    @BeforeEach
    void setUp() {=
        show = new Show(1, 2, 3, 60);
    }

    @Test
    void testGetShowNumber() {
        assertEquals(1, show.getShowNumber());
    }

    @Test
    void testGetSeatMap() {
        Map<String, Seat> seats = show.getSeatMap();
        assertNotNull(seats);
        assertEquals(6, seats.size()); // 2 rows * 3 seats per row
        assertTrue(seats.containsKey("A1"));
        assertTrue(seats.containsKey("B3"));
    }

    @Test
    void testIsValidSeat() {
        assertTrue(show.isValidSeat(Arrays.asList("A1", "A2")));
        assertFalse(show.isValidSeat(Arrays.asList("A1", "D4")));
    }

    @Test
    void testGetSeats() {
        Set<String> seatIds = show.getSeats();
        assertNotNull(seatIds);
        assertEquals(6, seatIds.size());
        assertTrue(seatIds.contains("A1"));
        assertTrue(seatIds.contains("B3"));
    }

    @Test
    void testGetCancellationWindowInMinutes() {
        assertEquals(60, show.getCancellationWindowInMinutes());
    }
}
