package exceptions;

public class NotFoundModelException extends RuntimeException {
    public NotFoundModelException(String message) {
        super(message);
    }
}
