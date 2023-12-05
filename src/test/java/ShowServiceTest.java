
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import showbooking.Show;
import showbooking.ShowService;

import static org.junit.jupiter.api.Assertions.*;

public class ShowServiceTest {
    private ShowService showService;

    @BeforeEach
    public void setUp() {
        showService = new ShowService();
    }

    @Test
    public void testSetupShow() {
        int showNumber = 1;
        int numberOfRows = 10;
        int seatsPerRow = 10;
        int cancellationWindow = 1;
        showService.setupShow(showNumber, numberOfRows, seatsPerRow, cancellationWindow);

        Show show = showService.viewShow(showNumber);
        assertNotNull(show);
        assertEquals(numberOfRows * seatsPerRow, show.getSeats().size());
    }

    @Test
    public void testViewNonExistingShow() {
        Show show = showService.viewShow(99);
        assertNull(show);
    }

    @Test
    void testSetupShow_ExceedMaxSeats() {
        int showNumber = 2;
        int numberOfRows = 30; // Assuming max rows is less than 30
        int seatsPerRow = 10;
        int cancellationWindow = 1;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            showService.setupShow(showNumber, numberOfRows, seatsPerRow, cancellationWindow);
        });

        String expectedMessage = "Number of rows cannot exceed 26";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testViewShow_ExistingShow() {
        int showNumber = 3;
        showService.setupShow(showNumber, 10, 10, 1);
        assertNotNull(showService.viewShow(showNumber), "Viewing an existing show should not return null.");
    }

}
