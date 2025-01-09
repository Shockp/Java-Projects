package ComputerShop.ShopCore;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import ComputerShop.Exceptions.IncorrectComputerException;
import ComputerShop.ShopCore.Computers.Computer;

public class Shop {
    private String name;
    private ArrayList<Computer> computers;
    private ArrayList<Computer> computersWithOffers;

    public Shop(String name) {
        checkName(name);
        computers = new ArrayList<>();
        computersWithOffers = new ArrayList<>();
    }

    private void checkName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("The name cannot be null.");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("The name cannot be blank.");
        }
        this.name = name;
    }

    public void addComputer(Computer computer) {
        if (computer == null) {
            throw new IllegalArgumentException ("The computer cannot be null.");
        }
        computers.add(computer);
    }

    public void putOffers() throws IncorrectComputerException {
        for (Computer computer : computers) {
            computer.putOffer();
            computersWithOffers.add(computer);
        }
    }
    public void putOffers(float discount) throws IncorrectComputerException {
        for (Computer computer : computers) {
            computer.putOffer(discount);
            computersWithOffers.add(computer);
        }
    }

    public void saveComputers() {
        String fileName = "orderedComputers.txt";

        ArrayList<Computer> sortedList = new ArrayList<>(computers);
        sortedList.sort(null);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Computer computer : sortedList) {
                writer.write(computer.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file " + fileName + "\nError: " + e.getMessage());
        }
    }

    public ArrayList<Computer> getComputersWithOffers() {
        return new ArrayList<>(computersWithOffers);
    }

    public Computer getComputer(float price) {
        float floorPrice = price - 5.0f;
        float ceilingPrice = price + 5.0f;
        
        for (Computer computer : computers) {
            if (computer.getFinalPrice() == floorPrice || computer.getFinalPrice() == ceilingPrice) {
                return computer;
            }
        }
        return null;
    }
}
