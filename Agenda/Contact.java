package Agenda;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Represents a contact with name, phone number, email, and an identifier.
 */
public class Contact implements Serializable, Comparable<Contact> {
    private static final long serialVersionUID = 1L;

    private String name;
    private long phone;
    private String email;
    private int id = 0;
    public static int nContacts = (int) (Math.random() * 15001);

    /**
     * Constructs a Contact with name, phone, and email.
     *
     * @param name  the name of the contact
     * @param phone the phone number of the contact
     * @param email the email of the contact
     */
    public Contact(String name, long phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Constructs a Contact with name and phone.
     *
     * @param name  the name of the contact
     * @param phone the phone number of the contact
     */
    public Contact(String name, long phone) {
        this(name, phone, "");
    }

    /**
     * Constructs a Contact with name and email.
     *
     * @param name  the name of the contact
     * @param email the email of the contact
     */
    public Contact(String name, String email) {
        this(name, 0, email);
    }

    /**
     * Constructs a Contact with name only.
     *
     * @param name the name of the contact
     */
    public Contact(String name) {
        this(name, 0, "");
    }

    /**
     * Returns the ID of the contact.
     *
     * @return the contact ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the contact.
     *
     * @param id the contact ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the contact.
     *
     * @return the contact name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the contact.
     *
     * @param name the contact name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the phone number of the contact.
     *
     * @return the contact phone number
     */
    public long getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the contact.
     *
     * @param phone the contact phone number to set
     */
    public void setPhone(long phone) {
        this.phone = phone;
    }

    /**
     * Returns the email of the contact.
     *
     * @return the contact email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the contact.
     *
     * @param email the contact email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of the contact.
     *
     * @return string representation of the contact
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(id).append(" Name: ").append(name);
        if (phone != 0) {
            sb.append(" Phone: ").append(phone);
        }
        if (!email.isEmpty()) {
            sb.append(" Email: ").append(email);
        }
        return sb.toString();
    }

    /**
     * Compares this contact with the specified contact for order.
     *
     * @param other the contact to be compared
     * @return a negative integer, zero, or a positive integer as this contact
     *         is less than, equal to, or greater than the specified contact
     */
    @Override
    public int compareTo(Contact other) {
        return this.name.compareTo(other.getName());
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Contact contact = (Contact) obj;
        return name.equals(contact.getName());
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this contact
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Custom serialization logic.
     *
     * @param out the ObjectOutputStream to write to
     * @throws IOException if an I/O error occurs while writing stream header
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(nContacts);
    }

    /**
     * Custom deserialization logic.
     *
     * @param in the ObjectInputStream to read from
     * @throws IOException            if an I/O error occurs while reading stream header
     * @throws ClassNotFoundException if the class of a serialized object could not be found
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        nContacts = in.readInt();
    }
}