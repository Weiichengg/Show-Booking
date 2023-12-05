package showbooking;

public class Result {
    private final boolean isSuccess;
    private final String message;

    public Result(boolean isSuccess, String message) {
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }
}
