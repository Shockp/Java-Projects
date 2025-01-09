package SecretSanta;

// Code given by the statement

import java.util.HashSet;

public class Group {
    private HashSet<Person> people;

    public Group() {
        people = new HashSet<>();
    }

    public HashSet<Person> getPeople() { return people; }

    public boolean addPerson(Person person) {
        return people.add(person);
    }
}