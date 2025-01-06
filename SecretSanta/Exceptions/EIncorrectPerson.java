package SecretSanta.Exceptions;

/**
 * Exception thrown when a Person has incorrect or invalid attributes.
 */
public class EIncorrectPerson extends RuntimeException{
    /**
     * Constructs a new EIncorrectPerson exception with {@code null} as its detail message.
     */
    public EIncorrectPerson() {
        super();
    }

    /**
     * Constructs a new EIncorrectPerson exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public EIncorrectPerson(String message) {
        super(message);
    }

    /**
     * Constructs a new EIncorrectPerson exception with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause the cause of the exception.
     */
    public EIncorrectPerson(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new EIncorrectPerson exception with the specified cause.
     *
     * @param cause the cause of the exception.
     */
    public EIncorrectPerson(Throwable cause) {
        super(cause);
    }
}
