package OnlineShop.ShopCore;

import java.io.Serializable;
import java.util.Objects;

import OnlineShop.Exceptions.InvalidClientException;

public class Client implements Comparable<Client>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final String EMAIL_SUFFIX = "@onlineshop.com";

    private String name;
    private String email;

    public Client(String name, String email) throws InvalidClientException {
        setName(name);
        setEmail(email);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) throws InvalidClientException {
        if (name == null || name.isBlank()) {
            throw new InvalidClientException("The name cannot be null or blank.");
        }
        this.name = name.trim();
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) throws InvalidClientException {
        if (email == null || email.isBlank()) {
            throw new InvalidClientException("The email cannot be null or blank.");
        }

        email = email.toLowerCase().trim();
        if (!email.endsWith(EMAIL_SUFFIX) || email.length() <= EMAIL_SUFFIX.length()) {
            throw new InvalidClientException("The email must follow the format: 'client@onlineshop.com'.");
        }
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + ", Email: " + this.getEmail();
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Client client = (Client) object;
        return Objects.equals(email, client.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public int compareTo(Client client) {
        if (client == null) {
            throw new NullPointerException("Cannot compare to null Client.");
        }
        return this.email.compareTo(client.email);
    }
}