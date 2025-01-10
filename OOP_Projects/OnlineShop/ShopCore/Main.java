package OnlineShop.ShopCore;

import OnlineShop.Exceptions.*;

public class Main {
    public static void main(String[] args) {
        Client c1 = null;
        Client c2 = null;
        Client c3 = null;
        try {
            c1 = new Client("Adrian", "adrian@onlineshop.com");
            c2 = new Client("Javi", "Javi@onlineshop.com");
            c3 = new Client("Pedro", "Pedro@onlineshop.com");
        } catch (InvalidClientException e) {
            System.err.println(e.getMessage());
        }

        Product p1 = null;
        Product p2 = null;
        Product p3 = null;

        try {
            p1 = new Product("Colacao", 5.50f);
            p2 = new Product("Pan", 0.50f);
            p3 = new Product("ColacaO", 5.10f);
        } catch (InvalidProductException e) {
            System.err.println(e.getMessage());
        }

        Order o1 = new Order(c3);
        Order o2 = new Order(c2);
        Order o3 = new Order(c1);
        
        o1.addProduct(p3);
        o1.addProduct(p3);
        o1.addProduct(p2);
        o1.removeProduct(p3);
        
        o2.addProduct(p3);
        o2.addProduct(p3);
        o2.addProduct(p2);

        o3.addProduct(p3);
        o3.addProduct(p1);
        o3.addProduct(p2);
        o3.addProduct(p2);

        Platform platform = new Platform();
        platform.registerOrder(o3);
        platform.registerOrder(o2);
        platform.registerOrder(o1);
        platform.reportPendingOrdersByTotalPrice();
        
        platform.processOrder(o3);
        platform.processOrder(o1);
        platform.reportPendingOrdersByTotalPrice();
        platform.saveSentOrders();

        platform.finishOrder(o3);
        platform.reportDeliveredOrders();
    }
}