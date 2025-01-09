package Agenda;

import Agenda.Exceptions.EContactNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents an agenda containing a set of contacts.
 */
public class Agenda implements Serializable {
    private static final long serialVersionUID = 1L;

    private Set<Contact> contacts;

    /**
     * Constructs an empty Agenda.
     */
    public Agenda() {
        contacts = new HashSet<>();
    }

    /**
     * Returns the set of contacts in the agenda.
     *
     * @return the set of contacts
     */
    public Set<Contact> getContacts() {
        return contacts;
    }

    /**
     * Sets the contacts in the agenda.
     *
     * @param contacts the set of contacts to set
     */
    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    /**
     * Returns the number of contacts in the agenda.
     *
     * @return the number of contacts
     */
    public int getNumberOfContacts() {
        return contacts.size();
    }

    /**
     * Returns a string representation of the agenda, listing all contacts.
     *
     * @return string representation of the agenda
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Contact c : contacts) {
            sb.append(c.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Displays the agenda details to the console.
     */
    public void displayAgenda() {
        System.out.println("--- Agenda ---");
        System.out.println("Number of contacts: " + getNumberOfContacts());
        System.out.println(toString());
        System.out.println("---------------");
    }

    /**
     * Adds a contact to the agenda.
     *
     * @param contact the contact to add
     * @return true if the contact was added successfully, false if it already exists
     */
    public boolean addContact(Contact contact) {
        boolean added = contacts.add(contact);
        if (added) {
            contact.setId(++Contact.nContacts);
        }
        return added;
    }

    /**
     * Adds a contact to the agenda with the specified name, phone, and email.
     *
     * @param name  the name of the contact
     * @param phone the phone number of the contact
     * @param email the email of the contact
     * @return true if the contact was added successfully, false if it already exists
     */
    public boolean addContact(String name, long phone, String email) {
        Contact contact = new Contact(name, phone, email);
        return addContact(contact);
    }

    /**
     * Finds the ID of a contact by name.
     *
     * @param name the name of the contact to find
     * @return the ID of the contact
     * @throws EContactNotFoundException if the contact is not found
     */
    public int findContactIndex(String name) throws EContactNotFoundException {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                return contact.getId();
            }
        }
        throw new EContactNotFoundException("Contact not found");
    }

    /**
     * Retrieves a contact by its ID.
     *
     * @param id the ID of the contact to retrieve
     * @return the Contact object with the specified ID
     * @throws EContactNotFoundException if the contact is not found
     */
    public Contact getContact(int id) throws EContactNotFoundException {
        for (Contact contact : contacts) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        throw new EContactNotFoundException("Contact not found");
    }

    /**
     * Deletes a contact from the agenda.
     *
     * @param contact the contact to delete
     * @return true if the contact was removed successfully, false otherwise
     * @throws EContactNotFoundException if the contact is not found
     */
    public boolean deleteContact(Contact contact) throws EContactNotFoundException {
        boolean removed = contacts.remove(contact);
        if (removed) {
            for (Contact c : contacts) {
                if (c.getId() > contact.getId()) {
                    c.setId(c.getId() - 1);
                }
            }
            Contact.nContacts--;
        } else {
            throw new EContactNotFoundException("Contact not found");
        }
        return removed;
    }

    /**
     * Serializes the agenda to a file.
     *
     * @param file the file to which the agenda is serialized
     * @throws IOException if an I/O error occurs during serialization
     */
    public void writeAgenda(File file) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(this);
        }
    }

    /**
     * Deserializes the agenda from a file.
     *
     * @param file the file from which the agenda is deserialized
     * @throws IOException            if an I/O error occurs during deserialization
     * @throws ClassNotFoundException if the class of a serialized object cannot be found
     */
    public void readAgenda(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Agenda loaded = (Agenda) in.readObject();
            this.contacts = loaded.getContacts();
        }
    }
}