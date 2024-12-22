package RecyclingMachine.Residues;

/**
 * Class representing a Glass residue.
 * It extends the abstract {@link Residue} class and represents a specific type of residue.
 */
public class Glass extends Residue {
	// Static attribute to keep track of the number of Glass instances created
	private static int num = 0;
	private static final double PRICE = 0.30; // Fixed price for Glass residue

	/**
	 * Constructor to initialize a glass residue.
	 * The price is fixed to 0.30 for all Glass instances
	 */
	public Glass() {
		super(PRICE);
		num++; // Increment the static num counter when a new instance is created
	}

	/**
	 * Returns the number of Glass instances created.
	 * 
	 * @return the number of Glass instances
	 */
	public static int getNum() { return num; }

	/**
	 * Returns the price of the Glass.
	 * 
	 * @return the price of the Glass
	 */
	public double getPrice() { return PRICE; }

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
