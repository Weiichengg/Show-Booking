package showbooking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Booking {
    private final String ticketNumber;
    private final List<Seat> bookedSeats;
    private final int showNumber; // Optional: reference to the show number for which the booking is made
    private final LocalDateTime bookedTimeStamp;
    private final String phone;

    public Booking(List<Seat> bookedSeats, int showNumber, String phone) {
        this.ticketNumber = UUID.randomUUID().toString(); // Generate a unique ticket number
        this.bookedSeats = bookedSeats;
        this.showNumber = showNumber;
        this.bookedTimeStamp = LocalDateTime.now();
        this.phone = phone;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }

    public String getSeats() {
        StringBuilder sb = new StringBuilder();
        for (Seat seat : bookedSeats) {
            sb.append(seat.getSeatId() + ' ');
        }
        return sb.toString();
    }

    public int getShowNumber() {
        return showNumber;
    }

    public LocalDateTime getTimestamp() {
        return bookedTimeStamp;
    }

    public String getBuyerPhone() {
        return this.phone;
    }
}
