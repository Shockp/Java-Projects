package ComputerShop.ShopCore.Computers;

import ComputerShop.Exceptions.IncorrectComputerException;

public class Laptop extends Computer {
    private static final float DISCOUNT = 0.15f;
    private float screenSize;

    public Laptop(String brand, float initialPrice, float screenSize) throws IncorrectComputerException {
        super(brand, initialPrice);
        checkScreenSize(screenSize);
    }

    private void checkScreenSize(float screenSize) {
        if (screenSize < 0) {
            throw new IllegalArgumentException("The screen cannot be negative.");
        }
        this.screenSize = screenSize;
    }

    private void checkDiscount(float discount) {
        if (discount < 0 || discount > 1) {
            throw new IllegalArgumentException("The discount must be a number between 0 and 1");
        }
    }

    @Override
    public void putOffer() throws IncorrectComputerException {
        checkDiscount(DISCOUNT);
        this.setFinalPrice(DISCOUNT);
    }
    @Override
    public void putOffer(float discount) throws IncorrectComputerException {
        checkDiscount(discount);
        this.setFinalPrice(discount);
    }

    @Override
    public String toString() {
        return super.toString() + "Screen Size: " + this.screenSize;
    }
}
