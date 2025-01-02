package Agenda;

import java.io.*;

public class Contact implements Serializable, Comparable {
    private String name;
    private long phone;
    private String email;
    private int id = 0;
    public static int nContacts = (int) (Math.random() * 15001);

    // Constructor with name, phone and email
    public Contact(String name, long phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Constructor with name and phone
    public Contact(String name, long phone) {
        this(name, phone, "");
    }

    // Constructor with name and email
    public Contact(String name, String email) {
        this(name, 0, email);
    }

    // Constructor with name only
    public Contact(String name) {
        this(name, 0, "");
    }

    // Getter ans setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter for phone
    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    // Getter and setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Converts object to a string
    public String toString() {
        String s;
        s = "Id: " + id + " Name: " + name;
        if (phone != 0) s = s + " Phone: " + phone;
        if (!email.equals("")) s = s + " Email: " + email;
        return s;
    }

    // Equals method to compare two objects
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Contact c = (Contact) o;
        return this.name.equals(c.getName());
    }

    // HashCode method
    public int hashCode() {
        return this.getName().hashCode();
    }

    // CompareTo method for sorting
    public int compareTo(Object o) {
        Contact c = (Contact) o;
        return this.getName().compareTo(c.getName());
    }

    // Custom serialization and deserialization
    private void writeObject(ObjectOutputStream stream) throws Exception {
        stream.defaultWriteObject();
        stream.writeObject(nContacts);
    }

    private void readObject(ObjectInputStream stream) throws Exception {
        stream.defaultReadObject();
        nContacts = (int) stream.readObject();
    }
}