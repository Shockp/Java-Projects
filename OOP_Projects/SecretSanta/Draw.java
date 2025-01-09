package SecretSanta;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import SecretSanta.Exceptions.*;

/**
 * Manages the Secret Santa draw process, including participants, restrictions, and result generation.
 * <p>
 * Allows adding participants individually or by reading from a file, setting restrictions
 * between participants, performing the draw while respecting all constraints, and exporting
 * the results to an output file.
 * </p>
 */
public class Draw {
    /**
     * Static counter to assign unique draw codes.
     */
    private static int numberOfDraws = 0;
    
    private final int DRAW_CODE;
    private String name;
    private HashSet<Person> participants;
    private ArrayList<Couple> coupleRestrictions;
    private ArrayList<Group> groupRestrictions;
    private HashMap<Person, Person> result;
    private HashMap<Person, String> participantCodes;

    /**
     * Constructs a new {@code Draw} with the specified name and an automatic numeric code.
     *
     * @param name the name of the draw, must not be {@code null} or blank
     * @throws IllegalArgumentException if {@code name} is {@code null} or blank
     */
    public Draw(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Draw name must not be null or blank.");
        }
        this.DRAW_CODE = ++numberOfDraws;
        this.name = name;
        this.participants = new HashSet<>();
        this.coupleRestrictions = new ArrayList<>();
        this.groupRestrictions = new ArrayList<>();
        this.result = new HashMap<>();
        this.participantCodes = new HashMap<>();
    }

    /**
     * Adds a participant to the draw.
     *
     * @param person the {@link Person} to add, must not be {@code null}
     * @throws IllegalArgumentException if {@code person} is {@code null}
     */
    public void addParticipant(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Participant cannot be null.");
        }
        if (participants.contains(person)) {
            System.err.println("Participant already exists: " + person.getName());
            return;
        }
        participants.add(person);
        assignParticipantCode(person); // Assigns a unique code to the participant
    }

    /**
     * Reads participants from a file and adds them to the draw.
     * <p>
     * The file should contain one participant per line, with the name and email separated by '#'.
     * Example line: "Alvaro#alvaro@alumnos.urjc.es"
     * </p>
     *
     * @param filePath the path to the input file, must not be {@code null} or blank
     * @throws IOException               if an I/O error occurs while reading the file
     * @throws EIncorrectPerson        if a participant has invalid attributes
     * @throws IllegalArgumentException  if {@code filePath} is {@code null} or blank
     */
    public void readData(String filePath) throws IOException, EIncorrectPerson {
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("File path must not be null or blank.");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] parts = line.split("#");
                if (parts.length != 2) {
                    System.err.println("Invalid line format at line " + lineNumber + " (expected 'Name#Email'): " + line);
                    continue;
                }
                String name = parts[0].trim();
                String email = parts[1].trim();
                if (name.isEmpty() || email.isEmpty()) {
                    System.err.println("Empty name or email at line " + lineNumber + ": " + line);
                    continue;
                }
                Person person = new Person(name, email);
                addParticipant(person);
            }
        }
    }

    /**
     * Adds a restricted couple to the draw, preventing them from gifting each other.
     *
     * @param couple the {@link Couple} to restrict, must not be {@code null}
     * @throws IllegalArgumentException if {@code couple} is {@code null} or any member is not a participant
     */
    public void addRestriction(Couple couple) {
        if (couple == null) {
            throw new IllegalArgumentException("Restriction couple cannot be null.");
        }
        if (!participants.contains(couple.getGiver()) || !participants.contains(couple.getReceiver())) {
            throw new IllegalArgumentException("Both persons in the couple must be participants.");
        }
        if (!coupleRestrictions.contains(couple)) {
            coupleRestrictions.add(couple);
        }
    }

    /**
     * Adds a restricted group to the draw, preventing group members from gifting each other.
     *
     * @param group the {@link Group} to restrict, must not be {@code null}
     * @throws IllegalArgumentException if {@code group} is {@code null} or contains persons not in participants
     */
    public void addRestriction(Group group) {
        if (group == null) {
            throw new IllegalArgumentException("Restriction group cannot be null.");
        }
        for (Person member : group.getPeople()) {
            if (!participants.contains(member)) {
                throw new IllegalArgumentException("All group members must be participants.");
            }
        }
        if (!groupRestrictions.contains(group)) {
            groupRestrictions.add(group);
        }
    }

    /**
     * Performs the Secret Santa draw, assigning each participant a unique recipient
     * while respecting all restrictions.
     *
     * @throws IllegalStateException if the draw cannot be completed due to constraints
     */
    public void doDraw() {
        if (participants.size() < 2) {
            throw new IllegalStateException("At least two participants are required to perform a draw.");
        }

        // Reset previous results if any
        result.clear();

        // Create lists of givers and receivers
        List<Person> givers = new ArrayList<>(participants);
        List<Person> receivers = new ArrayList<>(participants);

        // Shuffle receivers to randomize assignments
        Collections.shuffle(receivers);

        // Attempt to find a valid assignment with a maximum number of attempts
        int maxAttempts = 10000;
        boolean valid = false;

        for (int attempt = 1; attempt <= maxAttempts && !valid; attempt++) {
            Collections.shuffle(receivers);
            valid = true;
            result.clear();

            for (int i = 0; i < givers.size(); i++) {
                Person giver = givers.get(i);
                Person receiver = receivers.get(i);

                // Prevent gifting to oneself
                if (giver.equals(receiver)) {
                    valid = false;
                    break;
                }

                // Check if this pair is restricted
                if (isRestricted(giver, receiver)) {
                    valid = false;
                    break;
                }

                result.put(giver, receiver);
            }
        }

        if (!valid) {
            throw new IllegalStateException("Failed to perform the draw after " + maxAttempts + " attempts. Please check the restrictions.");
        }
    }

    /**
     * Writes the results of the draw to an output file named "output.txt".
     * <p>
     * The format of each line is: "P01 GIVE TO P06"
     * </p>
     *
     * @throws IOException              if an I/O error occurs while writing to the file
     * @throws IllegalStateException    if the draw has not been performed yet
     */
    public void writeOrderedList() throws IOException {
        if (result.isEmpty()) {
            throw new IllegalStateException("No draw results to write. Please perform the draw first.");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            for (Map.Entry<Person, Person> entry : result.entrySet()) {
                String giverCode = participantCodes.get(entry.getKey());
                String receiverCode = participantCodes.get(entry.getValue());
                bw.write(giverCode + " GIVE TO " + receiverCode);
                bw.newLine();
            }
        }
    }

    /**
     * Returns a string representation of the draw, including its name, code,
     * participants, restrictions, and results.
     *
     * @return a string representing the draw
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Draw Name: ").append(name).append("\n");
        sb.append("Draw Code: ").append(String.format("D%03d", DRAW_CODE)).append("\n\n");

        sb.append("Participants:\n");
        for (Person p : participants) {
            sb.append(participantCodes.get(p)).append(" - ").append(p.getName())
              .append(" (").append(p.getEmail()).append(")\n");
        }

        sb.append("\nRestrictions (Couples):\n");
        if (coupleRestrictions.isEmpty()) {
            sb.append("None\n");
        } else {
            for (Couple c : coupleRestrictions) {
                sb.append(c.getGiver().getName()).append(" cannot gift to ").append(c.getReceiver().getName()).append("\n");
            }
        }

        sb.append("\nRestrictions (Groups):\n");
        if (groupRestrictions.isEmpty()) {
            sb.append("None\n");
        } else {
            for (Group g : groupRestrictions) {
                sb.append("Group: ");
                List<String> memberNames = new ArrayList<>();
                for (Person p : g.getPeople()) {
                    memberNames.add(p.getName());
                }
                sb.append(String.join(", ", memberNames)).append("\n");
            }
        }

        sb.append("\nDraw Results:\n");
        if (result.isEmpty()) {
            sb.append("Draw not performed yet.\n");
        } else {
            for (Map.Entry<Person, Person> entry : result.entrySet()) {
                sb.append(participantCodes.get(entry.getKey()))
                  .append(" GIVE TO ")
                  .append(participantCodes.get(entry.getValue()))
                  .append("\n");
            }
        }

        return sb.toString();
    }

    /**
     * Assigns a unique code to a participant in the format "PXX", where "XX" is a zero-padded number.
     *
     * @param person the {@link Person} to assign a code, must not be {@code null}
     */
    private void assignParticipantCode(Person person) {
        if (participantCodes.containsKey(person)) {
            return; // Code already assigned
        }
        String code = String.format("P%02d", participantCodes.size() + 1);
        participantCodes.put(person, code);
    }

    /**
     * Checks if a given pair (giver and receiver) is restricted from gifting.
     *
     * @param giver    the {@link Person} who is giving, must not be {@code null}
     * @param receiver the {@link Person} who is receiving, must not be {@code null}
     * @return {@code true} if the pair is restricted; {@code false} otherwise
     */
    private boolean isRestricted(Person giver, Person receiver) {
        // Check couple restrictions
        for (Couple c : coupleRestrictions) {
            if ((c.getGiver().equals(giver) && c.getReceiver().equals(receiver)) ||
                (c.getReceiver().equals(giver) && c.getGiver().equals(receiver))) {
                return true;
            }
        }

        // Check group restrictions
        for (Group g : groupRestrictions) {
            if (g.getPeople().contains(giver) && g.getPeople().contains(receiver)) {
                return true;
            }
        }

        return false;
    }
}