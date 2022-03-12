package no.ntnu.edu.stud.idatt2105.williagt.calcbackend.model;

import java.util.Objects;

public class User {
    private long id;
    private String fullName;
    private String email;

    public User() {}

    public User(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, email);
    }

    @Override
    public String toString() {
        return "User: "+ fullName + ", " + email;
    }
}
