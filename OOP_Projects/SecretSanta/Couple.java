package SecretSanta;

import java.util.Objects;

/**
 * Represents a couple participating in Secret Santa.
 * <p>
 * A couple consists of two {@link Person} objects where the first person ({@code giver})
 * is the giver and the second person ({@code receiver}) is the receiver.
 * No person can give more than once, ensuring that each {@code giver} is unique across couples.
 * </p>
 */
public class Couple implements Comparable<Couple> {
    private Person giver;
    private Person receiver;

    /**
     * Constructs a new {@code Couple} with the specified giver and receiver.
     *
     * @param giver the giver in the couple, must not be {@code null}
     * @param receiver the receiver in the couple, must not be {@code null}
     * @throws IllegalArgumentException if either {@code giver} or {@code receiver} is {@code null}
     */
    public Couple(Person giver, Person receiver) {
        if (giver == null || receiver == null) {
            throw new IllegalArgumentException("Persons in a couple cannot be null.");
        }
        this.giver = giver;
        this.receiver = receiver;
    }

    /**
     * Returns the giver in the couple.
     *
     * @return the first {@link Person} of the couple
     */
    public Person getGiver() {
        return giver;
    }

    /**
     * Returns the receiver in the couple.
     *
     * @return the second {@link Person} of the couple
     */
    public Person getReceiver() {
        return receiver;
    }

    /**
     * Compares this couple with the specified couple for order.
     * <p>
     * The comparison is based solely on the giver's name and email (case-insensitive).
     * </p>
     *
     * @param other the {@code Couple} to be compared
     * @return a negative integer, zero, or a positive integer as this couple
     *         is less than, equal to, or greater than the specified couple
     */
    @Override
    public int compareTo(Couple other) {
        // Compare giver's name (case-insensitive)
        int nameComparison = this.giver.getName().compareToIgnoreCase(other.giver.getName());
        if (nameComparison != 0) {
            return nameComparison;
        }

        // If names are equal, compare giver's email (case-insensitive)
        return this.giver.getEmail().compareToIgnoreCase(other.giver.getEmail());
    }

    /**
     * Indicates whether some other object is "equal to" this couple.
     * <p>
     * Two couples are considered equal if they have the same giver {@code giver}.
     * Equality of givers is determined by their {@link Person#equals(Object)} method.
     * </p>
     *
     * @param obj the reference object with which to compare
     * @return {@code true} if this couple is the same as the {@code obj} argument;
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Couple) {
            Couple other = (Couple) obj;
            return this.giver.equals(other.giver);
        }

        return false;
    }

    /**
     * Returns a hash code value for the couple.
     * <p>
     * The hash code is based on the giver's {@code giver}.
     * </p>
     *
     * @return a hash code value for this couple
     */
    @Override
    public int hashCode() {
        return Objects.hash(giver);
    }

    /**
     * Returns a string representation of the couple.
     *
     * @return a string in the format "Couple(giver = [Person1], receiver = [Person2])"
     */
    @Override
    public String toString() {
        return "Couple(giver = " + giver + ", receiver = " + receiver + ")";
    }
}