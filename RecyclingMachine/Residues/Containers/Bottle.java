package RecyclingMachine.Residues.Containers;

import RecyclingMachine.Residues.Container;

/**
 * Abstract class representing a bottle, which is a type of container.
 * A bottle is a specific kind of container and extends the {@link Container} class.
 */
public abstract class Bottle extends Container {
	/**
	 * Constructor to initialize a bottle with a price and weight.
	 * 
	 * @param price the price of the bottle
	 * @param weight the weight of the bottle
	 */
	public Bottle(double price) {
		super(price);
	}

	/**
	 * Abstract method to return the residue type of the bottle.
	 * The residue type will be specific to this class.
	 * 
	 * @return the type of residue for the bottle
	 */
	@Override
	public abstract String getResidueType();
}
