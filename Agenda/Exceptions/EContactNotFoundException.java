package Agenda.Exceptions;

/**
 * Exception thrown when a contact is not found in the agenda.
 */
public class EContactNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new {@code EContactNotFoundException} with {@code null}
     * as its detail message.
     */
    public EContactNotFoundException() {
        super();
    }

    /**
     * Constructs a new {@code EContactNotFoundException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public EContactNotFoundException(String message) {
        super(message);
    }
}