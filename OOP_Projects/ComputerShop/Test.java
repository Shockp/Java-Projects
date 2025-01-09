package ComputerShop;

import ComputerShop.Exceptions.IncorrectComputerException;
import ComputerShop.ShopCore.*;
import ComputerShop.ShopCore.Computers.*;

public class Test {
    public static void main(String[] args) {
        try {
            // Create a Shop
            Shop myShop = new Shop("Tech Haven");

            // Create Computers
            DesktopComputer desktop1 = new DesktopComputer("Dell", 1200, CPU.INTEL);
            DesktopComputer desktop2 = new DesktopComputer("HP", 950, CPU.AMD);
            Laptop laptop1 = new Laptop("Asus", 800, 15.6f);
            Laptop laptop2 = new Laptop("Lenovo", 750, 14.0f);

            // Add Computers to the Shop
            myShop.addComputer(desktop1);
            myShop.addComputer(desktop2);
            myShop.addComputer(laptop1);
            myShop.addComputer(laptop2);

            // Apply offers
            myShop.putOffers();

            // Apply custom discount
            myShop.putOffers(0.2f); // 20% discount

            // Save computers to a file
            myShop.saveComputers();

            // Retrieve a computer within a price range
            Computer foundComputer = myShop.getComputer(760); // Searching for a computer near $760
            if (foundComputer != null) {
                System.out.println("Found computer: " + foundComputer);
            } else {
                System.out.println("No computer found in the specified price range.");
            }

            // Display computers with offers
            System.out.println("Computers with offers:");
            for (Computer computer : myShop.getComputersWithOffers()) {
                System.out.println(computer);
            }

        } catch (IncorrectComputerException e) {
            System.err.println("Error creating or processing a computer: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}