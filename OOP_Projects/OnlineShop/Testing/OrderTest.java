package OnlineShop.Testing;

import java.util.List;

import OnlineShop.ShopCore.*;
import OnlineShop.Exceptions.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderTest {

    private Client client;
    private Product product1;
    private Product product2;
    private Order order;

    @BeforeEach
    public void setUp() throws InvalidClientException, InvalidProductException {
        client = new Client("John Doe", "john.doe@onlineshop.com");
        product1 = new Product("Laptop", 999.99f);
        product2 = new Product("Mouse", 49.99f);
        order = new Order(client);
    }

    @Test
    public void testValidOrderCreation() {
        assertEquals(client, order.getClient());
        assertTrue(order.getProducts().isEmpty());
        assertEquals(OrderState.PENDING, order.getState());
    }

    @Test
    public void testSetClientNull() {
        assertThrows(NullPointerException.class, () -> {
            order.setClient(null);
        });
    }

    @Test
    public void testAddProduct() {
        order.addProduct(product1);
        List<Product> products = order.getProducts();
        assertEquals(1, products.size());
        assertEquals(product1, products.get(0));
    }

    @Test
    public void testAddProductNull() {
        assertThrows(NullPointerException.class, () -> {
            order.addProduct(null);
        });
    }

    @Test
    public void testRemoveProduct() {
        order.addProduct(product1);
        order.removeProduct(product1);
        assertTrue(order.getProducts().isEmpty());
    }

    @Test
    public void testRemoveProductNull() {
        assertThrows(NullPointerException.class, () -> {
            order.removeProduct(null);
        });
    }

    @Test
    public void testClearOrder() {
        order.addProduct(product1);
        order.addProduct(product2);
        order.clearOrder();
        assertTrue(order.getProducts().isEmpty());
    }

    @Test
    public void testGetTotalPrice() {
        order.addProduct(product1);
        order.addProduct(product2);
        assertEquals(1049.98f, order.getTotalPrice());
    }

    @Test
    public void testSetStateNull() {
        assertThrows(NullPointerException.class, () -> {
            order.setState(null);
        });
    }

    @Test
    public void testSetStateInvalidTransition() {
        assertThrows(IllegalArgumentException.class, () -> {
            order.setState(OrderState.PENDING);
        });
    }

    @Test
    public void testToString() {
        order.addProduct(product1);
        String expected = "Client: " + client + ", State: " + OrderState.PENDING + "\nProducts: [" + product1 + "]\nTotal price: 999.99";
        assertEquals(expected, order.toString());
    }
}