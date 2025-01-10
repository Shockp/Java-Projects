package OnlineShop.Testing;

import OnlineShop.Exceptions.InvalidClientException;
import OnlineShop.ShopCore.Client;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ClientTest {

    @Test
    public void testValidClientCreation() throws InvalidClientException {
        Client client = new Client("John Doe", "john.doe@onlineshop.com");
        assertEquals("John Doe", client.getName());
        assertEquals("john.doe@onlineshop.com", client.getEmail());
    }

    @Test
    public void testInvalidClientNameNull() {
        assertThrows(InvalidClientException.class, () -> {
            new Client(null, "john.doe@onlineshop.com");
        });
    }

    @Test
    public void testInvalidClientNameBlank() {
        assertThrows(InvalidClientException.class, () -> {
            new Client(" ", "john.doe@onlineshop.com");
        });
    }

    @Test
    public void testInvalidClientEmailNull() {
        assertThrows(InvalidClientException.class, () -> {
            new Client("John Doe", null);
        });
    }

    @Test
    public void testInvalidClientEmailBlank() {
        assertThrows(InvalidClientException.class, () -> {
            new Client("John Doe", " ");
        });
    }

    @Test
    public void testInvalidClientEmailFormat() {
        assertThrows(InvalidClientException.class, () -> {
            new Client("John Doe", "john.doe@gmail.com");
        });
    }

    @Test
    public void testEqualsMethod() throws InvalidClientException {
        Client client1 = new Client("John Doe", "john.doe@onlineshop.com");
        Client client2 = new Client("Jane Doe", "john.doe@onlineshop.com");
        assertEquals(client1, client2);
    }

    @Test
    public void testHashCodeMethod() throws InvalidClientException {
        Client client1 = new Client("John Doe", "john.doe@onlineshop.com");
        Client client2 = new Client("Jane Doe", "john.doe@onlineshop.com");
        assertEquals(client1.hashCode(), client2.hashCode());
    }

    @Test
    public void testCompareToMethod() throws InvalidClientException {
        Client client1 = new Client("John Doe", "john.doe@onlineshop.com");
        Client client2 = new Client("Jane Doe", "jane.doe@onlineshop.com");
        assertTrue(client1.compareTo(client2) > 0);
    }

    @Test
    public void testCompareToNullClient() throws InvalidClientException {
        Client client1 = new Client("John Doe", "john.doe@onlineshop.com");
        assertThrows(NullPointerException.class, () -> {
            client1.compareTo(null);
        });
    }
}