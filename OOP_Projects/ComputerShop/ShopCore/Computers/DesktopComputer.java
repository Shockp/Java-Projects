package ComputerShop.ShopCore.Computers;

import ComputerShop.Exceptions.IncorrectComputerException;
import ComputerShop.ShopCore.CPU;

public class DesktopComputer extends Computer {
    private static final float DISCOUNT = 0.15f;
    private CPU cpu;

    public DesktopComputer(String brand, float initialPrice, CPU cpu) throws IncorrectComputerException {
        super(brand, initialPrice);
        checkCPU(cpu);
    }

    private void checkCPU(CPU cpu) {
        if (cpu == null) {
            throw new IllegalArgumentException("The CPU cannot be null.");
        }
        this.cpu = cpu;
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
        return super.toString() + "CPU: " + this.cpu;
    }
}
