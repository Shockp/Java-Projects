package RecyclingMachine.Residues.Containers.Bottles;

import RecyclingMachine.Residues.Containers.Bottle;

/**
 * Class representing a Small Bottle residue.
 * It extends the abstract {@link Bottle} class and represents a specific type of residue.
 */
public class SmallBottle extends Bottle {
	// Static counter to track the number of SmallBottle instances created
	private static int num = 0;
	private static final double PRICE = 0.15;

	/**
	 * Constructor to initialize the price of the SmallBottle.
	 * Increments the instance counter when a new SmallBottle is created.
	 * Price is fixed to 0.15
	 */
	public SmallBottle() {
		super(PRICE);
		num++;
	}

	/**
	 * Returns the number of SmallBottle instances created.
	 * 
	 * @return the number of SmallBottles instances
	 */
	public static int getNum() {
		return num;
	}

	/**
	 * Returns the price of the Small Bottle.
	 * 
	 * @return the price of the Small Bottle
	 */
	public double getPrice() { return PRICE; }

	/**
	 * Returns the type of residue, specifically "Small Bottle".
	 * 
	 * @return the type of residue as a string
	 */
	@Override
	public String getResidueType() {
		return "Small Bottle";
	}
}