package OnlineShop.Testing;

import OnlineShop.Exceptions.InvalidProductException;
import OnlineShop.ShopCore.Product;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    public void testValidProductCreation() throws InvalidProductException {
        Product product = new Product("Laptop", 999.99f);
        assertEquals("Laptop", product.getName());
        assertEquals(999.99f, product.getPrice());
    }

    @Test
    public void testInvalidProductNameNull() {
        assertThrows(InvalidProductException.class, () -> {
            new Product(null, 999.99f);
        });
    }

    @Test
    public void testInvalidProductNameBlank() {
        assertThrows(InvalidProductException.class, () -> {
            new Product(" ", 999.99f);
        });
    }

    @Test
    public void testInvalidProductPriceNegative() {
        assertThrows(InvalidProductException.class, () -> {
            new Product("Laptop", -999.99f);
        });
    }

    @Test
    public void testEqualsMethod() throws InvalidProductException {
        Product product1 = new Product("Laptop", 999.99f);
        Product product2 = new Product("Laptop", 999.99f);
        assertEquals(product1, product2);
    }

    @Test
    public void testHashCodeMethod() throws InvalidProductException {
        Product product1 = new Product("Laptop", 999.99f);
        Product product2 = new Product("Laptop", 999.99f);
        assertEquals(product1.hashCode(), product2.hashCode());
    }

    @Test
    public void testCompareToMethod() throws InvalidProductException {
        Product product1 = new Product("Laptop", 999.99f);
        Product product2 = new Product("Laptop", 899.99f);
        assertTrue(product1.compareTo(product2) < 0);
    }

    @Test
    public void testCompareToNullProduct() throws InvalidProductException {
        Product product1 = new Product("Laptop", 999.99f);
        assertThrows(NullPointerException.class, () -> {
            product1.compareTo(null);
        });
    }
}