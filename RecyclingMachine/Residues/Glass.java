package RecyclingMachine.Residues;

/**
 * Class representing a Glass residue.
 * It extends the abstract {@link Residue} class and represents a specific type of residue.
 */
public class Glass extends Residue {
	// Static attribute to keep track of the number of Glass instances created
	private static int num = 0;

	/**
	 * Constructor to initialize price and weight for a glass residue.
	 * 
	 * @param price the price of the glass residue
	 * @param weight the weight of the glass residue
	 */
	public Glass(double price, double weight) {
		super(price, weight);
		num++; // Increment the static num counter when a new instance is created
	}

	/**
	 * Returns the number of Glass instances created.
	 * 
	 * @return the number of Glass instances
	 */
	public int getNum() { return num; }

	/**
	 * Abstract method implementation to get the type of residue, specifically "Glass".
	 * 
	 * @return the type of residue as a string
	 */
	@Override
	public String getResidueType() {
		return "Glass";
	}

	/**
	 * Returns a string representation of the Glass residue, including type, price, and weight.
	 * 
	 * @return a string representing the Glass residue
	 */
	@Override
	public String toString() {
		return "Residue Type: " + getResidueType() + ", Price: " + getPrice() + ", Weight: " + getWeight();
	}
}
