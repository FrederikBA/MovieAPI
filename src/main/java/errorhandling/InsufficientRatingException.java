package errorhandling;

public class InsufficientRatingException extends Exception {
    public InsufficientRatingException(String message) {
        super(message);
    }
}
