package showbooking;

public class Seat {
    private final String seatId;
    private boolean isBooked;

    public Seat(String seatId) {
        this.seatId = seatId;
        this.isBooked = false;
    }

    public String getSeatId() {
        return seatId;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
