package OnlineShop.Exceptions;

public class InvalidClientException extends Exception {
    public InvalidClientException() {
        super();
    }

    public InvalidClientException(String message) {
        super(message);
    }

    public InvalidClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidClientException(Throwable cause) {
        super(cause);
    }
}