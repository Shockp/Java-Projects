package RecyclingMachine.Tests.ResiduesTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import RecyclingMachine.Residues.Container;

class ContainerTest {

    // Concrete subclass of Container for testing purposes
    private static class TestContainer extends Container {
        public TestContainer(double price) {
            super(price);
        }

        @Override
        public String getResidueType() {
            return "TestContainer";
        }
    }

    @Test
    void testConstructor() {
        Container container = new TestContainer(10.0);
        assertEquals(10.0, container.getPrice());
        assertTrue(container.getWeight() >= 0 && container.getWeight() <= 1);
    }

    @Test
    void testGetPrice() {
        Container container = new TestContainer(15.0);
        assertEquals(15.0, container.getPrice());
    }

    @Test
    void testGetWeight() {
        Container container = new TestContainer(20.0);
        assertTrue(container.getWeight() >= 0 && container.getWeight() <= 1);
    }

    @Test
    void testSetPriceValid() {
        Container container = new TestContainer(25.0);
        container.setPrice(30.0);
        assertEquals(30.0, container.getPrice());
    }

    @Test
    void testSetPriceInvalid() {
        Container container = new TestContainer(35.0);
        assertThrows(IllegalArgumentException.class, () -> {
            container.setPrice(-5.0);
        });
    }
}