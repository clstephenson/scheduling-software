package com.clstephenson;

public class Address {
    private int id;
    private String addressLine1;
    private String addressLine2;
    private City city;
    private String zipCode;
    private String phoneNumber;

    public Address() {}

    public Address(String addressLine1, String addressLine2, City city, String zipCode, String phoneNumber) {

        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    public Address(int id, String addressLine1, String addressLine2, City city, String zipCode, String phoneNumber) {

        this.id = id;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        return String.format("[%d, %s, %s, %s, %s, %s]", this.id, this.addressLine1, this.addressLine2, this.city, this.zipCode, this.phoneNumber);
    }
}
