package RecyclingMachine.Tests.ResiduesTests.ContainersTests.BottlesTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import RecyclingMachine.Residues.Containers.Bottles.SmallBottle;

class SmallBottleTest {

    @Test
    void testConstructor() {
        int initialNum = SmallBottle.getNum();
        SmallBottle smallBottle = new SmallBottle();
        assertEquals(0.15, smallBottle.getPrice());
        assertTrue(smallBottle.getWeight() >= 0 && smallBottle.getWeight() <= 1);
        assertEquals(initialNum + 1, SmallBottle.getNum());
    }

    @Test
    void testGetPrice() {
        SmallBottle smallBottle = new SmallBottle();
        assertEquals(0.15, smallBottle.getPrice());
    }

    @Test
    void testGetNum() {
        int initialNum = SmallBottle.getNum();
        new SmallBottle();
        new SmallBottle();
        assertEquals(initialNum + 2, SmallBottle.getNum());
    }

    @Test
    void testGetResidueType() {
        SmallBottle smallBottle = new SmallBottle();
        assertEquals("Small Bottle", smallBottle.getResidueType());
    }
}