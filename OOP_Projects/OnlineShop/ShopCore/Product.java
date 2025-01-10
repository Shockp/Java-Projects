package OnlineShop.ShopCore;

import java.io.Serializable;
import java.util.Objects;

import OnlineShop.Exceptions.InvalidProductException;

public class Product implements Comparable<Product>, Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private float price;

    public Product(String name, float price) throws InvalidProductException {
        setName(name);
        setPrice(price);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) throws InvalidProductException {
        if (name == null || name.isBlank()) {
            throw new InvalidProductException("The name cannot be null or blank.");
        }
        this.name = name.trim();
    }

    public float getPrice() {
        return price;
    }
    public void setPrice(float price) throws InvalidProductException {
        if (price < 0) {
            throw new InvalidProductException("The price cannot be negative.");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + ", Price: " + this.getPrice();
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != getClass()) {
            return false;
        }
        Product product = (Product) object;
        return this.name.equalsIgnoreCase(product.name) && Float.compare(product.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), price);
    }

    @Override
    public int compareTo(Product product) {
        if (product == null) {
            throw new NullPointerException("Cannot compare to null Product.");
        }

        int nameComparison = this.name.compareToIgnoreCase(product.name);
        if (nameComparison != 0) {
            return nameComparison;
        }

        return Float.compare(product.price, price);
    }
}