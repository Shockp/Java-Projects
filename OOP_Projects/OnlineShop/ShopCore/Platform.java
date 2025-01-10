package OnlineShop.ShopCore;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Platform {
    private Set<Order> pendingOrders;
    private Set<Order> sentOrders;
    private Set<Order> deliveredOrders;

    public Platform() {
        pendingOrders = new HashSet<>();
        sentOrders = new HashSet<>();
        deliveredOrders = new HashSet<>();
    }

    public Set<Order> getPendingOrders() {
        return Collections.unmodifiableSet(pendingOrders);
    }

    public Set<Order> getSentOrders() {
        return Collections.unmodifiableSet(sentOrders);
    }

    public Set<Order> getDeliveredOrders() {
        return Collections.unmodifiableSet(deliveredOrders);
    }

    public void registerOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }
        if (pendingOrders.contains(order) || sentOrders.contains(order) || deliveredOrders.contains(order)) {
            throw new IllegalArgumentException("The order with the ID " + order.getOrderID() + " is registered already.");
        }
        pendingOrders.add(order);
    }

    public void processOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (order.getState() != OrderState.PENDING) {
            throw new IllegalArgumentException("Only pending orders can be processed. Order ID: " + order.getOrderID());
        }
        
        if (pendingOrders.remove(order)) {
            sentOrders.add(order);
            order.setState(OrderState.SENT);
        } else {
            throw new IllegalArgumentException("The order with the ID " + order.getOrderID() + " not found in pending orders.");
        }
    }

    public void finishOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (order.getState() != OrderState.SENT) {
            throw new IllegalArgumentException("Only sent orders can be processed. Order ID: " + order.getOrderID());
        }
        
        if (sentOrders.remove(order)) {
            deliveredOrders.add(order);
            order.setState(OrderState.DELIVERED);
        } else {
            throw new IllegalArgumentException("The order with the ID " + order.getOrderID() + " not found in sent orders.");
        }
    }

    public void reportDeliveredOrders() {
        String fileName = "deliveredOrders.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Order order : deliveredOrders) {
                writer.write(order.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writting to the file: " + fileName + ", Error: " + e.getMessage());
        }
    }

    public void reportPendingOrdersByTotalPrice() {
        String fileName = "pendingOrdersByTotalPrice.txt";

        List<Order> sortedPendingOrders = new ArrayList<>(pendingOrders);
        sortedPendingOrders.sort((o1, o2) -> Double.compare(o2.getTotalPrice(), o1.getTotalPrice()));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Order order : sortedPendingOrders) {
                writer.write(order.toString());
                writer.newLine();
            }
            System.out.println("Pending orders report generated successfully: " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + fileName + ", Error: " + e.getMessage());
        }
    }

    public void saveSentOrders() {
        String fileName = "sentOrders.ser";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(fileName);
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + fileName + ", Error: " + e.getMessage());
        }
    }
}