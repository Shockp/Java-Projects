package RecyclingMachine.Residues;

/**
 * Abstract class representing a container, which is a specific type of residue.
 * 
 * The container class allows setting and getting the price and weight of a container,
 * and can be further extended to repesent specific types of containers.
 * 
 * This class extends the {@link Residue} class and inherits its attributes like price and weight.
 */
public abstract class Container extends Residue {
	/**
     * Constructor to initialize a container with a price and weight.
     * 
     * @param price the price of the container
     * @param weight the weight of the container
     */
	public Container(double price, double weight) {
		super(price, weight);
	}
}