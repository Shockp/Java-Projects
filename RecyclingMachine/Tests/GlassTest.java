package RecyclingMachine.Tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import RecyclingMachine.Residues.Glass;

class GlassTest {

    @Test
    void testConstructor() {
        int initialNum = Glass.getNum();
        Glass glass = new Glass();
        assertEquals(0.30, glass.getPrice());
        assertTrue(glass.getWeight() >= 0 && glass.getWeight() <= 1);
        assertEquals(initialNum + 1, Glass.getNum());
    }

    @Test
    void testGetPrice() {
        Glass glass = new Glass();
        assertEquals(0.30, glass.getPrice());
    }

    @Test
    void testGetNum() {
        int initialNum = Glass.getNum();
        new Glass();
        new Glass();
        assertEquals(initialNum + 2, Glass.getNum());
    }

    @Test
    void testGetResidueType() {
        Glass glass = new Glass();
        assertEquals("Glass", glass.getResidueType());
    }
}