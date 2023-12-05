package showbooking;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;

public class Show {
    private final int showNumber;
    private final Map<String, Seat> seats;
    private final int cancellationWindowInMinutes;

    public Show(int showNumber, int numberOfRows, int seatsPerRow, int cancellationWindowInMinutes) {
        this.showNumber = showNumber;
        this.seats = new HashMap<>();
        this.cancellationWindowInMinutes = cancellationWindowInMinutes;

        for (char row = 'A'; row < 'A' + numberOfRows; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                String seatId = "" + row + seatNum;
                seats.put(seatId, new Seat(seatId));
            }
        }
    }

    public int getShowNumber() {
        return showNumber;
    }

    public Map<String, Seat> getSeatMap() {
        return seats;
    }

    public boolean isValidSeat(List<String> seatIds) {
        for (String seatId : seatIds) {
            if (!seats.containsKey(seatId)) {
                return false; // Found an entry in the list not present in the map's keys
            }
        }
        return true;
    }

    public Set<String> getSeats() {
        return seats.keySet();
    }

    public int getCancellationWindowInMinutes() {
        return cancellationWindowInMinutes;
    }
}
