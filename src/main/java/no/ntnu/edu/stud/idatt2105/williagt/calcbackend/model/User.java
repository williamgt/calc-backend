package no.ntnu.edu.stud.idatt2105.williagt.calcbackend.model;

import java.util.Objects;

public class User {
    private long id;
    private String fullName;
    private String email;
    private String address;
    private String username;
    private String password;
    private String phone;

    public User() {}

    public User(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    /*public User(String username, String password){
        this.username = username;
        this.password = password;
    }*/

    public User(long id, String fullName, String email, String address, String username, String password, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.username = username;
        this.password = password;
        this.phone = phone;
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

    public String getAddress() {
        return address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
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

    public void setAddress(String address) {
        this.address = address;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email) && id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, email, address, password, phone, username);
    }

    @Override
    public String toString() {
        return "User: "+ username + ", mail " + email;
    }
}
