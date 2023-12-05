import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import showbooking.ShowService;
import showbooking.Admin;
import showbooking.Show;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AdminTest {

    @Mock
    private ShowService showService;

    private Admin admin;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        admin = new Admin(showService);
    }

    @Test
    void testSetupShowValidInputs() {
        assertDoesNotThrow(() -> admin.setupShow(1, 10, 10, 30));
    }

    @Test
    void testSetupShowInvalidInputs() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            admin.setupShow(1, 27, 11, 30);
        });

        String expectedMessage = "The max seats per row is 10 and max rows is 26";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testViewShowWithExistingShow() {
        Show mockShow = new Show(1, 1, 1, 1); // Assuming Show is a class with the required methods
        when(showService.viewShow(1)).thenReturn(mockShow);

        admin.viewShow(1);

        verify(showService).viewShow(1);
    }

    @Test
    void testViewShowWithInvalidShow() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            admin.viewShow(2);
        });

        String expectedMessage = String.format("Show not found for show number: %d", 2);
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
