package org.example.model;

import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private boolean enabled;
    private List<Address> addresses;
    private List<Authority> authorities;

    public User() {
    }

    public User(int id, String name, String email, String password, boolean enabled) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

    public User(String name, String email, String password, boolean enabled) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<Authority> getAuthorities() { return authorities; }

    public void setAuthorities(List<Authority> authorities) { this.authorities = authorities; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", addresses=" + addresses +
                ", authorities=" + authorities +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() && isEnabled() == user.isEnabled() && Objects.equals(getName(), user.getName()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getAddresses(), user.getAddresses()) && Objects.equals(getAuthorities(), user.getAuthorities());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail(), getPassword(), isEnabled(), getAddresses(), getAuthorities());
    }
}
