package RecyclingMachine.UserInterface;

import java.util.ArrayList;

import RecyclingMachine.Residues.Residue;
import RecyclingMachine.Residues.Containers.Can;

/**
 * Class to represent a user for the recycling machine.
 */
public class User {
	// Attributes
	private String name;
	private int number;
	private double balance;
	private ArrayList<Residue> residues;

    /**
     * Constructor to initialize a user with a name and number.
     * The balance is set to 0.
     * And the residues are initialized as an empty list.
     * 
     * @param name the name of the user
     * @param number the number of the user
     */
    public User(String name, int number) {
        setName(name);
        setNumber(number);
        setBalance(0);
        residues = new ArrayList<Residue>();
    }

    /**
     * Returns the name of the user.
     * 
     * @return name of the user
     */
	public String getName() { return name; }

    /**
     * Returns the number of the user.
     * 
     * @return number of the user
     */
	public int getNumber() { return number; }

    /**
     * Returns the balance of the user.
     * 
     * @return balance of the user
     */
	public double getBalance() { return balance; }

    /**
     * Returns the residues of the user.
     * 
     * @return residues of the user
     */
	public ArrayList<Residue> getResidues() { return residues; }

	/**
     * Sets the name of the user.
     * Ensures that the name is not null or empty.
     * 
     * @param name the name of the user
     */
    public void setName(String name) { 
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    /**
     * Sets the number of the user.
     * Ensures that the number is not negative.
     * 
     * @param number the number of the user
     */
    public void setNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number cannot be negative");
        }
        this.number = number;
    }

    /**
     * Sets the balance of the user.
     * Ensures that the balance is not negative.
     * 
     * @param balance the balance of the user
     */
    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = balance;
    }

    /**
     * Adds a residue to the user's list of residues.
     * 
     * @param residue the residue to add
     */
    public void addResidue(Residue residue) {
        residues.add(residue);
        this.setBalance(this.getBalance() + residue.getPrice());
    }

    /**
     * Removes a residue from the user's list of residues.
     * 
     * @param residue the residue to remove
     */
    public void removeResidue(Residue residue) {
        residues.remove(residue);
        this.setBalance(this.getBalance() - residue.getPrice());
    }

    /**
     * Clear the user's list of residues.
     */
    public void clearResidues() {
        residues.clear();
    }

    /**
     * Print the total amount of money the user has recycled.
     */
    public void printTotalRecycled() {
        int total = 0;
        for (Residue residue : residues) {
            total += residue.getPrice();
        }

        System.out.println("Total recycled: " + total);
    }

    /**
     * Print the tags of all Can residues that the user has recycled.
     */
    public void printCanTagsRecycled() {
        for (Residue residue : residues) {
            if (residue instanceof Can) {
                Can can = (Can)residue;
                System.out.println("Tag: " + can.getTag());
            }
        }
    }

    /**
     * Return a boolean indicating whether the user is equal to another object.
     * Two users are equal if they have the same number.
     * 
     * @param o the object to compare
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        User u = (User) o;
        return this.getNumber() == u.getNumber();
    }
}