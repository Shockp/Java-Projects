package RecyclingMachine.Tests.ResiduesTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import RecyclingMachine.Residues.Residue;

class ResidueTest {
    private static class TestResidue extends Residue {
        public TestResidue(double price) {
            super(price);
        }

        @Override
        public String getResidueType() {
            return "TestResidue";
        }
    }

    @Test
    void testConstructor() {
        Residue residue = new TestResidue(10.0);
        assertEquals(10.0, residue.getPrice());
        assertTrue(residue.getWeight() >= 0 && residue.getWeight() <= 1);
    }

    @Test
    void testGetPrice() {
        Residue residue = new TestResidue(15.0);
        assertEquals(15.0, residue.getPrice());
    }

    @Test
    void testGetWeight() {
        Residue residue = new TestResidue(20.0);
        assertTrue(residue.getWeight() >= 0 && residue.getWeight() <= 1);
    }

    @Test
    void testSetPriceValid() {
        Residue residue = new TestResidue(25.0);
        residue.setPrice(30.0);
        assertEquals(30.0, residue.getPrice());
    }

    @Test
    void testSetPriceInvalid() {
        Residue residue = new TestResidue(35.0);
        assertThrows(IllegalArgumentException.class, () -> {
            residue.setPrice(-5.0);
        });
    }

    @Test
    void testToString() {
        Residue residue = new TestResidue(40.0);
        assertEquals("TestResidue", residue.toString());
    }
}