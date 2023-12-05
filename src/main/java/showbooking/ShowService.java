package showbooking;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class ShowService {
    private Map<Integer, Show> shows;
    private Map<Integer, List<Booking>> bookingMap;

    public ShowService() {
        this.shows = new HashMap<>();
        this.bookingMap = new HashMap<>();
    }

    public void setupShow(int showNumber, int numberOfRows, int seatsPerRow, int cancellationWindow) {
        if (numberOfRows <= 0 || seatsPerRow <= 0) {
            throw new IllegalArgumentException("Number of rows and seats per row must be greater than zero.");
        }
        if (cancellationWindow < 0) {
            throw new IllegalArgumentException("Cancellation window cannot be negative.");
        }
        if (shows.containsKey(showNumber)) {
            throw new IllegalArgumentException("A show with this number already exists.");
        }

        Show newShow = new Show(showNumber, numberOfRows, seatsPerRow, cancellationWindow);
        shows.put(showNumber, newShow);
        bookingMap.put(showNumber, new ArrayList<>());
    }

    public Show viewShow(int showNumber) {
        return shows.getOrDefault(showNumber, null);
    }

    public void addBooking(int showNumber, Booking booking) {
        List<Booking> bookingList = bookingMap.get(showNumber);
        bookingList.add(booking);
    }

    public void removeBooking(int showNumber, Booking booking) {
        List<Booking> bookingList = bookingMap.get(showNumber);
        bookingList.remove(booking);
    }

    public List<Booking> getBookings(int showNumber) {
        return bookingMap.get(showNumber);
    }

    // Additional methods as needed...
}
