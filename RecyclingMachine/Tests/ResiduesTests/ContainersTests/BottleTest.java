package RecyclingMachine.Tests.ResiduesTests.ContainersTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import RecyclingMachine.Residues.Containers.Bottle;

class BottleTest {

    // Concrete subclass of Bottle for testing purposes
    private static class TestBottle extends Bottle {
        public TestBottle(double price) {
            super(price);
        }

        @Override
        public String getResidueType() {
            return "TestBottle";
        }
    }

    @Test
    void testConstructor() {
        Bottle bottle = new TestBottle(10.0);
        assertEquals(10.0, bottle.getPrice());
        assertTrue(bottle.getWeight() >= 0 && bottle.getWeight() <= 1);
    }

    @Test
    void testGetPrice() {
        Bottle bottle = new TestBottle(15.0);
        assertEquals(15.0, bottle.getPrice());
    }

    @Test
    void testGetWeight() {
        Bottle bottle = new TestBottle(20.0);
        assertTrue(bottle.getWeight() >= 0 && bottle.getWeight() <= 1);
    }

    @Test
    void testSetPriceValid() {
        Bottle bottle = new TestBottle(25.0);
        bottle.setPrice(30.0);
        assertEquals(30.0, bottle.getPrice());
    }

    @Test
    void testSetPriceInvalid() {
        Bottle bottle = new TestBottle(35.0);
        assertThrows(IllegalArgumentException.class, () -> {
            bottle.setPrice(-5.0);
        });
    }

    @Test
    void testGetResidueType() {
        Bottle bottle = new TestBottle(40.0);
        assertEquals("TestBottle", bottle.getResidueType());
    }
}