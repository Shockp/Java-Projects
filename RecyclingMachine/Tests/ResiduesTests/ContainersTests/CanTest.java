package RecyclingMachine.Tests.ResiduesTests.ContainersTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import RecyclingMachine.Residues.Containers.Can;

class CanTest {

    @Test
    void testConstructor() {
        int initialNum = Can.getNum();
        Can can = new Can();
        assertEquals(0.10, can.getPrice());
        assertTrue(can.getWeight() >= 0 && can.getWeight() <= 1);
        assertNotNull(can.getTag());
        assertEquals(initialNum + 1, Can.getNum());
    }

    @Test
    void testGetPrice() {
        Can can = new Can();
        assertEquals(0.10, can.getPrice());
    }

    @Test
    void testGetNum() {
        int initialNum = Can.getNum();
        new Can();
        new Can();
        assertEquals(initialNum + 2, Can.getNum());
    }

    @Test
    void testGetTag() {
        Can can = new Can();
        assertNotNull(can.getTag());
        assertTrue(can.getTag().matches("\\d{4}-\\d{2}-\\d{2}"));
    }

    @Test
    void testGetResidueType() {
        Can can = new Can();
        assertEquals("Can", can.getResidueType());
    }
}