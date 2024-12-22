package RecyclingMachine.Residues.Containers;

import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import RecyclingMachine.Residues.Container;

/**
 * Class representing a Can residue.
 * A Can is a type of Container.
 */
public class Can extends Container {
	// Attributes
	private static int num = 0;
	private String tag;

	// Static Random instance for consistent random date generation
	private static final Random RANDOM = new Random();

	/**
	 * Constructor to initialize the price of the Can.
	 * Generates a random tag for the Can and increments the instance counter.
	 * 
	 * @param price the price of the Can
	 */
	public Can(double price) {
		super(price);
		setTag();
		num++;
	}

	/**
	 * Returns the number of Can instances created
	 * 
	 * @return the number of Can instances
	 */
	public static int getNum() { return num; }

	/**
	 * Returns the unique tag of the Can.
	 * 
	 * @return the tag of the can
	 */
	public String getTag() { return tag; }

	/**
	 * Sets the tag of the Can using a random expiration date.
	 */
	private void setTag() {
		this.tag = generateRandomDate();
	}

	/**
	 * Generates a random expiration date in the format "yyyy-MM-dd".
	 * The date ranges between the years 2024 and 2050.
	 * 
	 * @return a string representing a random expiration date
	 */
	private String generateRandomDate() {
		// Define the start and end year for the random date
		int startYear = 2024;
		int endYear = 2050;

		// Generate random year, month and day
		int year = startYear + RANDOM.nextInt(endYear - startYear + 1);
		int month = RANDOM.nextInt(12) + 1;
		int day = RANDOM.nextInt(LocalDate.of(year, month, 1).lengthOfMonth()) + 1;

		// Format the date as a string
		LocalDate date = LocalDate.of(year, month, day);
		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	@Override
	public String getResidueType() {
		return "Can";
	}
}