package SecretSanta;

import java.io.*;

import SecretSanta.Exceptions.*;

// Code given by the statement

public class Main {
    public static final String FINPUT = "data.txt";

    public static void main(String[] args) {
        try {
            Draw draw = new Draw("My first draw");
            draw.readData(FINPUT); // Reads participants from "data.txt"

            // Create participants manually
            Person p1 = new Person("P01", "p1@alumnos.urjc.es");
            Person p2 = new Person("P02", "p2@alumnos.urjc.es");
            Person p3 = new Person("P03", "p3@alumnos.urjc.es");
            Person p4 = new Person("P04", "p4@alumnos.urjc.es");
            Person p5 = new Person("P05", "p5@alumnos.urjc.es");
            Person p6 = new Person("P06", "p6@alumnos.urjc.es");
            Person p7 = new Person("P07", "p7@alumnos.urjc.es");
            Person p8 = new Person("P08", "p8@alumnos.urjc.es");

            // Add participants to the draw
            draw.addParticipant(p1);
            draw.addParticipant(p2);
            draw.addParticipant(p3);
            draw.addParticipant(p4);
            draw.addParticipant(p5);
            draw.addParticipant(p6);
            draw.addParticipant(p7);
            draw.addParticipant(p8);

            // Define restrictions
            Couple c1 = new Couple(p1, p3); // P01 cannot gift to P03

            Group g = new Group();
            g.addPerson(p2);
            g.addPerson(p7);
            g.addPerson(p8); // P02, P07, P08 cannot gift among themselves

            // Add restrictions to the draw
            draw.addRestriction(c1);
            draw.addRestriction(g);

            // Perform the draw
            draw.doDraw();

            // Optionally, write results to "output.txt"
            draw.writeOrderedList();

            // Print draw details to console
            System.out.println(draw);
        } catch (EIncorrectPerson e) {
            System.err.println("Invalid participant: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading or writing files: " + e.getMessage());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Error during draw: " + e.getMessage());
        }
    }
}