package RecyclingMachine.Tests.ResiduesTests.ContainersTests.BottlesTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import RecyclingMachine.Residues.Containers.Bottles.BigBottle;

class BigBottleTest {

    @Test
    void testConstructor() {
        int initialNum = BigBottle.getNum();
        BigBottle bigBottle = new BigBottle();
        assertEquals(0.20, bigBottle.getPrice());
        assertTrue(bigBottle.getWeight() >= 0 && bigBottle.getWeight() <= 1);
        assertEquals(initialNum + 1, BigBottle.getNum());
    }

    @Test
    void testGetPrice() {
        BigBottle bigBottle = new BigBottle();
        assertEquals(0.20, bigBottle.getPrice());
    }

    @Test
    void testGetNum() {
        int initialNum = BigBottle.getNum();
        new BigBottle();
        new BigBottle();
        assertEquals(initialNum + 2, BigBottle.getNum());
    }

    @Test
    void testGetResidueType() {
        BigBottle bigBottle = new BigBottle();
        assertEquals("Big Bottle", bigBottle.getResidueType());
    }
}