package OnlineShop.ShopCore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int orderCounter = 0;
    
    private final int ORDER_ID;
    private Client client;
    private List<Product> products;
    private OrderState state;

    public Order(Client client) {
        this.ORDER_ID = ++orderCounter;
        setClient(client);
        products = new ArrayList<>();
        state = OrderState.PENDING;
    }

    public int getOrderID() {
        return ORDER_ID;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        if (client == null) {
            throw new NullPointerException("Client cannot be null.");
        }
        this.client = client;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public OrderState getState() {
        return state;
    }
    public void setState(OrderState state) {
        if (state == null) {
            throw new NullPointerException("The state cannot be null.");
        }
        if (this.state == state) {
            throw new IllegalArgumentException("Invalid state transition.");
        }
        this.state = state;
    }

    @Override
    public String toString() {
        return "Client: " + this.getClient() + ", State: " + this.getState() + "\nProducts: " + products.toString() + "\nTotal price: " + getTotalPrice();
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != getClass()) {
            return false;
        }
        Order order = (Order) object;
        return Objects.equals(order.ORDER_ID, ORDER_ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ORDER_ID);
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new NullPointerException("Product cannot be null.");
        }
        products.add(product);
    }

    public void removeProduct(Product product) {
        if (product == null) {
            throw new NullPointerException("Product cannot be null.");
        }
        products.remove(product);
    }

    public void clearOrder() {
        products.clear();
    }

    public float getTotalPrice() {
        float totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
}