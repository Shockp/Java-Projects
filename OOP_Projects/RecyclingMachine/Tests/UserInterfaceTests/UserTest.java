package RecyclingMachine.Tests.UserInterfaceTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import RecyclingMachine.UserInterface.User;

import RecyclingMachine.Residues.Containers.Can;
import RecyclingMachine.Residues.Residue;

class UserTest {

    @Test
    void testConstructor() {
        User user = new User("John Doe", 123);
        assertEquals("John Doe", user.getName());
        assertEquals(123, user.getNumber());
        assertEquals(0, user.getBalance());
        assertTrue(user.getResidues().isEmpty());
    }

    @Test
    void testSetNameValid() {
        User user = new User("John Doe", 123);
        user.setName("Jane Doe");
        assertEquals("Jane Doe", user.getName());
    }

    @Test
    void testSetNameInvalid() {
        User user = new User("John Doe", 123);
        assertThrows(IllegalArgumentException.class, () -> {
            user.setName(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            user.setName("");
        });
    }

    @Test
    void testSetNumberValid() {
        User user = new User("John Doe", 123);
        user.setNumber(456);
        assertEquals(456, user.getNumber());
    }

    @Test
    void testSetNumberInvalid() {
        User user = new User("John Doe", 123);
        assertThrows(IllegalArgumentException.class, () -> {
            user.setNumber(-1);
        });
    }

    @Test
    void testSetBalanceValid() {
        User user = new User("John Doe", 123);
        user.setBalance(100.0);
        assertEquals(100.0, user.getBalance());
    }

    @Test
    void testSetBalanceInvalid() {
        User user = new User("John Doe", 123);
        assertThrows(IllegalArgumentException.class, () -> {
            user.setBalance(-10.0);
        });
    }

    @Test
    void testAddResidue() {
        User user = new User("John Doe", 123);
        Residue can = new Can();
        user.addResidue(can);
        assertEquals(1, user.getResidues().size());
        assertEquals(can.getPrice(), user.getBalance());
    }

    @Test
    void testRemoveResidue() {
        User user = new User("John Doe", 123);
        Residue can = new Can();
        user.addResidue(can);
        user.removeResidue(can);
        assertTrue(user.getResidues().isEmpty());
    }

    @Test
    void testClearResidues() {
        User user = new User("John Doe", 123);
        Residue can1 = new Can();
        Residue can2 = new Can();
        user.addResidue(can1);
        user.addResidue(can2);
        user.clearResidues();
        assertTrue(user.getResidues().isEmpty());
    }

    @Test
    void testPrintTotalRecycled() {
        User user = new User("John Doe", 123);
        Residue can1 = new Can();
        Residue can2 = new Can();
        user.addResidue(can1);
        user.addResidue(can2);
        user.printTotalRecycled();
    }

    @Test
    void testPrintCanTagsRecycled() {
        User user = new User("John Doe", 123);
        Can can1 = new Can();
        Can can2 = new Can();
        user.addResidue(can1);
        user.addResidue(can2);
        user.printCanTagsRecycled();
    }

    @Test
    void testEquals() {
        User user1 = new User("John Doe", 123);
        User user2 = new User("Jane Doe", 123);
        User user3 = new User("John Smith", 456);
        assertTrue(user1.equals(user2));
        assertFalse(user1.equals(user3));
        assertFalse(user1.equals(null));
        assertFalse(user1.equals("Not a User"));
    }
}