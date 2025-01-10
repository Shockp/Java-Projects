package OnlineShop.Testing;

import OnlineShop.Exceptions.InvalidClientException;
import OnlineShop.Exceptions.InvalidProductException;
import OnlineShop.ShopCore.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlatformTest {
    private Platform platform;
    private Client client;
    private Product product1;
    private Product product2;
    private Order order1;
    private Order order2;

    @BeforeEach
    public void setUp() throws InvalidClientException, InvalidProductException {
        platform = new Platform();
        client = new Client("John Doe", "john.doe@onlineshop.com");
        product1 = new Product("Laptop", 999.99f);
        product2 = new Product("Mouse", 49.99f);
        order1 = new Order(client);
        order2 = new Order(client);
        order1.addProduct(product1);
        order2.addProduct(product2);
    }

    @Test
    public void testRegisterOrder() {
        platform.registerOrder(order1);
        Set<Order> pendingOrders = platform.getPendingOrders();
        assertTrue(pendingOrders.contains(order1));
    }

    @Test
    public void testRegisterOrderNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            platform.registerOrder(null);
        });
    }

    @Test
    public void testRegisterDuplicateOrder() {
        platform.registerOrder(order1);
        assertThrows(IllegalArgumentException.class, () -> {
            platform.registerOrder(order1);
        });
    }

    @Test
    public void testProcessOrder() {
        platform.registerOrder(order1);
        platform.processOrder(order1);
        Set<Order> sentOrders = platform.getSentOrders();
        assertTrue(sentOrders.contains(order1));
        assertEquals(OrderState.SENT, order1.getState());
    }

    @Test
    public void testProcessOrderNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            platform.processOrder(null);
        });
    }

    @Test
    public void testProcessOrderNotPending() {
        platform.registerOrder(order1);
        platform.processOrder(order1);
        assertThrows(IllegalArgumentException.class, () -> {
            platform.processOrder(order1);
        });
    }

    @Test
    public void testFinishOrder() {
        platform.registerOrder(order1);
        platform.processOrder(order1);
        platform.finishOrder(order1);
        Set<Order> deliveredOrders = platform.getDeliveredOrders();
        assertTrue(deliveredOrders.contains(order1));
        assertEquals(OrderState.DELIVERED, order1.getState());
    }

    @Test
    public void testFinishOrderNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            platform.finishOrder(null);
        });
    }

    @Test
    public void testFinishOrderNotSent() {
        platform.registerOrder(order1);
        assertThrows(IllegalArgumentException.class, () -> {
            platform.finishOrder(order1);
        });
    }

    @Test
    public void testReportDeliveredOrders() throws IOException {
        platform.registerOrder(order1);
        platform.processOrder(order1);
        platform.finishOrder(order1);
        platform.reportDeliveredOrders();
        File file = new File("deliveredOrders.txt");
        assertTrue(file.exists());
        List<String> lines = Files.readAllLines(file.toPath());
        assertTrue(lines.contains(order1.toString()));
        file.delete();
    }

    @Test
    public void testReportPendingOrdersByTotalPrice() throws IOException {
        platform.registerOrder(order1);
        platform.registerOrder(order2);
        platform.reportPendingOrdersByTotalPrice();
        File file = new File("pendingOrdersByTotalPrice.txt");
        assertTrue(file.exists());
        List<String> lines = Files.readAllLines(file.toPath());
        assertTrue(lines.contains(order1.toString()));
        assertTrue(lines.contains(order2.toString()));
        file.delete();
    }

    @Test
    public void testSaveSentOrders() throws IOException {
        platform.registerOrder(order1);
        platform.processOrder(order1);
        platform.saveSentOrders();
        File file = new File("sentOrders.ser");
        assertTrue(file.exists());
        file.delete();
    }
}