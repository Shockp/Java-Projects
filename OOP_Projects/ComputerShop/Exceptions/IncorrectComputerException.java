package ComputerShop.Exceptions;

public class IncorrectComputerException extends RuntimeException {
    public IncorrectComputerException() {
        super();
    }

    public IncorrectComputerException(String message) {
        super(message);
    }

    public IncorrectComputerException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectComputerException(Throwable cause) {
        super(cause);
    }
}