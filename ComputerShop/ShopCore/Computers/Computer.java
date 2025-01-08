package ComputerShop.ShopCore.Computers;

import ComputerShop.Exceptions.IncorrectComputerException;

public abstract class Computer implements Comparable<Computer> {
    private String brand;
    private float initialPrice;
    private float finalPrice;

    public Computer(String brand, float initialPrice) throws IncorrectComputerException {
        checkBrand(brand);
        checkInitialPrice(initialPrice);
        this.finalPrice = initialPrice;
    }

    private void checkBrand(String brand) throws IncorrectComputerException {
        if (brand == null) {
            throw new IncorrectComputerException("The brand cannot be null.");
        }
        if (brand.isBlank()) {
            throw new IncorrectComputerException("The brand cannot be blank.");
        }
        this.brand = brand;
    }

    public float getInitialPrice() {
        return initialPrice;
    }
    private void checkInitialPrice(float initialPrice) throws IncorrectComputerException {
        if (initialPrice < 0) {
            throw new IncorrectComputerException("The price cannot be negative.");
        }
        this.initialPrice = initialPrice;
    }

    public float getFinalPrice() {
        return finalPrice;
    }
    public void setFinalPrice(float discount) throws IncorrectComputerException {
        if (finalPrice < 0) {
            throw new IncorrectComputerException("The price cannot be negative");
        }
        this.finalPrice = initialPrice - (initialPrice * discount);
    }

    @Override
    public String toString() {
        return "Brand: " + this.brand + "Initial price: " + this.initialPrice + "Final price: " + this.finalPrice;
    }

    @Override
    public int compareTo(Computer computer) {
        return (int) Float.compare(this.getFinalPrice(), computer.getFinalPrice());
    }

    public abstract void putOffer() throws IncorrectComputerException;
    public abstract void putOffer(float discount) throws IncorrectComputerException;

    public void removeOffer() {
        this.finalPrice = this.initialPrice;
    }
}