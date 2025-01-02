package Agenda;

import java.util.Comparator;

/**
 * Comparator implementation for comparing Contact objects based on their phone numbers.
 */
public class ComparePhone implements Comparator<Contact> {

    /**
     * Compares two Contact objects based on their phone numbers.
     *
     * @param c1 the first Contact to compare
     * @param c2 the second Contact to compare
     * @return a negative integer, zero, or a positive integer as the first Contact's phone
     *         number is less than, equal to, or greater than the second Contact's phone number
     * @throws ClassCastException if the arguments' types prevent them from being compared
     */
    @Override
    public int compare(Contact c1, Contact c2) throws ClassCastException {
        if (c1 == null || c2 == null) {
            throw new ClassCastException("Cannot compare null Contact objects.");
        }

        long phone1 = c1.getPhone();
        long phone2 = c2.getPhone();

        return Long.compare(phone1, phone2);
    }
}