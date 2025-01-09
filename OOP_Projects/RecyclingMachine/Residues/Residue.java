package RecyclingMachine.Residues;

import java.util.Random;

/**
 * Abstract class representing a residue.
 */
public abstract class Residue {
    // Attributes
    private double price;
    private double weight;

	// Static Random instance for consistent random weight generation
	private static final Random RANDOM = new Random();

    /**
     * Constructor to initialize price for a residue.
	 * The weight is randomly assigned between 0 and 1.
     * 
     * @param price the price of the residue
     */
    public Residue(double price) {
        setPrice(price);
        setWeight();
    }

    /**
     * Returns the price of the residue.
     * 
     * @return the price of the residue
     */
    public double getPrice() { return price; }

	/**
	 * Returns the weight of the residue.
	 * 
	 * @return the weight of the residue
	 */
    public double getWeight() { return weight; }

	/**
	 * Sets the price of the residue.
	 * Ensures that the price is not negative.
	 * This method is protected and can be used by subclasses to set the price.
	 * 
	 * @param price the price of the residue
	 * @throws IllegalArgumentException if the price is negative
	 */
    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }

    /**
	 * Sets the weight of the residue to a random value between 0 and 1.
	 */
    private void setWeight() { 
        this.weight = RANDOM.nextDouble();
    }

    /**
     * Abstract method to get the type of residue.
     * 
     * @return the type of residue as a string
     */
    public abstract String getResidueType();

    /**
     * Returns a string representation of the residue, including type, price, and weight.
     * 
     * @return a string representing the residue
     */
    @Override
    public String toString() {
        return getResidueType();
    }
}