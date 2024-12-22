package RecyclingMachine.Residues;

/**
 * Abstract class representing a residue.
 */
public abstract class Residue {
    // Attributes
    private double price;
    private double weight;

    /**
     * Constructor to initialize price and weight for a residue.
     * 
     * @param price the price of the residue
     * @param weight the weight of the residue
     */
    public Residue(double price, double weight) {
        setPrice(price);
        setWeight(weight);
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
    protected void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }

    /**
     * Sets the weight of the residue.
     * Ensures that the weight is not negative.
	 * This method is protected and can be used by subclasses to set the weight.
     * 
     * @param weight the weight of the residue
     * @throws IllegalArgumentException if the weight is negative
     */
    protected void setWeight(double weight) { 
        if (weight < 0) throw new IllegalArgumentException("Weight cannot be negative");
        this.weight = weight;
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
        return "Residue Type: " + getResidueType() + ", Price: " + price + ", Weight: " + weight;
    }
}