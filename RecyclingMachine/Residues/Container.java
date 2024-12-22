package RecyclingMachine.Residues;

/**
 * Abstract class representing a container, which is a specific type of residue.
 * 
 * The container class allows getting the price and weight of a container and setting the price,
 * and can be further extended to repesent specific types of containers.
 * 
 * This class extends the {@link Residue} class and inherits its attributes like price and weight.
 */
public abstract class Container extends Residue {
	/**
     * Constructor to initialize a container with a price.
     * 
     * @param price the price of the container
     */
	public Container(double price) {
		super(price);
	}
}