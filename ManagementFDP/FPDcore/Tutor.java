package ManagementFDP.FPDcore;

import java.io.Serializable;
import java.util.Objects;

public class Tutor implements Serializable {
    private static final String EMAIL_SUFFIX = "@tutor.urjc.es";

    private String email;
    private String name;

    public Tutor(String email, String name) {
        setEmail(email);
        setName(name);
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank.");
        }
        if(!email.toLowerCase().endsWith(EMAIL_SUFFIX)) {
            throw new IllegalArgumentException("The email must follow this format: 'name@tutor.urjc.es'");
        }
        this.email = email.toLowerCase();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank.");
        }
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Tutor t = (Tutor) o;
        return Objects.equals(this.email, t.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.email);
    }

    @Override
    public String toString() {
        return "Name: " + this.name + " | Email: " + this.email;
    }
}
