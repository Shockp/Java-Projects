package SecretSanta;

import java.util.Objects;

import SecretSanta.Exceptions.EIncorrectPerson;

/**
 * Represents a person participating in Secret Santa.
 * <p>
 * Identified by their name and email address. Both fields are non-null and non-blank.
 * Email addresses must end with "@alumnos.urjc.es".
 * Each person can both give and receive only one gift.
 * </p>
 */
public class Person implements Comparable<Person> {
    private static final String EMAIL_SUFFIX = "@alumnos.urjc.es";

    private String name;
    private String email;
    private boolean hasGift;
    private boolean hasReceivedGift;

    /**
     * Constructs a new Person with the specified name and email.
     *
     * @param name  the name of the person, must not be null or blank
     * @param email the email of the person, must not be null or blank and must end with "@alumnos.urjc.es"
     * @throws EIncorrectPerson if name or email is null/blank, or if email format is invalid
     */
    public Person(String name, String email) throws EIncorrectPerson {
        setName(name);
        setEmail(email);
        this.hasGift = false;
        this.hasReceivedGift = false;
    }

    /**
     * Returns the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name the new name of the person, must not be null or blank
     * @throws EIncorrectPerson if name is null or blank
     */
    public void setName(String name) throws EIncorrectPerson {
        if (name == null || name.isBlank()) {
            throw new EIncorrectPerson("Name must contain at least one character. Example: 'Alvaro'.");
        }
        this.name = name;
    }

    /**
     * Returns the email of the person.
     *
     * @return the email of the person
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the person.
     *
     * @param email the new email of the person, must not be null or blank and must end with "@alumnos.urjc.es"
     * @throws EIncorrectPerson if email is null or blank, or does not end with "@alumnos.urjc.es"
     */
    public void setEmail(String email) throws EIncorrectPerson {
        if (email == null || email.isBlank()) {
            throw new EIncorrectPerson("Email must contain at least one character. Example: 'alvaro@alumnos.urjc.es'.");
        }
        if (!email.toLowerCase().endsWith(EMAIL_SUFFIX)) {
            throw new EIncorrectPerson("Email must end with '@alumnos.urjc.es'. Example: 'alvaro@alumnos.urjc.es'.");
        }
        this.email = email.toLowerCase(); // Normalizar a minúsculas para consistencia
    }

    /**
     * Checks if the person has given a gift.
     *
     * @return {@code true} if the person has given a gift, {@code false} otherwise
     */
    public boolean hasGift() {
        return hasGift;
    }

    /**
     * Sets whether the person has given a gift.
     *
     * @param hasGift {@code true} if the person has given a gift, {@code false} otherwise
     */
    public void setHasGift(boolean hasGift) {
        this.hasGift = hasGift;
    }

    /**
     * Checks if the person has received a gift.
     *
     * @return {@code true} if the person has received a gift, {@code false} otherwise
     */
    public boolean hasReceivedGift() {
        return hasReceivedGift;
    }

    /**
     * Sets whether the person has received a gift.
     *
     * @param hasReceivedGift {@code true} if the person has received a gift, {@code false} otherwise
     */
    public void setHasReceivedGift(boolean hasReceivedGift) {
        this.hasReceivedGift = hasReceivedGift;
    }

    /**
     * Compares this person with the specified person for order.
     * <p>
     * The comparison is based first on the person's name (case-insensitive).
     * If the names are equal, the comparison is based on the email (case-insensitive).
     * </p>
     *
     * @param other the person to be compared.
     * @return a negative integer, zero, or a positive integer as this person
     *         is less than, equal to, or greater than the specified person.
     */
    @Override
    public int compareTo(Person other) {
        int nameComparison = this.name.compareToIgnoreCase(other.name);
        if (nameComparison != 0) {
            return nameComparison;
        }
        return this.email.compareToIgnoreCase(other.email);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two persons are considered equal if they have the same name and email (case-insensitive).
     *
     * @param obj the reference object with which to compare
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Person) {
            Person other = (Person) obj;
            return this.name.equalsIgnoreCase(other.name) &&
                   this.email.equalsIgnoreCase(other.email);
        }

        return false;
    }

    /**
     * Returns a hash code value for the person based on the name and email (both in lowercase).
     *
     * @return a hash code value for this person
     */
    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), email.toLowerCase());
    }

    /**
     * Returns a string representation of the person.
     *
     * @return the name of the person
     */
    @Override
    public String toString() {
        return name;
    }
}