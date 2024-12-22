package RecyclingMachine.Residues;

/**
 * Class representing a Glass residue.
 * It extends the abstract {@link Residue} class and represents a specific type of residue.
 */
public class Glass extends Residue {
	// Static attribute to keep track of the number of Glass instances created
	private static int num = 0;

	/**
	 * Constructor to initialize price for a glass residue.
	 * 
	 * @param price the price of the glass residue
	 */
	public Glass(double price) {
		super(price);
		num++; // Increment the static num counter when a new instance is created
	}

	/**
	 * Returns the number of Glass instances created.
	 * 
	 * @return the number of Glass instances
	 */
	public static int getNum() { return num; }

	/**
	 * Abstract method implementation to get the type of residue, specifically "Glass".
	 * 
	 * @return the type of residue as a string
	 */
	@Override
	public String getResidueType() {
		return "Glass";
	}
}
