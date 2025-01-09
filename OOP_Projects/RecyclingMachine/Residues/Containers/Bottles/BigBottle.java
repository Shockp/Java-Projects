package RecyclingMachine.Residues.Containers.Bottles;

import RecyclingMachine.Residues.Containers.Bottle;

/**
 * Class representing a Big Bottle residue.
 * It extends the abstract {@link Bottle} class and represents a specific type of residue.
 */
public class BigBottle extends Bottle {
	private static int num = 0; // Static counter to track the number of BigBottle instances created
	private static final double PRICE = 0.20;

	/**
	 * Constructor to initialize the BigBottle.
	 * Increments the instance counter when a new BigBottle is created.
	 * Price is fixed to 0.20
	 */
	public BigBottle() {
		super(PRICE);
		num++;
	}

	/**
	 * Returns the number of BigBottle instances created.
	 * 
	 * @return the number of BigBottles instances
	 */
	public static int getNum() {
		return num;
	}

	/**
	 * Returns the price of the Big Bottle.
	 * 
	 * @return the price of the Big Bottle
	 */
	public double getPrice() { return PRICE; }

	/**
	 * Returns the type of residue, specifically "Big Bottle".
	 * 
	 * @return the type of residue as a string
	 */
	@Override
	public String getResidueType() {
		return "Big Bottle";
	}
}