package Agenda;

import java.io.*;
import java.util.*;

import Agenda.Exceptions.EContactNotFoundException;

public class Agenda {
    private HashSet<Contact> contacts;

    public Agenda() {
        contacts = new HashSet<>();
    }

    public HashSet<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(HashSet<Contact> contacts) {
        this.contacts = contacts;
    }

    public int getNumberOfContacts() {
        return contacts.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Contact c : contacts) {
            sb.append(c.toString()).append("\n");
        }

        return sb.toString();
    }

    public void displayAgenda() {
        System.out.println("--- Agenda ---");
        System.out.println("Number of contacts: " + getNumberOfContacts());
        System.out.println(toString());
        System.out.println("---------------");
    }

    public boolean addContact(Contact c) {
        boolean added = contacts.add(c);
        if (added) {
            c.setId(++Contact.nContacts);
        }

        return added;
    }

    public boolean addContact(String name, long phone, String email) {
        Contact c = new Contact(name, phone, email);
        return addContact(c);
    }

    // Find index of a contact by name
    public int findContactIndex(String name) throws EContactNotFoundException {
        boolean found = false;
        int index = 0;
        Iterator<Contact> it = getContacts().iterator();

        while (it.hasNext() && !found) {
            Contact c = it.next();
            if (c.getName().equals(name)) {
                found = true;
                index = c.getId();
            }
        }

        if (found) return index;
        else throw new EContactNotFoundException("Contact not found");
    }

    // Get a contact by ID
    public Contact getContact(int id) throws EContactNotFoundException {
        for (Contact c : contacts) {
            if (c.getId() == id) return c;
        }

        throw new EContactNotFoundException("Contact not found");
    }

    // Delte a contact
    public boolean deleteContact(Contact c) throws EContactNotFoundException{
        boolean removed = contacts.remove(c);
        if (removed) {
            for (Contact contact : contacts) {
                if (contact.getId() > c.getId()) {
                    contact.setId(contact.getId() - 1);
                }
            }
            Contact.nContacts--;
        }
        return removed;
    }

    // Serialize the agenda to a file
    public void writeAgenda(File file) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(this);
        }
    }

    public void readAgenda(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Agenda loaded = (Agenda) in.readObject();
            this.contacts = loaded.getContacts();
        }
    }
}