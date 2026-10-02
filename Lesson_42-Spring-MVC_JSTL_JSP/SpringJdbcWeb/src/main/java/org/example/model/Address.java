package org.example.model;

public class Address {
    private int id;

    private String street;
    private String city;
    private String postalCode;
    private Integer userId;

    public Address(String street, String city, String postalCode, Integer userId) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.userId = userId;
    }

    public Address(int id, String street, String city, String postalCode, Integer userId) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.userId = userId;
    }

    public Address() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", userId=" + userId +
                '}';
    }
}
